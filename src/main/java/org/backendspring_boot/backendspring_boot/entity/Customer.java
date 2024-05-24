package org.backendspring_boot.backendspring_boot.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Customer", indexes = {
        @Index(name = "idx_antivirus", columnList= "antivirus_id")
})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , updatable = false)
    private Long id;

    @NotBlank(message = "Full name is required")
    @Column(name="fullName", nullable = false)
    private String fullName;

    @NotBlank(message = "Email is required")
    @Column(name="email", nullable = false)
    private String email;

    @Max(value = 110, message = "Age must be less than 110")
    @Min(value = 18, message = "Age must be greater than 18")
    @Column(name="age", nullable = false)
    private int age;

    //JsonIgnore
    @ManyToOne
    @JoinColumn(name = "antivirus_id", nullable = true)
    @JsonBackReference
    private Antivirus antivirus;

    public Customer(String fullName, String email, int age) {
        this.fullName = fullName;
        this.email = email;
        this.age = age;
    }
    public Customer(String fullName, String email, int age, Antivirus antivirus) {
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.antivirus = antivirus;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", antivirus_id=" + (antivirus != null ? antivirus.getId() : "null") +
                '}';
    }

    public boolean validationFails() {
        return this.fullName == null || this.email == null || this.antivirus == null;
    }
}
