package db2.redsocial.controllers;

import db2.redsocial.entities.Reactioncomment;
import db2.redsocial.models.Message;
import db2.redsocial.models.BasicResponse;
import db2.redsocial.services.ReactioncommentService;
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
@RequestMapping("/reactions-comments")
@CrossOrigin(origins = "*")
public class ReactioncommentController {
    
    @Autowired
    private ReactioncommentService reactioncommentRepo;
    
    @GetMapping
    public List<Reactioncomment> getAllRc() {
        return reactioncommentRepo.consult();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BasicResponse> addRc(@RequestBody Reactioncomment rc) {
        if (reactioncommentRepo.add(rc)) {
            BasicResponse response = new BasicResponse(rc.getIdReaction(), rc.getIdComment().getIdComment(), rc.getIdUser().getIdUser());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteRc(@PathVariable Integer id) {
        Message message;
        if (reactioncommentRepo.delete(id)) {
            message = new Message(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
            return ResponseEntity.ok(message);
        } else {
            message = new Message(HttpStatus.NOT_FOUND.value(), "Reaction comment not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRc(@Validated @RequestBody Reactioncomment rc) {
        Message message;
        reactioncommentRepo.update(rc);
        message = new Message(HttpStatus.OK.value(), "Reaction comment updated successfully");
        return ResponseEntity.ok(message);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/lenght")
    public Long recordsLenght() {
        return reactioncommentRepo.recordsLenght();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Optional<Reactioncomment> getRc(@PathVariable Integer id) {
        return reactioncommentRepo.search(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "user/{id}")
    public List<Reactioncomment> getPostsByIdUser(@PathVariable Integer id) {
        return reactioncommentRepo.searchRpByIdUser(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "comment/{id}")
    public List<Reactioncomment> getPostsByIdPost(@PathVariable Integer id) {
        return reactioncommentRepo.searchRpByIdComment(id);
    }
}
