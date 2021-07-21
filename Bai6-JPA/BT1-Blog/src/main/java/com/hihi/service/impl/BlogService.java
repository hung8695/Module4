package com.hihi.service.impl;

import com.hihi.model.Blog;
import com.hihi.repository.IBlogRepository;
import com.hihi.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository blogRepository;
    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        blogRepository.remove(id);
    }
}
