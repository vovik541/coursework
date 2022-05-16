package com.coursework.graph.handlers;

import com.coursework.graph.entity.GraphEdge;
import com.coursework.graph.entity.GraphNode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractEventHandler {
    protected static String connectToId;
    protected double orgSceneX, orgSceneY;

    public void changeHandler(AnchorPane rootPane) {
        if (connectToId != null) {
            changeColor(findGraphNodeById(rootPane, connectToId).get(), Color.GAINSBORO);
            connectToId = null;
        }
        List<GraphNode> nodes = getGraphNodes(rootPane);
        for (GraphNode node : nodes) {
            changeHandler(rootPane, node);
        }
    }

    public abstract void changeHandler(AnchorPane rootPane, GraphNode node);

    protected List<GraphNode> getGraphNodes(AnchorPane rootPane) {
        return rootPane.getChildren().stream()
                .filter(x -> x.getId() != null && x.getId().contains("Node_"))
                .map(x -> (GraphNode) x)
                .collect(Collectors.toList());
    }

    protected Optional<GraphNode> findGraphNodeById(AnchorPane rootPane, String id) {
        return rootPane.getChildren().stream()
                .filter(x -> x.getId() != null && x.getId().equals(id))
                .map(x -> (GraphNode) x)
                .findFirst();
    }

    protected Optional<Text> findNodeTextById(AnchorPane rootPane, String id) {
        return rootPane.getChildren().stream()
                .filter(x -> x.getId() != null && x.getId().equals(id))
                .map(x -> (Text) x)
                .findFirst();
    }
    protected Optional<GraphEdge> findGraphEdgeById(AnchorPane rootPane, String id) {
        return rootPane.getChildren().stream()
                .filter(x -> x.getId() != null && x.getId().equals(id))
                .map(x -> (GraphEdge) x)
                .findFirst();
    }
    protected List<GraphEdge> findAllEdgesByBeginningId(AnchorPane rootPane, String beginning) {
        return rootPane.getChildren().stream()
                .filter(x -> x.getId() != null && x.getId().contains(beginning))
                .map(x -> (GraphEdge) x)
                .collect(Collectors.toList());
    }

    protected void changeColor(GraphNode graphNode, Color color) {
        graphNode.setFill(color);
    }
}
