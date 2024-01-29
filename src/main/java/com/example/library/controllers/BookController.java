package com.example.library.controllers;

import com.example.library.dto.BookDto;
import com.example.library.services.BookService;
import com.example.library.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody BookDto bookDto
    ) {
        return ResponseEntity.ok(service.save(bookDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{book-id}")
    public ResponseEntity<BookDto> findById (@PathVariable("book-id") Integer bookId) {
        return ResponseEntity.ok(service.findById(bookId));
    }

    @DeleteMapping("/{book-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("book-id") Integer bookId
    ) {
        service.delete(bookId);
        return ResponseEntity.accepted().build();
    }
}
