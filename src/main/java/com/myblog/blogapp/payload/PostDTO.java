package com.myblog.blogapp.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Igor Suvorov
 */

@Data
public class PostDTO {

    private long id;

    @NotEmpty
    @Size(min = 2, message = "post title should have at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 5, message = "post description should have at least 5 characters")
    private String description;

    @NotEmpty
    @Size(min = 5, message = "post content should have at least 2 characters")
    private String content;

    Set<CommentDTO> comments;
}