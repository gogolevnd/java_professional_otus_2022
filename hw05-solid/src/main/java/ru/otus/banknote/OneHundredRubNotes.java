package ru.otus.banknote;

public class OneHundredRubNotes extends BankNote {
    private static final int ONE_HUNDRED = 100;

    public OneHundredRubNotes(int amount) {
        super();
        super.setAmount(amount);
        super.setNoteCount(getNoteCount(amount));
        super.setNoteValue(ONE_HUNDRED);
    }

    private int getNoteCount(int amount) {
        return amount / ONE_HUNDRED;
    }
}
