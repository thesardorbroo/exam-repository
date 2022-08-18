package com.example.uz.controller;

import com.example.uz.dto.BookDto;
import com.example.uz.dto.ResponseDto;
import com.example.uz.service.impl.BookServiceImpl;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@Data
public class BookController {

    private final BookServiceImpl service;

    @PostMapping
    public ResponseDto<BookDto> addNewBook(@RequestBody BookDto bookDto){
        return service.addNewBook(bookDto);
    }

    @GetMapping("/all")
    public ResponseDto<Page<BookDto>> getAllBook(@RequestParam Integer page, @RequestParam Integer size){
        return service.getAllBook(page, size);
    }

    @GetMapping
    public ResponseDto<Page<BookDto>> getBookByParam(@RequestParam MultiValueMap<String, String> map){
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseDto<BookDto> deleteBookById(@PathVariable Integer id){
        return service.deleteBookById(id);
    }

    @PutMapping
    public ResponseDto<BookDto> updateBook(@RequestBody BookDto bookDto){
        return service.updateBook(bookDto);
    }
}
