package com.northwind.etiya.product;

import com.northwind.etiya.category.Category;
import com.northwind.etiya.exceptions.types.BusinessException;
import com.northwind.etiya.supplier.Supplier;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@CrossOrigin()
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
    public void add(@RequestBody @Valid AddProductRequest request){

        // eğer aynı isimde ürün varsa BusinessException fırlat..
        Product product = new Product();
        product.setName(request.getProductName());
        product.setSupplier(Supplier.builder().supplierId(request.getSupplierId()).build());
        product.setCategory(Category.builder().categoryId(request.getCategoryId()).build());
        product.setDiscontinued(0);
        product.setUnitPrice(request.getUnitPrice());
        product.setUnitsInStock(request.getUnitsInStock());
        productRepo.save(product);
    }

    @PutMapping()
    public void update(@RequestBody UpdateProductRequest request){
        Product productToUpdate = productRepo.findById(request.getId()).orElseThrow();

        productToUpdate.setName(request.getProductName());
        productToUpdate.setSupplier(Supplier.builder().supplierId(request.getSupplierId()).build());
        productToUpdate.setCategory(Category.builder().categoryId(request.getCategoryId()).build());

        productRepo.save(productToUpdate);
    }

    @DeleteMapping()
    public void delete(@RequestParam int id){
        Product productToDelete = productRepo.findById(id).orElseThrow();
        productRepo.delete(productToDelete);
    }
}
