package fpt.edu.vn.sfafinal.repositories;

import fpt.edu.vn.sfafinal.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
