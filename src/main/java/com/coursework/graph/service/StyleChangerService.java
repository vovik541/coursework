package com.coursework.graph.service;

import com.coursework.graph.entity.nodeextension.GraphEdge;
import com.coursework.graph.entity.nodeextension.GraphNode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StyleChangerService {

    @Autowired
    private SearchFxElementsService searchService;

    public void changeAllToDefault(AnchorPane rootPane) {
        List<GraphNode> allGraphNodes = searchService.findAllGraphNodes(rootPane);
        allGraphNodes.forEach(this::stylishDefaultNode);
        List<GraphEdge> allGraphEdges = searchService.findAllGraphEdges(rootPane);
        allGraphEdges.forEach(this::stylishDefaultEdge);
    }

    public void stylishDefaultNode(GraphNode graphNode) {
        graphNode.setFill(Color.GAINSBORO);
        graphNode.setStrokeWidth(3);
        graphNode.setStroke(Color.BLACK);
    }

    public void stylishDefaultEdge(GraphEdge graphEdge) {
        graphEdge.setStrokeWidth(2);
        graphEdge.setStrokeLineCap(StrokeLineCap.BUTT);
        graphEdge.getStrokeDashArray().setAll(1.0, 4.0);
        graphEdge.setFill(Color.BLACK);
        graphEdge.setStroke(Color.BLACK);
        graphEdge.toFront();
    }

    public void connectEdge(GraphEdge edge, GraphNode begin, GraphNode end) {
        //todo
    }
}
