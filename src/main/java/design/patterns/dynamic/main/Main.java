package design.patterns.dynamic.main;

import design.patterns.dynamic.domain.User;
import design.patterns.dynamic.handler.MyHandler;
import design.patterns.dynamic.service.Service;
import design.patterns.dynamic.service.ServiceImpl;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        Service service = new ServiceImpl();

        Service proxyService = (Service)
                Proxy.newProxyInstance(service.getClass().getClassLoader(),
                        new Class[]{Service.class},
                        new MyHandler(service));

        User user = new User("John", "Doe");

        proxyService.handle(user);
    }
}
