package com.myblog.blogapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Igor Suvorov
 */
@Getter
@Setter
@Entity
@Table (name = "roles")
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (length = 50)
    private String name;

}
