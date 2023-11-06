package db2.redsocial.services;

import db2.redsocial.entities.Reactioncomment;
import db2.redsocial.interfaces.Options;
import db2.redsocial.repository.ReactioncommentRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service("ReactioncommentService")
public class ReactioncommentService implements Options<Reactioncomment, Integer> {

    @Autowired
    private ReactioncommentRepository reactioncommentRepo;

    @Override
    public List<Reactioncomment> consult() {
        return this.reactioncommentRepo.getAll();
    }

    @Override
    public Boolean add(Reactioncomment miObjeto) {
        Reactioncomment rc = reactioncommentRepo.save(miObjeto);
        return rc != null;
    }

    @Override
    public Long recordsLenght() {
        return reactioncommentRepo.count();
    }

    @Override
    public Boolean delete(Integer id) {
        reactioncommentRepo.deleteById(id);
        return !reactioncommentRepo.existsById(id);
    }

    @Override
    public Boolean update(Reactioncomment rc) {
        Optional<Reactioncomment> rcTemp = reactioncommentRepo.findById(rc.getIdReaction());
        if (rcTemp.isPresent()) {
            reactioncommentRepo.save(rc);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some field is missing");
        }
    }

    @Override
    public Optional<Reactioncomment> search(Integer id) {
        return reactioncommentRepo.findById(id);
    }

    public List<Reactioncomment> searchRpByIdUser(Integer id) {
        return reactioncommentRepo.getRcByUserId(id);
    }

    public List<Reactioncomment> searchRpByIdComment(Integer id) {
        return reactioncommentRepo.getRcByCommentId(id);
    }
}
