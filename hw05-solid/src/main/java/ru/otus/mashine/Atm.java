package ru.otus.mashine;

import lombok.extern.slf4j.Slf4j;
import ru.otus.processor.BankNoteCellInterface;

@Slf4j
public class Atm implements CashMashineInterface {
    private final BankNoteCellInterface bankNoteCellInterface;

    public Atm(BankNoteCellInterface bankNoteCellInterface) {
        this.bankNoteCellInterface = bankNoteCellInterface;
    }

    @Override
    public void putCashToATM(int amount) {
        bankNoteCellInterface.processNotes(amount);
    }

    @Override
    public void getCashFromATM(int amount) {
        bankNoteCellInterface.removeNotes(amount);
    }

    @Override
    public void noteBalance() {
        bankNoteCellInterface.cellInfo();
    }
}
