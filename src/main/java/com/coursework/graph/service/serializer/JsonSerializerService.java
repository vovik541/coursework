package com.coursework.graph.service.serializer;

import com.coursework.graph.entity.jsondto.GraphDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Data
@Service
public class JsonSerializerService implements Serializer {

    @Value("${file.storage.path}")
    private String fileStoragePath;

    @SneakyThrows
    public GraphDto loadGraphDto() {
        try (FileReader reader = new FileReader(fileStoragePath + "graph.json")) {
            BufferedReader br = new BufferedReader(reader);
            return new Gson().fromJson(br, GraphDto.class);
        }
    }

    @SneakyThrows
    public void saveGraphDto(GraphDto graphDto) {
        try (Writer writer = new FileWriter(fileStoragePath + "graph.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(graphDto, writer);
        }
    }
}
