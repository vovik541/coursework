package com.coursework.graph.controller;

import com.coursework.graph.handler.handlermanager.AbstractEventHandler;
import com.coursework.graph.handler.handlermanager.ConnectHandlerManager;
import com.coursework.graph.handler.handlermanager.DeleteHandlerManager;
import com.coursework.graph.handler.handlermanager.MoveHandlerManager;
import com.coursework.graph.facory.GraphNodeFactory;
import com.coursework.graph.service.AlgorithmService;
import com.coursework.graph.service.HandlerService;
import com.coursework.graph.service.SaveLoadService;
import com.coursework.graph.service.StyleChangerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

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
    @FXML
    private ImageView saveIcon;
    @FXML
    private ImageView loadIcon;
    @FXML
    private ImageView refreshIcon;
    @Autowired
    private GraphNodeFactory graphNodeFactory;
    @Autowired
    private HandlerService handlerService;
    @Autowired
    private AlgorithmService algorithmService;
    @Autowired
    private StyleChangerService styleChangerService;

    @Autowired
    private SaveLoadService saveLoadService;
    @Autowired
    private MoveHandlerManager moveHandlerManager;
    @Autowired
    private ConnectHandlerManager connectHandlerManager;
    @Autowired
    private DeleteHandlerManager deleteHandlerManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseAlgorithm.getItems().addAll(GREED_ALGORITHM.getValue(), APPROX_VERTEX_ALGORITHM.getValue());
        chooseAlgorithm.setOnAction(this::getAlgorithm);
        chooseAlgorithm.setValue("Greed Algorithm");
        saveIcon.setOnMouseClicked(x -> saveLoadService.saveGraph(rootPane));
        loadIcon.setOnMouseClicked(x -> saveLoadService.load(rootPane));
        refreshIcon.setOnMouseClicked(x -> styleChangerService.changeAllToDefault(rootPane));
    }

    public void getAlgorithm(ActionEvent event) {
        String value = chooseAlgorithm.getValue();
        algorithmLabel.setText(value);
    }

    public void createNode() {
        AbstractEventHandler handler;
        if (connectNodesRB.isSelected()) {
            handler = connectHandlerManager;
        } else if (moveNodeRB.isSelected()) {
            handler = moveHandlerManager;
        } else {
            handler = deleteHandlerManager;
        }
        graphNodeFactory.createGraphNode(rootPane, handler);
    }

    public void moveNoteSelected() {
        handlerService.changeHandler(rootPane, moveHandlerManager);
    }

    public void connectNoteSelected() {
        handlerService.changeHandler(rootPane, connectHandlerManager);
    }

    public void deleteNoteSelected() {
        handlerService.changeHandler(rootPane, deleteHandlerManager);
    }

    @SneakyThrows
    public void runAlgorithm() {
        algorithmService.findCoverage(rootPane, chooseAlgorithm.getValue());
    }

}
