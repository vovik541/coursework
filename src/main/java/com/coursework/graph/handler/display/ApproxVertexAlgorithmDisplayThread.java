package com.coursework.graph.handler.display;

import com.coursework.graph.entity.nodeextension.GraphEdge;
import com.coursework.graph.entity.nodeextension.GraphNode;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ApproxVertexAlgorithmDisplayThread extends Task<Void> {
    private LinkedHashMap<GraphEdge, List<GraphNode>> displayNodeStack;
    private HashMap<GraphEdge, List<GraphEdge>> displayEdgesStack;

    public ApproxVertexAlgorithmDisplayThread(LinkedHashMap<GraphEdge, List<GraphNode>> displayNodeStack,
                                              HashMap<GraphEdge, List<GraphEdge>> displayEdgesStack) {
        this.displayNodeStack = displayNodeStack;
        this.displayEdgesStack = displayEdgesStack;
    }

    @Override
    protected Void call() throws Exception {
        Text outputText = (Text) displayNodeStack.keySet().stream().findFirst().get()
                .getScene().lookup("#outputText");
        outputText.setText(outputText.getText() + " {");

        for (Map.Entry<GraphEdge, List<GraphNode>> entry : displayNodeStack.entrySet()) {
            Thread.sleep(1200);
            Platform.runLater(() -> {

                for (GraphNode node : entry.getValue()) {
                    outputText.setText(outputText.getText() + " " + node.getNodeId() + ",");
                    node.setFill(Color.RED);
                }
                for (GraphEdge edge : displayEdgesStack.get(entry.getKey())) {
                    if (entry.getKey().equals(edge)) {
                        edge.setStrokeWidth(4);
                        edge.setStroke(Color.RED);
                    } else {
                        edge.setStrokeWidth(2);
                        edge.setStroke(Color.BLUE);
                    }
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
