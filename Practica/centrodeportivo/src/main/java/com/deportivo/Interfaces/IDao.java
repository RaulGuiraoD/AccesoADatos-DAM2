package com.deportivo.Interfaces;

import java.util.List;

public interface IDao <T, V> {

    boolean createRecord(T models);
    T readRecord(V idModels);
    boolean updateRecord(T modelos, V idModel);
    boolean deleteRecord(V idModel);
    List<T> readRecords();

}