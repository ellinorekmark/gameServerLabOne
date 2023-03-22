package se.iths.gameserverlabone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {
    @Autowired
    Game game;

    @GetMapping("/login")
    String login(Model m){
        m.addAttribute("users", game.allUsers());
        return "loginpage";
    }

    @PostMapping("/olduser")
    String logOldUser(Model m, @RequestParam User userSelect){
        m.addAttribute("guess", new Guess());
        game.loginPlayer(userSelect);
        m.addAttribute("game", game);

        return "game";
    }

    @PostMapping("/newuser")
    String logInAction(Model m, @RequestParam String username){

        try {
            game.newPlayer(username);
        } catch (Exception e) {
            m.addAttribute("message", "Username already exists.");
            m.addAttribute("users", game.allUsers());
            return "loginpage";
        }
        m.addAttribute("outcome", "");
        m.addAttribute("game", game);
        m.addAttribute("guess", new Guess());


        return "game";
    }

    @PostMapping("/game")
    String playGame(Model m, @ModelAttribute("guess") Guess guess){
        m.addAttribute("outcome", game.guess(guess));
        m.addAttribute("game", game);

        return "game";
    }
}
