package org.backendspring_boot.backendspring_boot.entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Antivirus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , updatable = false)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="producer", nullable = false)
    private String producer;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="supportMultiPlatform", nullable = true)
    private boolean supportMultiPlatform;

    @Column(name="releaseDate", nullable = false)
    private java.sql.Date releaseDate;


    @JsonManagedReference
    @OneToMany(mappedBy = "antivirus", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Customer> customerList;

    public Antivirus(String name, String producer, String description, boolean supportMultiPlatform, java.sql.Date releaseDate) {
        this.name = name;
        this.producer = producer;
        this.description = description;
        this.supportMultiPlatform = supportMultiPlatform;
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Antivirus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", description='" + description + '\'' +
                ", supportMultiPlatform=" + supportMultiPlatform +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
