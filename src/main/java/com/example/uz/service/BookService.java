package com.example.uz.service;

import com.example.uz.dto.BookDto;
import com.example.uz.dto.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;

public interface BookService {

    // create
    ResponseDto<BookDto> addNewBook(BookDto bookDto);

    // read
    ResponseDto<Page<BookDto>> getAllBook(Integer page, Integer size);

    ResponseDto<Page<BookDto>> getBookByParam(MultiValueMap<String, String> map);

    // delete
    ResponseDto<BookDto> deleteBookById(Integer id);

    // update
    ResponseDto<BookDto> updateBook(BookDto bookDto);

}
