package org.example.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Antivirus {
    @Id
    private int id;
    private String name;
    private String producer;
    private String description;
    private boolean supportMultiPlatform;
    private Date releaseDate;

    @OneToMany(mappedBy = "antivirus")
    private List<Customer> customerList;

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
