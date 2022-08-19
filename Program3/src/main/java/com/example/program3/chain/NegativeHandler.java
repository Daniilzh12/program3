package com.example.program3.chain;

import com.example.program3.HelloController;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Random;

public class NegativeHandler extends Handler {
    public NegativeHandler(Handler processor, Player player) {
        super(processor,player);
    }

    public boolean process(Integer request, ImageView img, HelloController helloController) {
        int angle;
        int angle1 = new Random().nextInt(90)+91;
        int angle2 = new Random().nextInt(90)+271;
        int r =new Random().nextInt(2);
        if(r == 0) angle = angle1;
        else angle = angle2;
        RotateTransition rt = new RotateTransition(Duration.seconds(4),img);
        rt.setFromAngle(img.getRotate()-360);
        rt.setToAngle(angle+360);
        rt.setCycleCount(1);
        rt.play();
        rt.setOnFinished(event-> Lose(helloController));
        //img.setImage(new Image("fail.png"));
        return true;
    }
    public void Lose(HelloController helloController)
    {
        helloController.animStart(1);
    }
}
