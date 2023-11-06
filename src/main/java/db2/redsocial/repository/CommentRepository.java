package db2.redsocial.repository;

import db2.redsocial.entities.Comment;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
    
    @Query("SELECT c FROM Comment c")
    List<Comment> getAll();

    @Query("SELECT c FROM Comment c WHERE c.idOwner.idUser = :userId")
    List<Comment> getCommentsByUserId(@Param("userId") Integer userId);

    @Query("SELECT c FROM Comment c WHERE c.idParent.idPost = :postId")
    List<Comment> getCommentsByPostId(@Param("postId") Integer postId);
}
