package db2.redsocial.repository;

import db2.redsocial.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
    @Query("SELECT u FROM User u")
    List<User> getAll();
}
