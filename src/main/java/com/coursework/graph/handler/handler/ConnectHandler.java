package com.coursework.graph.handler.handler;

import com.coursework.graph.entity.nodeextension.GraphEdge;
import com.coursework.graph.entity.nodeextension.GraphNode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

import java.util.Optional;

public class ConnectHandler extends AbstractEventHandler {
    @Override
    public void changeHandler(AnchorPane rootPane, GraphNode node) {
        node.setOnMousePressed((x) -> {
            if (connectToId != null && !connectToId.equals(node.getId())) {
                if (noEdgeFound(rootPane, node)) {
                    Optional<GraphNode> byId = search.findGraphNodeById(rootPane, connectToId);
                    GraphEdge connected = connect(node, byId.get());
                    rootPane.getChildren().add(connected);
                    connectToId = null;
                    changeColor(byId.get(), Color.GAINSBORO);
                }
            } else {
                connectToId = node.getId();
                changeColor(node, Color.CORNFLOWERBLUE);
            }
        });
        node.setOnMouseDragged((x) -> {
        });
    }

    private GraphEdge connect(GraphNode node1, GraphNode node2) {
        GraphEdge graphEdge = new GraphEdge();
        graphEdge.startXProperty().bind(node1.centerXProperty());
        graphEdge.startYProperty().bind(node1.centerYProperty());
        graphEdge.endXProperty().bind(node2.centerXProperty());
        graphEdge.endYProperty().bind(node2.centerYProperty());
        graphEdge.setStrokeWidth(1);
        graphEdge.setStrokeLineCap(StrokeLineCap.BUTT);
        graphEdge.getStrokeDashArray().setAll(1.0, 4.0);
        graphEdge.setBeginNodeId(node1.getNodeId());
        graphEdge.setEndNodeId(node2.getNodeId());
        graphEdge.setId("Edge_" + node1.getNodeId() + "_" + node2.getNodeId());

        return graphEdge;
    }

    public boolean noEdgeFound(AnchorPane rootPane, GraphNode node) {
        String[] split = connectToId.split("_");
        String edgeId = "Edge_" + node.getNodeId() + "_" + split[split.length - 1];

        Optional<GraphEdge> graphEdgeById = search.findGraphEdgeById(rootPane, edgeId);
        if (graphEdgeById.isEmpty()) {
            edgeId = "Edge_" + split[split.length - 1] + "_" + node.getNodeId();
            graphEdgeById = search.findGraphEdgeById(rootPane, edgeId);
        }

        return graphEdgeById.isEmpty();
    }
}
