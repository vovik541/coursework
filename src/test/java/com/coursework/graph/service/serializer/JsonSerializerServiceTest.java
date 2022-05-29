package com.coursework.graph.service.serializer;

import com.coursework.graph.entity.jsondto.GraphDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.coursework.graph.util.TestCommons.creteGraphDto;
import static org.junit.jupiter.api.Assertions.*;

class JsonSerializerServiceTest {

    @Autowired
    private JsonSerializerService serializerService;

    @BeforeEach
    void setUp() {
        this.serializerService = new JsonSerializerService();
        this.serializerService.setFileStoragePath("src/test/java/com/coursework/graph/resources/");
    }

    @Test
    void serviceWritesAndReadsFromJsonFileWrite() {
        GraphDto expected = creteGraphDto();
        serializerService.saveGraphDto(expected);
        GraphDto result = serializerService.loadGraphDto();

        assertEquals(expected.getNodes().size(), result.getNodes().size());
        assertEquals(expected.getEdges().size(), result.getEdges().size());
        assertEquals(expected.getNodes().get(0).toString(), result.getNodes().get(0).toString());
        assertEquals(expected.getNodes().get(1).toString(), result.getNodes().get(1).toString());
        assertEquals(expected.getEdges().get(0).toString(), result.getEdges().get(0).toString());
    }
}