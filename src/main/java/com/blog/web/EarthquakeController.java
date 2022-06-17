package com.blog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.blog.domain.Earthquake;
import com.blog.service.EarthquakeService;

@Controller
public class EarthquakeController {

    @Autowired
    EarthquakeService earthquakeService;


    @RequestMapping("/searchLargestN")
    public ModelAndView searchLargestN(@RequestParam(required=true, defaultValue="1") Integer n){

        ModelAndView modelAndView=new ModelAndView("admin/earthquake_list");
        List<Earthquake> earthquakeList = earthquakeService.searchLargestN(n);
        modelAndView.addObject("earthquakeList", earthquakeList);
        return modelAndView;
    }

    @RequestMapping("/searchAroundPlace")
    public ModelAndView searchAroundPlace(@RequestParam(required=true) Integer distance,
            @RequestParam(required=true) Double currLongitude,
            @RequestParam(required=true) Double currLatitude){
        ModelAndView modelAndView=new ModelAndView("admin/earthquake_list");
        List<Earthquake> earthquakeList = earthquakeService.searchAroundPlace(distance, currLongitude , currLatitude );

        modelAndView.addObject("earthquakeList", earthquakeList);
        return modelAndView;
    }

    @RequestMapping("/searchScale")
    public ModelAndView searchScale(@RequestParam(required=true) String magType,
            @RequestParam(required=true) Double mag,
            @RequestParam(required=true) String startTime,
            @RequestParam(required = true) String endTime){
        ModelAndView modelAndView=new ModelAndView("admin/earthquake_list");
        List<Earthquake> earthquakeList = earthquakeService.searchScale(magType, mag, startTime, endTime);

        modelAndView.addObject("earthquakeList", earthquakeList);
        return modelAndView;
    }

    @RequestMapping("/countScale")
    public ModelAndView searchScale(@RequestParam(required=true) String magType,
            @RequestParam(required=true) Double minMag,
            @RequestParam(required=true) Double maxMag,
            @RequestParam(required = true) Integer recent){
        ModelAndView modelAndView=new ModelAndView("admin/earthquake_list");
        List<Integer> earthquakeList = earthquakeService.countScale(magType, minMag, maxMag, recent);
        modelAndView.addObject("earthquakeList", earthquakeList);
        return modelAndView;
    }

    @RequestMapping("/compareTwoPlace")
    public ModelAndView compareTwoPlace(@RequestParam(required=true) Integer distance,
            @RequestParam(required=true) Double longitude1,
            @RequestParam(required=true) Double latitude1,
            @RequestParam(required=true) Double longitude2,
            @RequestParam(required=true) Double latitude2,
            @RequestParam(required = true) Integer recent){
        ModelAndView modelAndView=new ModelAndView("admin/compareTwoPlace");
        Boolean b = earthquakeService.compareTwoPlace(distance, longitude1,
                latitude1, longitude2, latitude2);
        modelAndView.addObject("b", b);
        return modelAndView;
    }

    @RequestMapping("/getLargestEarthquake")
    public ModelAndView getLargestEarthquake(@RequestParam(required=true) Integer distance,
            @RequestParam(required=true) Double longitude1,
            @RequestParam(required=true) Double latitude1){
        ModelAndView modelAndView=new ModelAndView("admin/getLargestEarthquake");
        Earthquake earthquake = earthquakeService.getLargestEarthquake(distance, longitude1, latitude1);
        modelAndView.addObject("earthquake", earthquake);
        return modelAndView;
    }
}
