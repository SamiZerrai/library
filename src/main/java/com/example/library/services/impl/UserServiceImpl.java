package com.example.library.services.impl;

import com.example.library.config.JwtUtils;
import com.example.library.dto.AuthenticationRequest;
import com.example.library.dto.AuthenticationResponse;
import com.example.library.dto.UserDto;
import com.example.library.models.Book;
import com.example.library.models.Role;
import com.example.library.models.TypeRole;
import com.example.library.models.User;
import com.example.library.models.repositories.BookRepository;
import com.example.library.models.repositories.RoleRepository;
import com.example.library.models.repositories.UserRepository;
import com.example.library.services.UserService;
import com.example.library.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;

    @Override
    public UserDto save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        repository.save(user);
        return dto;
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
        repository.save(user);
    }


    public User register(User user) {

        if(!user.getEmail().contains("@")) {
            throw  new RuntimeException("Votre mail invalide");
        }
        if(!user.getEmail().contains(".")) {
            throw  new RuntimeException("Votre mail invalide");
        }

        Optional<User> userOptional = this.repository.findByEmail(user.getEmail());
        if(userOptional.isPresent()) {
            throw  new RuntimeException("Votre mail est déjà utilisé");
        }
        String mdpCrypte = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(mdpCrypte);

        Role roleUser = new Role();
        roleUser.setTitle(TypeRole.USER);
        user.setRole(roleUser);

        this.repository.save(user);
        return user;
    }

    public List<UserDto> findAuthorsWithCommonBooks() {
        return repository.findAuthorsWithCommonBooks()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repository
                .findByEmail(email)
                .orElseThrow(() -> new  UsernameNotFoundException("Aucun user ne corespond à cet identifiant"));
    }

}
