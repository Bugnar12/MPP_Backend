package org.backendspring_boot.backendspring_boot.entity;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Antivirus")
public class Antivirus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , updatable = false)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(name="name", nullable = false)
    private String name;

    @NotBlank(message = "Producer is required")
    @Column(name="producer", nullable = false)
    private String producer;

    @NotBlank(message = "Description is required")
    @Column(name="description", nullable = false)
    private String description;


    @Column(name="supportMultiPlatform", nullable = true)
    private boolean supportMultiPlatform;

    @NotNull(message = "Release date is required")
    @Column(name="releaseDate", nullable = false)
    private java.sql.Date releaseDate;

    //cascade = CascadeType.REMOVE -> this will remove all customers associated with this antivirus
    //fetch = FetchType.EAGER -> this will load all customers associated with this antivirus ; it is NOT lazy loading
    @OneToMany(mappedBy = "antivirus", fetch = FetchType.EAGER)
    @JsonManagedReference
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

    public boolean validationFails(){
        return name == null || producer == null || description == null || releaseDate == null || name.isEmpty() || producer.isEmpty() || description.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Antivirus antivirus = (Antivirus) o;
        return supportMultiPlatform == antivirus.supportMultiPlatform &&
                Objects.equals(id, antivirus.id) &&
                Objects.equals(name, antivirus.name) &&
                Objects.equals(producer, antivirus.producer) &&
                Objects.equals(description, antivirus.description) &&
                Objects.equals(releaseDate, antivirus.releaseDate);
    }

    public int getNumberOfCustomers() {
        if (this.customerList == null) {
            return 0;
        }
        return this.customerList.size();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, producer, description, supportMultiPlatform, releaseDate);
    }
}
