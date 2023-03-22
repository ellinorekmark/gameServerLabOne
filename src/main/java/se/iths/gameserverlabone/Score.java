package se.iths.gameserverlabone;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Score {

    @Id
    @GeneratedValue
    Long id;
    int points;
    public Score() {
    }
    public Score(int points) {
    this.points = points;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
}
