package edu.udea.financial.system.controllers;

import edu.udea.financial.system.entities.users.User;
import edu.udea.financial.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        if (userService.userExists(id)) {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        }
        return userNotFound(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUserById(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        ResponseEntity<?> response;
        if (userService.userExists(id)) {
            try {
                response = new ResponseEntity<>(userService.patchUserById(id, updates), HttpStatus.OK);
            } catch (Exception e) {
                response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            return response;
        }
        return userNotFound(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        if (userService.userExists(id)) {
            return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
        }
        return userNotFound(id);
    }

    private ResponseEntity<?> userNotFound(Long id) {
        return new ResponseEntity<>("No se encontró el usuario con ID: " + id, HttpStatus.NOT_FOUND);
    }

}
