package com.coursework.graph.algorithm;

import com.coursework.graph.entity.GraphEdge;
import com.coursework.graph.entity.GraphNode;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public interface Algorithm {
    List<GraphNode> usedNodes = new LinkedList<>();
    List<GraphEdge> usedEdges = new LinkedList<>();
    void findCoverage(List<GraphNode> nodes, List<GraphEdge> edges);
}
