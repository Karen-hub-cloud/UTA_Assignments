package com.blog.service;

import java.util.List;

import com.blog.domain.Earthquake;

public interface EarthquakeService {

    Earthquake selectById(String id);

    List<Earthquake> queryAll();

    int deleteById(String id);

    List<Earthquake> selectByWord(String word);

    List<Earthquake> searchLargestN(int n, String net, double min, double max);

    List<Earthquake> searchAroundPlace(double distance, double currLongitude, double currLatitude);

    List<Earthquake> searchScale(String time);

    List<Integer> countScale(String magType, double minMag, double maxMag, int recent);

    List<Earthquake> compareTwoPlace(double longitude1,
            double latitude1, double longitude2, double latitude2);

    Earthquake getLargestEarthquake (int distance, double longitude1,
            double latitude1);

}
