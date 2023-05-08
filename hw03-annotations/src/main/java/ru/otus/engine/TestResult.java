package ru.otus.engine;

public class TestResult {
    private final String colorOfResult;
    private final Object method;

    private final TestResultStatus testResultStatus;
    private final Throwable throwable;
    private final String colorToReset;
    private final int testPassed;
    private final int testFailed;
    private final int testSkipped;
    private final int testRunned;



    public TestResult(String colorOfResult, String method, TestResultStatus testResultStatus, Throwable throwable, String colorToReset, int testPassed, int testFailed, int testSkipped, int testRunned) {
        this.colorOfResult = colorOfResult;
        this.method = method;
        this.testResultStatus = testResultStatus;
        this.throwable = throwable;
        this.colorToReset = colorToReset;
        this.testPassed = testPassed;
        this.testFailed = testFailed;
        this.testSkipped = testSkipped;
        this.testRunned = testRunned;
    }

    public String toString() {
        String str = colorOfResult +
                '\n' +
                "Method: " +
                method +
                " Test Status: " +
                testResultStatus +
                " Errors: " +
                throwable +
                " Count of Passed Tests: " +
                testPassed +
                " Count of Failed Tests: " +
                testFailed +
                " Count of Skipped Tests: " +
                testSkipped +
                " Count of Runned Tests: " +
                testRunned +
                '\n' +
                colorToReset;
        return str;
    }


}
