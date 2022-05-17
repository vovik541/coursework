package com.coursework.graph.service.serializer;

import com.coursework.graph.entity.jsondto.GraphDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;

import java.io.*;

public class JsonSerializer implements Serializer {
    @SneakyThrows
    public GraphDto loadGraphDto() {
        try (FileReader reader = new FileReader("graph.json")) {
            BufferedReader br = new BufferedReader(reader);
            return new Gson().fromJson(br, GraphDto.class);
        }
    }

    @SneakyThrows
    public void saveGraphDto(GraphDto graphDto) {
        try (Writer writer = new FileWriter("graph.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(graphDto, writer);
        }
    }
}
