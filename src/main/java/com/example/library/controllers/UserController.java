package com.example.library.controllers;

import com.example.library.dto.UserDto;
import com.example.library.models.Book;
import com.example.library.models.User;
import com.example.library.services.BookService;
import com.example.library.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final BookService bookService;

    @PostMapping("/")
    public ResponseEntity<UserDto> save(
            @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(service.save(userDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserDto> findById (@PathVariable("user-id") Integer userId) {
        return ResponseEntity.ok(service.findById(userId));
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("user-id") Integer userId
    ) {
        service.delete(userId);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/{userId}/book/{bookId}")
    public ResponseEntity<Void> assignAuthorToBook(
            @PathVariable Integer bookId,
            @PathVariable Integer userId
    ) {
        service.addBookToAuthor(userId, bookId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/findAuthorsWithCommonBooks")
    public ResponseEntity<List<UserDto>> findAuthorsWithCommonBooks() {
        return ResponseEntity.ok(service.findAuthorsWithCommonBooks());
    }
}
