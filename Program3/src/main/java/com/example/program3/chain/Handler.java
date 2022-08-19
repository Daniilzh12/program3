package com.example.program3.chain;

import com.example.program3.HelloController;
import javafx.scene.image.ImageView;

public abstract class Handler {
    public static int SUCCESS = 1;
    public static int CHANCE = 3;
    private Handler processor;
    public Player player1;
    public Handler(Handler processor, Player player){
        this.processor = processor;
        this.player1=player;
    }

    public boolean process(Integer request, ImageView img, HelloController helloController){
        if(processor != null)
            return processor.process(request,img, helloController);
        else
            return true;
    }
}
