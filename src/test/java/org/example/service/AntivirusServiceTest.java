/*
package org.example.service;

import org.backendspring_boot.backendspring_boot.BackendSpringBootApplication;
import org.backendspring_boot.backendspring_boot.entity.Antivirus;
import org.backendspring_boot.backendspring_boot.exception.RepositoryException;
import org.backendspring_boot.backendspring_boot.repository.AntivirusRepositoryJPA;
import org.backendspring_boot.backendspring_boot.service.AntivirusServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.ArgumentCaptor;

import java.sql.Date;
import java.util.Optional;


@SpringBootTest(classes = BackendSpringBootApplication.class)
public class AntivirusServiceTest {
    @MockBean
    AntivirusRepositoryJPA antivirusRepositoryJPA;

    private AntivirusServiceImpl antivirusService;

    @BeforeEach
    public void setUp() {
        this.antivirusService = new AntivirusServiceImpl(antivirusRepositoryJPA, null);
    }

    @Test
    public void testGetAntivirusByIdSuccess() throws Exception {
        Antivirus antivirusToFind = new Antivirus("test", "test", "test", true, new Date(System.currentTimeMillis()));
        antivirusToFind.setId((long) 1);
        Mockito.when(this.antivirusRepositoryJPA.findById(antivirusToFind.getId())).thenReturn(java.util.Optional.of(antivirusToFind));

        Long idToSearch = this.antivirusService.getAntivirusById(antivirusToFind.getId()).getId();
        Assertions.assertEquals(this.antivirusService.getAntivirusById(idToSearch).getId(), idToSearch);
    }

    @Test
    public void testGetAntivirusByIdFails() {
        Long idToSearch = (long) -1;
        Mockito.when(this.antivirusRepositoryJPA.findById(idToSearch)).thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(RepositoryException.class, () -> this.antivirusService.getAntivirusById(idToSearch));
    }

    @Test
    public void testAddAntivirus() throws Exception {
        Antivirus antivirusToAdd = new Antivirus("test", "test", "test", true, new Date(System.currentTimeMillis()));
        antivirusToAdd.setId((long) 1);

        Mockito.when(this.antivirusRepositoryJPA.save(antivirusToAdd)).thenReturn(antivirusToAdd);
        Mockito.when(this.antivirusRepositoryJPA.findById(antivirusToAdd.getId())).thenReturn(Optional.of(antivirusToAdd));
        this.antivirusService.addAntivirus(antivirusToAdd);

        Antivirus foundAntivirus = this.antivirusService.getAntivirusById(antivirusToAdd.getId());

        Assertions.assertEquals(antivirusToAdd, foundAntivirus);
    }

    @Test
    public void testUpdateAntivirusSuccess() {
        Antivirus testAntivirus = new Antivirus("test", "test", "test", true, new Date(System.currentTimeMillis()));
        testAntivirus.setId((long) 2);

        Mockito.when(this.antivirusRepositoryJPA.findById(testAntivirus.getId())).thenReturn(Optional.of(testAntivirus));
        Mockito.when(this.antivirusRepositoryJPA.save(testAntivirus)).thenReturn(testAntivirus);

        try {
            this.antivirusService.updateAntivirus(testAntivirus.getId(), testAntivirus);
        }

        catch (Exception ignored) {}
    }

*/
/*    @Test
    public void testUpdateAntivirusThrowsException() {
        Antivirus antivirusToUpdate = new Antivirus("test", "test", "test", true, new Date(System.currentTimeMillis()));
        Mockito.when(this.antivirusRepositoryJPA.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(RepositoryException.class, () -> this.antivirusService.updateAntivirus(antivirusToUpdate.getId(), antivirusToUpdate));
    }*//*


    @Test
    public void testDeleteAntivirus() {
        Antivirus antivirusToDelete = new Antivirus("test", "test", "test", true, new Date(System.currentTimeMillis()));
        antivirusToDelete.setId((long) 2);

        Mockito.when(this.antivirusRepositoryJPA.findById(antivirusToDelete.getId())).thenReturn(Optional.empty());

        try {
            this.antivirusService.deleteAntivirus(antivirusToDelete.getId());
        } catch (RepositoryException e) {
            Assertions.assertTrue(e.getMessage().contains("Antivirus not found : Delete failed"));
        }
    }
}*/
