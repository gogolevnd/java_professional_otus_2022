package ru.otus.banknote;

import lombok.Getter;
import lombok.Setter;

@Getter
public class BankNote {

    int noteValue;

    int noteCount;

    int amount;

    public BankNote(int noteValue, int amount){
        this.noteValue = noteValue;
        this.amount = amount;
        this.noteCount = getNoteCount(amount);
    }

    private int getNoteCount(int amount) {
        return amount / noteValue;
    }

    @Override
    public String toString() {
        return String.valueOf(noteValue);
    }

}
