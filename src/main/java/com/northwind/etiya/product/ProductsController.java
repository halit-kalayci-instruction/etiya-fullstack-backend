package com.northwind.etiya.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@CrossOrigin
public class ProductsController {

    private ProductRepo productRepo;

    @Autowired
    public ProductsController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping()
    public List<Product> getAll(){
        return productRepo.findAll();
    }

    @PostMapping()
    public void add(AddProductRequest request){

    }

}
