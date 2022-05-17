package com.coursework.graph.handler.handler;

import com.coursework.graph.entity.GraphEdge;
import com.coursework.graph.entity.GraphNode;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class DeleteHandler extends AbstractEventHandler {
    @Override
    public void changeHandler(AnchorPane rootPane, GraphNode node) {
        node.setOnMousePressed((t) -> {
            String textId = "Text_" + node.getNodeId();
            rootPane.getChildren().remove(search.findNodeTextById(rootPane, textId).get());

            List<GraphEdge> edges = search.findAllGraphEdges(rootPane);

            for (GraphEdge edge : edges){
                if (edge.getBeginNodeId() == node.getNodeId() || edge.getEndNodeId() == node.getNodeId()){
                    rootPane.getChildren().remove(edge);
                }
            }

            rootPane.getChildren().remove(node);
        });
    }
}
