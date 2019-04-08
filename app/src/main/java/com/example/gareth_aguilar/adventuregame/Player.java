package com.example.gareth_aguilar.adventuregame;

import java.io.Serializable;
import java.util.Random;


public class Player implements Serializable {
    private int hp, sanity;
    private String name;
    private boolean mia;
    private int miaTimer;
    private boolean alien;
    private boolean[] illness;
    private Random rand;
    /*
    Illness 0 - Nothing
    Illness 1 - measles
    Illness 2 - smallpox
    Illness 3 - hypothermia
    Illness 4 - diarrhea
    */

    public Player(String n) {
        hp = 100;
        sanity = 100;
        name = n;
        mia = false;
        miaTimer = 0;
        alien = false;
        illness = new boolean[5];
        rand = new Random();
    }

    public String removeRandomIllness() {
        int r = rand.nextInt(5);
        if(illness[r] == true) {
            illness[r] = false;
            return getName()+" no longer has: "+getDiseaseName(r);
        }
        return "";
    }

    public boolean hasDisease() {
        for(int i=0;i<illness.length;i++) {
            if(illness[i] == true) {
                return true;
            }
        }
        return false;
    }

    public boolean isAlien() {
        return alien;
    }

    public boolean isAvailable() {
        return (getHp()>0 && mia == false);
    }

    public String getName() {
        return name;
    }

    public String setMia(int n) {
        mia = true;
        miaTimer = n;
        return getName()+" has left the party";
    }

    public void makeAlien() {
        alien = true;
    }

    public String subtractTimer() {
        if(miaTimer == 0) {
            return "";
        }

        miaTimer --;
        if(miaTimer<=0) {
            mia = false;
            miaTimer = 0;
            if(alien) {
                return getName()+" has returned, though he looks a little different";
            }
            return getName()+" has returned to the party";
        }
        return "";
    }

    public String addHp(int d) {
        hp += d;
        if(hp<=0) {
            hp = 0;
            return getName()+" died ";
        }
        if(d>0) {
            return (getName() + ": (" + d + " HP) ");
        } else {
            return (getName() + ": (" + d + " HP) ");
        }
    }
    public String addSanity(int d) {
        sanity += d;
        if (d > 0) {
            return (getName() + ": (+" + d + " SANITY) ");
        } else {
            return (getName() + ": (" + d + " SANITY) ");
        }
    }

    public int getSanity() {
        return sanity;
    }

    public int getHp() {
        return hp;
    }

    public String giveDisease(int d) {
        if(illness[d] == false) {
            illness[d] = true;
            return (getName()+" has been diagnosed with: "+getDiseaseName(d));
        }
        return (getName()+" already has "+getDiseaseName(d)+"!");
    }


    public String getDiseaseName(int d) {
        int disease = d;
        switch(disease) {
            case 0:
                return "";

            case 1:
                return "Measles";

            case 2:
                return "Smallpox";

            case 3:
                return "Hypothermia";

            case 4:
                return "diarrhea";
        }
        return "";
    }
    public String toString() {
        return getName()
                +"\nHP: "+hp
                +"\nSanity: "+sanity
                +"\nStatus: ";
    }
}