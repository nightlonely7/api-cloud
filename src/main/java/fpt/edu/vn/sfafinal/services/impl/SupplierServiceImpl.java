package fpt.edu.vn.sfafinal.services.impl;

import fpt.edu.vn.sfafinal.entities.Supplier;
import fpt.edu.vn.sfafinal.repositories.SupplierRepository;
import fpt.edu.vn.sfafinal.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier findById(Integer id) {
        return supplierRepository.findById(id).get();
    }

    @Override
    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public void deleteById(Integer id) {
        supplierRepository.deleteById(id);
    }
}
