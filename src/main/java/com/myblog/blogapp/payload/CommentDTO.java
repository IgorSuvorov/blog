package com.myblog.blogapp.payload;

import com.myblog.blogapp.model.Post;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Igor Suvorov
 */
@Data
public class CommentDTO {

    private long id;

    @NotEmpty(message = "name should not be empty")
    private String name;

    @NotEmpty(message = "email should not be empty")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 5, message = "comment should be at least 5 characters")
    private String body;
}
