package by.atm.app;

import by.atm.domain.Atm;
import by.atm.domain.Card;

import java.util.Set;

public class App {
    public static void main(String[] args) {

        Card.Builder builder1 = new Card.Builder();
        builder1.withNumber("1111-1111-1111-1111");
        builder1.withPin(1111);
        builder1.withBalance(100000);
        Card card1 = builder1.build();

        Card.Builder builder2 = new Card.Builder();
        builder2.withNumber("2222-2222-2222-2222");
        builder2.withPin(2222);
        builder2.withBalance(200000);
        Card card2 = builder2.build();

        Card.Builder builder3 = new Card.Builder();
        builder3.withNumber("3333-3333-3333-3333");
        builder3.withPin(3333);
        builder3.withBalance(300000);
        Card card3 = builder3.build();

        Atm atm = new Atm();
        atm.setBalance(11000000);
        atm.setCards(Set.of(card1.getNumber(), card2.getNumber(), card3.getNumber()));

    }
}
