package db2.redsocial.controllers;

import db2.redsocial.entities.User;
import db2.redsocial.models.Message;
import db2.redsocial.services.UserService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<User> getAllUsers() {
        return userService.consult();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Optional<User> getUser(@PathVariable Integer id) {
        return userService.search(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@Validated @RequestBody User user) {
        if (userService.add(user)) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        Message message;
        if (userService.delete(id)) {
            message = new Message(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
            return ResponseEntity.ok(message);
        } else {
            message = new Message(HttpStatus.NOT_FOUND.value(), "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@Validated @RequestBody User user) {
        Message message;
        userService.update(user);
        message = new Message(HttpStatus.OK.value(), "User updated successfully");
        return ResponseEntity.ok(message);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/lenght")
    public Long recordsLenght() {
        return userService.recordsLenght();
    }
}
