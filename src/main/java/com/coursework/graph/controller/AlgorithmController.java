package com.coursework.graph.controller;

import com.coursework.graph.algorithm.Algorithm;
import com.coursework.graph.algorithm.GreedAlgorithm;
import com.coursework.graph.configs.AlgorithmType;
import com.coursework.graph.entity.GraphEdge;
import com.coursework.graph.entity.GraphNode;
import com.coursework.graph.handler.AbstractEventHandler;
import com.coursework.graph.handler.ConnectHandler;
import com.coursework.graph.handler.DeleteHandler;
import com.coursework.graph.handler.MoveHandler;
import com.coursework.graph.facory.GraphNodeFactory;
import com.coursework.graph.service.AlgorithmService;
import com.coursework.graph.service.HandlerService;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.coursework.graph.configs.AlgorithmType.APPROX_VERTEX_ALGORITHM;
import static com.coursework.graph.configs.AlgorithmType.GREED_ALGORITHM;
import static com.coursework.graph.configs.Constants.MAIN_PAGE_URL;

@Component
@FxmlView(MAIN_PAGE_URL)
@NoArgsConstructor
public class AlgorithmController implements Initializable {
    @FXML
    private RadioButton createNodeRB;
    @FXML
    private RadioButton connectNodesRB, moveNodeRB, deleteNodeRB;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Text outputText;
    @FXML
    private Text algorithmLabel;
    @FXML
    private ChoiceBox<String> chooseAlgorithm;
    @Autowired
    private GraphNodeFactory graphNodeFactory;
    @Autowired
    private HandlerService handlerService;
    @Autowired
    private AlgorithmService algorithmService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseAlgorithm.getItems().addAll(GREED_ALGORITHM.getValue(), APPROX_VERTEX_ALGORITHM.getValue());
        chooseAlgorithm.setOnAction(this::getAlgorithm);
        chooseAlgorithm.setValue("Greed Algorithm");
    }
    public void getAlgorithm(ActionEvent event) {
        String value = chooseAlgorithm.getValue();
        algorithmLabel.setText(value);
    }
    public void createNode(ActionEvent event) {
        AbstractEventHandler handler;
        if (connectNodesRB.isSelected()){
            handler = new ConnectHandler();
        }else if (moveNodeRB.isSelected()){
            handler = new MoveHandler();
        } else {
            handler = new DeleteHandler();
        }
        graphNodeFactory.createGraphNode(rootPane, handler);
    }
    public void moveNoteSelected(ActionEvent event) {
        handlerService.changeHandler(rootPane, new MoveHandler());
    }
    public void connectNoteSelected(ActionEvent event) {
        handlerService.changeHandler(rootPane, new ConnectHandler());
    }
    public void deleteNoteSelected(ActionEvent event) {
        handlerService.changeHandler(rootPane, new DeleteHandler());
    }

    @SneakyThrows
    public void runAlgorithm(ActionEvent event) {
        algorithmService.findCoverage(rootPane, chooseAlgorithm.getValue());
    }
}
