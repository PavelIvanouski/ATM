package by.atm.domain;

import by.atm.util.DateUtil;
import by.atm.util.SerializationUtil;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Atm implements Serializable {
    private static Scanner scanner;
    private double balance;
    private Map<String, Card> cards;
    private static final long serialVersionUID = 1L;

    public Atm() {

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
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

        Object deserializedObject = SerializationUtil.deserializeObject(SerializationUtil.FILENAME);
        Atm test = new Atm();
        if (deserializedObject instanceof Atm) {
             test = (Atm) deserializedObject;
            System.out.println("Atm info loaded.");
        }

        Card.Builder builder1 = new Card.Builder();
        builder1.withNumber("1111-1111-1111-1111");
        builder1.withPin("1111");
        builder1.withBalance(1000);
        Card card1 = builder1.build();

        Card.Builder builder2 = new Card.Builder();
        builder2.withNumber("2222-2222-2222-2222");
        builder2.withPin("2222");
        builder2.withBalance(2000);
        Card card2 = builder2.build();

        Card.Builder builder3 = new Card.Builder();
        builder3.withNumber("3333-3333-3333-3333");
        builder3.withPin("3333");
        builder3.withBalance(3000);
        Card card3 = builder3.build();
        card3.setLockingDate(LocalDateTime.now().minusDays(1));

//        Atm atm = new Atm();
        this.setBalance(100000);
//        atm.setBalance(11000000);
        Map<String, Card> cardMap = new HashMap<>();
        cardMap.put(card1.getNumber(), card1);
        cardMap.put(card2.getNumber(), card2);
        cardMap.put(card3.getNumber(), card3);
        this.setCards(cardMap);
//        atm.setCards(cardMap);
//        return atm;
//        enterCard();
    }

    public void enterCard() {
        scanner = new Scanner(System.in);
        String enteredData = "";
        while (!enteredData.equals("x")) {
            System.out.println("Please, enter card number... (\'x\' to close the app)");
            enteredData = scanner.nextLine();
            Map<String, Card> amtCards = this.getCards();
            if (validateCardNumber(enteredData, amtCards)) {
                authorize(amtCards.get(enteredData));
            } else if (!enteredData.equals("x")) {
                System.out.println("Invalid data! Try again.");
            }
        }
    }

    public boolean validateCardNumber(String cardNumber, Map<String, Card> amtCards) {
        String regex = "^(\\d{4}\\-){3}\\d{4}$";
        return cardNumber.matches(regex) && amtCards.containsKey(cardNumber);
    }

    public void authorize(Card card) {
        String enteredData = "";
        if ((card.getLockingDate() != null)
                && (DateUtil.returnTwoDateDifferenceInHours(card.getLockingDate(), LocalDateTime.now()) < 24)) {
            Menu.showCardBlockingMessage(card);
            return;
        }
        for (int attempt = 3; attempt > 0; attempt--) {
            System.out.println("Please, enter PIN...(Number of attempts: " + attempt + " )");
            enteredData = scanner.nextLine();
            if (card.getPin().equals(enteredData.trim())) {
                System.out.println("Correct PIN entered!");
                transactions(card);
                break;
            } else {
                if (attempt == 1) {
                    card.setLockingDate(LocalDateTime.now());
                    Menu.showCardBlockingMessage(card);
                } else {
                    System.out.println("Invalid PIN entered!");
                }
            }
        }
    }

    public void transactions(Card card) {
        String enteredData = "";
        while (!enteredData.equals("4")) {
            Menu.showTransactionMenu();
            enteredData = scanner.nextLine();
            switch (enteredData) {
                case "1":
                    showBalance(card);
                    break;
                case "2":
                    withdraw(card);
                    break;
                case "3":
                    deposit(card);
                    break;
                case "4":
                    System.out.println("Thank you.");
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }

        }
    }

    public void showBalance(Card card) {
        System.out.println("Current balance : " + card.getBalance());
    }

    public void withdraw(Card card) {
        System.out.println("Enter an amount to withdraw: ");
        double amountToWithdraw = scanner.nextDouble();
        scanner.nextLine();
        double currentAtmBalance = this.getBalance();
        double currentCardBalance = card.getBalance();
        if (amountToWithdraw > currentCardBalance) {
            System.out.println("Sorry! Insufficient  card funds.");
            return;
        }
        if (amountToWithdraw > currentAtmBalance) {
            System.out.println("Sorry! Insufficient  atm funds.");
            return;
        }
        currentCardBalance -= amountToWithdraw;
        currentAtmBalance -= amountToWithdraw;
        this.setBalance(currentAtmBalance);
        card.setBalance(currentCardBalance);
        System.out.println("Please, take your money");
        showBalance(card);
    }

    public void deposit(Card card) {
        System.out.println("Before");
        System.out.println("Card balance: " + card.getBalance());
        System.out.println("Atm balance: " + this.getBalance());
        System.out.println("Enter an amount to deposit: ");
        double amountToDeposit = scanner.nextDouble();
        scanner.nextLine();
        double currentAtmBalance = this.getBalance();
        double currentCardBalance = card.getBalance();
        double depositMaxBound = 1000000.0;
        if (amountToDeposit > depositMaxBound) {
            System.out.println("Sorry! Deposit should bee less then " + depositMaxBound);
            return;
        }
        currentCardBalance += amountToDeposit;
        currentAtmBalance += amountToDeposit;
        this.setBalance(currentAtmBalance);
        card.setBalance(currentCardBalance);
        System.out.println("After");
        System.out.println("Card balance: " + card.getBalance());
        System.out.println("Atm balance: " + this.getBalance());

    }

    public void turnOffAtm() {
        System.out.println("Pressed 'x'.");
        SerializationUtil.serializeObject(this,SerializationUtil.FILENAME);
        System.out.println("Information has been saved.");
    }

}
