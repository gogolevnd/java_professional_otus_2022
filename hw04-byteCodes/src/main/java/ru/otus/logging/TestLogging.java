package ru.otus.logging;

import ru.otus.annotations.Log;

import java.util.Arrays;

public class TestLogging implements TestLoggingInterface{
    @Override
    @Log
    public void calculation(int param) {

    }

    @Override
    @Log
    public void calculation(String param1, int param2) {

    }

    @Override
    public void calculation(int param1, String param2) {

    }

    @Override
    @Log
    public void calculation(Object... params) {
        System.out.println("Executed calculation with: " + Arrays.toString(Arrays.stream(params).toArray()));

    }
}
