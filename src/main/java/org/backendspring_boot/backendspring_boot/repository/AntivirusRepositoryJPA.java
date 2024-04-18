package org.backendspring_boot.backendspring_boot.repository;

import org.backendspring_boot.backendspring_boot.entity.Antivirus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AntivirusRepositoryJPA extends JpaRepository<Antivirus, Long> {
}
