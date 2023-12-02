package com.user.management.repository;

import com.user.management.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    @Query(value = "SELECT * FROM USERS WHERE USER_ID=?1 AND PASSWORD=?2 AND IS_ACTIVE=?3", nativeQuery = true)
    Users findByUsernameAndPassword(String userName, String password, boolean isActive);

    @Query(value = "SELECT COUNT(*) FROM USERS WHERE EMAIL=?2 OR USER_ID=?1", nativeQuery = true)
    Long findByUsernameOrEmil(String username, String email);
}
