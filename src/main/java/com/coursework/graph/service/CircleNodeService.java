package com.coursework.graph.service;

import com.coursework.graph.entity.GraphNode;
import com.coursework.graph.handlers.AbstractEventHandler;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import org.springframework.stereotype.Service;


@Service
public class CircleNodeService {
    private int NumberOfNodes = 1;

    public void createCircle(AnchorPane rootPane, AbstractEventHandler handler) {
        GraphNode graphNode = new GraphNode();
        graphNode.setCenterX(80);
        graphNode.setCenterY(80);
        graphNode.setRadius(20);
        graphNode.setFill(Color.GAINSBORO);
        graphNode.setStrokeWidth(3);
        graphNode.setStroke(Color.BLACK);
        graphNode.setId("Node_" + NumberOfNodes);
        graphNode.setNodeId(NumberOfNodes);

        handler.changeHandler(rootPane, graphNode);

        Text text = new Text(String.valueOf(NumberOfNodes));
        text.xProperty().bind(graphNode.centerXProperty().add(20));
        text.yProperty().bind(graphNode.centerYProperty().add(-16));
        text.setId("Text_" + NumberOfNodes);
        text.setFont(new Font(26));
        text.toFront();
        NumberOfNodes++;
        rootPane.getChildren().addAll(graphNode, text);
    }

}
