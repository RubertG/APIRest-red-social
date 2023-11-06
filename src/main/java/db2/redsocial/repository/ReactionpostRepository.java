package db2.redsocial.repository;

import db2.redsocial.entities.Reactionpost;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionpostRepository extends JpaRepository<Reactionpost, Integer>{
    
    @Query("SELECT rp FROM Reactionpost rp")
    List<Reactionpost> getAll();

    @Query("SELECT rp FROM Reactionpost rp WHERE rp.idUser.idUser = :userId")
    List<Reactionpost> getRpByUserId(@Param("userId") Integer userId);
    
    @Query("SELECT rp FROM Reactionpost rp WHERE rp.idPost.idPost = :postId")
    List<Reactionpost> getRpByPostId(@Param("postId") Integer postId);

}
