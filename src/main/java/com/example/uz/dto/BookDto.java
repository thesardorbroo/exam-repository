package com.example.uz.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class BookDto {

    private Integer id;

    private String name;

    private Double price;

    private Date publishedAt;

    private String publishedBy;

    private String authorName;

    private Integer page;

    private String genre;
}
