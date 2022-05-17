package com.coursework.graph.service;

import com.coursework.graph.algorithm.Algorithm;
import com.coursework.graph.algorithm.GreedAlgorithm;
import com.coursework.graph.entity.nodeextension.Graph;
import com.coursework.graph.entity.nodeextension.GraphEdge;
import com.coursework.graph.entity.nodeextension.GraphNode;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.coursework.graph.configs.AlgorithmType.GREED_ALGORITHM;

@Service
public class AlgorithmService {
    @Autowired
    public SearchFxElementsService search;

    public void findCoverage(AnchorPane rootPanel, String algorithmName) {
        Algorithm algorithm = getAlgorithm(algorithmName);

        List<GraphNode> nodes = search.findAllGraphNodes(rootPanel);
        List<GraphEdge> edges = search.findAllGraphEdges(rootPanel);

        algorithm.findCoverage(new Graph(nodes, edges));
    }

    private Algorithm getAlgorithm(String algorithmName) {
        if (GREED_ALGORITHM.getValue().equals(algorithmName)) {
            return new GreedAlgorithm();
        } else {
            return null; //todo
        }
    }

}
