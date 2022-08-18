package com.example.uz.repository;

import com.example.uz.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findByName(String name);

    boolean existsByName(String name);
}
