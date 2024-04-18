package org.backendspring_boot.backendspring_boot.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , updatable = false)
    private Long id;

    @Column(name="fullName", nullable = false)
    private String fullName;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="age", nullable = false)
    private int age;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "antivirus_id", nullable = false)
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
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", antivirus_id=" + (antivirus != null ? antivirus.getId() : "null") +
                '}';
    }
}
