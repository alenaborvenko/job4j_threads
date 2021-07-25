package ru.job4j.ref;

import java.util.List;

public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache userCache = new UserCache();
        User user = User.of("name");
        userCache.add(user);
        userCache.add(User.of("new user"));
        Thread first = new Thread(
                () -> user.setName("rename")
        );
        Thread second = new Thread(
                () -> {
                    List<User> findAll = userCache.findAll();
                    findAll.get(1).setName("rename two");
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
        userCache.findAll().forEach(System.out::println);
    }
}
