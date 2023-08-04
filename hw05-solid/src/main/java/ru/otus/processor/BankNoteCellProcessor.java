package ru.otus.processor;

import lombok.extern.slf4j.Slf4j;
import ru.otus.banknote.BankNote;

import java.util.*;

@Slf4j
public class BankNoteCellProcessor implements BankNoteCellInterface {
    public Queue<BankNote> bankNotes = new LinkedList<>();
    private Queue<BankNote> listOfOneThousendRubNotes = new LinkedList<>();
    private Queue<BankNote> listOfFiveHundredRubNotes = new LinkedList<>();
    private Queue<BankNote> listOfOneHundredRubNotes = new LinkedList<>();

    @Override
    public void processNotes(int amount) {
        if (amount == 0) {
            log.info("Вставьте купюры в автомат");
        } else {
            if (amount >= 1000) {
                BankNote oneThousendBankNote = new BankNote(1000, amount);
                addNote(oneThousendBankNote);
                amount = calculateAmount(oneThousendBankNote);
            }
            if (amount >= 500) {
                BankNote fiveHundredBankNote = new BankNote(500, amount);
                addNote(fiveHundredBankNote);
                amount = calculateAmount(fiveHundredBankNote);
            }
            if (amount >= 100) {
                BankNote oneHundredBankNote = new BankNote(100, amount);
                addNote(oneHundredBankNote);
                amount = calculateAmount(oneHundredBankNote);
            }
            if (amount != 0) {
                log.info("Аппарат не принимает купюры на сумму {} рублей", amount);
            }
            collectCellInfo();
        }
    }

    @Override
    public void removeNotes(int amount) {
        if (amount == 0) {
            log.info("Введите сумму для выдачи");
        } else {
            if (amount >= 1000) {
                amount = deleteNote(amount, listOfOneThousendRubNotes);
            }
            if (amount >= 500) {
                amount = deleteNote(amount, listOfFiveHundredRubNotes);
            }
            if (amount >= 100) {
                amount = deleteNote(amount, listOfOneHundredRubNotes);
            }
            if (amount != 0) {
                log.info("Нет купюр на сумму {} рублей", amount);
            }
        }
    }

    @Override
    public void cellInfo() {
        getNoteInfo();
    }

    private void addNote(BankNote bankNote) {
        int i;
        for (i = 0; i < bankNote.getNoteCount(); i++) {
            bankNotes.add(bankNote);
        }
        log.info("В ячейку добавлено {} купюр(а/ы) номиналом {} рублей", i, bankNote);
    }

    private int calculateAmount(BankNote bankNote) {
        int amount = bankNote.getAmount();
        if (amount >= bankNote.getNoteValue()) {
            amount = bankNote.getAmount() % bankNote.getNoteValue();
        }
        return amount;
    }

    private int deleteNote(int amount, Queue<BankNote> list) {
        int num = 0;
        int noteValue = list.element().getNoteValue();
        for (int i = 0; i <= list.size(); i++) {
            if (amount >= noteValue) {
                list.remove();
                amount -= noteValue;
                num += 1;
            }
        }
        log.info("Из ячейки изъято {} купюр(а/ы) номиналом {} рублей", num, noteValue);
        return amount;

    }

    private void getNoteInfo() {
        if (listOfOneThousendRubNotes.size() == 0 && listOfFiveHundredRubNotes.size() == 0 && listOfOneHundredRubNotes.size() == 0) {
            log.info("В ячейке нет доступных для выдачи купюр");
        } else {
            if (listOfOneThousendRubNotes.size() != 0) {
                log.info("В ячейке {} купюр(а/ы) номиналом {} рублей", listOfOneThousendRubNotes.size(), listOfOneThousendRubNotes.element().getNoteValue());
            }
            if (listOfOneThousendRubNotes.size() == 0) {
                log.info("В ячейке нет купюр номиналом 1000 рублей");
            }
            if (listOfFiveHundredRubNotes.size() != 0) {
                log.info("В ячейке {} купюр(а/ы) номиналом {} рублей", listOfFiveHundredRubNotes.size(), listOfFiveHundredRubNotes.element().getNoteValue());
            }
            if (listOfFiveHundredRubNotes.size() == 0) {
                log.info("В ячейке нет купюр номиналом 500 рублей");
            }
            if (listOfOneHundredRubNotes.size() != 0) {
                log.info("В ячейке {} купюр(а/ы) номиналом {} рублей", listOfOneHundredRubNotes.size(), listOfOneHundredRubNotes.element().getNoteValue());
            }
            if (listOfOneHundredRubNotes.size() == 0) {
                log.info("В ячейке нет купюр номиналом 100 рублей");
            }
        }
    }

    private void collectCellInfo() {
        for (BankNote bankNote : bankNotes) {
            if (bankNote.getNoteValue() == 1000) {
                listOfOneThousendRubNotes.add(bankNote);

            }
            if (bankNote.getNoteValue() == 500) {
                listOfFiveHundredRubNotes.add(bankNote);
            }
            if (bankNote.getNoteValue() == 100) {
                listOfOneHundredRubNotes.add(bankNote);
            }
        }
        bankNotes.removeAll(listOfOneThousendRubNotes);
        bankNotes.removeAll(listOfFiveHundredRubNotes);
        bankNotes.removeAll(listOfOneHundredRubNotes);
    }
}
