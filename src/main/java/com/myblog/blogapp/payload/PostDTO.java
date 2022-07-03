package com.myblog.blogapp.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Igor Suvorov
 */
@ApiModel(description = "Post model information")
@Data
public class PostDTO {

    @ApiModelProperty(value = "Blog post id")
    private long id;

    @ApiModelProperty(value = "Blog post title")
    @NotEmpty
    @Size(min = 2, message = "post title should have at least 2 characters")
    private String title;

    @ApiModelProperty(value = "Blog post description")
    @NotEmpty
    @Size(min = 5, message = "post description should have at least 5 characters")
    private String description;

    @ApiModelProperty(value = "Blog post conent")
    @NotEmpty
    @Size(min = 5, message = "post content should have at least 2 characters")
    private String content;

    @ApiModelProperty(value = "Blog post comments")
    Set<CommentDTO> comments;
}