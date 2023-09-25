package ru.itmo.dao;

import java.util.ArrayDeque;
import java.util.List;

public interface DAO<T> {

    void create(T t);
    void update(int id, T t);
    void delete(int id);
    void delete(T t);
    void clear();
    T getFirst();
    T get(int id);
    T getMin();
    ArrayDeque<T> getAll();

}
