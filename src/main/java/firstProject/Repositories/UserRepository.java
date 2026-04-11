package firstProject.Repositories;

import java.util.ArrayList;
import java.util.List;

import firstProject.Models.User;

public class UserRepository {

    private final List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
        users.add(new User(1L, "Ali", "Mammadov"));
        users.add(new User(2L, "Nigar", "Aliyeva"));
        users.add(new User(3L, "Murad", "Hasanov"));
        users.add(new User(4L, "Leyla", "Karimova"));
        users.add(new User(5L, "Orxan", "Quliyev"));
    }

    public List<User> getAllUsersList() {
        return users;
    }

    public User getUserById(Long id) {
        User user = null;
        for (User u : users) {
            if (u.getId().equals(id)) {
                user = u;
                break;
            }
        }
        return user;
    }
}
