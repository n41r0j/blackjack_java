package BlackJack.view;

import BlackJack.model.rules.AbstractRulesFactory;

public class SimpleView implements IView {

    public void displayWelcomeMessage() {
        for (int i = 0; i < 50; i++) {
            System.out.print("\n");
        }
        System.out.println("Hello Black Jack World");
        System.out.println("Type 'p' to play or 'q' to Quit\n");
    }

    @Override
    public void displayInstructions() {
        System.out.println("Type 'h' to hit, 's' to stand or 'q' to Quit\n");

    }

    public GameInput getInput() {
        try {
            int c = System.in.read();
            while (c == '\r' || c == '\n') {
                c = System.in.read();
            }
            switch (c) {
                case 'p':
                    return GameInput.PLAY;
                case 'h':
                    return GameInput.HIT;
                case 's':
                    return GameInput.STAND;
                case 'q':
                    return GameInput.QUIT;
            }
        } catch (java.io.IOException e) {
            System.out.println("" + e);
            return GameInput.NONE;
        }
        return GameInput.NONE;
    }

    public void displayCard(BlackJack.model.Card a_card) {
        System.out.println("" + a_card.getValue() + " of " + a_card.getColor());
    }

    public void displayPlayerHand(Iterable<BlackJack.model.Card> a_hand, int a_score) {
        displayHand("Player", a_hand, a_score);
    }

    public void displayDealerHand(Iterable<BlackJack.model.Card> a_hand, int a_score) {
        displayHand("Dealer", a_hand, a_score);
    }

    private void displayHand(String a_name, Iterable<BlackJack.model.Card> a_hand, int a_score) {
        System.out.println(a_name + " Has: ");
        for (BlackJack.model.Card c : a_hand) {
            displayCard(c);
        }
        System.out.println("Score: " + a_score);
        System.out.println("");
    }

    public void displayGameOver(boolean a_dealerIsWinner) {
        System.out.println("GameOver: ");
        if (a_dealerIsWinner) {
            System.out.println("Dealer Won!");
        } else {
            System.out.println("You Won!");
        }

    }

    @Override
    public void displayRulesOfGame(AbstractRulesFactory rules) {
        DutchAbstractRuleVisitor ruleVisitor = new DutchAbstractRuleVisitor();

        System.out.println("\nGame rules:\n");
        ruleVisitor.visit(rules.getHitRule());
        ruleVisitor.visit(rules.getGameWinRule());
        ruleVisitor.visit(rules.getNewGameRule());

    }
}
