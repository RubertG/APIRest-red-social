package db2.redsocial.services;

import db2.redsocial.entities.Friend;
import db2.redsocial.interfaces.Options;
import db2.redsocial.repository.FriendRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service("FriendService")
public class FriendService implements Options<Friend, Integer> {

    @Autowired
    private FriendRepository friendRepo;

    @Override
    public List<Friend> consult() {
        return this.friendRepo.getAll();
    }

    @Override
    public Boolean add(Friend miObjeto) {
        Friend f = friendRepo.save(miObjeto);
        return f != null;
    }

    @Override
    public Long recordsLenght() {
        return friendRepo.count();
    }

    @Override
    public Boolean delete(Integer id) {
        friendRepo.deleteById(id);
        return !friendRepo.existsById(id);
    }

    @Override
    public Boolean update(Friend friend) {
        Optional<Friend> friendTemp = friendRepo.findById(friend.getIdFriendship());
        if (friendTemp.isPresent()) {
            friendRepo.save(friend);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some field is missing");
        }
    }

    @Override
    public Optional<Friend> search(Integer id) {
        return friendRepo.findById(id);
    }

    public List<Friend> searchFriendsByIdUser(Integer id) {
        return friendRepo.getFriendsByUserId(id);
    }
}
