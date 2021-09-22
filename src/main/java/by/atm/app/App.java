package by.atm.app;

import by.atm.domain.Atm;
import by.atm.domain.Card;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Atm atm = new Atm();
        atm.startAtm();
        atm.enterCard();
        atm.turnOffAtm();
    }
}
