package userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage implements Storage {
    @GuardedBy("this")
    private final List<User> userStorage = new ArrayList<>();

    @Override
    public synchronized boolean add(User user) {
        return userStorage.add(user);
    }

    @Override
    public synchronized boolean update(User user) {
        int index = findIndex(user.getId());
        if (index == -1) {
            return false;
        }
        userStorage.set(index, user);
        return true;
    }

    private synchronized int findIndex(int id) {
        for (int i = 0; i < userStorage.size(); i++) {
            if (userStorage.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public synchronized boolean delete(User user) {
        int index = findIndex(user.getId());
        if (index == -1) {
            return false;
        }
        userStorage.remove(index);
        return true;
    }

    @Override
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        int indexFrom = findIndex(fromId);
        int indexTo = findIndex(toId);
        if (indexFrom == -1 || indexTo == -1) {
            return false;
        }
        User userFrom = userStorage.get(indexFrom);
        User userTo = userStorage.get(indexTo);
        int amountFrom = userFrom.getAmount();
        if (amountFrom < amount) {
            return false;
        }
        userFrom.setAmount(amountFrom - amount);
        userTo.setAmount(userTo.getAmount() + amount);
        return true;
    }

    @Override
    public synchronized List<User> findAll() {
        return userStorage;
    }
}
