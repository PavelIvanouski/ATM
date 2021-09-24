package main.java.by.atm.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Card implements Serializable {
    private String number;
    private String pin;
    private double balance;
    private LocalDateTime lockingDate;
    private static final long serialVersionUID = 1L;

    public static class Builder {

        private String number;
        private String pin;
        private double balance;
        private LocalDateTime lockingDate;

        public Builder withNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder withPin(String pin) {
            this.pin = pin;
            return this;
        }

        public Builder withBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public Card build() {
            Card card = new Card();
            card.number = this.number;
            card.pin = this.pin;
            card.balance = this.balance;
            return card;
        }

    }

    public Card() {

    }

    public Card(String number, String pin, double balance, LocalDateTime lockingDate) {
        this.number = number;
        this.pin = pin;
        this.balance = balance;
        this.lockingDate = lockingDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getLockingDate() {
        return lockingDate;
    }

    public void setLockingDate(LocalDateTime lockingDate) {
        this.lockingDate = lockingDate;
    }

    @Override
    public String toString() {
        return "Card{" +
                "number='" + number + '}';
    }

}
