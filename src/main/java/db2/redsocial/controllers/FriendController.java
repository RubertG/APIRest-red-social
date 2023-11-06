package db2.redsocial.controllers;

import db2.redsocial.entities.Friend;
import db2.redsocial.models.BasicResponse;
import db2.redsocial.models.Message;
import db2.redsocial.services.FriendService;
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
@RequestMapping("/friends")
@CrossOrigin(origins = "*")
public class FriendController {
    
    @Autowired
    private FriendService friendService;
    
    @GetMapping
    public List<Friend> getAllFriends() {
        return friendService.consult();
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BasicResponse> addFriend(@RequestBody Friend f) {
        if (friendService.add(f)) {
            BasicResponse response = new BasicResponse(f.getIdFriendship(), f.getIdUser1().getIdUser(), f.getIdUser2().getIdUser());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteFriend(@PathVariable Integer id) {
        Message message;
        if (friendService.delete(id)) {
            message = new Message(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
            return ResponseEntity.ok(message);
        } else {
            message = new Message(HttpStatus.NOT_FOUND.value(), "Friend not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateFriend(@Validated @RequestBody Friend p) {
        Message message;
        friendService.update(p);
        message = new Message(HttpStatus.OK.value(), "Friend updated successfully");
        return ResponseEntity.ok(message);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/lenght")
    public Long recordLenght() {
        return friendService.recordsLenght();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Optional<Friend> getFriend(@PathVariable Integer id) {
        return friendService.search(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "user/{id}")
    public List<Friend> getFriendsByIdUser(@PathVariable Integer id) {
        return friendService.searchFriendsByIdUser(id);
    }
}