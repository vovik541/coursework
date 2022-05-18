package com.coursework.graph.entity.jsondto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GraphDto {
    List<NodeDto> nodes;
    List<EdgeDto> edge;

    public GraphDto(List<NodeDto> nodes, List<EdgeDto> edge) {
        this.nodes = nodes;
        this.edge = edge;
    }
}
