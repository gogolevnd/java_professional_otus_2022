package ru.otus.banknote;

public class FiveHundredRubNotes extends BankNote {

    private static final int FIVE_HUNDRED = 500;

    public FiveHundredRubNotes(int amount) {
        super();
        super.setAmount(amount);
        super.setNoteCount(getNoteCount(amount));
        super.setNoteValue(FIVE_HUNDRED);
    }

    private int getNoteCount(int amount) {
        return amount / FIVE_HUNDRED;
    }
}
