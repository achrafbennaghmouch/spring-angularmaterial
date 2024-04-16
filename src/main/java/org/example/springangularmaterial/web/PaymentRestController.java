package org.example.springangularmaterial.web;

import org.example.springangularmaterial.entities.Payment;
import org.example.springangularmaterial.entities.PaymentStatus;
import org.example.springangularmaterial.entities.PaymentType;
import org.example.springangularmaterial.entities.Student;
import org.example.springangularmaterial.repository.PaymentRepository;
import org.example.springangularmaterial.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

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
        return studentRepository.findByprogramId(programID);
    }

}
