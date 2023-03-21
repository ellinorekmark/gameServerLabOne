package se.iths.gameserverlabone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UserRepository rep;


    public User setPlayer(String username) {
        User player;
        List<User> users = rep.findByName(username);
        if (users.size() > 0) {
            player = users.get(0);

        } else {
            player = rep.save(new User(username));
        }
        return player;
    }

    public User updateUser(User user) {
        return (rep.save(user));
    }


    ArrayList<String> getGlobalHighScore() {
        ArrayList<String> highScores = new ArrayList<>();
        List<User> all = rep.findAll();

        for (User u : all) {
            if(u.scores != null && u.scores.size() > 0) {
                highScores.add(u.getName() + " - " + u.getPlayerHighScore());
            }
        }
        return highScores;
    }



    public ArrayList<PlayerAverage> getGlobalAverage() {
        ArrayList<String> highScores = new ArrayList<>();
        List<User> all = rep.findAll();
        ArrayList<PlayerAverage> playerAverages = new ArrayList<>();
        for (User u : all) {
            if(u.scores != null && u.scores.size() > 0) {
                playerAverages.add(new PlayerAverage(u.name, Double.parseDouble(u.getPlayerAverage())));
            }
        }

        playerAverages.sort(Comparator.comparingDouble(s -> s.average));



        return playerAverages;
    }
}
