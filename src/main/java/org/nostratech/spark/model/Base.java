package org.nostratech.spark.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by aguswinarno on 9/7/17.
 */
@Data
@MappedSuperclass
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    private Integer version;

    @Column(name = "secure_id", length = 60)
    private String secureId;

}
