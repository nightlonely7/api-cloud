package fpt.edu.vn.sfafinal.services;

import fpt.edu.vn.sfafinal.entities.Payment;
import fpt.edu.vn.sfafinal.models.Cart;

import java.util.List;

public interface PaymentService {

    List<Payment> findAll();

    Payment findById(Integer id);

    void save(List<Cart> carts);

    void deleteById(Integer id);
}
