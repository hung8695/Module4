package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(Product product) {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        productService.save(product);
        return modelAndView;
    }

    @GetMapping("/products")
    public ModelAndView listProduct(){
        Iterable<Product> products = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("/product/listProduct");
        modelAndView.addObject("products",products);
        return modelAndView;
    }


    @GetMapping("/edit-product/{id}")
    public ModelAndView showCreateForm(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/product/editProduct");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/edit-product")
    public ModelAndView update(@ModelAttribute("product") Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/listProduct");
        Iterable<Product> products = productService.findAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/remove-product/{id}")
    public ModelAndView showRemoveProductForm(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        if(product.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/product/removeProduct");
            modelAndView.addObject("product",product.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/remove-product")
    public String remove(@ModelAttribute("product") Product product){
        productService.remove(product.getId());
        return "redirect:/products";
    }


}
