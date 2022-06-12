package com.blog.web;

import com.blog.domain.Article;
import com.blog.domain.DataDemo;
import com.blog.service.impl.ArticleServiceImpl;
import com.blog.service.impl.CommentServiceImpl;
import com.blog.service.impl.DataServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {
    @Autowired
    public ArticleServiceImpl articleService;
    @Autowired
    public DataServiceImpl dataService;
    @Autowired
    public CommentServiceImpl commentService;
    @RequestMapping("/")
    public ModelAndView index(@RequestParam(required=true,defaultValue="1") Integer page, @RequestParam(required=false,defaultValue="5") Integer pageSize){
        ModelAndView modelAndView =new ModelAndView("index");
        PageHelper.startPage(page, pageSize);
        List<Article> articles=articleService.queryAll();
        PageInfo<Article> pageInfo=new PageInfo<Article>(articles);
        modelAndView.addObject("articles",articles);
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/list")
    public String list(@RequestParam(required=true,defaultValue="1") Integer page, @RequestParam(required=false,defaultValue="5") Integer pageSize){
        ModelAndView modelAndView =new ModelAndView("/admin/dataDemo_list");
        PageHelper.startPage(page, pageSize);
        List<DataDemo> dataDemos = dataService.queryAll();
        PageInfo<DataDemo> pageInfo=new PageInfo<DataDemo>(dataDemos);
        modelAndView.addObject("dataDemos",dataDemos);
        modelAndView.addObject("pageInfo",pageInfo);
        return "/admin/dataDemo_list";
    }
}
