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
    String loginPage(Model m){
        return "loginpage";
    }
    @PostMapping("/startgame")
    String logInAction(Model m, @RequestParam String username){
        game.setPlayer(username);
        m.addAttribute("guess", new Guess());
        m.addAttribute("pastGuesses", game.getPastGuesses());
        m.addAttribute("playerHighScore", game.localHighscore());
        m.addAttribute("playerAverage", game.averageScore());
        m.addAttribute("globalScores", game.globalScore());
        m.addAttribute("globalAverage", game.globalAverage());

        return "game";
    }

    @PostMapping("/game")
    String playGame(Model m, @ModelAttribute("guess") Guess guess){

        m.addAttribute("player", game.getPlayer());
        m.addAttribute("outcome", game.guess(guess));
        m.addAttribute("pastGuesses", game.getPastGuesses());
        m.addAttribute("user", game.getPlayer().getName());
        m.addAttribute("playerHighScore", game.localHighscore());
        m.addAttribute("playerAverage", game.averageScore());
        m.addAttribute("globalScores", game.globalScore());
        m.addAttribute("globalAverage", game.globalAverage());

        return "game";
    }





}
