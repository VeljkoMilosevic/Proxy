package design.patterns.dynamic.domain;

public class User {

    private String name;
    private String username;

    public User(final String name, final String username) {
        this.name = name;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
