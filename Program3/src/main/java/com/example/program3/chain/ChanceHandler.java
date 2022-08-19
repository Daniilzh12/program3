package com.example.program3.chain;

import com.example.program3.HelloController;
import javafx.animation.RotateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Random;

public class ChanceHandler extends Handler{
    public ChanceHandler(Handler processor, Player player) {
        super(processor, player);
    }
    public boolean process(Integer request, ImageView img, HelloController helloController) {
        if(request!=CHANCE) return super.process(request,img, helloController);
        else {
            int angle = new Random().nextInt(90)+181;
            //img.setImage(new Image("chance.jpg"));
            RotateTransition rt = new RotateTransition(Duration.seconds(4),img);
            rt.setFromAngle(img.getRotate()-360);
            rt.setToAngle(angle + 360);
            rt.setCycleCount(1);
            rt.play();
            rt.setOnFinished(event-> Chance(helloController));
                return true;
        }
    }
    public void Chance(HelloController helloController)
    {
        helloController.animStart(1);
        player1.addNumber(1);
    }
}
