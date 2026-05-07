package com.ruslk.pricetracker.controller;


import com.ruslk.pricetracker.model.Product;
import com.ruslk.pricetracker.repository.ProductRepository;
import com.ruslk.pricetracker.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ScraperService scraperService;

    @Autowired
    ProductRepository repository;



    @GetMapping
    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PostMapping
    public Product addNewProduct(@RequestBody Product product){
        Double price = scraperService.getPriceByUrl(product.getUrl());

        product.setCurrentPrice(price);
        return repository.save(product);
    }

}
