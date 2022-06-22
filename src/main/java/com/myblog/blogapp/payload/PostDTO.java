package com.myblog.blogapp.payload;

import lombok.Data;

import java.util.Set;

/**
 * @author Igor Suvorov
 */

@Data
public class PostDTO {

    private long id;
    private String title;
    private String description;
    private String content;
    Set<CommentDTO> comments;
}