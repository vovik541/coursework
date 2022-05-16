package com.coursework.graph.controller;

import com.coursework.graph.handlers.AbstractEventHandler;
import com.coursework.graph.handlers.ConnectHandler;
import com.coursework.graph.handlers.DeleteHandler;
import com.coursework.graph.handlers.MoveHandler;
import com.coursework.graph.service.CircleNodeService;
import com.coursework.graph.service.HandlerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lombok.NoArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

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
    @Autowired
    private CircleNodeService circleNodeService;

    @Autowired
    private HandlerService handlerService;
    @FXML
    private Text outputText;
    @FXML
    private Text algorithmLabel;
    @FXML
    private ChoiceBox<String> chooseAlgorithm;
    private String[] algorithms = {"Greed Algorithm", "Approx Vertex Cover Algorithm"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseAlgorithm.getItems().addAll(algorithms);
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
        circleNodeService.createCircle(rootPane, handler);
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

    public void runAlgorithm(ActionEvent event) {

    }


}
