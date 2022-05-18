package com.coursework.graph.service;

import com.coursework.graph.entity.nodeextension.GraphEdge;
import com.coursework.graph.entity.nodeextension.GraphNode;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchFxElementsService {
    public List<GraphNode> findAllGraphNodes(AnchorPane rootPane) {
        return rootPane.getChildren().stream()
                .filter(x -> x.getId() != null && x.getId().contains("Node_"))
                .map(x -> (GraphNode) x)
                .collect(Collectors.toList());
    }

    public Optional<GraphNode> findGraphNodeById(AnchorPane rootPane, String id) {
        return rootPane.getChildren().stream()
                .filter(x -> x.getId() != null && x.getId().equals(id))
                .map(x -> (GraphNode) x)
                .findFirst();
    }

    public Optional<Text> findNodeTextById(AnchorPane rootPane, String id) {
        return rootPane.getChildren().stream()
                .filter(x -> x.getId() != null && x.getId().equals(id))
                .map(x -> (Text) x)
                .findFirst();
    }

    public List<Text> findAllTextNodes(AnchorPane rootPane) {
        return rootPane.getChildren().stream()
                .filter(x -> x.getId() != null && x.getId().contains("Text_"))
                .map(x -> (Text) x)
                .collect(Collectors.toList());
    }

    public Optional<GraphEdge> findGraphEdgeById(AnchorPane rootPane, String id) {
        return rootPane.getChildren().stream()
                .filter(x -> x.getId() != null && x.getId().equals(id))
                .map(x -> (GraphEdge) x)
                .findFirst();
    }

    public List<GraphEdge> findAllGraphEdges(AnchorPane rootPane) {
        return rootPane.getChildren().stream()
                .filter(x -> x.getId() != null && x.getId().contains("Edge_"))
                .map(x -> (GraphEdge) x)
                .collect(Collectors.toList());
    }

    public Optional<Node> findById(AnchorPane rootPane, String id) {
        return rootPane.getChildren().stream()
                .filter(x -> x.getId() != null && x.getId().equals(id))
                .findFirst();
    }
}
