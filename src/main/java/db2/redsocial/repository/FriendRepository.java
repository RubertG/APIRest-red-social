package db2.redsocial.repository;

import db2.redsocial.entities.Friend;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer>{
    
    @Query("SELECT f FROM Friend f")
    List<Friend> getAll();

    @Query("SELECT f FROM Friend f WHERE f.idUser1.idUser = :userId")
    List<Friend> getFriendsByUserId(@Param("userId") Integer userId);
}
