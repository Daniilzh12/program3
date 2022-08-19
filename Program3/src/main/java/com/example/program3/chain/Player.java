package com.example.program3.chain;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    ListView listview;
    Label Money;
    private Integer number;

    public Player(String name, Integer number, Label money, ListView lw) {
        this.name = name;
        this.number = number;
        Money = money;
        listview = lw;
        Money.setText(String.valueOf(this.number));
    }

    public boolean pay(Integer number) {
        if(number <= this.number) {
            this.number-=number;
            Money.setText(String.valueOf(this.number));
            listview.refresh();
            return true;
        }
        else return false;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public boolean addNumber(Integer number) {
        this.number+= number;
        Money.setText(String.valueOf(this.number));
        listview.refresh();
        return this.number == 10000;
    }
}
