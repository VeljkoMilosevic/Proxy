package design.patterns.dynamic.service;

import design.patterns.dynamic.domain.User;

public class ServiceImpl implements Service {

    @Override
    public void handle(final User user) {
        System.out.println(String.format("Handling user '%s'!", user));
    }
}
