package controller;

import entity.Antivirus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.AntivirusServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/antiviruses")
public class AntivirusController {

    @Autowired
    private AntivirusServiceImpl antivirusService;

    public AntivirusController(AntivirusServiceImpl antivirusService)
    {
        this.antivirusService = antivirusService;
    }
    @GetMapping
    public List<Antivirus> getAllAntiviruses(){
        return antivirusService.getALlAntivirus();
    }
    @GetMapping("/{id}")
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
