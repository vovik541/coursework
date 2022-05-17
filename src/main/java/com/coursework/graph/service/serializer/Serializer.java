package com.coursework.graph.service.serializer;

import com.coursework.graph.entity.jsondto.GraphDto;

public interface Serializer {
    GraphDto loadGraphDto();

    void saveGraphDto(GraphDto graphDto);
}
