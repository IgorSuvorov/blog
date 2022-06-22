package com.myblog.blogapp.service;

import com.myblog.blogapp.exception.PostNotFoundException;
import com.myblog.blogapp.model.Post;
import com.myblog.blogapp.payload.PostDTO;
import com.myblog.blogapp.payload.PostResponse;
import com.myblog.blogapp.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Igor Suvorov
 */

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        return mapEntityToDTO(postRepository.save(mapDTOToEntity(postDTO)));
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();

        List<PostDTO> content = listOfPosts.stream().
               map(post -> mapEntityToDTO(post)).
               collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDTO getPostById(long id) {
        Post post = postRepository.findById(id).
                orElseThrow(() -> new PostNotFoundException(id));

        return mapEntityToDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, long id) {
        Post post = postRepository.findById(id).
                orElseThrow(() -> new PostNotFoundException(id));

        post.setTitle(postDTO.getTitle());
        post.setDescription((postDTO.getDescription()));
        post.setContent(postDTO.getContent());
        Post updatedPost = postRepository.save(post);

        return mapEntityToDTO(updatedPost);
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    private PostDTO mapEntityToDTO(Post post) {
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);

        return postDTO;
    }

    private Post mapDTOToEntity(PostDTO postDTO){
        Post post = modelMapper.map(postDTO, Post.class);

        return post;
    }
}
