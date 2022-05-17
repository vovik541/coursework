package com.coursework.graph.algorithm;

import com.coursework.graph.entity.nodeextension.Graph;
import com.coursework.graph.entity.nodeextension.GraphEdge;
import com.coursework.graph.entity.nodeextension.GraphNode;

import java.util.LinkedList;
import java.util.List;

public interface Algorithm {
    List<GraphNode> usedNodes = new LinkedList<>();
    List<GraphEdge> usedEdges = new LinkedList<>();

    void findCoverage(Graph graph);
}
