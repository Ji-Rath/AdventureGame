package com.example.gareth_aguilar.adventuregame;

import java.io.Serializable;
import java.util.Random;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class AdventureGame extends AppCompatActivity implements Serializable {
    private int day, month, year, season, food, money, medicine, progress;
    private Player[] players = new Player[3];
    Random rand;
    //TextFileSave txt;

    public AdventureGame(String[] p) {
        rand = new Random();
        //txt = new TextFileSave();
        day = 0;
        month = 0;
        year = 1200;
        season = 0;
        medicine = 50; //Out of 100
        food = 50; //Out of 100
        progress = 0; //Out of 100;
        money = 10000; //yen (100 yen = about 1 dollar)
        players[0] = new Player(p[0]);
        players[1] = new Player(p[1]);
        players[2] = new Player(p[2]);
    }
    public void addDay() {
        day ++;
        if (day > 30) {
            day = 0;
            month ++;
        }
        if (month > 11) {
            month = 0;
            year ++;
            season = 0;
        }
        if (month > 2) {
            season = 1;
        }
        if (month > 5) {
            season = 2;
        }
        if (month > 8) {
            season = 3;
        }
    }

    public void randomMainEvent(int e){
        // 0 - Echigo, 1 - Shinano, 2 - Mino, 3 - Omu
        switch(e) {
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
        }
    }
    public void menu() {
        String[] b = new String[3];
        int temp = 0;
        System.out.println("What would you like to do today?\n");
        b[0] = "Continue";
        b[1] = "Set Up Camp";
        b[2] = "Check Status";
        for(int i=0;i<b.length;i++) {
            System.out.print("["+i+"]"+" "+b[i]+" | ");
        }
        System.out.println("\nCurrent Progress: "+progress+"/100");
        switch(temp) {
            case 0:
                //Main adventure
                break;

            case 1:
                //Setup camp
                camp();
                break;

            case 2:
                //Check stats
                stats();
                break;
        }
    }
    public void camp() {

    }
    public void stats() {
        for(int i=0;i<players.length;i++) {
            System.out.println(players[i].toString()+"\n");
        }
        menu();
    }
    public String addProgress(int p) {
        progress += p;
        if(p>0) {
            return ("(+" + p + " PROGRESS) ");
        } else {
            return ("(" + p + " PROGRESS) ");
        }
    }
    public int getProgress() {
        return progress;
    }
    public Player getPlayer(int p) {
        return players[p];
    }

    public String takeMoney(int m) {
        money -= m;
        return ("(-"+m+" YEN) ");
    }
    public String takeFood(int f) {
        food -= f;
        return ("(-"+f+" FOOD) ");
    }

    public int randomEvent(Button btn_left, Button btn_right, TextView txt_main) {
        int random = rand.nextInt(1);
        final int p = rand.nextInt(3);
        int choice = -1;
        switch(random) {
            case 0:
                txt_main.setText("A group of poorly equipped theives block the way. They demand money and food to continue.");
                btn_left.setText("Comply");
                btn_right.setText("Run Away");
                break;
            case 1:
                System.out.println(players[p].getName()+" was searching for food when he found some bright red berries.");
                //choice = pick("Eat The Berries","Do Nothing");
                switch(choice) {
                    case 0: //eat
                        System.out.println(players[p].getName()+" eagerly takes a bite out of one of the berries");
                        players[p].giveDisease(4);
                        System.out.println("\n");
                        break;
                    case 1: //nothing
                        System.out.println(players[p].getName()+" puts the berries down and carries on his merry way.");
                        break;
                }
                break;
        }
        //pause();
        return random;
    }
}