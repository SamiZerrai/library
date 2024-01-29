package com.example.library.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Book extends AbstractEntity{

    private String title;

    private String description;

    private Genre genre;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> users;

}
