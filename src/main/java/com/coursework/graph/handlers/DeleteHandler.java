package com.coursework.graph.handlers;

import com.coursework.graph.entity.GraphEdge;
import com.coursework.graph.entity.GraphNode;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class DeleteHandler extends AbstractEventHandler {
    @Override
    public void changeHandler(AnchorPane rootPane, GraphNode node) {
        node.setOnMousePressed((t) -> {
            String textId = "Text_" + node.getNodeId();
            rootPane.getChildren().remove(findNodeTextById(rootPane, textId).get());

            String beginningId = "Edge_";
            List<GraphEdge> edges = findAllEdgesByBeginningId(rootPane, beginningId);

            for (GraphEdge edge : edges){
                if (edge.getBeginNodeId() == node.getNodeId() || edge.getEndNodeId() == node.getNodeId()){
                    rootPane.getChildren().remove(edge);
                }
            }

            rootPane.getChildren().remove(node);
        });
    }
}
