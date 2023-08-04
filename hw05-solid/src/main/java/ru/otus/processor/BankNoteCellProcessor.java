package ru.otus.processor;

import lombok.extern.slf4j.Slf4j;
import ru.otus.banknote.BankNote;
import ru.otus.banknote.FiveHundredRubNotes;
import ru.otus.banknote.OneHundredRubNotes;
import ru.otus.banknote.OneThousendRubNotes;

import java.util.*;

@Slf4j
public class BankNoteCellProcessor implements BankNoteCellInterface {
    public Queue<BankNote> bankNotes = new LinkedList<>();
    private OneThousendRubNotes oneThousendRubNotes;
    private FiveHundredRubNotes fiveHundredRubNotes;
    private OneHundredRubNotes oneHundredRubNotes;
    private Queue<BankNote> listOfOneThousendRubNotes = new LinkedList<>();
    private Queue<BankNote> listOfFiveHundredRubNotes = new LinkedList<>();
    private Queue<BankNote> listOfOneHundredRubNotes = new LinkedList<>();

    @Override
    public void processNotes(int amount) {
        if (amount == 0) {
            log.info("Вставьте купюры в автомат");
        } else {
            if (amount >= 1000) {
                setNoteClass(new OneThousendRubNotes(amount));
                addNote(oneThousendRubNotes);
                amount = calculateAmount(oneThousendRubNotes);
            }
            if (amount >= 500) {
                setNoteClass(new FiveHundredRubNotes(amount));
                addNote(fiveHundredRubNotes);
                amount = calculateAmount(fiveHundredRubNotes);
            }
            if (amount >= 100) {
                setNoteClass(new OneHundredRubNotes(amount));
                addNote(oneHundredRubNotes);
                amount = calculateAmount(oneHundredRubNotes);
            }
            if (amount != 0) {
                log.info("Нет купюр на сумму {} рублей", amount);
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
                amount = deleteNote(amount, listOfOneThousendRubNotes, oneThousendRubNotes);
            }
            if (amount >= 500) {
                amount = deleteNote(amount, listOfFiveHundredRubNotes, fiveHundredRubNotes);
            }
            if (amount >= 100) {
                amount = deleteNote(amount, listOfOneHundredRubNotes, oneHundredRubNotes);
            }
            if (amount != 0) {
                log.info("Аппарат не может принимать купюры на сумму {} рублей", amount);
            }
        }
    }

    @Override
    public void cellInfo() {
        getNoteInfo();
    }

    private void setNoteClass(BankNote bankNote) {
        if (bankNote instanceof OneThousendRubNotes) {
            oneThousendRubNotes = (OneThousendRubNotes) bankNote;
        }
        if (bankNote instanceof FiveHundredRubNotes) {
            fiveHundredRubNotes = (FiveHundredRubNotes) bankNote;
        }
        if (bankNote instanceof OneHundredRubNotes) {
            oneHundredRubNotes = (OneHundredRubNotes) bankNote;
        }
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

    private int deleteNote(int amount, Queue<BankNote> list, BankNote bankNote) {
        int num = 0;
        for (int i = 0; i <= list.size(); i++) {
            BankNote element = list.element();
            if (element.equals(bankNote) && amount >= bankNote.getNoteValue()) {
                list.remove();
                amount -= bankNote.getNoteValue();
                bankNote.setAmount(amount);
                num += 1;
            }
        }
        log.info("Из ячейки изъято {} купюр(а/ы) номиналом {} рублей", num, bankNote);
        return amount;

    }

    private void getNoteInfo() {
        if (listOfOneThousendRubNotes.size() == 0 && listOfFiveHundredRubNotes.size() == 0 && listOfOneHundredRubNotes.size() == 0) {
            log.info("В ячейке нет доступных для выдачи купюр");
        } else {
            if (listOfOneThousendRubNotes.size() != 0) {
                log.info("В ячейке {} купюр(а/ы) номиналом {} рублей", listOfOneThousendRubNotes.size(), oneThousendRubNotes.getNoteValue());
            }
            if (listOfOneThousendRubNotes.size() == 0) {
                log.info("В ячейке нет купюр номиналом 1000 рублей");
            }
            if (listOfFiveHundredRubNotes.size() != 0) {
                log.info("В ячейке {} купюр(а/ы) номиналом {} рублей", listOfFiveHundredRubNotes.size(), fiveHundredRubNotes.getNoteValue());
            }
            if (listOfFiveHundredRubNotes.size() == 0) {
                log.info("В ячейке нет купюр номиналом 500 рублей");
            }
            if (listOfOneHundredRubNotes.size() != 0) {
                log.info("В ячейке {} купюр(а/ы) номиналом {} рублей", listOfOneHundredRubNotes.size(), oneHundredRubNotes.getNoteValue());
            }
            if (listOfOneHundredRubNotes.size() == 0) {
                log.info("В ячейке нет купюр номиналом 100 рублей");
            }
        }
    }

    private void collectCellInfo() {
        for (BankNote bankNote : bankNotes) {
            if (bankNote instanceof OneThousendRubNotes) {
                listOfOneThousendRubNotes.add(bankNote);
            }
            if (bankNote instanceof FiveHundredRubNotes) {
                listOfFiveHundredRubNotes.add(bankNote);
            }
            if (bankNote instanceof OneHundredRubNotes) {
                listOfOneHundredRubNotes.add(bankNote);
            }
        }
    }
}
