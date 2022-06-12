package com.blog.service;

import java.util.List;

import com.blog.domain.DataDemo;

public interface DataService {

    DataDemo selectById(Integer id);

    List<DataDemo> queryAll();

    boolean update(DataDemo dataDemo);

    int deleteById(Integer id);

    List<DataDemo> selectByWord(String word);

    boolean insert(DataDemo dataDemo);
}
