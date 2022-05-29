package com.coursework.graph.service;

import static com.coursework.graph.util.TestCommons.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.coursework.graph.entity.facory.GraphEdgeFactory;
import com.coursework.graph.entity.facory.GraphNodeFactory;
import com.coursework.graph.entity.jsondto.GraphDto;
import com.coursework.graph.entity.nodeextension.Graph;
import com.coursework.graph.handler.handlermanager.ConnectHandlerManager;
import com.coursework.graph.handler.handlermanager.DeleteHandlerManager;
import com.coursework.graph.handler.handlermanager.MoveHandlerManager;
import com.coursework.graph.service.serializer.JsonSerializerService;
import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaveLoadServiceTest {

    @Mock
    private SearchFxElementsService searchFxElementsServiceMock;
    @Mock
    private JsonSerializerService jsonSerializerServiceMock;
    @Mock
    private ConnectHandlerManager connectHandlerManagerMock;
    @Mock
    private MoveHandlerManager moveHandlerManagerMock;
    @Mock
    private DeleteHandlerManager deleteHandlerManagerMock;
    @Mock
    private GraphNodeFactory graphNodeFactoryMock;
    @Mock
    private GraphEdgeFactory graphEdgeFactoryMock;
    @Mock
    private MapperService mapperServiceMock;
    @Mock
    private AnchorPane rootPaneMock;
    private SaveLoadService saveLoadService;

    @BeforeEach
    void setUp() {
        this.saveLoadService = new SaveLoadService(searchFxElementsServiceMock,
                mapperServiceMock,
                jsonSerializerServiceMock,
                connectHandlerManagerMock,
                moveHandlerManagerMock,
                deleteHandlerManagerMock,
                graphNodeFactoryMock,
                graphEdgeFactoryMock);
    }

    @Test
    void saveGraph() {
        GraphDto dto = creteGraphDto();

        when(searchFxElementsServiceMock.findAllGraphNodes(rootPaneMock)).thenReturn(creteGraphNodes());
        when(searchFxElementsServiceMock.findAllGraphEdges(rootPaneMock)).thenReturn(creteGraphEdges());
        when(mapperServiceMock.graphToDto(any(Graph.class))).thenReturn(dto);
        doNothing().when(jsonSerializerServiceMock).saveGraphDto(dto);

        saveLoadService.saveGraph(rootPaneMock);

        verify(searchFxElementsServiceMock, times(1)).findAllGraphNodes(rootPaneMock);
        verify(searchFxElementsServiceMock, times(1)).findAllGraphEdges(rootPaneMock);
        verify(mapperServiceMock, times(1)).graphToDto(any(Graph.class));
        verify(jsonSerializerServiceMock, times(1)).saveGraphDto(dto);
    }
}