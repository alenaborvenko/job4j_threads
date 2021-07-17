package userstorage;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserStorageTest {

    @Test
    public void whenTransferOK() throws InterruptedException {
        Storage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        storage.add(user1);
        storage.add(user2);
        Thread first = new ThreadUserStorage(storage);
        Thread second = new ThreadUserStorage(storage);
        first.start();
        second.start();
        first.join();
        second.join();
        user1.setAmount(0);
        user2.setAmount(300);
        assertThat(storage.findAll(), is(List.of(
                user1,
                user2
        )));
    }

    @Test
    public void whenUpdate() {
        Storage storage = new UserStorage();
        User user1 = new User(1, 100);
        storage.add(user1);
        User user2 = new User(1, 200);
        storage.update(user2);
        assertThat(storage.findAll().get(0), is(user2));
    }

    @Test
    public void whenDelete() {
        Storage storage = new UserStorage();
        User user1 = new User(1, 100);
        storage.add(user1);
        storage.delete(user1);
        assertThat(storage.findAll(), is(Collections.emptyList()));
    }

    private static class ThreadUserStorage extends Thread {
        private final Storage storage;

        private ThreadUserStorage(final Storage userStorage) {
            this.storage = userStorage;
        }

        @Override
        public void run() {
            this.storage.transfer(1, 2, 50);
        }
    }
}