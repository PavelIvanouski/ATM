package main.java.by.atm.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Menu {

    public static void showCardBlockingMessage(Card card) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Card " + card.getNumber() + " was blocked at " + card.getLockingDate().format(formatter));
        System.out.println("Try after " + card.getLockingDate().plusHours(24).format(formatter));
    }

    public static void showTransactionMenu() {
        System.out.println("1 : Check Balance");
        System.out.println("2 : Withdraw");
        System.out.println("3 : Deposit");
        System.out.println("4 : EXIT");
        System.out.println("Enter your choice...");
    }

}
