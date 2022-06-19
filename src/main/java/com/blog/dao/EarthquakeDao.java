package com.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import com.blog.domain.Earthquake;

/**
 * @author tiankun <tiankun@kuaishou.com>
 * Created on 2022-06-14
 */
@Repository
public interface EarthquakeDao {
    int deleteByPrimaryKey(String id);

    /*int insert(Earthquake record);

    int update(Earthquake record);*/

    List<Earthquake> queryAll();

    List<Earthquake> searchLargestN(@Param("n") int n, @Param("net") String net,
            @Param("min") double min, @Param("max") double max);

    List<Earthquake> selectByWord(String word);

    List<Earthquake> searchByPlace(String word);

    List<Earthquake> countScale(String time);

    int recentlyQuakes(String magType, double minMag, double maxMag, int recent);

    Earthquake selectById(String id);
}
