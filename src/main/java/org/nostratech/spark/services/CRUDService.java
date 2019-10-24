package org.nostratech.spark.services;

import java.util.Collection;

/**
 * agus w on 12/14/15.
 */
public interface CRUDService<V> {

    public Boolean post(V v);
    public Boolean put(V v);
    public Boolean delete(String id);
    public Collection<V> get();
    public V get(String id);
}
