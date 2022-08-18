package com.example.uz.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(generator = "book_generator")
    @SequenceGenerator(name = "book_generator", sequenceName = "book_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "published_at")
    private Date publishedAt;

    @Column(name = "published_by")
    private String publishedBy;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "page")
    private Integer page;

    @Column(name = "genre")
    private String genre;
}
