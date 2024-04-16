package org.example.springangularmaterial.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor
@ToString @Data @Builder
public class Student {
    @Id
    private String id;
    private String fname;
    private String lname;
    private String code;
    private String programmeId;
    private String photo;
}
