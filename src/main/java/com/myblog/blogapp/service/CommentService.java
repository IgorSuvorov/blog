package com.myblog.blogapp.service;

import com.myblog.blogapp.payload.CommentDTO;

import java.util.List;

/**
 * @author Igor Suvorov
 */
public interface CommentService {

    CommentDTO createComment(long postId, CommentDTO commentDTO);

    List<CommentDTO> getCommentsByPostId(long postId);

    CommentDTO getCommentById(long postId, long commentId);

    void deleteComment(long postId, long commentId);

    CommentDTO updateComment(CommentDTO commentDTO, long postId, long commentId);
}
