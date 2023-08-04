package ru.otus;

import org.junit.jupiter.api.Test;
import ru.otus.mashine.Atm;
import ru.otus.mashine.CashMashineInterface;
import ru.otus.processor.BankNoteCellProcessor;

public class AtmTest {

    @Test
    public void testAddCashToAtm(){
        CashMashineInterface atm = new Atm(new BankNoteCellProcessor());
        atm.putCashToATM(2600);
        atm.noteBalance();
    }

    @Test
    public void testAdd50CashToAtm(){
        CashMashineInterface atm = new Atm(new BankNoteCellProcessor());
        atm.putCashToATM(50);
        atm.noteBalance();
    }

    @Test
    public void testAdd0CashToAtm(){
        CashMashineInterface atm = new Atm(new BankNoteCellProcessor());
        atm.putCashToATM(0);
        atm.noteBalance();
    }

    @Test
    public void testAdd1650CashToAtm(){
        CashMashineInterface atm = new Atm(new BankNoteCellProcessor());
        atm.putCashToATM(1650);
        atm.noteBalance();
    }

    @Test
    public void testGetCashFromAtm(){
        CashMashineInterface atm = new Atm(new BankNoteCellProcessor());
        atm.putCashToATM(2600);
        atm.noteBalance();
        atm.getCashFromATM(2600);
        atm.noteBalance();
    }

    @Test
    public void testGetPartOfCashFromAtm(){
        CashMashineInterface atm = new Atm(new BankNoteCellProcessor());
        atm.putCashToATM(2600);
        atm.noteBalance();
        atm.getCashFromATM(100);
        atm.noteBalance();
    }

    @Test
    public void testGet160OfCashFromAtm(){
        CashMashineInterface atm = new Atm(new BankNoteCellProcessor());
        atm.putCashToATM(2600);
        atm.noteBalance();
        atm.getCashFromATM(1600);
        atm.noteBalance();
    }

    @Test
    public void testGet700fCashFromAtm(){
        CashMashineInterface atm = new Atm(new BankNoteCellProcessor());
        atm.putCashToATM(2600);
        atm.noteBalance();
        atm.getCashFromATM(700);
        atm.noteBalance();
    }
}
