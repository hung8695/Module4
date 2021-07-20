package cg.wbd.grandemonstration.controller;

import cg.wbd.grandemonstration.model.Customer;
import cg.wbd.grandemonstration.service.CustomerService;
import cg.wbd.grandemonstration.service.CustomerServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CustomerController {

    private CustomerService customerService = new CustomerServiceFactory().getInstance();

    @GetMapping("/customers")
    public String showList(Model model){
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers",customers);


        return  "customers/list.jsp";
    }


}
