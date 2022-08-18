package com.example.uz.service.mapper;

import com.example.uz.dto.BookDto;
import com.example.uz.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "publishedAt", source = "book.publishedAt", dateFormat = "dd-MM-yyyy")
    BookDto toDto(Book book);

    @Mapping(target = "publishedAt", source = "bookDto.publishedAt", dateFormat = "dd-MM-yyyy")
    Book toEntity (BookDto bookDto);
}
