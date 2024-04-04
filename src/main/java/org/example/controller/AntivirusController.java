package org.example.controller;

import org.example.entity.Antivirus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.service.AntivirusServiceImpl;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class AntivirusController {
    private static final Logger logger = LoggerFactory.getLogger(AntivirusController.class);

    @Autowired
    private AntivirusServiceImpl antivirusService;

    public AntivirusController(AntivirusServiceImpl antivirusService)
    {
        this.antivirusService = antivirusService;
    }
    @GetMapping("/antivirusList")
    public List<Antivirus> getAllAntiviruses(){
        logger.info("Getting all antiviruses\n");
        return antivirusService.getAllAntivirus();
    }
    @GetMapping("antivirusList/{id}")
    public Antivirus getAntivirusById(@PathVariable int id){
        logger.info("Getting antivirus with id: \n" + id + "\n");
        return antivirusService.getAntivirusById(id);
    }

    @PostMapping("/antivirusList")
    public void addAntivirus(@RequestBody Antivirus antivirus)
    {
        antivirusService.addAntivirus(antivirus);
        logger.info("Adding antivirus: " + antivirus + "\n");
    }
    @PutMapping("antivirusList/{id}")
    public void updateAntivirus(@PathVariable int id, @RequestBody Antivirus updatedAntivirus)
    {
        antivirusService.updateAntivirus(id, updatedAntivirus);
        logger.info("Updating antivirus with id: " + id + " with data: " + updatedAntivirus + "\n");
    }

    @DeleteMapping("antivirusList/{id}")
    public void deleteAntivirus(@PathVariable int id){
        antivirusService.deleteAntivirus(id);
        logger.info("Deleting antivirus with id: " + id + "\n");
    }

}
