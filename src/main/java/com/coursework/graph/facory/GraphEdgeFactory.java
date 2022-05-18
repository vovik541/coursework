package com.coursework.graph.facory;

import com.coursework.graph.entity.nodeextension.GraphEdge;
import com.coursework.graph.entity.nodeextension.GraphNode;
import com.coursework.graph.service.StyleChangerService;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraphEdgeFactory {
    @Autowired
    private StyleChangerService styleService;

    public GraphEdge createGraphEdge(AnchorPane rootPane, GraphNode node1, GraphNode node2) {
        GraphEdge graphEdge = new GraphEdge();
        graphEdge.startXProperty().bind(node1.centerXProperty());
        graphEdge.startYProperty().bind(node1.centerYProperty());
        graphEdge.endXProperty().bind(node2.centerXProperty());
        graphEdge.endYProperty().bind(node2.centerYProperty());
        graphEdge.setBeginNodeId(node1.getNodeId());
        graphEdge.setEndNodeId(node2.getNodeId());
        graphEdge.setId("Edge_" + node1.getNodeId() + "_" + node2.getNodeId());
        styleService.stylishDefaultEdge(graphEdge);

        rootPane.getChildren().add(graphEdge);

        return graphEdge;
    }

}
