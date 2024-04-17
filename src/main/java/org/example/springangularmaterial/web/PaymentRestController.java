package org.example.springangularmaterial.web;

import org.example.springangularmaterial.entities.Payment;
import org.example.springangularmaterial.entities.PaymentStatus;
import org.example.springangularmaterial.entities.PaymentType;
import org.example.springangularmaterial.entities.Student;
import org.example.springangularmaterial.repository.PaymentRepository;
import org.example.springangularmaterial.repository.StudentRepository;
import org.example.springangularmaterial.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;

    public PaymentRestController(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.paymentRepository =paymentRepository;
    }

    @GetMapping("/payments")
    public List<Payment> allPayments(){
        return paymentRepository.findAll();
    }

    @GetMapping("/students/{code}/payments")
    public List<Payment> allPaymentsByStudents(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }
    @GetMapping("/paymentsStatus")
    public List<Payment> allPaymentsByStatus(@RequestParam PaymentStatus status){
        return paymentRepository.findByPaymentStatus(status);
    }

    @GetMapping("/paymentsType")
    public List<Payment> allPaymentsByTypes(@RequestParam PaymentType type){
        return paymentRepository.findByPaymentType(type);
    }

    @GetMapping("/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id){
        return paymentRepository.findById(id).get();
    }

    @GetMapping("/students")
    public List<Student> allStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/students/{code}")
    public Student getStudentByCode(@PathVariable String code){
        return studentRepository.findByCode(code);
    }

    @GetMapping("/studentsProgram")
    public List<Student> getStudentsByProgramId(@RequestParam String programID){
        return studentRepository.findByprogrammeId(programID);
    }

    @PutMapping("/paymentsStatus/{id}")
    public  Payment updatePaymentStatus(PaymentStatus status, @PathVariable Long id){
        return this.paymentService.updatePaymentStatus(status,id);
    }

    @PostMapping(value = "/savePayment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file, LocalDate date, PaymentType type,double amount, String studentCode) throws IOException {
       return this.paymentService.savePayment(file,date,type,amount,studentCode);
    }

    @GetMapping(value = "/paymentFile/{paymentId}", produces = {MediaType.APPLICATION_PDF_VALUE})
    public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
        return this.paymentService.getPaymentFile(paymentId);
    }
}
