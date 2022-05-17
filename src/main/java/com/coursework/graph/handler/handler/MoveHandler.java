package com.coursework.graph.handler.handler;

import com.coursework.graph.entity.GraphNode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class MoveHandler extends AbstractEventHandler {
    @Override
    public void changeHandler(AnchorPane rootPane, GraphNode node) {
        node.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            Circle c = (Circle) (t.getSource());
        });
        node.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;

            Circle c = (Circle) (t.getSource());

            c.setCenterX(c.getCenterX() + offsetX);
            c.setCenterY(c.getCenterY() + offsetY);

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
        });
    }
}
