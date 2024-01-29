package com.example.library.dto;

import com.example.library.models.Book;
import com.example.library.models.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    @NotNull(message = "Le prenom ne doit pas etre vide")
    @NotEmpty(message = "Le prenom ne doit pas etre vide")
    @NotBlank(message = "Le prenom ne doit pas etre vide")
    private String firstname;

    @NotNull(message = "Le nom ne doit pas etre vide")
    @NotEmpty(message = "Le nom ne doit pas etre vide")
    @NotBlank(message = "Le nom ne doit pas etre vide")
    private String lastname;

    @DateTimeFormat
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotNull(message = "Le email ne doit pas etre vide")
    @NotEmpty(message = "Le email ne doit pas etre vide")
    @NotBlank(message = "Le email ne doit pas etre vide")
    @Email(message = "L'email n'est pas conforme")
    private String email;

    @NotNull(message = "Le mot de passe ne doit pas etre vide")
    @NotEmpty(message = "Le mot de passe ne doit pas etre vide")
    @NotBlank(message = "Le mot de passe ne doit pas etre vide")
    @Size(min = 8, max = 16, message = "Le mot de passe doit etre entre 8 et 16 caracteres")
    private String password;

    private List<Book> books;

    public static UserDto fromEntity(User user) {
        //null check
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .password(user.getPassword())
                .books(user.getBooks())
                .build();
    }

    public static User toEntity(UserDto user) {
        //null check
        return User.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .password(user.getPassword())
                .books(user.getBooks())
                .build();
    }
}
