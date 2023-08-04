package ru.otus.banknote;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BankNote {

    int noteValue;
    int noteCount;
    int amount;

    @Override
    public String toString() {
        return String.valueOf(noteValue);
    }

}
