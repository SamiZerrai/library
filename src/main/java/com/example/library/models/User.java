package com.example.library.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "_user")
public class User extends AbstractEntity {

    private String firstname;

    private String lastname;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "authorBook",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books = new ArrayList<>();

    @OneToOne
    private Role role;
}
