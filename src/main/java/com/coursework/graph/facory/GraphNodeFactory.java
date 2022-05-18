package com.coursework.graph.facory;

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


@Component
public class GraphNodeFactory {

    @Autowired
    private StyleChangerService styleService;
    public void createGraphNode(AnchorPane rootPane, AbstractEventHandler handler) {
        int numberOfNodes = generateNumberOfRaw(rootPane);

        GraphNode graphNode = new GraphNode();
        styleService.stylishDefaultNode(graphNode);

        graphNode.setCenterX(600);
        graphNode.setCenterY(100);
        graphNode.setRadius(20);
        graphNode.setId("Node_" + numberOfNodes);
        graphNode.setNodeId(numberOfNodes);

        Text text = new Text(String.valueOf(numberOfNodes));
        text.xProperty().bind(graphNode.centerXProperty().add(20));
        text.yProperty().bind(graphNode.centerYProperty().add(-16));
        text.setId("Text_" + numberOfNodes);
        text.setFont(new Font(26));
        text.toFront();

        handler.changeHandler(rootPane, graphNode);

        rootPane.getChildren().addAll(graphNode, text);
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
