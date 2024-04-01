package service;

import entity.Antivirus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AntivirusServiceImpl implements IService{
    private List<Antivirus> antivirusList = new ArrayList<>();
    private int nextId = 1;

    public AntivirusServiceImpl(){
        antivirusList.add(new Antivirus(1, "Kaspersky", "Kaspersky Lab", "Kaspersky is a well-known antivirus software", true, new Date("2020-01-01")));
        antivirusList.add(new Antivirus(2, "Bitdefender", "Bitdefender", "Bitdefender is a Romanian antivirus.", true, new Date("2001-11-06")));
        antivirusList.add(new Antivirus(3, "Avast", "Avast Software", "Avast is a Czech antivirus software", true, new Date("2010-10-10")));
        antivirusList.add(new Antivirus(4, "Norton", "NortonLifeLock", "Norton is an antivirus software", true, new Date("2016-01-01")));
        antivirusList.add(new Antivirus(5, "McAfee", "McAfee", "McAfee is an antivirus software", true, new Date("2006-01-01")));
        antivirusList.add(new Antivirus(6, "ESET", "ESET", "ESET is an antivirus software", true, new Date("2017-01-01")));
        antivirusList.add(new Antivirus(7, "SuperAntiSpyware", "SuperAntiSpyware", "SuperAntiSpyware is an antivirus software", false, new Date("2000-01-01")));
        antivirusList.add(new Antivirus(8, "Malwarebytes", "Malwarebytes", "Malwarebytes is an antivirus software", true, new Date("2018-01-01")));
        antivirusList.add(new Antivirus(9, "Spybot", "Safer-Networking", "Spybot is an antivirus software", false, new Date("2002-01-01")));
        antivirusList.add(new Antivirus(10, "Windows Defender", "Microsoft", "Windows Defender is an antivirus software", true, new Date("2015-01-01")));
        antivirusList.add(new Antivirus(11, "Avira", "Avira", "Avira is an antivirus software", true, new Date("2019-01-01")));
        antivirusList.add(new Antivirus(12, "Panda", "Panda Security", "Panda is an antivirus software", true, new Date("2011-01-01")));
        antivirusList.add(new Antivirus(13, "AVG", "AVG Technologies", "AVG is an antivirus software", true, new Date("2008-01-01")));
        antivirusList.add(new Antivirus(14, "WhatAVirus", "WhatAVirus", "WhatAVirus is an antivirus software", false, new Date("2017-01-01")));
        antivirusList.add(new Antivirus(15, "VirusDestroyer", "VirusDestroyer", "VirusDestroyer is an antivirus software", false, new Date("2000-01-01")));
        nextId = antivirusList.size() + 1; //so we add the next antivirus with the next id
    }

    @Override
    public List<Antivirus> getALlAntivirus() {
        return antivirusList;
    }

    @Override
    public Antivirus getAntivirusById(int id) {
        return antivirusList.stream().filter(antivirus -> antivirus.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void addAntivirus(Antivirus antivirus) {
        antivirus.setId(nextId);
        nextId++;
        antivirusList.add(antivirus);
    }

    @Override
    public void updateAntivirus(int id, Antivirus updatedAntvirus) {
        Antivirus antivirus = getAntivirusById(id);
        if(antivirus != null){
            antivirus.setName(updatedAntvirus.getName());
            antivirus.setProducer(updatedAntvirus.getProducer());
            antivirus.setDescription(updatedAntvirus.getDescription());
            antivirus.setSupportMultiPlatform(updatedAntvirus.isSupportMultiPlatform());
            antivirus.setReleaseDate(updatedAntvirus.getReleaseDate());
        }
    }

    @Override
    public void deleteAntivirus(int id) {
        Antivirus antivirus = getAntivirusById(id);
        if(antivirus != null){
            antivirusList.remove(antivirus);
        }
    }
}
