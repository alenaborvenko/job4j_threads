package userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage implements Storage {
    @GuardedBy("this")
    private final Map<Integer, Integer> userStorage = new HashMap<>();

    @Override
    public synchronized boolean add(User user) {
        userStorage.putIfAbsent(user.getId(), user.getAmount());
        return true;
    }

    @Override
    public synchronized boolean update(User user) {
        return userStorage.put(user.getId(), user.getAmount()) != null;
    }

    @Override
    public synchronized boolean delete(User user) {
        return userStorage.remove(user.getId()) != null;
    }

    @Override
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        int amountFrom = userStorage.get(fromId);
        if (amountFrom < amount || userStorage.get(toId) == null) {
            return false;
        }
        userStorage.put(fromId, amountFrom - amount);
        userStorage.put(toId, userStorage.get(toId) + amount);
        return true;
    }
}
