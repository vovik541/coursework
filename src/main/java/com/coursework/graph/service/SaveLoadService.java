package com.coursework.graph.service;

import com.coursework.graph.entity.jsondto.EdgeDto;
import com.coursework.graph.entity.jsondto.GraphDto;
import com.coursework.graph.entity.jsondto.NodeDto;
import com.coursework.graph.entity.nodeextension.Graph;
import com.coursework.graph.entity.nodeextension.GraphEdge;
import com.coursework.graph.entity.nodeextension.GraphNode;
import com.coursework.graph.entity.facory.GraphEdgeFactory;
import com.coursework.graph.entity.facory.GraphNodeFactory;
import com.coursework.graph.handler.handlermanager.AbstractEventHandler;
import com.coursework.graph.handler.handlermanager.ConnectHandlerManager;
import com.coursework.graph.handler.handlermanager.DeleteHandlerManager;
import com.coursework.graph.handler.handlermanager.MoveHandlerManager;
import com.coursework.graph.service.serializer.JsonSerializerService;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.coursework.graph.util.Utility.transformNodeId;

@Service
public class SaveLoadService {
    @Autowired
    public SearchFxElementsService search;
    @Autowired
    public MapperService mapperService;
    @Autowired
    public JsonSerializerService serializerService;

    @Autowired
    public ConnectHandlerManager connectHandlerManager;
    @Autowired
    public MoveHandlerManager moveHandlerManager;
    @Autowired
    public DeleteHandlerManager deleteHandlerManager;
    @Autowired
    public GraphNodeFactory graphNodeFactory;

    @Autowired
    public GraphEdgeFactory graphEdgeFactory;

    public void saveGraph(AnchorPane rootPane) {
        Graph graph = new Graph(search.findAllGraphNodes(rootPane), search.findAllGraphEdges(rootPane));
        GraphDto graphDto = mapperService.graphToDto(graph);
        serializerService.saveGraphDto(graphDto);
    }

    public void loadGraph(AnchorPane rootPane) {
        deleteAllElementsFromView(rootPane);

        GraphDto graphDto = serializerService.loadGraphDto();

        for (NodeDto nodeDto : graphDto.getNodes()) {
            graphNodeFactory.createGraphNode(rootPane,
                    connectHandlerManager,
                    nodeDto.getX(),
                    nodeDto.getY(),
                    nodeDto.getNodeId());
        }

        for (EdgeDto edgeDto : graphDto.getEdges()) {
            graphEdgeFactory.createGraphEdge(rootPane,
                    search.findGraphNodeById(rootPane, transformNodeId(edgeDto.getBeginNodeId())).get(),
                    search.findGraphNodeById(rootPane, transformNodeId(edgeDto.getEndNodeId())).get());
        }

        setCurrentHandler(rootPane);
    }

    private void deleteAllElementsFromView(AnchorPane rootPane) {
        List<GraphNode> allGraphNodes = search.findAllGraphNodes(rootPane);
        List<GraphEdge> allGraphEdges = search.findAllGraphEdges(rootPane);
        List<Text> texts = search.findAllTextNodes(rootPane);

        allGraphNodes.forEach(x -> rootPane.getChildren().remove(x));
        allGraphEdges.forEach(x -> rootPane.getChildren().remove(x));
        texts.forEach(x -> rootPane.getChildren().remove(x));
    }

    private void setCurrentHandler(AnchorPane rootPane) {
        RadioButton radioButton = (RadioButton) search.findById(rootPane, "moveNodeRB").get();

        AbstractEventHandler handler;
        if (radioButton.isSelected()) {
            handler = moveHandlerManager;
        } else {
            radioButton = (RadioButton) search.findById(rootPane, "connectNodesRB").get();
            if (radioButton.isSelected()) {
                handler = connectHandlerManager;
            } else {
                handler = deleteHandlerManager;
            }
        }

        handler.changeHandler(rootPane);
    }

}
