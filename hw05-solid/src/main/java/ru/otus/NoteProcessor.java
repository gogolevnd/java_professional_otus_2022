package ru.otus;

import java.util.ArrayList;
import java.util.Collection;

public class NoteProcessor {

    int noteCount;
    public Collection<BankNote> bankNotes = new ArrayList<>();

    public void addNote(int count, BankNote bankNote) {
        noteCount = count;
        for (int i = 0; i < count; i++) {
            bankNotes.add(bankNote);
            System.out.println("В ячейку добавили - " + bankNote.toString());
        }
    }

    public void removeNote(BankNote bankNote) {
        bankNotes.remove(bankNote);
        noteCount -= 1;
    }

    public int calculateNote(int amount, BankNote bankNote){
        int remainNotes = 0;
        int noteValue = bankNote.getNoteValue();
        if (amount >= noteValue){
            int numNotes = amount / noteValue;
            remainNotes = amount % noteValue;
            addNote(numNotes, bankNote);
        }
        return remainNotes;

    }
}
