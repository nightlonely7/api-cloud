package fpt.edu.vn.sfafinal.repositories;

import fpt.edu.vn.sfafinal.entities.Payment;
import fpt.edu.vn.sfafinal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findAllByUser(User user);
}
