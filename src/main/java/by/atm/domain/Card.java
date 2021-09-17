package by.atm.domain;

import java.time.LocalDateTime;

public class Card {
    private String number;
    private int pin;
    private int balance;
    private LocalDateTime lockingDate;

    public static class Builder {

        private String number;
        private int pin;
        private int balance;
        private LocalDateTime lockingDate;

        public Builder withNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder withPin(int pin) {
            this.pin = pin;
            return this;
        }

        public Builder withBalance(int balance) {
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

    public Card(String number, Integer pin, Integer balance, LocalDateTime lockingDate) {
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

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
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
