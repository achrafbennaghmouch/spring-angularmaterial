package org.example.springangularmaterial.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor
@ToString @Data @Builder
public class Student {
    @Id
    private String id;
    private String fname;
    private String lname;
    @Column(unique = true)
    private String code;
    private String programmeId;
    private String photo;
}
