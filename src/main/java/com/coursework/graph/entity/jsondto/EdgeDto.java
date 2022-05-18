package com.coursework.graph.entity.jsondto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EdgeDto {
    private int beginNodeId;
    private int endNodeId;
}
