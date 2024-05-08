package org.backendspring_boot.backendspring_boot.service;

import org.backendspring_boot.backendspring_boot.entity.Antivirus;
import org.backendspring_boot.backendspring_boot.entity.Customer;
import org.backendspring_boot.backendspring_boot.exception.RepositoryException;
import org.backendspring_boot.backendspring_boot.repository.AntivirusRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AntivirusServiceImpl implements IAntivirusService {
    private final AntivirusRepositoryJPA antivirusRepo;
    private final ICustomerService customerService;

    @Autowired
    public AntivirusServiceImpl(AntivirusRepositoryJPA antivirusRepo, ICustomerService customerService)
    {
        this.antivirusRepo = antivirusRepo;
        this.customerService = customerService;
    }

    @Override
    public List<Antivirus> getAllAntivirus() {
        return antivirusRepo.findAll();
    }

    @Override
    public Antivirus getAntivirusById(Long id) throws RepositoryException {
        return antivirusRepo.findById(id).orElseThrow(() -> new RepositoryException("Antivirus not found : Get by id failed"));
    }

    @Override
    public void addAntivirus(Antivirus antivirus) {
        antivirusRepo.save(antivirus);
    }

    @Override
    public void updateAntivirus(Long id, Antivirus updatedAntvirus) throws RepositoryException {
        if(id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }

        // Check if the Antivirus with the given id exists in the database
        Optional<Antivirus> antivirusOptional = antivirusRepo.findById(id);
        if(antivirusOptional.isEmpty()) {
            throw new RepositoryException("Antivirus not found : Update failed");
        }

        // Proceed with the update
        Antivirus antivirus = antivirusOptional.get();
        antivirus.setName(updatedAntvirus.getName());
        antivirus.setProducer(updatedAntvirus.getProducer());
        antivirus.setDescription(updatedAntvirus.getDescription());
        antivirus.setSupportMultiPlatform(updatedAntvirus.isSupportMultiPlatform());
        antivirusRepo.save(antivirus);
    }

    @Override
    public void deleteAntivirus(Long id) throws RepositoryException {
        Optional<Antivirus> antivirusOptional = antivirusRepo.findById(id);
        if(antivirusOptional.isPresent()) {
            Antivirus antivirus = antivirusOptional.get();
            List<Customer> customers = antivirus.getCustomerList();
            for(Customer customer : customers) {
                customer.setAntivirus(null);
                System.out.println(customer);
                customerService.updateCustomer(customer.getId(), customer);
                System.out.println(customer);
            }
            antivirusRepo.deleteById(id);
        } else {
            throw new RepositoryException("Antivirus not found : Delete failed");
        }
    }

    // In AntivirusServiceImpl.java
    public Map<Long, Integer> getAntivirusCustomerCount() {
        List<Antivirus> antivirusList = this.getAllAntivirus();
        Map<Long, Integer> antivirusCustomerCount = new HashMap<>();
        for (Antivirus antivirus : antivirusList) {
            antivirusCustomerCount.put(antivirus.getId(), antivirus.getNumberOfCustomers());
        }
        return antivirusCustomerCount;
    }

}
