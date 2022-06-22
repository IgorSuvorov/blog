package com.myblog.blogapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Igor Suvorov
 */

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CommentNotFoundException extends RuntimeException {

    private long id;

    public CommentNotFoundException(long id) {
        super(String.format("no comments found with id: " + id));
        this.id = id;
    }
}
