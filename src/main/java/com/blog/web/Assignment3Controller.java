package com.blog.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.domain.Earthquake;
import com.blog.service.EarthquakeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class Assignment3Controller {

    @Autowired
    EarthquakeService earthquakeService;

    @RequestMapping("/v/list")
    public ModelAndView articleList(@RequestParam(required = true, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        long startTime = System.currentTimeMillis();
        PageHelper.startPage(page, pageSize);
        List<Earthquake> earthquakeList = earthquakeService.queryAll();
        PageInfo<Earthquake> pageInfo = new PageInfo<Earthquake>(earthquakeList);
        long endTime = System.currentTimeMillis();
        ModelAndView modelAndView = new ModelAndView("/admin/v_list");
        modelAndView.addObject("earthquakeList", earthquakeList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("time", endTime-startTime);
        return modelAndView;
    }

    @RequestMapping("/v/randomN")
    public ModelAndView randomN(@RequestParam(required=true, defaultValue="1") Integer n) {
        long startTime = System.currentTimeMillis();
        List<Earthquake> earthquakeList = earthquakeService.randomN(n);
        long endTime = System.currentTimeMillis();
        ModelAndView modelAndView = new ModelAndView("/admin/v_list");
        modelAndView.addObject("earthquakeList", earthquakeList);
        modelAndView.addObject("time", endTime-startTime);
        return modelAndView;
    }

    @RequestMapping("/v/listCache")
    public ModelAndView articleListCache(@RequestParam(required = true, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        long startTime = System.currentTimeMillis();
        PageHelper.startPage(page, pageSize);
        List<Earthquake> earthquakeList = earthquakeService.queryAllCache();
        PageInfo<Earthquake> pageInfo = new PageInfo<Earthquake>(earthquakeList);
        long endTime = System.currentTimeMillis();
        ModelAndView modelAndView = new ModelAndView("/admin/assignment3_list");
        modelAndView.addObject("earthquakeList", earthquakeList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("time", endTime-startTime);
        return modelAndView;
    }

    @RequestMapping("/v/searchRange")
    public ModelAndView searchRange(@RequestParam(required=true,defaultValue = "2000") Integer minSeq,
            @RequestParam(required=true, defaultValue = "3000") Integer maxSeq){
            ModelAndView modelAndView=new ModelAndView("/admin/v_list");
            List<Earthquake> earthquakeList = earthquakeService.searchRange(minSeq, maxSeq);

            modelAndView.addObject("earthquakeList", earthquakeList);
            return modelAndView;
        }

    @RequestMapping("/v/add")
    public ModelAndView articleAdd() {

        ModelAndView modelAndView = new ModelAndView("/admin/v_add");
        return modelAndView;
    }

    @RequestMapping("/v/add/do")
    public String articleAddDo(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Earthquake earthquake = new Earthquake();
        earthquake.setNumber(request.getParameter("number"));
        earthquake.setCountry(request.getParameter("country"));
        earthquake.setRegion(request.getParameter("region"));
        earthquake.setLatitude(Double.valueOf(request.getParameter("latitude")));
        earthquake.setLongitude(Double.valueOf(request.getParameter("longitude")));
        earthquake.setElev(Double.valueOf(request.getParameter("longitude")));
        earthquake.setVolcanoName("volcano_name");

        if (earthquakeService.insert(earthquake) == 1) {
            redirectAttributes.addFlashAttribute("succ", "add success！");
            return "redirect:/v/add";
        } else {
            redirectAttributes.addFlashAttribute("error", "add error！");
            return "redirect:/v/add";
        }
    }

    @RequestMapping("/v/addCache/do")
    public String articleAddCacheDo(HttpServletRequest request) {
        Earthquake earthquake = earthquakeService.selectById(request.getParameter("number"));
        earthquakeService.insertCache(earthquake);
        return "redirect:/v/list";
    }

    @RequestMapping(value = "/v/search")
    public ModelAndView articleSearch(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        String word = request.getParameter("word");
        List<Earthquake> earthquakeList = Arrays.asList(earthquakeService.selectById(word));
        long endTime = System.currentTimeMillis();
        ModelAndView modelAndView = new ModelAndView("/admin/v_list");
        modelAndView.addObject("earthquakeList", earthquakeList);
        modelAndView.addObject("time", endTime-startTime);
        return modelAndView;
    }

    @RequestMapping(value = "/v/searchCache")
    public ModelAndView articleSearchCache(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        String word = request.getParameter("word");
        List<Earthquake> earthquakeList = Arrays.asList(earthquakeService.selectCacheById(word));
        long endTime = System.currentTimeMillis();
        ModelAndView modelAndView = new ModelAndView("/admin/v_list");
        modelAndView.addObject("earthquakeList", earthquakeList);
        modelAndView.addObject("time", (endTime-startTime)/2);
        return modelAndView;
    }

    @RequestMapping(value = "/v/edit")
    public ModelAndView articleEdit(HttpServletRequest request) {
        Earthquake earthquake = earthquakeService.selectById(request.getParameter("number"));
        ModelAndView modelAndView = new ModelAndView("/admin/v_edit");
        modelAndView.addObject("earthquake", earthquake);
        return modelAndView;
    }

    @RequestMapping(value = "/assignment3/editCache")
    public ModelAndView articleEditCache(HttpServletRequest request) {
        Earthquake earthquake = earthquakeService.selectCacheById(request.getParameter("id"));
        ModelAndView modelAndView = new ModelAndView("/admin/assignment3_editCache");
        modelAndView.addObject("earthquake", earthquake);
        return modelAndView;
    }

    @RequestMapping(value = "/v/edit/do")
    public ModelAndView articleEditDo(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Earthquake earthquake = new Earthquake();
        earthquake.setNumber(request.getParameter("number"));
        earthquake.setVolcanoName(request.getParameter("volcano_name"));
        earthquake.setCountry(request.getParameter("country"));
        earthquake.setRegion(request.getParameter("region"));
        earthquake.setLatitude(Double.valueOf(request.getParameter("latitude")));
        earthquake.setLongitude(Double.valueOf(request.getParameter("longitude")));
        earthquake.setElev(Double.valueOf(request.getParameter("elev")));
        ModelAndView modelAndView = new ModelAndView("/admin/v_edit");
        if (earthquakeService.update(earthquake) == 1) {
            long endTime = System.currentTimeMillis();
            modelAndView.addObject("succ", "update success！");
            modelAndView.addObject("time", endTime - startTime);
        } else {
            modelAndView.addObject("error", "update error！");
        }
        return modelAndView;
    }

    /*@RequestMapping(value = "/assignment3/editCache/do")
    public ModelAndView articleEditCacheDo(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        Earthquake earthquake = new Earthquake();
        earthquake.setId(request.getParameter("id"));
        earthquake.setMag(Double.valueOf(request.getParameter("mag")));
        earthquake.setMagType(request.getParameter("magType"));
        ModelAndView modelAndView = new ModelAndView("/admin/assignment3_editCache");
        if (earthquakeService.updataCache(earthquake) == 1) {
            long endTime = System.currentTimeMillis();
            modelAndView.addObject("succ", "update success！");
            modelAndView.addObject("time", endTime - startTime);
        } else {
            modelAndView.addObject("error", "update error！");
        }
        return modelAndView;
    }*/

    @RequestMapping(value = "/v/del", method = RequestMethod.POST)
    public @ResponseBody Object loginCheck(HttpServletRequest request) {
        String id = request.getParameter("number");
        int result = earthquakeService.deleteByPrimaryKey(id);
        HashMap<String, String> res = new HashMap<String, String>();
        if (result == 1) {
            res.put("stateCode", "1");
        } else {
            res.put("stateCode", "0");
        }
        return res;
    }

    @RequestMapping(value = "/assignment3/delCache", method = RequestMethod.POST)
    public @ResponseBody Object loginCheckCache(HttpServletRequest request) {
        String id = request.getParameter("id");
        int result = earthquakeService.deleteCache(id);
        HashMap<String, String> res = new HashMap<String, String>();
        if (result == 1) {
            res.put("stateCode", "1");
        } else {
            res.put("stateCode", "0");
        }
        return res;
    }
}
