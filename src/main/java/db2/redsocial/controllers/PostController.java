package db2.redsocial.controllers;

import db2.redsocial.entities.Post;
import db2.redsocial.models.Message;
import db2.redsocial.models.PostResponse;
import db2.redsocial.services.PostService;
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
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postRepo;

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepo.consult();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostResponse> addPost(@RequestBody Post p) {
        if (postRepo.add(p)) {
            PostResponse response = new PostResponse(p.getIdPost(), p.getTitle(), p.getContent(), p.getCreationDate(), p.getIdOwner().getIdUser());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Integer id) {
        Message message;
        if (postRepo.delete(id)) {
            message = new Message(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
            return ResponseEntity.ok(message);
        } else {
            message = new Message(HttpStatus.NOT_FOUND.value(), "Post not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePost(@Validated @RequestBody Post p) {
        Message message;
        postRepo.update(p);
        message = new Message(HttpStatus.OK.value(), "Post updated successfully");
        return ResponseEntity.ok(message);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/lenght")
    public Long recordLenght() {
        return postRepo.recordsLenght();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Optional<Post> getPost(@PathVariable Integer id) {
        return postRepo.search(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "user/{id}")
    public List<Post> getPostsByIdUser(@PathVariable Integer id) {
        return postRepo.searchRpByIdUser(id);
    }
}
