package com.coursework.graph.entity.jsondto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphDto {
    private List<NodeDto> nodes;
    private List<EdgeDto> edges;
}
