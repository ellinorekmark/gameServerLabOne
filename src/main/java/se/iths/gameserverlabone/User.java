package se.iths.gameserverlabone;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    Long id;
    String name;

    @OneToMany(cascade = CascadeType.ALL)
    List<Score> scores;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addScore(Score points) {
        if(scores == null){
            scores = new ArrayList<>();
            scores.add(points);
        }
        else {
            scores.add(points);
        }
    }

        String getPlayerHighScore() {

            if (scores == null || scores.isEmpty()) {
                return "No games played.";
            }
            else if(scores.size() == 1){
                return ""+ scores.get(0).getPoints();
            }
            else {
                Score highScore = scores.get(0);
                for (int i = 0; i < scores.size(); i++) {
                    if (highScore.getPoints()> scores.get(i).getPoints()){
                        highScore = scores.get(i);
                    }
                }

                return "" + highScore.getPoints();

            }
        }
        String getPlayerAverage(){
            if (scores == null || scores.isEmpty()){
                return "No games played.";
            }
            else{
                int total = 0;
                for (Score score: scores) {
                    total += score.getPoints();
                }
                int average = total/(scores.size());
                return ""+average;
            }
        }
}
