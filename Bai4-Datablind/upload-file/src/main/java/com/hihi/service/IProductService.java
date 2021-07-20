package com.hihi.service;

import java.util.List;

public interface IProductService<T> {
    List<T> findAll();

    T findById(int id);

    void save(T t);

    void update(int id, T t);

    void remove(int id);

}
