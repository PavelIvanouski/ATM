package by.atm.app;

import by.atm.domain.Atm;
import by.atm.domain.Card;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
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
        Map<String, Card> cardMap = new HashMap<>();
        cardMap.put(card1.getNumber(),card1);
        cardMap.put(card2.getNumber(),card2);
        cardMap.put(card3.getNumber(),card3);
        atm.setCards(cardMap);
        System.out.println("Welcome to AMT Vacanda-bank");

        Map<String, Card> amtCards = atm.getCards();

        Scanner scanner = new Scanner(System.in);
        String pressedKey = "";
        while (!pressedKey.equals("x")) {
            System.out.println("Please, enter card number... (\'x\' to close the app)");
            pressedKey = scanner.nextLine();
            if (amtCards.containsKey(pressedKey)) {
                Card currentCard = amtCards.get(pressedKey);
                System.out.println("1");
            } else if (!"x".equals(pressedKey)){
                System.out.println("Invalid data!");
            }
//            switch (pressedKey) {
//                case "1":
//                    while (!pressedKey.equals("p")) {
//                        System.out.println();
//                        System.out.println("Please, enter card number... (\'p\' to previous menu)");
//                        pressedKey = scanner.nextLine();
//                        if (atm.getCards().containsKey(pressedKey)) {
//                            System.out.println("1");
//                        }
//                    }
//                    break;
//                case "x":
//                    System.out.println("Pressed 'x'. Exit...");
//                    break;
//                default:
//                    System.out.println("Invalid command");
//                    break;
//            }
        }

    }
}
