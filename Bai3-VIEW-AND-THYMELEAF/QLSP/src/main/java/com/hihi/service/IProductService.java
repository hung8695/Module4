package com.hihi.service;

import java.util.List;

public interface IProductService<T>{
    List<T> findAll();
    void update(int id,T t);
    void remove(int id);
    void save(T t);
    T findById(int id);

}