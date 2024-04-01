package org.example.controller;

import org.example.entity.Antivirus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.service.AntivirusServiceImpl;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class AntivirusController {

    @Autowired
    private AntivirusServiceImpl antivirusService;

    public AntivirusController(AntivirusServiceImpl antivirusService)
    {
        this.antivirusService = antivirusService;
    }
    @GetMapping("/antivirusList")
    public List<Antivirus> getAllAntiviruses(){
        return antivirusService.getAllAntivirus();
    }
    @GetMapping("antivirusList/{id}")
    public Antivirus getAntivirusById(@PathVariable int id){
        return antivirusService.getAntivirusById(id);
    }

    @PostMapping
    public void addAntivirus(@RequestBody Antivirus antivirus)
    {
        antivirusService.addAntivirus(antivirus);
    }
    @PutMapping("/{id}")
    public void updateAntivirus(@PathVariable int id, @RequestBody Antivirus updatedAntivirus)
    {
        antivirusService.updateAntivirus(id, updatedAntivirus);
    }

    @DeleteMapping("/{id}")
    public void deleteAntivirus(@PathVariable int id){
        antivirusService.deleteAntivirus(id);
    }

}
