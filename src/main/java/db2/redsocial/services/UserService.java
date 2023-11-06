package db2.redsocial.services;

import db2.redsocial.entities.User;
import db2.redsocial.interfaces.Options;
import db2.redsocial.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service("UserService")
public class UserService implements Options<User, Integer> {

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> consult() {
        return this.userRepo.getAll();
    }

    @Override
    public Boolean add(User myObject) {
        User userSave = userRepo.save(myObject);
        return userSave != null;
    }

    @Override
    public Boolean delete(Integer id) {
        userRepo.deleteById(id);
        return !userRepo.existsById(id);
    }

    @Override
    public Long recordsLenght() {
        return userRepo.count();
    }

    @Override
    public Boolean update(User user) {
        Optional<User> userTemp = userRepo.findById(user.getIdUser());
        if (userTemp.isPresent()) {
            userRepo.save(user);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some field is missing or not exist");
        }
    }

    @Override
    public Optional<User> search(Integer id) {
        return userRepo.findById(id);
    }
}
