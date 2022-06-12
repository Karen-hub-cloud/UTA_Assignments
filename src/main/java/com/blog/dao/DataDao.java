package com.blog.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.blog.domain.DataDemo;

@Repository
public interface DataDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DataDemo record);

    int update(DataDemo record);

    List<DataDemo> queryAll();

    List<DataDemo> selectByWord(String word);

    DataDemo selectById(Integer id);
}
