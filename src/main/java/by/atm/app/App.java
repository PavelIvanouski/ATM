package main.java.by.atm.app;

import main.java.by.atm.domain.Atm;
import main.java.by.atm.domain.Card;
import main.java.by.atm.exceptions.AtmLoadException;

import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Atm atm;
        System.out.println("Welcome to Vacanda-bank");
        try {
            atm = Atm.startAtm();
        } catch (AtmLoadException e) {
            e.printStackTrace();
            atm = Atm.startAtmManually();
        }
        Scanner scanner = new Scanner(System.in);
        String enteredData = "";
        Card currentCard;
        Map<String, Card> atmCards = atm.getCards();
        while (true) {
            System.out.println("Please, enter card number... (\'x\' to close the app)");
            enteredData = scanner.nextLine();
            if ("x".equals(enteredData)) {
                System.out.println("Pressed 'x'.");
                break;
            }
            if (!atm.validateCardNumber(enteredData, atmCards)) {
                System.out.println("Invalid data! Try again.");
                continue;
            }
            currentCard = atmCards.get(enteredData);
            if (!atm.authorize(currentCard)) {
                continue;
            }
            atm.transactions(currentCard);
        }
        atm.turnOffAtm();
    }
}
