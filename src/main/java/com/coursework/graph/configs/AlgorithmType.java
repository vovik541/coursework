package com.coursework.graph.configs;

public enum AlgorithmType {
    GREED_ALGORITHM("Greed Algorithm"),
    APPROX_VERTEX_ALGORITHM("Approx Vertex Cover Algorithm");

    private String value;

    AlgorithmType(String name) {
        this.value = name;
    }
    public String getValue() {
        return value;
    }
}
