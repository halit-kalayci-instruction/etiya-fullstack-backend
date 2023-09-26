package com.northwind.etiya.product;

import com.northwind.etiya.category.Category;
import com.northwind.etiya.supplier.Supplier;
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

    @GetMapping("getById")
    public Product getById(@RequestParam int id){
        return productRepo.findById(id).orElseThrow();
    }

    @PostMapping()
    public void add(@RequestBody AddProductRequest request){
        Product product = new Product();
        product.setId(99);
        product.setName(request.getProductName());
        product.setSupplier(Supplier.builder().supplierId(request.getSupplierId()).build());
        product.setCategory(Category.builder().categoryId(request.getCategoryId()).build());
        product.setDiscontinued(0);
        productRepo.save(product);
    }

}
