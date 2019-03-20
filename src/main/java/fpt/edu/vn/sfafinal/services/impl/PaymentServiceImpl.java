package fpt.edu.vn.sfafinal.services.impl;

import fpt.edu.vn.sfafinal.entities.Payment;
import fpt.edu.vn.sfafinal.entities.PaymentDetail;
import fpt.edu.vn.sfafinal.entities.Product;
import fpt.edu.vn.sfafinal.entities.User;
import fpt.edu.vn.sfafinal.exceptions.UnauthorizedException;
import fpt.edu.vn.sfafinal.models.Cart;
import fpt.edu.vn.sfafinal.repositories.PaymentDetailRepository;
import fpt.edu.vn.sfafinal.repositories.PaymentRepository;
import fpt.edu.vn.sfafinal.repositories.ProductRepository;
import fpt.edu.vn.sfafinal.repositories.UserRepository;
import fpt.edu.vn.sfafinal.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PaymentDetailRepository paymentDetailRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, ProductRepository productRepository, UserRepository userRepository, PaymentDetailRepository paymentDetailRepository) {
        this.paymentRepository = paymentRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.paymentDetailRepository = paymentDetailRepository;
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment findById(Integer id) {
        return paymentRepository.findById(id).get();
    }

    @Override
    public void save(List<Cart> carts) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new UnauthorizedException("Unauthorized!");
        }
        User user = userRepository.findByUsername(auth.getName());

        Payment payment = new Payment();
        payment.setUser(user);
        paymentRepository.saveAndFlush(payment);

        for (Cart cart : carts) {
            PaymentDetail paymentDetail = new PaymentDetail();
            Product product = productRepository.findById(cart.getProduct().getId()).get();
            paymentDetail.setProduct(product);
            paymentDetail.setPrice(product.getPrice());
            paymentDetail.setQuantity(cart.getQuantity());
            paymentDetail.setPayment(payment);
            product.setQuantity(product.getQuantity() - cart.getQuantity());
            productRepository.save(product);
            paymentDetailRepository.save(paymentDetail);
        }
        //Payment savedPayment = paymentRepository.save(payment);
        //System.out.println(savedPayment);
        System.out.println(paymentDetailRepository.findAll());
    }

    @Override
    public void deleteById(Integer id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public boolean isCartProductQuantityValid(List<Cart> carts) {
        Product product;
        for(Cart cart: carts) {
            product = productRepository.findById(cart.getProduct().getId()).get();
            if(cart.getQuantity() > product.getQuantity())
                return false;
        }
        return true;
    }
}
