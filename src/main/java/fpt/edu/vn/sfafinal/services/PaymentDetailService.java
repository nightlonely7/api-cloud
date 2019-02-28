package fpt.edu.vn.sfafinal.services;

import fpt.edu.vn.sfafinal.entities.PaymentDetail;

import java.util.List;

public interface PaymentDetailService {

    List<PaymentDetail> findAll();

    PaymentDetail findById(Integer id);

    void deleteById(Integer id);
}
