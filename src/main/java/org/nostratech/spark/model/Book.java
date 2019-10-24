package org.nostratech.spark.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by aguswinarno on 9/7/17.
 */
@Data
@Entity
@Table(name = "books")
public class Book extends Base {

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "author", length = 100)
    private String author;

}
