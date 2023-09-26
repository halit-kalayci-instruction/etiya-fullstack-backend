package com.northwind.etiya.supplier;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("suppliers")
@CrossOrigin
public class SuppliersController {
    private final SupplierRepo supplierRepo;

    public SuppliersController(SupplierRepo supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    @GetMapping()
    public List<Supplier> getAll(){
        return this.supplierRepo.findAll();
    }
}
