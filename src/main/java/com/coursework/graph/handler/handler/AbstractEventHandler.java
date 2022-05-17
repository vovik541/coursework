package com.coursework.graph.handler.handler;

import com.coursework.graph.entity.GraphNode;
import com.coursework.graph.service.SearchFxElementsService;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.List;

public abstract class AbstractEventHandler {

    protected SearchFxElementsService search = new SearchFxElementsService();
    protected static String connectToId;
    protected double orgSceneX, orgSceneY;

    public void changeHandler(AnchorPane rootPane) {
        if (connectToId != null) {
            changeColor(search.findGraphNodeById(rootPane, connectToId).get(), Color.GAINSBORO);
            connectToId = null;
        }
        List<GraphNode> nodes = search.findAllGraphNodes(rootPane);
        for (GraphNode node : nodes) {
            changeHandler(rootPane, node);
        }
    }

    public abstract void changeHandler(AnchorPane rootPane, GraphNode node);



    protected void changeColor(GraphNode graphNode, Color color) {
        graphNode.setFill(color);
    }
}
