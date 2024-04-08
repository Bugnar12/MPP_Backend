package org.example.service;

import org.example.entity.Antivirus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

@Service
public class AntivirusServiceImpl implements IService{
    private final List<Antivirus> antivirusList = new ArrayList<>();
    private int nextId = 1;

    public AntivirusServiceImpl(){
        try {
            Faker faker = new Faker();
            for(int i = 0; i < 100; i++)
            {
                Antivirus generatedAntivirus = generateRandomAntivirus(faker);
                addAntivirus(generatedAntivirus);
            }

            //--------------------
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            antivirusList.add(new Antivirus(1, "Kaspersky", "Kaspersky Lab", "Kaspersky Lab is a multinational cybersecurity and anti-virus provider headquartered in Moscow, Russia and operated by a holding company in the United Kingdom.", true, format.parse("2020-01-01")));
            antivirusList.add(new Antivirus(2, "Bitdefender", "Bitdefender", "Bitdefender is a Romanian cybersecurity and anti-virus software company.", true, format.parse("2001-11-06")));
            antivirusList.add(new Antivirus(3, "Avast", "Avast Software", "Avast is a Czech multinational cybersecurity software company headquartered in Prague, Czech Republic.", true, format.parse("2010-10-10")));
            antivirusList.add(new Antivirus(4, "Norton", "NortonLifeLock", "NortonLifeLock Inc. is an American software company headquartered in Tempe, Arizona, United States.", true, format.parse("2016-01-01")));
            antivirusList.add(new Antivirus(5, "ESET", "ESET", "ESET is a Slovak internet security company that offers anti-virus and firewall products.", false, format.parse("2017-01-01")));
            antivirusList.add(new Antivirus(6, "McAfee", "McAfee", "McAfee, LLC is an American global computer security software company headquartered in Santa Clara, California.", true, format.parse("2006-01-01")));
            nextId = antivirusList.size() + 1; //so we add the next antivirus with the next id
            System.out.println(antivirusList.size());
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
    }

    private Antivirus generateRandomAntivirus(Faker faker) {
        String name = faker.app().name();
        String producer = faker.company().name();
        String description = faker.lorem().sentence();
        boolean supportMultiPlatform = faker.bool().bool();
        Date releaseDate = faker.date().past(10, TimeUnit.DAYS);

        return new Antivirus(0, name, producer, description, supportMultiPlatform, releaseDate);
    }

    @Override
    public List<Antivirus> getAllAntivirus() {
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
