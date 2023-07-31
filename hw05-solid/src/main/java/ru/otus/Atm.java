package ru.otus;

public class Atm implements CashMashineInterface{
    @Override
    public void putCashToATM(int amount) {
        NoteProcessor noteProcessor = new NoteProcessor();
        int remainAmount = 0;
        if (amount >= 1000){
            remainAmount = noteProcessor.calculateNote(amount, new OneThousendRubNotes());
        }
        if (remainAmount != 0){
            remainAmount = noteProcessor.calculateNote(remainAmount, new FiveHundredRubNotes());
        }
        if (remainAmount != 0){
            remainAmount = noteProcessor.calculateNote(remainAmount, new OneHundredRubNotes());
        }
        if (remainAmount != 0){
            System.out.println("Купюра не может быть принята");
        }

    }

    @Override
    public void getCashFromATM(int amount) {

    }

    @Override
    public int accountBalance() {
        return 0;
    }
}
