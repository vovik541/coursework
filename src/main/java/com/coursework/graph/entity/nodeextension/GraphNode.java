package com.coursework.graph.entity.nodeextension;

import javafx.scene.shape.Circle;

public class GraphNode extends Circle {
    private int nodeId;

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }
}
