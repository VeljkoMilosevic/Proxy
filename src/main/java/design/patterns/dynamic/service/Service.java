package design.patterns.dynamic.service;

import design.patterns.dynamic.domain.User;

public interface Service {

    void handle(final User user);
}
