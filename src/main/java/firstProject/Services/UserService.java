package firstProject.Services;

import java.util.List;
import java.util.Optional;

import firstProject.Models.User;
import firstProject.Repositories.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
