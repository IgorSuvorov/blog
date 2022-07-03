package com.myblog.blogapp.payload;

import com.myblog.blogapp.model.Post;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Igor Suvorov
 */
@Api(value = "Comment model information")
@Data
public class CommentDTO {

    @ApiModelProperty(value = "Comment id")
    private long id;

    @ApiModelProperty(value = "Comment name")
    @NotEmpty(message = "name should not be empty")
    private String name;

    @ApiModelProperty(value = "Comment email")
    @NotEmpty(message = "email should not be empty")
    @Email
    private String email;

    @ApiModelProperty(value = "Comment body")
    @NotEmpty
    @Size(min = 5, message = "comment should be at least 5 characters")
    private String body;
}
