package com.coursework.graph.entity.nodeextension;

import javafx.scene.shape.Line;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class GraphEdge extends Line {
    private int beginNodeId;
    private int endNodeId;

    public boolean isConnectedToNodeId(int nodeId) {
        return beginNodeId == nodeId || endNodeId == nodeId;
    }
}
