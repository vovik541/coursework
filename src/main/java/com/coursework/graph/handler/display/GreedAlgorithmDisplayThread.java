package com.coursework.graph.handler.display;

import com.coursework.graph.entity.nodeextension.GraphEdge;
import com.coursework.graph.entity.nodeextension.GraphNode;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GreedAlgorithmDisplayThread extends Task<Void> {
    private LinkedHashMap<GraphNode, List<GraphEdge>> displayStack;

    public GreedAlgorithmDisplayThread(LinkedHashMap<GraphNode, List<GraphEdge>> displayStack) {
        this.displayStack = displayStack;
    }

    @Override
    protected Void call() throws Exception {
        Text outputText = (Text) displayStack.keySet().stream().findFirst().get()
                .getScene().lookup("#outputText");
        outputText.setText(outputText.getText() + " {");

        for (Map.Entry<GraphNode, List<GraphEdge>> entry : displayStack.entrySet()) {

            outputText.setText(outputText.getText() + " " + entry.getKey().getNodeId() + ",");

            Thread.sleep(1000);
            Platform.runLater(() -> {
                entry.getKey().setFill(Color.RED);
                for (GraphEdge edge : entry.getValue()) {
                    edge.setStrokeWidth(4);
                    edge.setStroke(Color.RED);
                    edge.setStrokeLineCap(StrokeLineCap.SQUARE);
                    edge.toBack();
                }
            });
        }
        outputText.setText(outputText.getText() + " ]");
        outputText.setText(outputText.getText().replace(", ]", " ]"));
        return null;
    }

    public void start() {
        new Thread(this).start();
    }

}
