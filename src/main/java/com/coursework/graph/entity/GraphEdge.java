package com.coursework.graph.entity;

import javafx.scene.shape.Line;

public class GraphEdge extends Line {
    private int beginNodeId;
    private int endNodeId;

    public boolean isConnectedToNodeId(int nodeId){
        return beginNodeId == nodeId || endNodeId == nodeId;
    }
    public int getBeginNodeId() {
        return beginNodeId;
    }

    public void setBeginNodeId(int beginNodeId) {
        this.beginNodeId = beginNodeId;
    }

    public int getEndNodeId() {
        return endNodeId;
    }

    public void setEndNodeId(int endNodeId) {
        this.endNodeId = endNodeId;
    }
}
