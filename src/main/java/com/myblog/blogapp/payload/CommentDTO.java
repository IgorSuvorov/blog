package com.myblog.blogapp.payload;

import com.myblog.blogapp.model.Post;
import lombok.Data;

/**
 * @author Igor Suvorov
 */
@Data
public class CommentDTO {

    private long id;
    private String name;
    private String email;
    private String body;
}
