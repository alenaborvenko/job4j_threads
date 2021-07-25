package userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage implements Storage {
    @GuardedBy("this")
    private final Map<Integer, User> userStorage = new HashMap<>();

    @Override
    public synchronized boolean add(User user) {
        userStorage.putIfAbsent(user.getId(), user);
        return true;
    }

    @Override
    public synchronized boolean update(User user) {
        return userStorage.put(user.getId(), user) != null;
    }

    @Override
    public synchronized boolean delete(User user) {
        return userStorage.remove(user.getId()) != null;
    }

    @Override
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User userFrom = userStorage.get(fromId);
        User userTo = userStorage.get(toId);
        if (userFrom == null || userFrom.getAmount() < amount || userTo == null) {
            return false;
        }
        userFrom.setAmount(userFrom.getAmount() - amount);
        userTo.setAmount(userTo.getAmount() + amount);
        return update(userFrom) && update(userTo);
    }
}
