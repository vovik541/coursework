package com.coursework.graph.entity.jsondto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class NodeDto {
    private double x;
    private double y;
    private int nodeId;
}
