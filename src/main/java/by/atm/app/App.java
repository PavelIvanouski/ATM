package by.atm.app;

import by.atm.domain.Atm;
import by.atm.domain.Card;
import by.atm.exceptions.AtmLoadException;

import java.time.LocalDateTime;
import java.util.HashMap;
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
        while (!enteredData.equals("x")) {
            System.out.println("Please, enter card number... (\'x\' to close the app)");
            enteredData = scanner.nextLine();
            if (atm.validateCardNumber(enteredData, atmCards)) {
                currentCard = atmCards.get(enteredData);
                if (atm.authorize(currentCard)) {
                    atm.transactions(currentCard);
                }
            } else if (!enteredData.equals("x")) {
                System.out.println("Invalid data! Try again.");
            }
        }
        atm.turnOffAtm();
    }
}
