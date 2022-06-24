package com.myblog.blogapp.repository;

import com.myblog.blogapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Igor Suvorov
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    Optional<User> findByUserNameOrEmail(String userName, String email);

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);
}
