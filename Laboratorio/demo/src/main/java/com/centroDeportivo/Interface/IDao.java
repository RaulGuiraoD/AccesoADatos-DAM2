package com.centroDeportivo.Interface;

import java.util.List;

public interface IDao<T, V> {
    boolean createRecord(T models);
    T readRecord(V idModel);
    boolean updateRecord(T models, V idModel);
    boolean deleteRecord(V idModel);
    List<T> readRecords();
}