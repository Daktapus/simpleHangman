package com.example.benjamindamore.a155891hangman;

public class ListItemObject {
    public int highscore;
    public String ord;
    public int antalForkerteGæt;

    public ListItemObject(int highscore, String ord, int antalForkerteGæt) {
        this.highscore = highscore;
        this.ord = ord;
        this.antalForkerteGæt = antalForkerteGæt;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public String getOrd() {
        return ord;
    }

    public void setOrd(String ord) {
        this.ord = ord;
    }

    public int getAntalForkerteGæt() {
        return antalForkerteGæt;
    }

    public void setAntalForkerteGæt(int antalForkerteGæt) {
        this.antalForkerteGæt = antalForkerteGæt;
    }

    @Override
    public String toString() {
        return "ListItemObject{" +
                "highscore=" + highscore +
                ", ord='" + ord + '\'' +
                ", antalForkerteGæt=" + antalForkerteGæt +
                '}';
    }
}