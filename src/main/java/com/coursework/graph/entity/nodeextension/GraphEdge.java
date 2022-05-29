package com.coursework.graph.entity.nodeextension;

import javafx.scene.shape.Line;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GraphEdge extends Line {
    private int beginNodeId;
    private int endNodeId;

    public boolean isConnectedToNodeId(int nodeId) {
        return beginNodeId == nodeId || endNodeId == nodeId;
    }
}
