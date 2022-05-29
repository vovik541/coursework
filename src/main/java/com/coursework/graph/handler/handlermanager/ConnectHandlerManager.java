package com.coursework.graph.handler.handlermanager;

import com.coursework.graph.entity.nodeextension.GraphEdge;
import com.coursework.graph.entity.nodeextension.GraphNode;
import com.coursework.graph.entity.facory.GraphEdgeFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.coursework.graph.util.Utility.getIdFromEdgeFullId;
import static com.coursework.graph.util.Utility.transformEdgeId;

@Service
public class ConnectHandlerManager extends AbstractEventHandler {
    @Autowired
    private GraphEdgeFactory edgeFactory;

    @Override
    public void changeHandler(AnchorPane rootPane, GraphNode node) {
        node.setOnMousePressed((x) -> {
            if (connectToId != null && !connectToId.equals(node.getId())) {
                if (noEdgeFound(rootPane, node)) {
                    Optional<GraphNode> byId = search.findGraphNodeById(rootPane, connectToId);
                    edgeFactory.createGraphEdge(rootPane, node, byId.get());
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

    public boolean noEdgeFound(AnchorPane rootPane, GraphNode node) {
        int connectedTo = getIdFromEdgeFullId(connectToId);
        String edgeId = transformEdgeId(node.getNodeId(), connectedTo);

        Optional<GraphEdge> graphEdgeById = search.findGraphEdgeById(rootPane, edgeId);
        if (graphEdgeById.isEmpty()) {
            edgeId = transformEdgeId(connectedTo, node.getNodeId());
            graphEdgeById = search.findGraphEdgeById(rootPane, edgeId);
        }

        return graphEdgeById.isEmpty();
    }
}
