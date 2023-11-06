package db2.redsocial.services;

import db2.redsocial.entities.Post;
import db2.redsocial.interfaces.Options;
import db2.redsocial.repository.PostRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service("PostService")
public class PostService implements Options<Post, Integer> {

    @Autowired
    private PostRepository postRepo;

    @Override
    public List<Post> consult() {
        return this.postRepo.findAll();
    }

    @Override
    public Boolean add(Post miObjeto) {
        Post post = postRepo.save(miObjeto);
        if (post.getCreationDate() == null) {
            post.setCreationDate(Calendar.getInstance().getTime());
        }
        return post != null;
    }

    @Override
    public Long recordsLenght() {
        return postRepo.count();
    }

    @Override
    public Boolean delete(Integer id) {
        postRepo.deleteById(id);
        return !postRepo.existsById(id);
    }

    @Override
    public Boolean update(Post post) {
        Optional<Post> postTemp = postRepo.findById(post.getIdPost());
        if (postTemp.isPresent()) {
            postRepo.save(post);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some field is missing");
        }
    }

    @Override
    public Optional<Post> search(Integer id) {
        return postRepo.findById(id);
    }

    public List<Post> searchRpByIdUser(Integer id) {
        return postRepo.getPostsByUserId(id);
    }
}
