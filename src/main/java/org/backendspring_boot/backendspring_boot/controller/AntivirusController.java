package org.backendspring_boot.backendspring_boot.controller;
import org.backendspring_boot.backendspring_boot.service.AntivirusServiceImpl;

import org.backendspring_boot.backendspring_boot.entity.Antivirus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AntivirusController {
    private static final Logger logger = LoggerFactory.getLogger(AntivirusController.class);

    @Autowired
    private AntivirusServiceImpl antivirusService;

    public AntivirusController(AntivirusServiceImpl antivirusService)
    {
        this.antivirusService = antivirusService;
    }
    @GetMapping("/antivirusList")
    public ResponseEntity<List<Antivirus>> getAllAntivirus(){
        List<Antivirus> antivirusList = antivirusService.getAllAntivirus();
        antivirusList.forEach(antivirus -> logger.info(antivirus.toString()));
        logger.info("Getting all antivirus\n");
        return ResponseEntity.ok(antivirusList);
    }

    @MessageMapping("/broadcast")
    @SendTo("/topic/reply")

    public String broadcastMessage(@Payload String message)
    {
        return "Recieved message " + message;
    }

/*    @Scheduled(fixedRate = 10000)
    public void sendAntivirusPeriodically()
    {
        Antivirus generatedAntivirus = antivirusService.generateAndAddAntivirus();
        simpMessagingTemplate.convertAndSend("/topic/antivirus", generatedAntivirus);
    }*/
}