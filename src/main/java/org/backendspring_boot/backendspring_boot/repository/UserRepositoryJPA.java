package org.backendspring_boot.backendspring_boot.repository;

import org.backendspring_boot.backendspring_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepositoryJPA extends JpaRepository<User, Long> {
    User findByUsername(String username);

    ///version which does NOT prevent SQL injection
    ///@Query("SELECT u FROM Users u where username = '" + username + "'")
    ///User findUserByUsername(String username);

    ///version which PREVENTS SQL injection due to prepared statement and parametrized query
    ///the placeholder :username in the query is escaped automatically, preventing sql injection attacks
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findUserByUsername(@Param("username") String username);

    User findByEmail(String email);
}
