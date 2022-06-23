package com.myblog.blogapp.controller;

import com.myblog.blogapp.payload.CommentDTO;
import com.myblog.blogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Igor Suvorov
 */
@RestController
@RequestMapping("/api/posts/")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(
            @PathVariable(value = "postId") long postId,
            @RequestBody CommentDTO commentDTO
    ) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public List<CommentDTO> getCommentsByPostId(@PathVariable(value = "postId") long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "commentId") long commentId
    ) {
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(
            @RequestBody CommentDTO commentDTO,
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "commentId") long commentId

    ) {
        return new ResponseEntity<>(commentService.updateComment(commentDTO, postId, commentId), HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "commentId") long commentId
    ) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment has been deleted", HttpStatus.OK);
    }
}
