package com.example.library.models.repositories;

import com.example.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT DISTINCT u FROM _user u JOIN author_book ab ON u.id = ab.author_id JOIN Book b ON b.id = ab.book_id JOIN author_book abOther ON abOther.book_id = b.id WHERE abOther.author_id <> ab.author_id")
    List<User> findAuthorsWithCommonBooks();
}
