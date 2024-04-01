package service;

import entity.Antivirus;

import java.util.List;

public interface IService {
    List<Antivirus> getALlAntivirus();
    Antivirus getAntivirusById(int id);
    void addAntivirus(Antivirus antivirus);
    void updateAntivirus(int id, Antivirus updatedAntvirus);
    void deleteAntivirus(int id);
}
