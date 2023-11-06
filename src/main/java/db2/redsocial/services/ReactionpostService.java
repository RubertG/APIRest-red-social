package db2.redsocial.services;

import db2.redsocial.entities.Reactionpost;
import db2.redsocial.interfaces.Options;
import db2.redsocial.repository.ReactionpostRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service("ReactionpostService")
public class ReactionpostService implements Options<Reactionpost, Integer> {

    @Autowired
    private ReactionpostRepository reactionpostRepo;

    @Override
    public List<Reactionpost> consult() {
        return this.reactionpostRepo.getAll();
    }

    @Override
    public Boolean add(Reactionpost rp) {
        Reactionpost rpAux = reactionpostRepo.save(rp);
        return rpAux != null;
    }

    @Override
    public Long recordsLenght() {
        return reactionpostRepo.count();
    }

    @Override
    public Boolean delete(Integer id) {
        reactionpostRepo.deleteById(id);
        return !reactionpostRepo.existsById(id);
    }

    @Override
    public Boolean update(Reactionpost rp) {
        Optional<Reactionpost> rcTemp = reactionpostRepo.findById(rp.getIdReaction());
        if (rcTemp.isPresent()) {
            reactionpostRepo.save(rp);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some field is missing");
        }
    }

    @Override
    public Optional<Reactionpost> search(Integer id) {
        return reactionpostRepo.findById(id);
    }

    public List<Reactionpost> searchRpByIdUser(Integer id) {
        return reactionpostRepo.getRpByUserId(id);
    }

    public List<Reactionpost> searchRpByIdPost(Integer id) {
        return reactionpostRepo.getRpByPostId(id);
    }
}
