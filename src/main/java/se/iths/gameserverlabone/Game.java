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

    private ArrayList<Integer> answer = new ArrayList<>();
    private ArrayList<GuessResult> previousGuesses = new ArrayList<>();
    private List<PlayerPoints> globalHighScores;
    private List<PlayerPoints> globalAverages;

    User player;
    @Autowired
    UserService service;

    public User getPlayer() {
        return player;
    }

    public void newPlayer(String player) throws Exception {
        globalHighScores = service.getGlobalHighScore();
        globalAverages = service.getGlobalAverage();

        try {
            this.player = service.setPlayer(player);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loginPlayer(User user) {
        globalHighScores = service.getGlobalHighScore();
        globalAverages = service.getGlobalAverage();
        player = user;
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

        previousGuesses.add(new GuessResult(guesses.toString(), bullCount, cowCount));

        if (bullCount == 4) {
            int totalGuesses = previousGuesses.size();
            updateUserStats();
            newGame();
            return "Success! You got it in " + totalGuesses + " guesses.";
        } else {
            return "Bulls: " + bullCount + " Cows: " + cowCount;
        }
    }

    private void updateUserStats() {
        Score newScore = new Score(previousGuesses.size());
        player.addScore(newScore);
        player = service.updateUser(player);
        globalHighScores = service.getGlobalHighScore();
        globalAverages = service.getGlobalAverage();

    }

    void newGame() {
        answer.clear();
        previousGuesses.clear();
        generateAnswers();
    }

    public ArrayList<GuessResult> getPastGuesses() {
        ArrayList<GuessResult> reverse = new ArrayList<>();
        for (int i = previousGuesses.size() - 1; i >= 0; i--) {
            reverse.add(previousGuesses.get(i));
        }
        return reverse;
    }

    public String localHighScore() {
        return player.getPlayerHighScore();
    }

    public String averageScore() {
        return player.getPlayerAverage();
    }

    public List<PlayerPoints> globalScore() {
        return globalHighScores;
    }

    public List<PlayerPoints> globalAverage() {
        return globalAverages;
    }

    public List<User> allUsers() {
        return service.getAll();
    }
}
