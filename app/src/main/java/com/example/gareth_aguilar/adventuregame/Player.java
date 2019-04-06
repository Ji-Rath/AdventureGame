package com.example.gareth_aguilar.adventuregame;

import java.io.Serializable;

public class Player implements Serializable {
    private int hp, stamina, sanity;
    private String name;
    private boolean[] illness = new boolean[5];
    /*
    Illness 0 - Nothing
    Illness 1 - measles
    Illness 2 - smallpox
    Illness 3 - hypothermia
    Illness 4 - diarrhea
    */

    public Player(String n) {
        hp = 100;
        stamina = 100;
        sanity = 100;
        name = n;
    }

    public String getName() {
        return name;
    }

    public String damagePlayer(int d) {
        hp -= d;
        return (getName()+": (-"+d+" HP) ");
    }

    public void giveDisease(int d) {
        if(illness[d] == false) {
            illness[d] = true;
            System.out.println(getName()+" has been diagnosed with: "+getDiseaseName(d));
        }
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
        return "Stats for "+getName()
                +"\nHP: "+hp
                +"\nSanity: "+sanity
                +"\nStamina: "+stamina;
    }
}