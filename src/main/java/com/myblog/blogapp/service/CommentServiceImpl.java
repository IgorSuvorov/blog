package com.myblog.blogapp.service;

import com.myblog.blogapp.exception.CommentNotFoundException;
import com.myblog.blogapp.exception.PostNotFoundException;
import com.myblog.blogapp.model.Comment;
import com.myblog.blogapp.model.Post;
import com.myblog.blogapp.payload.CommentDTO;
import com.myblog.blogapp.repository.CommentRepository;
import com.myblog.blogapp.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Igor Suvorov
 */
@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl (CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDTO createComment(long postId, CommentDTO commentDTO) {
        Comment comment = mapDTOToEntity(commentDTO);
        Post post = getPost(postId);
        comment.setPost(post);

        return mapEntityToDTO(commentRepository.save(comment));
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().
                map(comment -> mapEntityToDTO(comment)).
                collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(long postId, long commentId) {
        Post post = getPost(postId);
        Comment comment = getComment(commentId);
        if (!comment.getPost().getId().equals(post.getId())) {
                throw new CommentNotFoundException(commentId);
        }

        return mapEntityToDTO(comment);
    }

    @Override
    public CommentDTO updateComment(CommentDTO commentDTO, long postId, long commentId) {
        Post post = getPost(postId);

        Comment comment = getComment(commentId);

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new CommentNotFoundException(commentId);
        }

        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());

        Comment updatedComment = commentRepository.save(comment);

        return mapEntityToDTO(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = getPost(postId);
        Comment comment = getComment(commentId);
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new CommentNotFoundException(commentId);
        }

        commentRepository.deleteById(commentId);
    }

    private CommentDTO mapEntityToDTO(Comment comment) {
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);

        return commentDTO;
    }

    private Comment mapDTOToEntity(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);

        return comment;
    }

    private Post getPost(long postId) {
        Post post = postRepository.findById(postId).
                orElseThrow(() -> new PostNotFoundException(postId));

        return post;
    }

    private Comment getComment(long commentId) {
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(() -> new CommentNotFoundException(commentId));

        return comment;
    }
}
