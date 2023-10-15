package edu.udea.financial.system.services;

import edu.udea.financial.system.entities.users.User;
import edu.udea.financial.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User patchUserById(Long id, Map<String, String> updates) {

        User user = getUserById(id);

        updates.forEach((key, value) -> {
            switch (key) {
                case "dni" -> user.setDni(value);
                case "name" -> user.setName(value);
                case "email" -> user.setEmail(value);
                case "password" -> user.setPassword(value);
                case "role" -> user.setRole(User.Role.valueOf(value));
                default -> throw new IllegalArgumentException("Campo de actualización no válido: " + key);
            }
        });
        userRepository.save(user);
        return user;
    }

    public String deleteUserById(Long id) {
        userRepository.deleteById(id);
        return "El usuario con ID: " + id + " se ha eliminado";
    }

    public boolean userExists(Long id) {
        return userRepository.existsById(id);
    }
}
