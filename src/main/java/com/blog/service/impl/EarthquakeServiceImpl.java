package com.blog.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.EarthquakeDao;
import com.blog.domain.Earthquake;
import com.blog.service.EarthquakeService;

/**
 * @author tiankun <tiankun@kuaishou.com>
 * Created on 2022-06-15
 */
@Service
public class EarthquakeServiceImpl implements EarthquakeService {

    @Autowired
    public EarthquakeDao earthquakeDao;
    private static final double EARTH_RADIUS = 6378.137;

    public Earthquake selectById(String id) {
        return earthquakeDao.selectById(id);
    }

    public List<Earthquake> queryAll() {
        return earthquakeDao.queryAll();
    }

    public int deleteById(String id) {
        return earthquakeDao.deleteByPrimaryKey(id);
    }

    public List<Earthquake> selectByWord(String word) {
        return earthquakeDao.selectByWord(word);
    }

    public List<Earthquake> searchLargestN(int n, String net, double min, double max) {
        return earthquakeDao.searchLargestN(n, net, min, max);
    }

    public List<Earthquake> searchAroundPlace(double distance, double currLongitude, double currLatitude) {
        List<Earthquake> earthquakeList = earthquakeDao.queryAll();
        if (earthquakeList == null) {
            return new ArrayList<Earthquake>();
        }
        List<Earthquake> result =  new ArrayList<Earthquake>();
        for (Earthquake earthquake : earthquakeList) {
            Double latitude =  earthquake.getLatitude();
            Double longitude = earthquake.getLongitude();
            if(getDistance(currLongitude, currLatitude, longitude, latitude) < distance){
                result.add(earthquake);
            }
        }
        return result;
    }

    public List<Earthquake> searchScale(String time) {
        List<Earthquake> earthquakeList = earthquakeDao.countScale(time);
        if (earthquakeList == null) {
            return new ArrayList<Earthquake>();
        }
        return earthquakeList;
    }

    public List<Integer> countScale(String magType, double minMag, double maxMag, int recent) {
        List<Integer> slot = new ArrayList<Integer>();
        slot.add(earthquakeDao.recentlyQuakes(magType, 1, 2, recent));
        slot.add(earthquakeDao.recentlyQuakes(magType, 2, 3, recent));
        slot.add(earthquakeDao.recentlyQuakes(magType, 3, 4, recent));
        slot.add(earthquakeDao.recentlyQuakes(magType, 4, 7, recent));
        return slot;
    }

    public List<Earthquake> compareTwoPlace(double longitude1,
            double latitude1, double longitude2, double latitude2){
        double distance = getDistance(longitude1,latitude1,longitude2,latitude2);
        List<Earthquake> finalEarthquake = new ArrayList<Earthquake>();
        finalEarthquake.addAll(searchAroundPlace(distance, longitude1, latitude1));
        finalEarthquake.addAll(searchAroundPlace(distance, longitude2, latitude2));
        return finalEarthquake;
    }

    public Earthquake getLargestEarthquake (int distance, double longitude1,
            double latitude1) {
        List<Earthquake> earthquakeList = searchAroundPlace(distance, longitude1, latitude1);
        double max = 0;
        Earthquake largest = null;
        for (Earthquake earthquake : earthquakeList){
            if (earthquake.getMag() > max) {
                max = earthquake.getMag();
                largest = earthquake;
            }
        }
        return largest;
    }

    private boolean currGreatPar(String curr, String par) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date sd1= df.parse(curr);
            Date sd2= df.parse(par);
            return sd1.after(sd2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取距离
     * @param longitude1
     * @param latitude1
     * @param longitude2
     * @param latitude2
     * @return
     */
    private double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 纬度
        double lat1 = Math.toRadians(latitude1);
        double lat2 = Math.toRadians(latitude2);
        // 经度
        double lng1 = Math.toRadians(longitude1);
        double lng2 = Math.toRadians(longitude2);
        // 纬度之差
        double a = lat1 - lat2;
        // 经度之差
        double b = lng1 - lng2;
        // 计算两点距离的公式
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        // 弧长乘地球半径, 返回单位: 千米
        s =  s * EARTH_RADIUS;
        return s;
    }
}
