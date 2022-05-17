package com.coursework.graph.javafxappication;

import com.coursework.graph.SpringGraphApplication;
import com.coursework.graph.controller.AlgorithmController;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class JavaFxApplication extends javafx.application.Application {
    public ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(SpringGraphApplication.class)
                .run(args);
    }
    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }
    @Override
    public void start(Stage stage) throws IOException {
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(AlgorithmController.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}