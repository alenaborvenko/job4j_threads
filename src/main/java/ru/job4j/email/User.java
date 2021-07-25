package ru.job4j.email;

public class User {
    private final String name;
    private final String email;

    private User(String name, String email) {
        this.email = email;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
