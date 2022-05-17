package com.coursework.graph.algorithm;

import com.coursework.graph.entity.nodeextension.Graph;
import com.coursework.graph.entity.nodeextension.GraphEdge;
import com.coursework.graph.entity.nodeextension.GraphNode;

import com.coursework.graph.handler.task.TaskThread;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GreedAlgorithm implements Algorithm {

    @Override
    public void findCoverage(Graph graph) {
        GraphNode node;
        List<GraphEdge> edges;

        LinkedHashMap<GraphNode, List<GraphEdge>> displayStack = new LinkedHashMap<>();

        while (!graph.getEdges().isEmpty()) {
            node = findNodeWithMostEdges(graph);
            edges = graph.getAllEdgesByNodeId(node.getNodeId());
            graph.deleteEdgesFromGraph(edges);
            graph.deleteNodeFromGraph(node);

            displayStack.put(node, edges);
        }
        new TaskThread(displayStack).start();
    }

    private GraphNode findNodeWithMostEdges(Graph graph) {
        int maxNumberOfEdges = 0;
        GraphNode maxNode = null;

        List<GraphEdge> edges;
        for (GraphNode node : graph.getNodes()) {
            edges = graph.getAllEdgesByNodeId(node.getNodeId());
            if (edges.size() > maxNumberOfEdges) {
                maxNumberOfEdges = edges.size();
                maxNode = node;
            }
        }

        return maxNode;
    }


}
