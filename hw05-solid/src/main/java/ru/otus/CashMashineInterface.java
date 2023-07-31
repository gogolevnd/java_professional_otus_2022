package ru.otus;

public interface CashMashineInterface {
    void putCashToATM(int amount);
    void getCashFromATM(int amount);

    int accountBalance();
}
