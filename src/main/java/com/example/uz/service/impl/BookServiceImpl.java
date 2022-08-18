package com.example.uz.service.impl;

import com.example.uz.dto.BookDto;
import com.example.uz.dto.ResponseDto;
import com.example.uz.entity.Book;
import com.example.uz.repository.BookRepository;
import com.example.uz.service.BookService;
import com.example.uz.service.mapper.BookMapperImpl;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
@Data
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    private final BookMapperImpl mapper;

    @Override
    public ResponseDto<BookDto> addNewBook(BookDto bookDto) {
        if (repository.existsByName(bookDto.getName())){
            return ResponseDto.<BookDto>builder().code(-1).success(false).message("Data is already exists!").build();
        }
        Book book = mapper.toEntity(bookDto);
        book = repository.save(book);
        return ResponseDto.<BookDto>builder().code(1).success(true).message("OK").data(mapper.toDto(book)).build();
    }

    @Override
    public ResponseDto<Page<BookDto>> getAllBook(Integer p, Integer s) {
        PageRequest request = PageRequest.of(p, s);

        Page<BookDto> page = repository.findAll(request).map(mapper::toDto);

        return ResponseDto.<Page<BookDto>>builder().code(1).success(true).message("OK").data(page).build();
    }

    @Override
    public ResponseDto<Page<BookDto>> getBookByParam(MultiValueMap<String, String> map) {
        return null;
    }

    @Override
    public ResponseDto<BookDto> deleteBookById(Integer id) {
        if (!repository.existsById(id)){
            return ResponseDto.<BookDto>builder().code(-1).success(false).message("Data is not found!").build();
        }
        Book book = repository.findById(id).get();
        repository.deleteById(id);

        return ResponseDto.<BookDto>builder().code(1).success(true).message("OK").data(mapper.toDto(book)).build();
    }

    @Override
    public ResponseDto<BookDto> updateBook(BookDto bookDto) {
//        System.out.println(bookDto.getName());
//        System.out.println(bookDto);
        if (repository.existsByName(bookDto.getName())){
            Book book = repository.findById(bookDto.getId()).get();
            book = Book.builder()
                    .id(bookDto.getId() != null? bookDto.getId() : book.getId())
                    .name(bookDto.getName() != null? bookDto.getName() : book.getName())
                    .page(bookDto.getPage() != null? bookDto.getPage() : book.getPage())
                    .price(bookDto.getPrice() != null? bookDto.getPrice() : book.getPrice())
                    .publishedAt(bookDto.getPublishedAt() != null? bookDto.getPublishedAt() : book.getPublishedAt())
                    .publishedBy(bookDto.getPublishedBy() != null? bookDto.getPublishedBy() : book.getPublishedBy())
                    .authorName(bookDto.getAuthorName() != null? bookDto.getAuthorName() : book.getAuthorName())
                    .genre(bookDto.getGenre() != null? bookDto.getGenre() : book.getGenre())
                    .build();

            repository.save(book);

            return ResponseDto.<BookDto>builder().code(1).success(true).message("OK").data(mapper.toDto(book)).build();

        }

        return ResponseDto.<BookDto>builder().code(-1).success(false).message("Data is not found!").build();
        }
}
