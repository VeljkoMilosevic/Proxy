package design.patterns.dynamic.handler;

import design.patterns.dynamic.service.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandler implements InvocationHandler {

    private final Service service;

    public MyHandler(final Service service) {
        this.service = service;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        System.out.println("Invoking proxy service");
        method.invoke(service, args);
        System.out.println("Finished proxy service");
        return true;
    }
}
