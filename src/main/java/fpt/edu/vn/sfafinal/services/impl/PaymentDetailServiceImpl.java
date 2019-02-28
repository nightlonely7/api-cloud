package fpt.edu.vn.sfafinal.services.impl;

import fpt.edu.vn.sfafinal.entities.Payment;
import fpt.edu.vn.sfafinal.entities.PaymentDetail;
import fpt.edu.vn.sfafinal.entities.Product;
import fpt.edu.vn.sfafinal.entities.User;
import fpt.edu.vn.sfafinal.models.Cart;
import fpt.edu.vn.sfafinal.repositories.PaymentDetailRepository;
import fpt.edu.vn.sfafinal.repositories.PaymentRepository;
import fpt.edu.vn.sfafinal.repositories.ProductRepository;
import fpt.edu.vn.sfafinal.repositories.UserRepository;
import fpt.edu.vn.sfafinal.services.PaymentDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentDetailServiceImpl implements PaymentDetailService {

    private final PaymentDetailRepository paymentDetailRepository;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Autowired
    public PaymentDetailServiceImpl(PaymentDetailRepository paymentDetailRepository, ProductRepository productRepository, PaymentRepository paymentRepository, UserRepository userRepository) {
        this.paymentDetailRepository = paymentDetailRepository;
        this.productRepository = productRepository;
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PaymentDetail> findAll() {
        return paymentDetailRepository.findAll();
    }

    @Override
    public PaymentDetail findById(Integer id) {
        return paymentDetailRepository.findById(id).get();
    }


    @Override
    public void deleteById(Integer id) {
        paymentDetailRepository.deleteById(id);
    }
}
