package ru.otus;

import ru.otus.logging.TestLoggingInterface;

import static ru.otus.ioc.Ioc.createMyClass;

public class Demo {
    public static void main(String[] args) {
        TestLoggingInterface testLoggingInterface = createMyClass();
        testLoggingInterface.calculation(6);
        testLoggingInterface.calculation(6, "Hello");
        testLoggingInterface.calculation("Hello", 6);
        testLoggingInterface.calculation("Hello", "World");
        testLoggingInterface.calculation(1,2,3,4,5,6);

    }
}
