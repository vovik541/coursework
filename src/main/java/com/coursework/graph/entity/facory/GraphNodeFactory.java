package com.coursework.graph.entity.facory;

import com.coursework.graph.entity.nodeextension.GraphNode;
import com.coursework.graph.handler.handlermanager.AbstractEventHandler;
import com.coursework.graph.service.StyleChangerService;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.coursework.graph.util.Utility.transformNodeId;
import static com.coursework.graph.util.Utility.transformTextId;


@Component
public class GraphNodeFactory {
    @Autowired
    private StyleChangerService styleService;

    public void createGraphNode(AnchorPane rootPane, AbstractEventHandler handler) {
        int numberOfNodes = generateNumberOfRaw(rootPane);

        GraphNode graphNode = new GraphNode();
        styleService.stylishDefaultNode(graphNode);
        this.setNodeAttributes(graphNode, numberOfNodes, 600, 100);

        Text text = new Text(String.valueOf(numberOfNodes));
        this.setTextToNode(graphNode, text, numberOfNodes);

        handler.changeHandler(rootPane, graphNode);
        rootPane.getChildren().addAll(graphNode, text);
    }

    public void createGraphNode(AnchorPane rootPane, AbstractEventHandler handler, double x, double y, int id) {
        GraphNode graphNode = new GraphNode();
        styleService.stylishDefaultNode(graphNode);
        this.setNodeAttributes(graphNode, id, x, y);

        Text text = new Text(String.valueOf(id));
        this.setTextToNode(graphNode, text, id);

        handler.changeHandler(rootPane, graphNode);
        rootPane.getChildren().addAll(graphNode, text);
    }

    private void setNodeAttributes(GraphNode graphNode, int nodeId, double x, double y) {
        graphNode.setCenterX(x);
        graphNode.setCenterY(y);
        graphNode.setRadius(20);
        graphNode.setId(transformNodeId(nodeId));
        graphNode.setNodeId(nodeId);
    }

    private void setTextToNode(GraphNode graphNode, Text text, int id) {
        text.xProperty().bind(graphNode.centerXProperty().add(20));
        text.yProperty().bind(graphNode.centerYProperty().add(-16));
        text.setId(transformTextId(id));
        text.setFont(new Font(26));
        text.toFront();
    }

    private int generateNumberOfRaw(AnchorPane rootPane) {
        List<GraphNode> nodes = rootPane.getChildren().stream()
                .filter(x -> x.getId() != null && x.getId().contains("Node_"))
                .map(x -> (GraphNode) x)
                .collect(Collectors.toList());

        nodes.sort(Comparator.comparingInt(GraphNode::getNodeId));

        for (int i = 1; i <= nodes.size(); i++) {
            if (i != nodes.get(i - 1).getNodeId()) {
                return i;
            }
        }
        return nodes.size() + 1;
    }

}
