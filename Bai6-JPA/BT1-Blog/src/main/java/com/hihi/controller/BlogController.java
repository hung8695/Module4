package com.hihi.controller;

import com.hihi.model.Blog;
import com.hihi.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("blogs")
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @GetMapping
    public ModelAndView findAll(){
        return new ModelAndView("/listBlog").addObject("blogs",blogService.findAll());
    }
    @GetMapping("/create-blog")
    public ModelAndView showCreateBlog(){
        ModelAndView modelAndView = new ModelAndView("/createBlog");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }
    @PostMapping("/create-blog")
    public ModelAndView save(@ModelAttribute ("blog") Blog blog){
        ModelAndView modelAndView = new ModelAndView("/listBlog");
        blogService.save(blog);
        modelAndView.addObject("blogs",blogService.findAll());
        return modelAndView;
    }
    @GetMapping("/edit-blog/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Blog blog = blogService.findById(id);
        if(blog!=null){
            ModelAndView modelAndView = new ModelAndView("/editBlog");
            modelAndView.addObject("blog",blog);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error");
            return modelAndView;
        }
    }
    @PostMapping("/edit-blog")
    public ModelAndView update(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/listBlog");
        modelAndView.addObject("blogs",blogService.findAll());
        return modelAndView;
    }

}
