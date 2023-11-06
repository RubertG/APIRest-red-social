package db2.redsocial.repository;

import db2.redsocial.entities.Reactioncomment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactioncommentRepository extends JpaRepository<Reactioncomment, Integer>{
    
    @Query("SELECT rc FROM Reactioncomment rc")
    List<Reactioncomment> getAll();

    @Query("SELECT rc FROM Reactioncomment rc WHERE rc.idUser.idUser = :userId")
    List<Reactioncomment> getRcByUserId(@Param("userId") Integer userId);
    
    @Query("SELECT rc FROM Reactioncomment rc WHERE rc.idComment.idComment = :commentId")
    List<Reactioncomment> getRcByCommentId(@Param("commentId") Integer commentId);
}
