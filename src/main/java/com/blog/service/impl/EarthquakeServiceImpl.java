package com.blog.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.EarthquakeDao;
import com.blog.domain.Earthquake;
import com.blog.service.EarthquakeService;
import com.blog.service.RedisService;

/**
 * @author tiankun <tiankun@kuaishou.com>
 * Created on 2022-06-15
 */
@Service
public class EarthquakeServiceImpl implements EarthquakeService {

    @Autowired
    public EarthquakeDao earthquakeDao;

    @Autowired
    public RedisService redisService;

    private static final double EARTH_RADIUS = 6378.137;

    @Override
    public int deleteByPrimaryKey(String id) {
        return earthquakeDao.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteCache(String id) {
        redisService.del(id);
        return 1;
    }

    @Override
    public int insert(Earthquake record) {
        return earthquakeDao.insert(record);
    }

    @Override
    public int insertCache(Earthquake record) {
        redisService.put(record.getNumber(),record);
        return 1;
    }

    @Override
    public int update(Earthquake record) {
        return earthquakeDao.update(record);
    }

    @Override
    public int updataCache(Earthquake record) {
        if(redisService.hasKey(record.getNumber())){
            redisService.del(record.getNumber());
            redisService.put(record.getNumber(), record);
            return 1;
        }
        return 0;
    }

    @Override
    public Earthquake selectById(String id) {
        return earthquakeDao.selectById(id);
    }

    @Override
    public Earthquake selectCacheById(String id) {
        System.out.println("cache返回结果" + redisService.get(id));
        return (Earthquake) redisService.get(id);
    }

    @Override
    public List<Earthquake> queryAll() {
        return earthquakeDao.queryAll();
    }

    @Override
    public List<Earthquake> randomN(int n) {
        return earthquakeDao.randomN(n);
    }

    @Override
    public List<Earthquake> searchRange(int minSeq, int maxSeq){
        return earthquakeDao.searchRange(minSeq,maxSeq);
    }

    @Override
    public List<Earthquake> queryAllCache() {
        Set<String> keys = earthquakeDao.queryAll().stream().map(e->e.getNumber()).collect(Collectors.toSet());
        return redisService.getAll(keys)
                .values()
                .stream()
                .map(e -> (Earthquake)e)
                .collect(Collectors.toList());
    }

    /*@Override
    public List<Earthquake> selectByParams(Earthquake record) {
        return earthquakeDao.selectByParams(record);
    }

    @Override
    public List<Earthquake> selectByWord(String word) {
        return earthquakeDao.selectByWord(word);
    }

    @Override
    public List<Earthquake> searchLargestN(int n) {
        return earthquakeDao.searchLargestN(n);
    }
*/
//    @Override
//    public List<Earthquake> searchAroundPlace(int distance, double currLongitude, double currLatitude) {
//        List<Earthquake> earthquakeList = earthquakeDao.queryAll();
//        if (earthquakeList == null) {
//            return new ArrayList<Earthquake>();
//        }
//        List<Earthquake> result =  new ArrayList<Earthquake>();
//        for (Earthquake earthquake : earthquakeList) {
//            Double latitude =  earthquake.getLatitude();
//            Double longitude = earthquake.getLongitude();
//            if(getDistance(currLongitude, currLatitude, longitude, latitude) < 500){
//                result.add(earthquake);
//            }
//        }
//        return result;
//    }

//    @Override
//    public List<Earthquake> searchScale(String magType, double mag, String startTime, String endTime) {
//        List<Earthquake> earthquakeList = earthquakeDao.countScale(magType, mag);
//        if (earthquakeList == null) {
//            return new ArrayList<Earthquake>();
//        }
//        List<Earthquake> result =  new ArrayList<Earthquake>();
//        for (Earthquake earthquake : earthquakeList) {
//            if (currGreatPar(earthquake.getTime(),startTime) &&
//            currGreatPar(endTime, earthquake.getTime())){
//                result.add(earthquake);
//            }
//        }
//        return result;
//    }

   /* @Override
    public List<Integer> countScale(String magType, double minMag, double maxMag, int recent) {
        List<Integer> slot = new ArrayList<Integer>();
        slot.add(earthquakeDao.recentlyQuakes(magType, 1, 2, recent));
        slot.add(earthquakeDao.recentlyQuakes(magType, 2, 3, recent));
        slot.add(earthquakeDao.recentlyQuakes(magType, 3, 4, recent));
        slot.add(earthquakeDao.recentlyQuakes(magType, 4, 7, recent));
        return slot;
    }*/

//    @Override
//    public boolean compareTwoPlace(int distance, double longitude1,
//            double latitude1, double longitude2, double latitude2){
//        int a = searchAroundPlace(distance, longitude1, latitude1).size();
//        int b = searchAroundPlace(distance, longitude2, latitude2).size();
//        return a > b;
//    }

    /*@Override
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
    }*/

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
