package fpt.edu.vn.sfafinal.controllers;

import fpt.edu.vn.sfafinal.entities.Payment;
import fpt.edu.vn.sfafinal.entities.PaymentDetail;
import fpt.edu.vn.sfafinal.entities.User;
import fpt.edu.vn.sfafinal.models.Cart;
import fpt.edu.vn.sfafinal.repositories.PaymentDetailRepository;
import fpt.edu.vn.sfafinal.services.PaymentService;
import fpt.edu.vn.sfafinal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/payments")
@CrossOrigin
public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;
    private final PaymentDetailRepository paymentDetailRepository;

    @Autowired
    public PaymentController(PaymentService paymentService, UserService userService, PaymentDetailRepository paymentDetailRepository) {
        this.paymentService = paymentService;
        this.userService = userService;
        this.paymentDetailRepository = paymentDetailRepository;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> readAll(Principal principal) {

        User user = (User) userService.loadUserByUsername(principal.getName());

        List<Payment> payments = paymentService.findAllByUser(user);
        for (Payment payment : payments) {
            List<PaymentDetail> paymentDetails = paymentDetailRepository.findByPayment(payment);
            payment.setPaymentDetails(paymentDetails);
        }
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> readById(@PathVariable("id") Integer id) {
        Payment payment = paymentService.findById(id);
        return ResponseEntity.ok(payment);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody List<Cart> carts) {

        if (!paymentService.isCartProductQuantityValid(carts))
            return ResponseEntity.ok("Quantity Exceed");
        paymentService.save(carts);

        return ResponseEntity.ok().build();
    }

}
