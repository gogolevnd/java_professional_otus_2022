package ru.otus.ioc;

import ru.otus.annotations.Log;
import ru.otus.logging.TestLogging;
import ru.otus.logging.TestLoggingInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Ioc {

    private Ioc() {
    }

    public static TestLoggingInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface testLogging;

        DemoInvocationHandler(TestLoggingInterface testLogging) {
            this.testLogging = testLogging;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (testLogging.getClass().getMethod(method.getName(), method.getParameterTypes()).isAnnotationPresent(Log.class)){
                System.out.println("executed method: " + method.getName() + ", " + "param: " + Arrays.toString(method.getParameters()));
                return testLogging.getClass().getMethod(method.getName(), method.getParameterTypes()).invoke(testLogging, args);
            } else {
                return testLogging.getClass().getMethod(method.getName(), method.getParameterTypes()).invoke(testLogging, args);
            }

        }
    }
}
