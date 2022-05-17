package com.coursework.graph.entity;

import com.sun.javafx.geom.Edge;
import javafx.scene.shape.Circle;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

public class GraphNode extends Circle {
    private int nodeId;

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }


}
