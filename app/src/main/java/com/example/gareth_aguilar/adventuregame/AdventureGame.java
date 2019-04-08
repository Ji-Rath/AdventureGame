package com.example.gareth_aguilar.adventuregame;

import java.io.Serializable;
import java.util.Random;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

public class AdventureGame extends AppCompatActivity implements Serializable {
    private int day, month, year, season, food, money, progress;
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
                break;

            case 2:
                //Check stats
                stats();
                break;
        }
    }

    public int getPlayerCount() {
        int count = 0;
        for(int i=0;i<3;i++) {
            if(players[i].isAvailable()) {
                count++;
            }
        }
        return count;
    }
    public String getTime() {
        return "D: "+day+" M: "+month+" Y: "+year;
    }

    public String getLocation() {
        return "Forest";
    }

    public void stats() {
        for(int i=0;i<players.length;i++) {
            System.out.println(players[i].toString()+"\n");
        }
        menu();
    }
    public String addProgress(int p) {
        if(p==0) {
            return "";
        }
        progress += p;
        if(progress <0) {
            progress = 0;
        }
        addDay();
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
    public int getFood() {
        return food;
    }
    public int getMoney() {
        return money;
    }

    public String addMoney(int m) {
        money += m;
        if(m>0) {
            return ("(+" + m + " YEN) ");
        } else {
            return ("(" + m + " YEN) ");
        }
    }
    public String addFood(int f) {
        food += f;
        if(f>0) {
            return ("(+" + f + " FOOD) ");
        } else {
            return ("(" + f + " FOOD) ");
        }
    }

    public int randomEvent(int p, Button btn_left, Button btn_right, TextView txt_main, ImageView img_main) {
        int random = rand.nextInt(4);
        switch(random) {
            case 0:
                txt_main.setText("A poorly equipped thief block the way. He demands money and food to pass.");
                btn_left.setText("Handover the goods");
                btn_right.setText("Ignore");
                img_main.setImageResource(R.drawable.thief);
                break;
            case 1:
                txt_main.setText(players[p].getName()+" was scavenging for food when he found some bright red berries hanging from a bush.");
                btn_left.setText("Eat the berries");
                btn_right.setText("Run Away");
                img_main.setImageResource(R.drawable.bush);
                break;
            case 2:
                txt_main.setText(players[p].getName()+" encounters a group of highly trained ninjas, they look intimidating");
                btn_left.setText("Fight the ninjas");
                btn_right.setText("Show off six-pack abs");
                img_main.setImageResource(R.drawable.sneak);
                break;
            case 3:
                txt_main.setText(players[p].getName()+" stumbles upon a wild dog, it appears to be friendly");
                btn_left.setText("Pet the Dog");
                btn_right.setText("Run");
                img_main.setImageResource(R.drawable.doggo);
                break;
            case 4:
                txt_main.setText("A passing priest wishes to give you his blessing");
                btn_left.setText("Accept blessing");
                btn_right.setText("Reject blessing");
                img_main.setImageResource(R.drawable.priest);
                break;
        }
        return random;
    }
    public void minorRandomEvent(int p,int p2, TextView txt_minorevent, TextView txt_details) {
        String str = "";
        switch(rand.nextInt(15)) {
            case 0:
                txt_minorevent.setText(players[p].getName()+" trips on a rock and cuts his leg");
                str += players[p].addHp(-10);
                break;
            case 1:
                txt_minorevent.setText(players[p].getName()+" and "+players[p2].getName()+" run into each other and fall to the ground");
                str += players[p].addHp(-5);
                str += players[p2].addHp(-5);
                break;
            case 2:
                txt_minorevent.setText(players[p].getName()+" does a little tap dance");
                str += players[p].addSanity(5);
                str += addProgress(5);
                break;
            case 3:
                txt_minorevent.setText(players[p].getName()+" accidentally falls off a cliff");
                str += players[p].addHp(-80);
                break;
            case 4:
                txt_minorevent.setText(players[p].getName()+" soils their pants");
                str += players[p].addSanity(-15);
                break;
            case 5:
                txt_minorevent.setText(players[p].getName()+" forgot to breathe and passed out");
                str += addProgress(-5);
                str += players[p].addHp(-20);
                break;
            case 6:
                txt_minorevent.setText(players[p].getName()+" sneezes on "+players[p2].getName());
                str += players[p].giveDisease(3);
                break;
            case 7:
                txt_minorevent.setText(players[p].getName()+" finds some spare change on the ground");
                str += addMoney(100);
                str += addProgress(5);
                break;
            case 8:
                txt_minorevent.setText(players[p].getName()+" contemplates the meaning of life");
                str += players[p].addSanity(5);
                str += addProgress(5);
                break;
            case 9:
                txt_minorevent.setText(players[p].getName()+" sniffs some nearby roses. Their face begins to swell up.");
                str += players[p].giveDisease(3);
                break;
            case 10:
                txt_minorevent.setText(players[p].getName()+" lets out a loud fart and attracts a wild bear");
                str += players[p].addHp(-70);
                break;
            case 11:
                txt_minorevent.setText(players[p].getName()+" decides to take a nap.");
                str += addProgress(-5);
                break;
            case 12:
                txt_minorevent.setText(players[p].getName()+" picks his nose intensely");
                str += addProgress(3);
                break;
            case 13:
                txt_minorevent.setText(players[p].getName()+" chokes on a passing mosquito");
                str += players[p].addHp(-5);
                break;
            case 14:
                txt_minorevent.setText("A strange beam radiates from the heavens and lifts "+players[p].getName()+" into the sky");
                str += players[p].setMia(2);
                players[p].makeAlien();
                break;
            case 15:
                txt_minorevent.setText(players[p].getName()+" took a wrong path ang got lost");
                str += players[p].setMia(5);
                break;
            case 16:
                txt_minorevent.setText(players[p].getName()+" got bored and left");
                str += players[p].setMia(1);
                str += addProgress(5);
                break;
            case 17:
                txt_minorevent.setText(players[p].getName()+" runs through a thorn bush");
                str += players[p].addHp(-10);
                break;
            case 18:
                txt_minorevent.setText(players[p].getName()+" saw "+players[p2].getName()+" pick his nose");
                str += players[p2].addSanity(-20);
                break;
        }
        str += checkSanity()+checkHealth();

        txt_details.setText(str);
    }

    public String checkSanity() {
        int count = 0;
        for(int i = 0;i<3;i++) {
            if(getPlayer(i).getSanity()<60 && getPlayer(i).isAvailable()) {
                count++;
            }
        }
        if(count != 0) {
            return "\n" + addProgress(-count) + " DUE TO INSANITY";
        }
        return "";
    }

    public String checkHealth() {
        String str = "\n";
        for(int i = 0;i<3;i++) {
            if(getPlayer(i).hasDisease() && getPlayer(i).isAvailable()) {
                str += getPlayer(i).addHp(-5);
            }
        }
        if(!str.equals("\n")) {
            str += " DUE TO ILLNESS ";
        }
        return str;
    }
}