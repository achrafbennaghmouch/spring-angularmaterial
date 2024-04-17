package org.example.springangularmaterial.services;

import org.example.springangularmaterial.entities.Payment;
import org.example.springangularmaterial.entities.PaymentStatus;
import org.example.springangularmaterial.entities.PaymentType;
import org.example.springangularmaterial.repository.PaymentRepository;
import org.example.springangularmaterial.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {
    private PaymentRepository paymentRepository;
    private StudentRepository studentRepository;

    public PaymentService(PaymentRepository paymentRepository, StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }
    public Payment savePayment(MultipartFile file, LocalDate date, PaymentType type, double amount, String studentCode) throws IOException {
        Path folderPath = Paths.get(System.getProperty("user.home"),"enset-data","payments");
        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }
        String filename= UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"),"enset-data","payments",filename+".pdf");
        Files.copy(file.getInputStream(),filePath);
        Payment payment = Payment.builder()
                .date(date)
                .paymentType(type)
                .student(studentRepository.findByCode(studentCode))
                .amount(amount)
                .file(filePath.toUri().toString())
                .paymentStatus(PaymentStatus.CREATED)
                .build();
        return paymentRepository.save(payment);
    }
    public  Payment updatePaymentStatus(PaymentStatus status, Long id){
        Payment payment = paymentRepository.findById(id).get();
        payment.setPaymentStatus(status);
        return paymentRepository.save(payment);
    }
    public byte[] getPaymentFile(Long paymentId) throws IOException {
        Payment payment = paymentRepository.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }
}
