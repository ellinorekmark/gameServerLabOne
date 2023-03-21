package se.iths.gameserverlabone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@SessionScope
public class Game {

    ArrayList<Integer> answer = new ArrayList<>();
    ArrayList<String> previousGuesses = new ArrayList<>();
    ArrayList<String> lastGameGuesses = new ArrayList<>();

    User player;
    @Autowired
    UserService service;

    public User getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = service.setPlayer(player);
    }

    public Game() {
        newGame();

    }

    private void generateAnswers() {
        while (answer.size() < 4) {
            int x = new Random().nextInt(0, 10);
            if (!answer.contains(x)) {
                answer.add(x);
                System.out.println(x);
            }
        }
    }


    public String guess(Guess g) {
        ArrayList<Integer> guess = new ArrayList<>();

        try {
            guess.add(Integer.parseInt(g.getA()));
            guess.add(Integer.parseInt(g.getB()));
            guess.add(Integer.parseInt(g.getC()));
            guess.add(Integer.parseInt(g.getD()));
        } catch (NumberFormatException e) {
            return "Invalid guess, try again.";
        }

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                if (guess.get(i).equals(guess.get(j))) {
                    return "Invalid guess, duplicate digits. Try again.";
                }
            }
        }


        int bullCount = 0;
        int cowCount = 0;
        StringBuilder guesses = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            guesses.append(guess.get(i));
            if (answer.contains(guess.get(i))) {
                if (answer.get(i).equals(guess.get(i))) {
                    bullCount++;
                } else {
                    cowCount++;
                }
            }
        }
        String result = " B: " + bullCount + "  C: " + cowCount;
        guesses.append(result);
        previousGuesses.add(guesses.toString());

        if (bullCount == 4) {
            int totalGuesses = previousGuesses.size();
            //checkHighScore();
            updateUserStats();
            newGame();
            return "Success! You got it in " + totalGuesses + " guesses.";
        } else {
            return result;
        }
    }

    private void updateUserStats() {
        Score newScore = new Score(previousGuesses.size());
        //newScore.setUser(player);
        player.addScore(newScore);
        player = service.updateUser(player);

    }

    void newGame() {
        answer.clear();
        lastGameGuesses = previousGuesses;
        previousGuesses.clear();
        generateAnswers();
    }

    public ArrayList<String> getPastGuesses() {
        if (previousGuesses.isEmpty()) {
            return lastGameGuesses;
        } else {
            return previousGuesses;
        }
    }

    String localHighscore(){
        return player.getPlayerHighScore();
    }
    String averageScore(){
        return player.getPlayerAverage();
    }

    List<String> globalScore(){
        return service.getGlobalHighScore();
    }

    public List<PlayerAverage> globalAverage() {
        return service.getGlobalAverage();
    }
}
