package se.iths.gameserverlabone;

public class GuessResult {

    public String getGuess() {
        return guess;
    }
    public int getBulls() {
        return Bulls;
    }
    public int getCows() {
        return Cows;
    }
    public GuessResult(String guess, int bulls, int cows) {
        this.guess = guess;
        Bulls = bulls;
        Cows = cows;
    }
    public String guess;
    public int Bulls;
    public int Cows;
}
