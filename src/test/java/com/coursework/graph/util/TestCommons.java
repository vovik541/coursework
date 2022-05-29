package com.coursework.graph.util;

import com.coursework.graph.entity.jsondto.EdgeDto;
import com.coursework.graph.entity.jsondto.GraphDto;
import com.coursework.graph.entity.jsondto.NodeDto;
import com.coursework.graph.entity.nodeextension.GraphEdge;
import com.coursework.graph.entity.nodeextension.GraphNode;

import java.util.LinkedList;
import java.util.List;

public class TestCommons {
    public static GraphDto creteGraphDto() {
        NodeDto node1 = new NodeDto(1., 1., 1);
        NodeDto node2 = new NodeDto(2., 2., 2);

        EdgeDto edge = new EdgeDto(1, 2);

        List<NodeDto> nodesDto = new LinkedList<>(){{
            add(node1);
            add(node2);
        }};
        List<EdgeDto> edgesDto = new LinkedList<>(){{
            add(edge);
        }};

        return new GraphDto(nodesDto,edgesDto);
    }

    public static List<GraphNode> creteGraphNodes() {
        GraphNode node1 = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);

        return new LinkedList<>(){{
            add(node1);
            add(node2);
        }};
    }
    public static List<GraphEdge> creteGraphEdges() {
        GraphEdge edge = new GraphEdge(1, 2);

        return new LinkedList<>(){{
            add(edge);
        }};
    }

}
