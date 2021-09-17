package by.atm.domain;

import java.util.Set;

public class Atm {
    private int balance;
    private Set<String> cards;

    public Atm() {

    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Set<String> getCards() {
        return cards;
    }

    public void setCards(Set<String> cards) {
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
