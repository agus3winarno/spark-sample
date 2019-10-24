package org.nostratech.spark.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by aguswinarno on 9/7/17.
 */
@NoRepositoryBean
public interface BaseRepository<T> extends CrudRepository<T, Integer>{

     public T findBySecureId(String secureId);
}
