package com.blog.dao;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.blog.domain.Earthquake;

/**
 * @author tiankun <tiankun@kuaishou.com>
 * Created on 2022-06-14
 */
@Repository
public interface EarthquakeDao {
    int deleteByPrimaryKey(String id);

    int insert(Earthquake record);

    int update(Earthquake record);

    Earthquake selectById(String id);

    List<Earthquake> queryAll();

    List<Earthquake> searchLargestN(int n);

    List<Earthquake> selectByParams(Earthquake record);

    List<Earthquake> selectByWord(String word);

    List<Earthquake> searchByPlace(String word);

    List<Earthquake> countScale(String magType, double mag);

    int recentlyQuakes(String magType, double minMag, double maxMag, int recent);
}
