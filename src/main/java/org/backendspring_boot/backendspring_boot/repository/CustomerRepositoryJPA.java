package org.backendspring_boot.backendspring_boot.repository;

import org.backendspring_boot.backendspring_boot.entity.Antivirus;
import org.backendspring_boot.backendspring_boot.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface CustomerRepositoryJPA extends JpaRepository<Customer, Long> {
    List<Customer> findAllByAntivirusId(Long antivirus_id);
    Page<Customer> findAllByAntivirusId(Long antivirus_id, Pageable pageable);

    @Query("SELECT COUNT(*) FROM Customer c WHERE c.antivirus.id = ?1")
    int noOfCustomersByAntivirus(Long antivirus_id);
}
