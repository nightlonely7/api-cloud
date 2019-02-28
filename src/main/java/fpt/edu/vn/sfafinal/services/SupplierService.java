package fpt.edu.vn.sfafinal.services;

import fpt.edu.vn.sfafinal.entities.Supplier;

import java.util.List;

public interface SupplierService {

    List<Supplier> findAll();

    Supplier findById(Integer id);

    void save(Supplier supplier);

    void deleteById(Integer id);
}
