package com.coursework.graph.algorithm;

import com.coursework.graph.entity.nodeextension.Graph;
import com.coursework.graph.entity.nodeextension.GraphEdge;
import com.coursework.graph.entity.nodeextension.GraphNode;
import com.coursework.graph.handler.display.ApproxVertexAlgorithmDisplayThread;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ApproxVertexCoverAlgorithm implements Algorithm {
    @Override
    public void findCoverage(Graph graph) {
        List<GraphNode> nodesByEdge;
        List<GraphEdge> allEdgesByNodes;

        LinkedHashMap<GraphEdge, List<GraphNode>> displayNodeStack =
                new LinkedHashMap<>();
        HashMap<GraphEdge, List<GraphEdge>> displayEdgesStack =
                new LinkedHashMap<>();

        while (!graph.getEdges().isEmpty()) {
            GraphEdge edge = getAnyEdge(graph);
            allEdgesByNodes = graph.getAllEdgesByNodeId(edge.getBeginNodeId());
            allEdgesByNodes.addAll(graph.getAllEdgesByNodeId(edge.getEndNodeId()));

            nodesByEdge = graph.getNodesByEdge(edge);

            graph.deleteEdgesByNodeId(edge.getBeginNodeId());
            graph.deleteNodeByGraphNodeId(edge.getBeginNodeId());
            graph.deleteEdgesByNodeId(edge.getEndNodeId());
            graph.deleteNodeByGraphNodeId(edge.getEndNodeId());

            displayEdgesStack.put(edge, allEdgesByNodes);
            displayNodeStack.put(edge, nodesByEdge);
        }

        new ApproxVertexAlgorithmDisplayThread(displayNodeStack, displayEdgesStack).start();
    }

    private GraphEdge getAnyEdge(Graph graph) {
        return graph.getEdges().get(0);
    }
}
