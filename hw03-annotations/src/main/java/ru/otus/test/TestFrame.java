package ru.otus.test;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class TestFrame {

    @Before
    public void beforeTest(){
        System.out.println("Run beforeAnnotation test");
    }

    @Test
    public void test(){
        System.out.println("Run testAnnotation test");
    }

    @After
    public void afterTest() throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }

    @Before
    public void beforeTest1() throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }

    @Test
    public void test1(){
        throw new RuntimeException();
    }

    @After
    public void afterTest1(){
        System.out.println("Run afterAnnotation test");
    }
}
