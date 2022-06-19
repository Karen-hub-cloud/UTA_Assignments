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

    @RequestMapping("/earthquake")
    public String about(){
        return "admin/earthquake";
    }

    /**
     * 11 改造
     */
    @RequestMapping("/searchLargestN")
    public ModelAndView searchLargestN(@RequestParam(required=true) String net,
            @RequestParam(required=true) double min,
            @RequestParam(required=true) double max){

        ModelAndView modelAndView=new ModelAndView("admin/earthquake_list");
        List<Earthquake> earthquakeList = earthquakeService.searchLargestN(5, net, min, max);
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

    /**
     * 12 改造
     */
    @RequestMapping("/searchScale")
    public ModelAndView searchScale(@RequestParam(required=true) String time){
        ModelAndView modelAndView=new ModelAndView("admin/earthquake_list");
        List<Earthquake> earthquakeList = earthquakeService.searchScale(time);
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

    /**
     * 10 改造
     */
    @RequestMapping("/compareTwoPlace")
    public ModelAndView compareTwoPlace(
            @RequestParam(required=true) Double longitude1,
            @RequestParam(required=true) Double latitude1,
            @RequestParam(required=true) Double longitude2,
            @RequestParam(required=true) Double latitude2){
        ModelAndView modelAndView=new ModelAndView("admin/earthquake_list");

        List<Earthquake> earthquakeList = earthquakeService.compareTwoPlace(longitude1,
                latitude1, longitude2, latitude2);
        modelAndView.addObject("earthquakeList", earthquakeList);
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
