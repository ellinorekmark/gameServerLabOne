package se.iths.gameserverlabone;

public class Guess {


    public String a;
    public String b;
    public String c;

    public void setGame(String game) {
        this.game = game;
    }

    public String d;

    public void setA(String a) {
        this.a = a;
    }

    public void setB(String b) {
        this.b = b;
    }

    public void setC(String c) {
        this.c = c;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getGame() {
        return game;
    }

    public String game;

    public Guess(String a, String b, String c, String d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public Guess() {
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }
}
