package com.coursework.graph.algorithm;

import com.coursework.graph.controller.AlgorithmController;
import com.coursework.graph.entity.GraphEdge;
import com.coursework.graph.entity.GraphNode;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class GreedAlgorithm implements Algorithm{
    @Inject
    public ApplicationContext applicationContext;
    @SneakyThrows
    @Override
    public void findCoverage(List<GraphNode> nodes, List<GraphEdge> edges) {

    }

    {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (GraphNode node : nodes) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    Platform.runLater(new Runnable() {
                        public void run() {
                            node.setFill(Color.RED);
                        }
                    });
                }
                return null;
            }
        };
        Thread th = new Thread(task);
        th.start();
    }
}
