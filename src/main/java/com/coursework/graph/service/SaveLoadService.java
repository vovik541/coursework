package com.coursework.graph.service;

import com.coursework.graph.entity.jsondto.GraphDto;
import com.coursework.graph.entity.nodeextension.Graph;
import com.coursework.graph.service.serializer.JsonSerializerService;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveLoadService {
    @Autowired
    public SearchFxElementsService search;
    @Autowired
    public MapperService mapperService;
    @Autowired
    public JsonSerializerService serializerService;

    public void saveGraph(AnchorPane rootPane) {
        Graph graph = new Graph(search.findAllGraphNodes(rootPane), search.findAllGraphEdges(rootPane));
        GraphDto graphDto = mapperService.graphToDto(graph);
        serializerService.saveGraphDto(graphDto);
    }

    public Graph load() {
        GraphDto dto = serializerService.loadGraphDto();
        //todo
//        Graph graph = mapperService.dtoToGraph(dto);
//        GraphDto graphDto = mapperService.graphToDto(graph);
//        serializerService.saveGraphDto(graphDto);
        return null;
    }

}
