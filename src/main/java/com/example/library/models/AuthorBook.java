package com.example.library.models;

import jakarta.persistence.*;

@Entity
@Table(name = "AuthorBook")
public class AuthorBook {

    @Id
    @Column(name = "author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
