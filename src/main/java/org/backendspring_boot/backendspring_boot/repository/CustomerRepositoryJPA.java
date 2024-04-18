package org.backendspring_boot.backendspring_boot.repository;

import org.backendspring_boot.backendspring_boot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepositoryJPA extends JpaRepository<Customer, Long> {
    List<Customer> findAllByAntivirusId(Long antivirus_id);
}
