package db2.redsocial.controllers;

import db2.redsocial.entities.Reactionpost;
import db2.redsocial.models.Message;
import db2.redsocial.models.BasicResponse;
import db2.redsocial.services.ReactionpostService;
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
@RequestMapping("/reactions-posts")
@CrossOrigin(origins = "*")
public class ReactionpostController {
    
    @Autowired
    private ReactionpostService reactionpostRepo;
    
    @GetMapping
    public List<Reactionpost> getAllRp() {
        return reactionpostRepo.consult();
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BasicResponse> addRp(@RequestBody Reactionpost rp) {
        if (reactionpostRepo.add(rp)) {
            BasicResponse response = new BasicResponse(rp.getIdReaction(), rp.getIdPost().getIdPost(), rp.getIdUser().getIdUser());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteRp(@PathVariable Integer id) {
        Message message;
        if (reactionpostRepo.delete(id)) {
            message = new Message(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
            return ResponseEntity.ok(message);
        } else {
            message = new Message(HttpStatus.NOT_FOUND.value(), "Reaction post not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRp(@Validated @RequestBody Reactionpost rp) {
        Message message;
        reactionpostRepo.update(rp);
        message = new Message(HttpStatus.OK.value(), "Reaction post  updated successfully");
        return ResponseEntity.ok(message);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/lenght")
    public Long recordsLenght() {
        return reactionpostRepo.recordsLenght();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Optional<Reactionpost> getRp(@PathVariable Integer id) {
        return reactionpostRepo.search(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "user/{id}")
    public List<Reactionpost> getPostsByIdUser(@PathVariable Integer id) {
        return reactionpostRepo.searchRpByIdUser(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "post/{id}")
    public List<Reactionpost> getPostsByIdPost(@PathVariable Integer id) {
        return reactionpostRepo.searchRpByIdPost(id);
    }
}
