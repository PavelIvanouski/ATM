package by.atm.domain;

import by.atm.exceptions.AtmLoadException;
import by.atm.util.DateUtil;
import by.atm.util.SerializationUtil;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Atm implements Serializable {
    private static Scanner scanner;
    private double balance;
    private Map<String, Card> cards;
    private static final double DEPOSIT_MAX_BOUND = 1000000.0;
    private static final long serialVersionUID = 1L;

    public Atm() {
        scanner = new Scanner(System.in);
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

    public static Atm startAtm() throws AtmLoadException {
        Object deserializedObject = SerializationUtil.deserializeObject(SerializationUtil.FILENAME);
        Atm atm = new Atm();
        if (deserializedObject instanceof Atm) {
            atm = (Atm) deserializedObject;
            System.out.println("Atm info loaded.");
        }
        if ((atm.getBalance() == 0) || (atm.getCards() == null)) {
            throw new AtmLoadException("Atm load failed!");
        }
        return atm;
    }

    public static Atm startAtmManually() {
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

        Atm atm = new Atm();
        atm.setBalance(1000000);
        Map<String, Card> cardMap = new HashMap<>();
        cardMap.put(card1.getNumber(), card1);
        cardMap.put(card2.getNumber(), card2);
        cardMap.put(card3.getNumber(), card3);
        atm.setCards(cardMap);
        return atm;
    }

    public boolean validateCardNumber(String cardNumber, Map<String, Card> amtCards) {
        String regex = "^(\\d{4}\\-){3}\\d{4}$";
        return cardNumber.matches(regex) && amtCards.containsKey(cardNumber);
    }

    public boolean authorize(Card card) {
        String enteredData = "";
        if ((card.getLockingDate() != null)
                && (DateUtil.returnTwoDateDifferenceInHours(card.getLockingDate(), LocalDateTime.now()) < 24)) {
            Menu.showCardBlockingMessage(card);
            return false;
        }
        for (int attempt = 3; attempt > 0; attempt--) {
            System.out.println("Please, enter PIN...(Number of attempts: " + attempt + " )");
            enteredData = scanner.nextLine();
            if (card.getPin().equals(enteredData.trim())) {
                System.out.println("Correct PIN entered!");
//                transactions(card);
                return true;
//                break;
            } else {
                if (attempt == 1) {
                    card.setLockingDate(LocalDateTime.now());
                    Menu.showCardBlockingMessage(card);
                } else {
                    System.out.println("Invalid PIN entered!");
                }
            }
        }
        return false;
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
        double amountToWithdraw = 0;
        do {
            System.out.println("Enter an amount to withdraw (>0) : ");
            try {
                amountToWithdraw = scanner.nextDouble();
            } catch (InputMismatchException e) {
                e.printStackTrace();
                System.out.println("Try again.");
                scanner.next();
            }
        } while (amountToWithdraw <= 0);
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
        System.out.println("The withdraw was successful.");
        showBalance(card);
    }

    public void deposit(Card card) {
        double amountToDeposit = 0;
        do {
            System.out.println("Enter an amount to deposit (>0): ");
            try {
                amountToDeposit = scanner.nextDouble();
            } catch (InputMismatchException e) {
                e.printStackTrace();
                System.out.println("Try again.");
                scanner.next();
            }
        } while (amountToDeposit <= 0);
        scanner.nextLine();
        double currentAtmBalance = this.getBalance();
        double currentCardBalance = card.getBalance();
        if (amountToDeposit > DEPOSIT_MAX_BOUND) {
            System.out.println("Sorry! Deposit should be less then " + DEPOSIT_MAX_BOUND);
            return;
        }
        currentCardBalance += amountToDeposit;
        currentAtmBalance += amountToDeposit;
        this.setBalance(currentAtmBalance);
        card.setBalance(currentCardBalance);
        System.out.println("The deposit was successful.");
        showBalance(card);
    }

    public void turnOffAtm() {
        System.out.println("Pressed 'x'.");
        SerializationUtil.serializeObject(this, SerializationUtil.FILENAME);
        System.out.println("Information has been saved.");
    }

}
