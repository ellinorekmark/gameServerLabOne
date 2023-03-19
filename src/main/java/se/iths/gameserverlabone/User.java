package se.iths.gameserverlabone;

import jakarta.persistence.*;

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

    public void setName(String name) {
        this.name = name;
    }

    public void addScore(Score points) {
        if(scores == null){
            scores = List.of(points);
        }
        else {
            scores.add(points);
        }
    }
}
