package BlackJack;

import BlackJack.model.Game;
import BlackJack.model.rules.AmericanRuleFactory;
import BlackJack.model.rules.BasicHard17RuleFactory;
import BlackJack.view.*;
import BlackJack.controller.*;

public class Program {

    public static void main(String[] a_args) {
        IView v;

        if (a_args.length > 0 && a_args[0].equals("nl")) {
            v = new DutchView();
        } else {
            v = new SimpleView();
        }
        Game g = new Game(new BasicHard17RuleFactory());
        PlayGame ctrl = new PlayGame(g, v);

        while (ctrl.play(g, v)) ;

    }
}