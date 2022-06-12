package com.blog.web;

import com.blog.domain.DataDemo;
import com.blog.service.impl.CommentServiceImpl;
import com.blog.service.impl.DataServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;

@Controller
public class DataDemoController {

    @Autowired
    DataServiceImpl dataService;
    @Autowired
    public CommentServiceImpl commentService;


    @RequestMapping("/admin/dataDemo/list")
    public ModelAndView articleList(@RequestParam(required=true,defaultValue="1") Integer page, @RequestParam(required=false,defaultValue="10") Integer pageSize){
        PageHelper.startPage(page, pageSize);
        List<DataDemo> dataDemos=dataService.queryAll();
        PageInfo<DataDemo> pageInfo=new PageInfo<DataDemo>(dataDemos);
        ModelAndView modelAndView=new ModelAndView("/admin/dataDemo_list");
        modelAndView.addObject("dataDemos",dataDemos);
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }
    @RequestMapping("/admin/dataDemo/add")
    public ModelAndView articleAdd(){
        ModelAndView modelAndView=new ModelAndView("/admin/dataDemo_add");

        return modelAndView;
    }
    @RequestMapping("/admin/dataDemo/add/do")
    public String articleAddDo(HttpServletRequest request,RedirectAttributes redirectAttributes){
        DataDemo dataDemo=new DataDemo();
        dataDemo.setName(request.getParameter("name"));
        dataDemo.setNum(request.getParameter("num"));
        dataDemo.setKeywords(request.getParameter("keywords"));
        dataDemo.setPicture(request.getParameter("picture"));
        if (dataService.insert(dataDemo)){
            redirectAttributes.addFlashAttribute("succ", "add success！");
            return "redirect:/admin/dataDemo/add";
        }else {
            redirectAttributes.addFlashAttribute("error", "add error！");
            return "redirect:/admin/dataDemo/add";
        }
    }

    @RequestMapping(value = "/admin/dataDemo/search")
    public ModelAndView articleSearch(HttpServletRequest request){
        String word=request.getParameter("word");
        List<DataDemo> dataDemos=dataService.selectByWord(word);

        ModelAndView modelAndView=new ModelAndView("/admin/dataDemo_list");
        modelAndView.addObject("dataDemos",dataDemos);
        return modelAndView;
    }
    @RequestMapping(value = "/admin/dataDemo/edit")
    public ModelAndView articleEdit(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        DataDemo dataDemo=dataService.selectById(id);
        ModelAndView modelAndView=new ModelAndView("/admin/dataDemo_edit");
        modelAndView.addObject("dataDemo",dataDemo);
        return modelAndView;
    }
    @RequestMapping(value = "/admin/dataDemo/edit/do")
    public ModelAndView articleEditDo(HttpServletRequest request){
        DataDemo dataDemo=new DataDemo();
        dataDemo.setId(Integer.parseInt(request.getParameter("id")));
        dataDemo.setName(request.getParameter("name"));
        dataDemo.setNum(request.getParameter("num"));
        dataDemo.setPicture(request.getParameter("picture"));
        dataDemo.setKeywords(request.getParameter("keywords"));
        ModelAndView modelAndView=new ModelAndView("/admin/dataDemo_edit");
        if (dataService.update(dataDemo)){
            modelAndView.addObject("succ", "update success！");
        }else {
            modelAndView.addObject("error", "update error！");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/api/dataDemo/del", method = RequestMethod.POST)
    public @ResponseBody Object loginCheck(HttpServletRequest request) {
        int id=Integer.parseInt(request.getParameter("id"));
        int result=dataService.deleteById(id);
        HashMap<String, String> res = new HashMap<String, String>();
        if (result==1){
            res.put("stateCode", "1");
        }else {
            res.put("stateCode", "0");
        }
        return res;
    }
}
