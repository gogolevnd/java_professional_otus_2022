package ru.otus.mashine;

public interface CashMashineInterface {
    void putCashToATM(int amount);

    void getCashFromATM(int amount);

    void noteBalance();
}
