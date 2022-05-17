package com.coursework.graph.facory;

import com.coursework.graph.entity.GraphNode;
import com.coursework.graph.handler.handler.AbstractEventHandler;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class GraphNodeFactory {

    public void createGraphNode(AnchorPane rootPane, AbstractEventHandler handler) {
        int numberOfNodes = generateNumberOfRaw(rootPane);

        GraphNode graphNode = new GraphNode();
        graphNode.setCenterX(80);
        graphNode.setCenterY(80);
        graphNode.setRadius(20);
        graphNode.setFill(Color.GAINSBORO);
        graphNode.setStrokeWidth(3);
        graphNode.setStroke(Color.BLACK);
        graphNode.setId("Node_" + numberOfNodes);
        graphNode.setNodeId(numberOfNodes);

        handler.changeHandler(rootPane, graphNode);

        Text text = new Text(String.valueOf(numberOfNodes));
        text.xProperty().bind(graphNode.centerXProperty().add(20));
        text.yProperty().bind(graphNode.centerYProperty().add(-16));
        text.setId("Text_" + numberOfNodes);
        text.setFont(new Font(26));
        text.toFront();
        rootPane.getChildren().addAll(graphNode, text);
    }

    private int generateNumberOfRaw(AnchorPane rootPane){
        List<GraphNode> nodes = rootPane.getChildren().stream()
                .filter(x -> x.getId() != null && x.getId().contains("Node_"))
                .map(x -> (GraphNode) x)
                .collect(Collectors.toList());

        nodes.sort(Comparator.comparingInt(GraphNode::getNodeId));

        for (int i = 1; i <= nodes.size(); i++){
            if (i != nodes.get(i-1).getNodeId()){
                return i;
            }
        }
        return nodes.size()+1;
    }

}
