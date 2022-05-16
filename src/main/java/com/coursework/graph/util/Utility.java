package com.coursework.graph.util;

import com.coursework.graph.javafxappication.JavaFxApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.SneakyThrows;

import java.util.Objects;

public class Utility {
    @SneakyThrows
    public static Parent getParent(String parentPath){
        return FXMLLoader.load(Objects.requireNonNull(JavaFxApplication.class.getResource(parentPath)));
    }
}
