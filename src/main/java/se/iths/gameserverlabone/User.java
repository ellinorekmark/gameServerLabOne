package se.iths.gameserverlabone;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String name;

    @OneToMany(cascade = CascadeType.REMOVE)
    List<Score> scores;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}
