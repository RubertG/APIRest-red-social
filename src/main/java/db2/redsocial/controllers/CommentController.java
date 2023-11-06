package db2.redsocial.controllers;

import db2.redsocial.entities.Comment;
import db2.redsocial.models.CommentResponse;
import db2.redsocial.models.Message;
import db2.redsocial.services.CommentService;
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
@RequestMapping("/comments")
@CrossOrigin(origins = "*")
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    
    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.consult();
    }
    
        @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentResponse> addComment(@RequestBody Comment c) {
        if (commentService.add(c)) {
            CommentResponse response = new CommentResponse(c.getIdComment(), c.getContent(), c.getCreationDate(), c.getIdOwner().getIdUser(), c.getIdParent().getIdPost());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer id) {
        Message message;
        if (commentService.delete(id)) {
            message = new Message(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
            return ResponseEntity.ok(message);
        } else {
            message = new Message(HttpStatus.NOT_FOUND.value(), "Comment not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateComment(@Validated @RequestBody Comment p) {
        Message message;
        commentService.update(p);
        message = new Message(HttpStatus.OK.value(), "Comment updated successfully");
        return ResponseEntity.ok(message);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/lenght")
    public Long recordLenght() {
        return commentService.recordsLenght();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Optional<Comment> getComment(@PathVariable Integer id) {
        return commentService.search(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "user/{id}")
    public List<Comment> getPostsByIdUser(@PathVariable Integer id) {
        return commentService.searchCommentsByIdUser(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "post/{id}")
    public List<Comment> getPostsByIdPost(@PathVariable Integer id) {
        return commentService.searchCommentsByIdPost(id);
    }
}
