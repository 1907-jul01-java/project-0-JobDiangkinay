package com.revature.entities;

import java.util.List;

/**
 * 
 * An interface for Data Access Object
 *
 * @param <E> Generic type.
 */
public interface Dao<E> {
    void insert(E e);

    List<E> getAll();

    void update();

    void delete(String e);
}