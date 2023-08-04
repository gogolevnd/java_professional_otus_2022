package ru.otus.banknote;

public class OneThousendRubNotes extends BankNote {

    private static final int ONE_THOUSEND = 1000;

    public OneThousendRubNotes(int amount) {
        super();
        super.setAmount(amount);
        super.setNoteCount(getNoteCount(amount));
        super.setNoteValue(ONE_THOUSEND);
    }

    private int getNoteCount(int amount) {
        return amount / ONE_THOUSEND;
    }

}
