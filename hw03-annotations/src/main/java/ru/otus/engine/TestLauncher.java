package ru.otus.engine;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TestLauncher {
    private final ArrayList<Method> beforeAnnotationMethods = new ArrayList<>();
    private final ArrayList<Method> testAnnotationMethods = new ArrayList<>();
    private final ArrayList<Method> afterAnnotationMethods = new ArrayList<>();

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    public final String ANSI_PURPLE = "\u001B[35m";

    private final ArrayList<TestResult> results = new ArrayList<>();

    private int testPassed = 0;
    private int testFailed = 0;
    private int testSkipped = 0;
    private int testRunned = 0;

    public TestLauncher(Class<?> clazz){
        run(clazz);
    }

    private static Object getInstanceOfClass(Class<?> clazz) {
        Object object;
        try {
            object = clazz.getConstructor(null).newInstance(null);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException exception) {
            throw new RuntimeException(exception);
        }
        return object;

    }

    private Queue<ArrayList<Method>> getQueueWithMethods(Class<?> clazz) {
        Queue<ArrayList<Method>> queue = new LinkedList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Before.class)) {
                beforeAnnotationMethods.add(method);

            }
            if (method.isAnnotationPresent(Test.class)) {
                testAnnotationMethods.add(method);

            }
            if (method.isAnnotationPresent(After.class)) {
                afterAnnotationMethods.add(method);

            }

        }
        queue.add(beforeAnnotationMethods);
        queue.add(testAnnotationMethods);
        queue.add(afterAnnotationMethods);
        return queue;
    }

    private ArrayList<TestResult> invokeMethod(Object object, Queue<ArrayList<Method>> queue) {
        boolean isSuccess = true;
        for (ArrayList<Method> list : queue) {
            for (Method method : list) {
                if (method.isAnnotationPresent(After.class)){
                    isSuccess = true;
                }
                if (isSuccess) {
                    try {
                        method.invoke(object);
                        testPassed++;
                        testRunned++;
                        results.add(new TestResult(ANSI_GREEN, method.getName(), TestResultStatus.PASSED, null, ANSI_RESET, testPassed, testFailed, testSkipped, testRunned));

                    } catch (IllegalAccessException | InvocationTargetException exception) {
                        testFailed++;
                        testRunned++;
                        results.add(new TestResult(ANSI_RED, method.getName(), TestResultStatus.ERROR, exception.getCause(), ANSI_RESET, testPassed, testFailed, testSkipped, testRunned));
                        if (method.isAnnotationPresent(Before.class)) {
                            isSuccess = false;
                        }
                    }
                }
                else {
                    testSkipped++;
                    testRunned++;
                    results.add(new TestResult(ANSI_PURPLE, method.getName(), TestResultStatus.SKIPPED, new Throwable("SKIPPED DUE BEFORE FAIL"), ANSI_RESET, testPassed, testFailed, testSkipped, testRunned));
                }
            }
        }
        return results;
    }

    private void run(Class<?> clazz){
        for (TestResult result : invokeMethod(getInstanceOfClass(clazz), getQueueWithMethods(clazz))) {
            System.out.println(result);
        }
    }

}
