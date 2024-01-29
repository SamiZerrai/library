package com.example.library.services.impl;

import com.example.library.dto.BookDto;
import com.example.library.models.Book;
import com.example.library.models.repositories.BookRepository;
import com.example.library.services.BookService;
import com.example.library.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private ObjectsValidator<BookDto> validator;

    @Override
    public BookDto save(BookDto dto) {
        validator.validate(dto);
        Book book = BookDto.toEntity(dto);
        repository.save(book);
        return dto;
    }

    @Override
    public List<BookDto> findAll() {
        return repository.findAll()
                .stream()
                .map(BookDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto findById(Integer id) {
        return repository.findById(id)
                .map(BookDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No book found with the ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
