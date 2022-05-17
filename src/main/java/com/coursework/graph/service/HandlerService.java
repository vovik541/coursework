package com.coursework.graph.service;

import com.coursework.graph.handler.AbstractEventHandler;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Service;

@Service
public class HandlerService {
    public void changeHandler(AnchorPane rootPane, AbstractEventHandler handler){
        handler.changeHandler(rootPane);
    }
}
