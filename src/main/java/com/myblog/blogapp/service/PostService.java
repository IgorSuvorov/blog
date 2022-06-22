package com.myblog.blogapp.service;

import com.myblog.blogapp.payload.PostDTO;
import com.myblog.blogapp.payload.PostResponse;

import java.util.List;

/**
 * @author Igor Suvorov
 */

public interface PostService {

    PostDTO createPost(PostDTO postDTO);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDTO getPostById(long id);

    PostDTO updatePost(PostDTO postDTO, long id);

    void deletePost(long id);
}
