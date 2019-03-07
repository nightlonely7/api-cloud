package fpt.edu.vn.sfafinal.controllers;

import fpt.edu.vn.sfafinal.entities.Supplier;
import fpt.edu.vn.sfafinal.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/suppliers")
@CrossOrigin
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> readAll() {
        List<Supplier> suppliers = supplierService.findAll();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> readById(@PathVariable("id") Integer id) {
        Supplier supplier = supplierService.findById(id);
        return ResponseEntity.ok(supplier);
    }

    @PostMapping
    public ResponseEntity create(
            @Valid @RequestBody Supplier supplier) {
        supplierService.save(supplier);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(
            @PathVariable("id") Integer id,
            @Valid @RequestBody Supplier supplier) {
        supplier.setId(id);
        supplierService.save(supplier);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        supplierService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
