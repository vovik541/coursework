package com.coursework.graph;

import com.coursework.graph.javafxappication.JavaFxApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringGraphApplication {

    public static void main(String[] args) {
        Application.launch(JavaFxApplication.class, args);
    }
}
