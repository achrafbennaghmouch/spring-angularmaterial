package org.example.springangularmaterial;

import org.example.springangularmaterial.entities.Payment;
import org.example.springangularmaterial.entities.PaymentStatus;
import org.example.springangularmaterial.entities.PaymentType;
import org.example.springangularmaterial.entities.Student;
import org.example.springangularmaterial.repository.PaymentRepository;
import org.example.springangularmaterial.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class SpringAngularmaterialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAngularmaterialApplication.class, args);
	}
//@Bean
	CommandLineRunner  commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository){
		return args -> {
				studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).fname("Achraf")
						.code("GH642747").lname("Bengh").programmeId("SDIA").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).fname("Ayoub")
					.code("JH8675678").lname("Chakir").programmeId("SI").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).fname("Taha")
					.code("YZ67653").lname("Loulidi").programmeId("MIA").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).fname("Ayoub")
					.code("OI85768").lname("Rafiqui").programmeId("AVOCAT").build());

			PaymentType[] paymentTypes= PaymentType.values();
			Random random = new Random();
			studentRepository.findAll().forEach(student -> {
				for(int i=0 ; i<10; i++){
					int index = random.nextInt(paymentTypes.length);
					Payment payment = Payment.builder()
							.amount(1000+(int) (Math.random()+20000))
							.paymentType(paymentTypes[index])
							.paymentStatus(PaymentStatus.CREATED)
							.date(LocalDate.now())
							.student(student)
							.build();
					paymentRepository.save(payment);
				}
			});

		};
}
}
