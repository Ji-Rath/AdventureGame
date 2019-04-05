package com.example.gareth_aguilar.adventuregame;

import java.util.Scanner;
import java.util.Random;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class AdventureGame extends AppCompatActivity {
    private int day, month, year, season, food, money, medicine, progress;
    private Player[] players = new Player[3];
    Scanner scan;
    Random rand;
    TextFileSave txt;
    public AdventureGame() {
        scan = new Scanner(System.in);
        rand = new Random();
        txt = new TextFileSave();
        day = 0;
        month = 0;
        year = 1200;
        season = 0;
        medicine = 50; //Out of 100
        food = 50; //Out of 100
        progress = 0; //Out of 100;
        money = 10000; //yen (100 yen = about 1 dollar)
        players[0] = new Player("Bob");
        players[1] = new Player("Billy");
        players[2] = new Player("Joe");
    }

    public void pickCharacter() {
        System.out.println("Please enter 3 names");
        String[] p = {scan.next(),scan.next(),scan.next()};
        txt.saveName(p);
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
    public boolean pause() {
        System.out.println("Enter anything to continue....");
        String s = "asdasd";
        if (scan.next() == s) {
            return true;
        }
        return false;
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

        System.out.println("What would you like to do today?\n");
        b[0] = "Continue";
        b[1] = "Set Up Camp";
        b[2] = "Check Status";
        for(int i=0;i<b.length;i++) {
            System.out.print("["+i+"]"+" "+b[i]+" | ");
        }
        System.out.println("\nCurrent Progress: "+progress+"/100");
        switch(scan.nextInt()) {
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
        pause();
        menu();
    }
    public void addProgress(int p) {
        progress += p;
    }
    public int getProgress() {
        return progress;
    }
    public Player getPlayer(int p) {
        return players[p];
    }

    public void takeMoney(int m) {
        money -= m;
        System.out.print("(-"+m+" YEN)");
    }
    public void takeFood(int f) {
        food -= f;
        System.out.print("(-"+f+" FOOD)");
    }

    public void randomEvent(TextView txt_main, Button btn_1, Button btn_2) {
        int random = rand.nextInt(1);
        final int p = rand.nextInt(3);
        int choice = -1;
        switch(random) {
            case 0:
                txt_main.setText("A group of poorly equipped theives block the way. They demand money and food to continue.");
                btn_1.setText("Comply");
                btn_2.setText("Run Away");
                btn_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("One of the thieves draw their knife, leading to an intense battle. "
                                +players[p].getName()+" gets injured in the process");
                        players[p].damagePlayer(30);
                    }
                });
                btn_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("The men accept the offering and allow you to pass.");
                        takeFood(15);
                        takeMoney(5000);
                    }
                });
                System.out.println("Next Event!");
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
    }
}