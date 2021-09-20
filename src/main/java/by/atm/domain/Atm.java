package by.atm.domain;

import java.util.Map;
import java.util.Set;

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
}
