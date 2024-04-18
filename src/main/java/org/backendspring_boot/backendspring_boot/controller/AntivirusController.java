package org.backendspring_boot.backendspring_boot.controller;

import org.backendspring_boot.backendspring_boot.exception.RepositoryException;
import org.backendspring_boot.backendspring_boot.service.AntivirusServiceImpl;
import org.backendspring_boot.backendspring_boot.entity.Antivirus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AntivirusController {

    @Autowired
    private AntivirusServiceImpl antivirusService;

    public AntivirusController(AntivirusServiceImpl antivirusService) {
        this.antivirusService = antivirusService;
    }

    @GetMapping("/antivirusList/{id}")
    public ResponseEntity<Antivirus> getAntivirusById(@PathVariable Long id) {
        try {
            Antivirus antivirus = antivirusService.getAntivirusById(id);
            return ResponseEntity.ok(antivirus);
        } catch (RepositoryException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/antivirusList")
    public ResponseEntity<List<Antivirus>> getAllAntivirus() {
        List<Antivirus> antivirusList = antivirusService.getAllAntivirus();
        return ResponseEntity.ok(antivirusList);
    }

    @PostMapping("/antivirusList")
    public ResponseEntity<Antivirus> addAntivirus(@Valid @RequestBody Antivirus antivirus) {
        if(antivirus.validationFails())
        {
            return ResponseEntity.badRequest().build();
        }
        antivirusService.addAntivirus(antivirus);
        return ResponseEntity.ok(antivirus);
    }

    @PutMapping("/antivirusList/{id}")
    public ResponseEntity<Antivirus> updateAntivirus(@PathVariable Long id, @Valid @RequestBody Antivirus updatedAntivirus) {
        if(updatedAntivirus.validationFails())
        {
            return ResponseEntity.badRequest().build();
        }
        try {
            antivirusService.updateAntivirus(id, updatedAntivirus);
            return ResponseEntity.ok(updatedAntivirus);
        } catch (RepositoryException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/antivirusList/{id}")
    public ResponseEntity<Antivirus> deleteAntivirus(@PathVariable Long id) {
        try {
            antivirusService.deleteAntivirus(id);
            return ResponseEntity.ok().build();
        } catch (RepositoryException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @MessageMapping("/broadcast")
    @SendTo("/topic/reply")
    public String broadcastMessage(@Payload String message) {
        return "Recieved message " + message;
    }
}

/*    @Scheduled(fixedRate = 10000)
    public void sendAntivirusPeriodically()
    {
        Antivirus generatedAntivirus = antivirusService.generateAndAddAntivirus();
        simpMessagingTemplate.convertAndSend("/topic/antivirus", generatedAntivirus);
    }*/