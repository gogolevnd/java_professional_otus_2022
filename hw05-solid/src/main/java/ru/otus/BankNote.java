package ru.otus;

public class BankNote {

    int noteValue;

    public void setNoteValue(int noteValue){
        this.noteValue = noteValue;
    }

    public int getNoteValue(){
        return noteValue;
    }

    @Override
    public String toString(){
        return "Купюра номиналом " + noteValue;
    }

}
