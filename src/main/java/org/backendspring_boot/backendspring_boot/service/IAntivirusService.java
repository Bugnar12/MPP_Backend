package org.backendspring_boot.backendspring_boot.service;

import org.backendspring_boot.backendspring_boot.entity.Antivirus;

import java.util.List;

public interface IAntivirusService {
    List<Antivirus> getAllAntivirus();
    Antivirus getAntivirusById(Long id);
    void addAntivirus(Antivirus antivirus);
    void updateAntivirus(Long id, Antivirus updatedAntvirus);
    void deleteAntivirus(Long id);
}
