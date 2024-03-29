package com.myblog.blogapp.controller;

import com.myblog.blogapp.payload.PostDTO;
import com.myblog.blogapp.payload.PostResponse;
import com.myblog.blogapp.service.PostService;
import com.myblog.blogapp.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Igor Suvorov
 */
@Api(value = "CRUD Rest APIs for Post resources")
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation(value = "Create Post REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Posts REST API")
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation(value = "Get Post By Id REST API")
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Update Post By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(
            @Valid @RequestBody PostDTO postDTO,
            @PathVariable(name = "id") long id
    ) {
        return new ResponseEntity<>(postService.updatePost(postDTO, id), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Post By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
        public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePost(id);

        return new ResponseEntity<>("Post has been successfully deleted.", HttpStatus.OK);
    }
}
