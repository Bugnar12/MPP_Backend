package org.backendspring_boot.backendspring_boot.service;

import org.backendspring_boot.backendspring_boot.entity.Antivirus;
import org.backendspring_boot.backendspring_boot.repository.AntivirusRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AntivirusServiceImpl implements IAntivirusService {
    private final AntivirusRepositoryJPA antivirusRepo;

    @Autowired
    public AntivirusServiceImpl(AntivirusRepositoryJPA antivirusRepo)
    {
        this.antivirusRepo = antivirusRepo;
    }

    @Override
    public List<Antivirus> getAllAntivirus() {
        return antivirusRepo.findAll();
    }

    @Override
    public Antivirus getAntivirusById(Long id) {
        return antivirusRepo.findById(id).orElse(null);
    }

    @Override
    public void addAntivirus(Antivirus antivirus) {
        antivirusRepo.save(antivirus);
    }

    @Override
    public void updateAntivirus(Long id, Antivirus updatedAntvirus) {
        Antivirus antivirus = antivirusRepo.findById(id).orElse(null);
        if(antivirus != null)
        {
            antivirus.setName(updatedAntvirus.getName());
            antivirus.setProducer(updatedAntvirus.getProducer());
            antivirus.setDescription(updatedAntvirus.getDescription());
            antivirus.setSupportMultiPlatform(updatedAntvirus.isSupportMultiPlatform());
            antivirusRepo.save(antivirus);
        }
    }

    @Override
    public void deleteAntivirus(Long id) {
        antivirusRepo.deleteById(id);
    }

}
