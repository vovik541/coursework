package com.coursework.graph.entity.nodeextension;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class Graph {

    private List<GraphNode> nodes;
    private List<GraphEdge> edges;

    public Graph(List<GraphNode> nodes, List<GraphEdge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public GraphNode getNodeByGraphNodeId(int nodeId) {
        for (GraphNode node : nodes) {
            if (nodeId == node.getNodeId()) {
                return node;
            }
        }
        return new GraphNode();
    }

    public void deleteNodeByGraphNodeId(int nodeId) {
        nodes.removeIf(node -> nodeId == node.getNodeId());
    }

    public List<GraphEdge> getAllEdgesByNodeId(int nodeId) {
        List<GraphEdge> edgesByNode = new LinkedList<>();

        for (GraphEdge edge : edges) {
            if (edge.isConnectedToNodeId(nodeId)) {
                edgesByNode.add(edge);
            }
        }
        return edgesByNode;
    }

    public void deleteEdgesByNodeId(int nodeId) {
        edges.removeIf(edge -> edge.isConnectedToNodeId(nodeId));
    }

    public List<GraphNode> getNodesByEdge(GraphEdge edge) {
        List<GraphNode> connectedTo = new LinkedList<>();
        for (GraphNode node : nodes) {
            if (edge.isConnectedToNodeId(node.getNodeId())) {
                connectedTo.add(node);
            }
        }

        return connectedTo;
    }

    public void deleteNodeFromGraph(GraphNode node) {
        this.nodes.remove(node);
    }

    public void deleteEdgesFromGraph(List<GraphEdge> edges) {
        this.edges.removeAll(edges);
    }

}
