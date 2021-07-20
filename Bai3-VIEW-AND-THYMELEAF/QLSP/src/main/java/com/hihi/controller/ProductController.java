package com.hihi.controller;

import com.hihi.model.Product;
import com.hihi.service.IProductService;
import com.hihi.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService=new ProductService() ;

    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping("/upload")
    public ModelAndView showCreateIMG() {
        ModelAndView modelAndView = new ModelAndView("/createIMG");
        return modelAndView;
    }


    @PostMapping("/show")
    public ModelAndView saveIMG(@RequestParam MultipartFile image, @RequestParam String imageLink) {
        String fileName = image.getOriginalFilename();
        try {
            FileCopyUtils.copy(image.getBytes(),
                    new File(fileUpload + fileName)); // coppy ảnh từ ảnh nhận được vào thư mục quy định,
            // đường dẫn ảo là /hihi/
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView("/index2");
        modelAndView.addObject("src", fileName);
        modelAndView.addObject("srcImg", imageLink);
        return modelAndView;
    }


    @GetMapping ()
    public String index(Model model){
        model.addAttribute("products",productService.findAll());
        return "/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("product", new Product() );
        return "/create";
    }

    @PostMapping ("/save")
    public String save(Product product){
        product.setId((int) (Math.random() * 10000));
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id,Model model){
        model.addAttribute(productService.findById(id));
        return "/edit";
    }

    @PostMapping("/update")
    public String update(Product product, RedirectAttributes redirectAttributes){
        productService.update(product.getId(), product);
        redirectAttributes.addFlashAttribute("success","Edit product successfully!");
        return "redirect:/products";
    }

    @GetMapping("/{id}/remove")
    public String remove(Model model, @PathVariable int id){
        model.addAttribute("customer",productService.findById(id));
        return "/delete";
    }

    @PostMapping("/delete")
    public String remove(Product product,RedirectAttributes redirectAttributes){
        productService.remove(product.getId());
        redirectAttributes.addFlashAttribute("success","Delete product successfully!");
        return "redirect:/products";
    }

    @GetMapping ("/search")
    public String search(Model model,@RequestParam  String search){
        model.addAttribute("products",productService.findByName(search));
        return "/index";
    }
}
