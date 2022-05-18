package com.coursework.graph.service;

import com.coursework.graph.entity.jsondto.EdgeDto;
import com.coursework.graph.entity.jsondto.GraphDto;
import com.coursework.graph.entity.jsondto.NodeDto;
import com.coursework.graph.entity.nodeextension.Graph;
import com.coursework.graph.entity.nodeextension.GraphEdge;
import com.coursework.graph.entity.nodeextension.GraphNode;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MapperService {

    public GraphDto graphToDto(Graph graph) {
        return new GraphDto(
                nodesToDto(graph.getNodes()),
                edgesToDto(graph.getEdges())
        );
    }

    private List<NodeDto> nodesToDto(List<GraphNode> nodes) {
        List<NodeDto> dtoNodes = new LinkedList<>();

        for (GraphNode node : nodes) {
            dtoNodes.add(
                    NodeDto.builder()
                            .x(node.getCenterX())
                            .y(node.getCenterY())
                            .nodeId(node.getNodeId())
                            .build()
            );
        }

        return dtoNodes;
    }

    private List<EdgeDto> edgesToDto(List<GraphEdge> edges) {
        List<EdgeDto> dtoEdges = new LinkedList<>();

        for (GraphEdge edge : edges) {
            dtoEdges.add(new EdgeDto(edge.getBeginNodeId(), edge.getEndNodeId()));
        }

        return dtoEdges;
    }
}