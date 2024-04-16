package org.example.springangularmaterial.repository;

import org.example.springangularmaterial.entities.Payment;
import org.example.springangularmaterial.entities.PaymentStatus;
import org.example.springangularmaterial.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByPaymentStatus(PaymentStatus status);
    List<Payment> findByPaymentType(PaymentType type);
}
