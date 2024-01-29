package com.example.library.services.impl;

import com.example.library.dto.AuthenticationRequest;
import com.example.library.dto.AuthenticationResponse;
import com.example.library.dto.UserDto;
import com.example.library.models.Book;
import com.example.library.models.Role;
import com.example.library.models.User;
import com.example.library.models.repositories.BookRepository;
import com.example.library.models.repositories.RoleRepository;
import com.example.library.models.repositories.UserRepository;
import com.example.library.services.UserService;
import com.example.library.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    public static  final String ROLE_USER = "ROLE_USER";
    private final UserRepository repository;
    private final BookRepository bookRepository;
    private final ObjectsValidator<UserDto> validator;
    private final RoleRepository roleRepository;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        return repository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return repository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user was found with the provided ID" + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check before delete
    }

    @Override
    public void addBookToAuthor(Integer userId, Integer bookId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("No user was found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("No book was found"));
            user.getBooks().add(book);
            book.getUsers().add(user);
    }

    private Role findOrCreateRole(String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElse(null);
        if (role == null){
            return roleRepository.save(Role.builder()
                            .name(roleName)
                    .build());
        }
        return role;
    }

    public List<UserDto> findAuthorsWithCommonBooks() {
        return repository.findAuthorsWithCommonBooks()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());

    }
}
