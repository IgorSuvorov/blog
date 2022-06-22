package com.myblog.blogapp.repository;

import com.myblog.blogapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Igor Suvorov
 */

public interface PostRepository extends JpaRepository<Post, Long> {
}
