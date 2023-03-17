package se.iths.gameserverlabone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Service
@SessionScope
public class UserService {
    @Autowired
    UserRepository rep;


    public User setPlayer(String username) {
        User player;
        List<User> users = rep.findByName(username);
        if (users.size()>0){
           player = users.get(0);

        }
        else{
            player = rep.save(new User(username));
        }
        return player;
    }
}
