package by.atm.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Atm {
    private int balance;
    private Map<String, Card> cards;

    public Atm() {

    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Map<String, Card> getCards() {
        return cards;
    }

    public void setCards(Map<String, Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Atm{" +
                "balance=" + balance +
                ", cards=" + cards +
                '}';
    }

    public void startAtm() {
        System.out.println("Welcome to Vacanda-bank Atm");
        //deserialization
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

//        Atm atm = new Atm();
        this.setBalance(11000000);
//        atm.setBalance(11000000);
        Map<String, Card> cardMap = new HashMap<>();
        cardMap.put(card1.getNumber(), card1);
        cardMap.put(card2.getNumber(), card2);
        cardMap.put(card3.getNumber(), card3);
        this.setCards(cardMap);
//        atm.setCards(cardMap);
//        return atm;
        enterCard();
    }

    public void enterCard() {

        Scanner scanner = new Scanner(System.in);
        String enteredData = "";
        while (!enteredData.equals("x")) {
            System.out.println("Please, enter card number... (\'x\' to close the app)");
            enteredData = scanner.nextLine();
            Map<String, Card> amtCards = this.getCards();
            if (validateCardNumber(enteredData, amtCards)) {
                System.out.println("1");
                checkPIN(amtCards.get(enteredData),scanner);
            } else if (!enteredData.equals("x")) {
                System.out.println("Invalid data! Try again.");
            }
        }

//        while (!enteredData.equals("x")) {
//            System.out.println("Please, enter card number... (\'x\' to close the app)");
//            enteredData = scanner.nextLine();
//            if (amtCards.containsKey(enteredData)) {
//                Card currentCard = amtCards.get(enteredData);
//                System.out.println("1");
//            } else if (!"x".equals(enteredData)){
//                System.out.println("Invalid data!");
//            }
////            switch (pressedKey) {
////                case "1":
////                    while (!pressedKey.equals("p")) {
////                        System.out.println();
////                        System.out.println("Please, enter card number... (\'p\' to previous menu)");
////                        pressedKey = scanner.nextLine();
////                        if (atm.getCards().containsKey(pressedKey)) {
////                            System.out.println("1");
////                        }
////                    }
////                    break;
////                case "x":
////                    System.out.println("Pressed 'x'. Exit...");
////                    break;
////                default:
////                    System.out.println("Invalid command");
////                    break;
////            }
//        }
    }

    public boolean validateCardNumber(String cardNumber, Map<String, Card> amtCards) {
        String regex = "^(\\d{4}\\-){3}\\d{4}$";
        return cardNumber.matches(regex) && amtCards.containsKey(cardNumber);
    }

    public void checkPIN(Card card, Scanner scanner) {
        String enteredData = "";

    }

    public void turnOffAtm() {
        System.out.println("Pressed 'x'.");
        //serialization
        System.out.println("Information has been saved.");
    }

}
