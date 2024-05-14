package org.backendspring_boot.backendspring_boot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Users") ///"Users" beacause User is reserved in SQL Server
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @Email
    private String email;

    @Column(nullable = false)
    @Size(min = 6)
    private String password;

    @Column(nullable = false, unique = true)
    @Size(min = 4)
    private String username;

    ///constructor used when a user will register
    public User(String email, String password, String username)
    {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
