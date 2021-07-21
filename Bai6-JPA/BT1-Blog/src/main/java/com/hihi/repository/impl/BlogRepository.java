package com.hihi.repository.impl;

import com.hihi.model.Blog;
import com.hihi.repository.IBlogRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BlogRepository implements IBlogRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Blog> findAll() {
        String sql = "SELECT b FROM Blog b";
        TypedQuery<Blog>  query = em.createQuery(sql,Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findById(long id) {
      TypedQuery<Blog> query = em.createQuery("SELECT b FROM Blog b WHERE b.id = :id",Blog.class);
      query.setParameter("id",id);

      return query.getSingleResult();
    }

    @Override
    public void save(Blog blog) {
        if(blog.getId()!=null){
            em.merge(blog);
        } else {
            em.persist(blog);
        }
    }

    @Override
    public void remove(long id) {
        if(findById(id)!=null){
            em.remove(id);
        }
    }
}
