package db2.redsocial.repository;

import db2.redsocial.entities.Post;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
    
    @Query("SELECT p FROM Post p")
    List<Post> getAll();

    @Query("SELECT p FROM Post p WHERE p.idOwner.idUser = :userId")
    List<Post> getPostsByUserId(@Param("userId") Integer userId);
}

