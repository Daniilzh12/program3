package com.example.program3;

import com.example.program3.chain.ActionChain;
import com.example.program3.chain.Player;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {

    public ImageView wheel;
    public ListView<Player> LeaderBoard;
    public TextField PlayerName;
    public Label countMoney;
    Timeline timeline;
    public ImageView triangle;
    public ToggleButton theme;
    public ImageView personAvatar;
    public HBox person;
    public int indexSel=0;
    public TextArea personText;
    public AnchorPane pane;
    ActionChain action=null;
    Player player1;
    ObservableList<Player> players;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wheel.setImage(new Image("wheel.png"));
        triangle.setImage(new Image("triangle.png"));
        wheel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> play());
        players = FXCollections.observableArrayList();
        LeaderBoard.itemsProperty().setValue(players);
        LeaderBoard.setCellFactory(param -> new ListCell<Player>() {
            @Override
            protected void updateItem(Player item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName() + ": " + item.getNumber());
                }
            }
        });
        wheel.setOnRotationFinished(rotateEvent -> LeaderBoard.refresh());
        pane.setOnMouseClicked(mouseEvent -> animStop());
        animStart(0);
    }
    public void animStart(int scene)
    {
        if(timeline!=null) timeline.stop();
        int time = 700;
        List<Image> images = new ArrayList<>();

        switch (scene) {
            case 0 -> {
                personText.setText("Эй, приятель!\nСлушай, тут такое дело,\nмне нужно немного денег(50),\nне поможешь по-братски:)");
                images.add(new Image("person2.jpg"));
                images.add(new Image("person1.jpg"));
            }
            case 1 -> {
                personText.setText("Не повезло");
                images.add(new Image("person3.jpg"));
                images.add(new Image("person1.jpg"));
            }
            case 2 -> {
                personText.setText("Нормалек!");
                images.add(new Image("person4.png"));
                images.add(new Image("person5.png"));
            }
            case 3 -> {
                personText.setText("Спасибо, братан. Все, пока, мне пора.");
                images.add(new Image("car1.png"));
                images.add(new Image("car2.png"));
                images.add(new Image("car3.png"));
                images.add(new Image("car4.png"));
                time =50;
            }
        }
        personText.setVisible(true);
        person.setVisible(true);
        timeline = new Timeline (
                new KeyFrame (
                        Duration.millis(time),
                        ae -> {
                            indexSel++;
                            if(indexSel>=images.size())
                                indexSel=0;
                            personAvatar.setImage(images.get(indexSel));
                        }
                )
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    public void animStop()
    {
        person.setVisible(false);
        personText.setVisible(false);
        timeline.stop();
    }

    public void themeChange()
    {
        if(theme.isSelected())
            wheel.setImage(new Image("wheel2.png"));
        else wheel.setImage(new Image("wheel.png"));
    }
    public void play()
    {
        if (player1!=null) {
            onStart();
            player1.pay(1);
            if (action == null) return;
            if (action.process()) init();
            else action = null;
        }
        else new Alert(Alert.AlertType.ERROR,"Вы не создали игрока!").show();
    }
    public void createPlayer()
    {
        int number = 10;
        if (!Objects.equals(PlayerName.getText(), "")) {
            if(PlayerName.getText().equals("Толян")) number = 50;
            player1 = new Player(PlayerName.getText(), number, countMoney,LeaderBoard);
            players.add(player1);
        }
        else new Alert(Alert.AlertType.ERROR,"Вы не ввели имя игрока!").show();
    }

    public void onStart() {
        if(!init()) return;
        action=new ActionChain(player1,wheel,this);
    }

    public boolean init(){
        if(player1.getNumber()==0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Средств на счете недостаточно!");
            alert.show();
            action=null;
            return false;
        }
        else return true;
    }
}