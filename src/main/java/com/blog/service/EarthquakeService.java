package com.blog.service;

import java.util.List;

import com.blog.domain.Earthquake;

public interface EarthquakeService {

    Earthquake selectById(String id);

    List<Earthquake> queryAll();

    int deleteById(String id);

    List<Earthquake> selectByWord(String word);

    List<Earthquake> searchLargestN(int n);

    List<Earthquake> searchAroundPlace(int distance, double currLongitude, double currLatitude);

    List<Earthquake> searchScale(String magType, double mag, String startTime, String endTime);

    List<Integer> countScale(String magType, double minMag, double maxMag, int recent);

    boolean compareTwoPlace(int distance, double longitude1,
            double latitude1, double longitude2, double latitude2);

    Earthquake getLargestEarthquake (int distance, double longitude1,
            double latitude1);

}
