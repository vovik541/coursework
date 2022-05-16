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
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(MAIN_MENU_URL)));
//
//        Scene scene = new Scene(root);
//        Line line = new Line(100, 100, 150, 150);

//        line.setStrokeWidth(5);
//        line.setStroke(Color.RED);
//
//        root.getChildren().add(line);
//
//        Circle circle = new Circle();
//        circle.setCenterX(160);
//        circle.setCenterY(160);
//        circle.setRadius(50);
//        circle.setFill(Color.GRAY);
//        circle.setStrokeWidth(5);
//        circle.setStroke(Color.BLACK);


//        root.getChildren().add(circle);

//        InputStream iconStream = new FileInputStream("src/main/resources/pages/images/icon.png");
//        Image icon = new Image(iconStream);
//        stage.getIcons().add(icon);
//        stage.setTitle("Graph vertex cover");
//        stage.setScene(scene);
//        stage.show();
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(AlgorithmController.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static class StageReadyEvent extends ApplicationEvent {

        public Stage getStage(){
            return Stage.class.cast(getSource());
        }

        public StageReadyEvent(Object source) {
            super(source);
        }
    }
}