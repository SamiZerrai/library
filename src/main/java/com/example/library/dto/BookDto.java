package com.example.library.dto;

import com.example.library.models.Genre;
import com.example.library.models.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BookDto {

    @NotNull(message = "Le titre ne doit pas etre vide")
    @NotEmpty(message = "Le titre ne doit pas etre vide")
    @NotBlank(message = "Le titre ne doit pas etre vide")
    private String title;

    @NotNull(message = "Le description ne doit pas etre vide")
    @NotEmpty(message = "Le description ne doit pas etre vide")
    @NotBlank(message = "Le description ne doit pas etre vide")
    private String description;

    private Genre genre;


    public static BookDto fromEntity(Book book) {
        //null check
        return BookDto.builder()
                .title(book.getTitle())
                .description(book.getDescription())
                .genre(book.getGenre())
                .build();
    }

    public static Book toEntity(BookDto book) {
        //null check
        return Book.builder()
                .title(book.getTitle())
                .description(book.getDescription())
                .genre(book.getGenre())
                .build();
    }
}
