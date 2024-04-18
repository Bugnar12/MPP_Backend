package org.backendspring_boot.backendspring_boot.service;

import org.backendspring_boot.backendspring_boot.entity.Antivirus;
import org.backendspring_boot.backendspring_boot.exception.RepositoryException;

import java.util.List;

public interface IAntivirusService {
    List<Antivirus> getAllAntivirus();
    Antivirus getAntivirusById(Long id) throws RepositoryException;
    void addAntivirus(Antivirus antivirus);
    void updateAntivirus(Long id, Antivirus updatedAntvirus) throws RepositoryException;
    void deleteAntivirus(Long id) throws RepositoryException;
}
