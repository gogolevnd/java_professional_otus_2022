package ru.otus.logging;

public interface TestLoggingInterface {
    void calculation(int param);
    void calculation(String param1, int param2);
    void calculation(int param1, String param2);
    void calculation(Object... params);
}
