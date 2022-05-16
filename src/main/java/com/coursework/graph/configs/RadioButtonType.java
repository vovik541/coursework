package com.coursework.graph.configs;

public enum RadioButtonType {
    DELETE("DELETE"),
    CONNECT("CONNECT"),
    MOVE("MOVE");

    private String name;

    RadioButtonType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
