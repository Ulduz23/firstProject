package firstProject.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
}
