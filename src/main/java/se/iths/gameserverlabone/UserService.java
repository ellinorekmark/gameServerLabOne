package se.iths.gameserverlabone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UserRepository rep;

    public User setPlayer(String username) throws Exception {
        User player;
        if(rep.existsByName(username)){
            throw new Exception("User already exists");
        }
        else {
            player = rep.save(new User(username));
        }
        return player;
    }



    public User updateUser(User user) {
        return (rep.save(user));
    }


    ArrayList<PlayerPoints> getGlobalHighScore() {
        ArrayList<PlayerPoints> playerPoints = new ArrayList<>();
        List<User> all = rep.findAll();

        for (User u : all) {
            if(u.scores != null && u.scores.size() > 0) {
                playerPoints.add(new PlayerPoints(u.name, Double.parseDouble(u.getPlayerHighScore())));
            }
        }
        playerPoints.sort(Comparator.comparingDouble(s -> s.points));
        return playerPoints;
    }

    public ArrayList<PlayerPoints> getGlobalAverage() {
        List<User> all = rep.findAll();
        ArrayList<PlayerPoints> playerPoints = new ArrayList<>();
        for (User u : all) {
            if(u.scores != null && u.scores.size() > 0) {
                playerPoints.add(new PlayerPoints(u.name, Double.parseDouble(u.getPlayerAverage())));
            }
        }
        playerPoints.sort(Comparator.comparingDouble(s -> s.points));
        return playerPoints;
    }

    public List<String> getAllUsernames() {
        List<User> users = rep.findAll();
        ArrayList<String> usernames = new ArrayList<>();
        for (User u: users) {
            usernames.add(u.getName());
        }
        return usernames;
    }

    public List<User> getAll() {
        return rep.findAll();
    }
}
