package com.example.library.services;

import com.example.library.dto.AuthenticationRequest;
import com.example.library.dto.AuthenticationResponse;
import com.example.library.dto.UserDto;

import java.util.List;

public interface UserService extends AbstractService<UserDto> {

    void addBookToAuthor(Integer userId, Integer bookId);

    List<UserDto> findAuthorsWithCommonBooks();

    AuthenticationResponse register(UserDto user);

    AuthenticationResponse authenticate(AuthenticationRequest request);

}
