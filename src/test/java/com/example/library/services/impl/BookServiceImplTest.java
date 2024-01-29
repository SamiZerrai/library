package com.example.library.services.impl;

import com.example.library.dto.BookDto;
import com.example.library.models.Book;
import com.example.library.models.Genre;
import com.example.library.models.repositories.BookRepository;
import com.example.library.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl service;

    @Mock
    private BookRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void should_return_all_books() {
        List<Book> books = new ArrayList<>();
                books.add(new Book(
                "Le Seigneur des anneaux",
                "Le Seigneur des anneaux est un roman de J. R. R. Tolkien paru en trois volumes en 1954 et 1955.",
                Genre.FANTASY));

                when(repository.findAll()).thenReturn(books);
                when(BookDto.fromEntity(any(Book.class)))
                        .thenReturn(new BookDto(
                "Le Seigneur des anneaux",
                "Le Seigneur des anneaux est un roman de J. R. R. Tolkien paru en trois volumes en 1954 et 1955.",
                Genre.FANTASY
                        ));

                List<BookDto> responseDtos = service.findAll();

                assertEquals(books.size(), responseDtos.size());
    }
}