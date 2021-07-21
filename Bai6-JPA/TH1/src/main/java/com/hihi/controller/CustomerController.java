package com.hihi.controller;

import com.hihi.model.Customer;
import com.hihi.service.ICustomerService;
import com.hihi.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    ICustomerService customerService ;
    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute("customer") Customer customer){
        ModelAndView modelAndView = new ModelAndView("/index");
        customerService.insertWidthStoredProcedure(customer);
        modelAndView.addObject("customers",customerService.findAll());
        modelAndView.addObject("message","New customer created successfully!");
        return modelAndView;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("/index");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("message","New customer created successfully!");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("customer", customer);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView update(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("customers",customerService.findAll());
        modelAndView.addObject("messageUpdate","Update customer  successfully!");
        return modelAndView;
    }
}
