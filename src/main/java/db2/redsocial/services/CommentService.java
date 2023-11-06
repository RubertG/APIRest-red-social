package db2.redsocial.services;

import db2.redsocial.entities.Comment;
import db2.redsocial.interfaces.Options;
import db2.redsocial.repository.CommentRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service("CommentService")
public class CommentService implements Options<Comment, Integer> {

    @Autowired
    private CommentRepository commentRepo;

    @Override
    public List<Comment> consult() {
        return this.commentRepo.getAll();
    }

    @Override
    public Boolean add(Comment miObjeto) {
        Comment c = commentRepo.save(miObjeto);
        if (c.getCreationDate() == null) {
            c.setCreationDate(Calendar.getInstance().getTime());
        }
        return c != null;
    }

    @Override
    public Long recordsLenght() {
        return commentRepo.count();
    }

    @Override
    public Boolean delete(Integer id) {
        commentRepo.deleteById(id);
        return !commentRepo.existsById(id);
    }

    @Override
    public Boolean update(Comment c) {
        Optional<Comment> commentTemp = commentRepo.findById(c.getIdComment());
        if (commentTemp.isPresent()) {
            commentRepo.save(c);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some field is missing");
        }
    }

    @Override
    public Optional<Comment> search(Integer id) {
        return commentRepo.findById(id);
    }

    public List<Comment> searchCommentsByIdUser(Integer id) {
        return commentRepo.getCommentsByUserId(id);
    }

    public List<Comment> searchCommentsByIdPost(Integer id) {
        return commentRepo.getCommentsByPostId(id);
    }
}
