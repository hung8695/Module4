package com.hihi.repository;

import java.util.List;

public interface IGeneralRepository <T>{
    List<T> findAll();
    T findById(long id);
    void save(T t);
    void remove(long id);
}
