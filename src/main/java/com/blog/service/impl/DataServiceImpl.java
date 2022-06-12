package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.DataDao;
import com.blog.domain.DataDemo;
import com.blog.service.DataService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tiankun <tiankun@kuaishou.com>
 * Created on 2022-06-12
 */
@Service
@Slf4j
public class DataServiceImpl implements DataService {

    @Autowired
    public DataDao dataDao;

    public DataDemo selectById(Integer id) {
        return dataDao.selectById(id);
    }

    public List<DataDemo> queryAll() {
        return dataDao.queryAll();
    }

    public boolean update(DataDemo dataDemo) {
        return dataDao.update(dataDemo) > 0 ? true : false;
    }

    public int deleteById(Integer id) {
        return dataDao.deleteByPrimaryKey(id);
    }

    public List<DataDemo> selectByWord(String word) {
        return dataDao.selectByWord(word);
    }

    public boolean insert(DataDemo dataDemo) {
        return dataDao.insert(dataDemo) > 0 ? true : false;
    }
}
