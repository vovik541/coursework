package com.coursework.graph.handler.task;

import com.coursework.graph.entity.GraphEdge;
import com.coursework.graph.entity.GraphNode;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskThread extends Task<Void> {

    LinkedHashMap<GraphNode, List<GraphEdge>> displayStack;

    public TaskThread(LinkedHashMap<GraphNode, List<GraphEdge>> displayStack) {
        this.displayStack = displayStack;
    }

    @Override
    protected Void call() throws Exception {
        for (Map.Entry<GraphNode, List<GraphEdge>> entry : displayStack.entrySet()) {

            Thread.sleep(1000);
            Platform.runLater(new Runnable() {
                public void run() {
                    entry.getKey().setFill(Color.RED);
                    for (GraphEdge edge : entry.getValue()) {
                        edge.setStrokeWidth(4);
                        edge.setStroke(Color.RED);
                        edge.setStrokeLineCap(StrokeLineCap.SQUARE);
                        edge.toBack();
                    }
                }
            });
        }
        return null;
    }

    public void start() {
        new Thread(this).start();
    }

}
