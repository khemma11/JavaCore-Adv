package storage;

import exception.ModelNotFoundException;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getPhonNumber(), user);
    }

    public User getUserByPhonNumberAndByPassword(String phonNumber, String password) throws ModelNotFoundException {
        for (User user : users.values()) {
            if (user.getPhonNumber().equals(phonNumber) && user.getPassword().equals(password)) {
                return user;
            }

        }
        throw new ModelNotFoundException(String.format("User with %s phonNumber and $s password does not exist", phonNumber, password));

    }

    public User getUserByPhonNumber(String phonNumber) throws ModelNotFoundException {
        for (User user : users.values()) {
            if (user.getPhonNumber().equals(phonNumber)) {
                return user;
            }
        }
        throw new ModelNotFoundException(String.format("user with %s phonNumber does not exist", phonNumber));
    }

    public boolean isEmpti() {
        return users.isEmpty();
    }
}
