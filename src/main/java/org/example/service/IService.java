package org.example.service;

import org.example.entity.Antivirus;

import java.util.List;

public interface IService {
    List<Antivirus> getAllAntivirus();
    Antivirus getAntivirusById(int id);
    void addAntivirus(Antivirus antivirus);
    void updateAntivirus(int id, Antivirus updatedAntvirus);
    void deleteAntivirus(int id);
}
