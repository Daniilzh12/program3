package com.example.program3.chain;

import com.example.program3.HelloController;
import javafx.animation.RotateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Random;

public class PositiveHandler extends Handler{

    public PositiveHandler(Handler processor, Player player) {
        super(processor,player);
    }

    public boolean process(Integer request, ImageView img, HelloController helloController) {
        if(request!=SUCCESS) return super.process(request,img, helloController);
        else {
            int angle = new Random().nextInt(90)+1;
            RotateTransition rt = new RotateTransition(Duration.seconds(4),img);
            rt.setFromAngle(img.getRotate()-360);
            rt.setToAngle(angle+360);
            rt.setCycleCount(1);
            rt.play();
            rt.setOnFinished(event-> Win(helloController));
            return true;
        }
    }
    public void Win(HelloController helloController)
    {
        player1.addNumber(3);
        if(player1.getNumber()>=50) helloController.animStart(3);
        else helloController.animStart(2);
    }
}
