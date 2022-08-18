package com.example.uz.repository;

import com.example.uz.dto.BookDto;
import com.example.uz.entity.Book;
import com.example.uz.service.mapper.BookMapperImpl;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@Data
public class UniversalBookRepository {

    private final EntityManager manager;

    private final BookMapperImpl mapper;

    public Page<BookDto> getBookByParam(MultiValueMap<String, String> map, Pageable pageable){

        StringBuilder builder = new StringBuilder();
        addConditionToString(map, builder);

        String query1 = "SELECT * FROM book WHERE 1=1" + builder;
        String query2 = "SELECT count(1) FROM book WHERE 1=1" + builder;

        Query getQuery = manager.createNativeQuery(query1, Book.class);
        Query countQuery = manager.createNativeQuery(query2, Integer.class);

        addParameters(map, getQuery);
        addParameters(map, countQuery);

        List<Book> bookList = getQuery.getResultList();
        Integer summary = countQuery.getFirstResult();

        List<BookDto> bookDtoList = bookList.stream().map(mapper::toDto).toList();

        Page<BookDto> impl = new PageImpl(bookDtoList, pageable, summary);

        return impl;
    }

    private void addConditionToString(MultiValueMap<String, String> map, StringBuilder builder){
        if(map.containsKey("id")){
            builder.append(" and id = :id");
        }
        if(map.containsKey("name")){
            builder.append(" and name = :name");
        }
        if(map.containsKey("price")){
            builder.append(" and price = :price");
        }
        if(map.containsKey("publishedAt")){
            builder.append(" and published_at = :publishedAt");
        }
        if(map.containsKey("publishedBy")){
            builder.append(" and publishedBy = :publishedBy");
        }
        if(map.containsKey("authorName")){
            builder.append(" and authorName = :authorName");
        }
        if(map.containsKey("page")){
            builder.append(" and page = :page");
        }
        if(map.containsKey("genre")){
            builder.append(" and genre = :genre");
        }
    }

    private void addParameters(MultiValueMap<String, String> map, Query query){
        if(map.containsKey("id")){
            query.setParameter("id", map.getFirst("id"));
        }
        if(map.containsKey("name")){
            query.setParameter("name", map.getFirst("name"));
        }
        if(map.containsKey("price")){
            query.setParameter("price", map.getFirst("price"));
        }
        if(map.containsKey("publishedAt")){
            query.setParameter("publishedAt", map.getFirst("publishedAt"));
        }
        if(map.containsKey("publishedBy")){
            query.setParameter("publishedBy", map.getFirst("publishedBy"));
        }
        if(map.containsKey("authorName")){
            query.setParameter("authorName", map.getFirst("authorName"));
        }
        if(map.containsKey("page")){
            query.setParameter("page", map.getFirst("page"));
        }
        if(map.containsKey("genre")){
            query.setParameter("genre", map.getFirst("genre"));
        }
    }
}
