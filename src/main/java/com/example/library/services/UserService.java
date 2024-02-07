package com.example.library.services;

import com.example.library.dto.AuthenticationRequest;
import com.example.library.dto.AuthenticationResponse;
import com.example.library.dto.UserDto;
import com.example.library.models.User;

import java.util.List;

public interface UserService extends AbstractService<UserDto> {

    void addBookToAuthor(Integer userId, Integer bookId);

    List<UserDto> findAuthorsWithCommonBooks();

    User register(User user);

    User loadUserByUsername(String email);
}
