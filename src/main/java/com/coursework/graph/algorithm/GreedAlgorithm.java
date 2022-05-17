package com.coursework.graph.algorithm;

import com.coursework.graph.controller.AlgorithmController;
import com.coursework.graph.entity.Graph;
import com.coursework.graph.entity.GraphEdge;
import com.coursework.graph.entity.GraphNode;

import com.coursework.graph.handler.task.TaskThread;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.*;

@Component
public class GreedAlgorithm implements Algorithm {

    @Override
    public void findCoverage(Graph graph) {
        GraphNode node;
        List<GraphEdge> edges;

        LinkedHashMap<GraphNode, List<GraphEdge>> displayStack = new LinkedHashMap<>();

        while (!graph.getEdges().isEmpty()){
            node = findNodeWithMostEdges(graph);
            edges = graph.getAllEdgesByNodeId(node.getNodeId());
            graph.deleteEdgesFromGraph(edges);
            graph.deleteNodeFromGraph(node);

            displayStack.put(node, edges);
        }
        new TaskThread(displayStack).start();
    }

    private GraphNode findNodeWithMostEdges(Graph graph){
        int maxNumberOfEdges = 0;
        GraphNode maxNode = null;

        List<GraphEdge> edges;
        for (GraphNode node: graph.getNodes()){
            edges = graph.getAllEdgesByNodeId(node.getNodeId());
            if(edges.size() > maxNumberOfEdges){
                maxNumberOfEdges = edges.size();
                maxNode = node;
            }
        }

        return maxNode;
    }



}
