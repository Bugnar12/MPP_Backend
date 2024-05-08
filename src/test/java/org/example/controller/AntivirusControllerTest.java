package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.backendspring_boot.backendspring_boot.BackendSpringBootApplication;
import org.backendspring_boot.backendspring_boot.controller.AntivirusController;
import org.backendspring_boot.backendspring_boot.entity.Antivirus;
import org.backendspring_boot.backendspring_boot.service.AntivirusServiceImpl;
import org.backendspring_boot.backendspring_boot.exception.RepositoryException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = AntivirusController.class)
@ContextConfiguration(classes= BackendSpringBootApplication.class)
@AutoConfigureMockMvc(addFilters = false)
public class AntivirusControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AntivirusServiceImpl antivirusService;

    @Test
    @WithMockUser(username = "testUser", roles = {"USER", "ADMIN"})
    public void testGetAntivirusByIdSuccess() throws Exception {
        Long antivirusId = 1L;
        Antivirus antivirus = new Antivirus("test", "test", "test", true, new java.sql.Date(System.currentTimeMillis()));
        antivirus.setId(antivirusId);

        when(antivirusService.getAntivirusById(antivirusId)).thenReturn(antivirus);

        mockMvc.perform(get("/antivirusList/" + antivirusId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("test")));
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER", "ADMIN"})
    public void testGetAntivirusByIdNotFound() throws Exception {
        Long antivirusId = -1L;

        when(antivirusService.getAntivirusById(antivirusId)).thenThrow(new RepositoryException("Antivirus not found"));

        mockMvc.perform(get("/antivirusList/" + antivirusId))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER", "ADMIN"})
    public void testGetAllAntivirus() throws Exception {
        Antivirus antivirus = new Antivirus("test", "test", "test", true, new java.sql.Date(System.currentTimeMillis()));
        antivirus.setId(1L);

        when(antivirusService.getAllAntivirus()).thenReturn(Collections.singletonList(antivirus));

        mockMvc.perform(get("/antivirusList"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("test")));
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER", "ADMIN"})
    public void testAddAntivirus() throws Exception {
        Antivirus antivirus = new Antivirus("test", "test", "test", true, new java.sql.Date(System.currentTimeMillis()));
        antivirus.setId(1L);

        mockMvc.perform(post("/antivirusList")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(antivirus)))
                .andExpect(status().isOk());

        ArgumentCaptor<Antivirus> antivirusCaptor = ArgumentCaptor.forClass(Antivirus.class);
        verify(antivirusService, times(1)).addAntivirus(antivirusCaptor.capture());

        Antivirus capturedAntivirus = antivirusCaptor.getValue();
        assertEquals(antivirus.getId(), capturedAntivirus.getId());
        assertEquals(antivirus.getName(), capturedAntivirus.getName());
        assertEquals(antivirus.getProducer(), capturedAntivirus.getProducer());
        assertEquals(antivirus.getDescription(), capturedAntivirus.getDescription());
        assertEquals(antivirus.isSupportMultiPlatform(), capturedAntivirus.isSupportMultiPlatform());
        assertEquals(antivirus.getReleaseDate().toString(), capturedAntivirus.getReleaseDate().toString());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER", "ADMIN"})
    public void testUpdateAntivirus() throws Exception {
        Antivirus antivirus = new Antivirus("test", "test", "test", true, new java.sql.Date(System.currentTimeMillis()));
        antivirus.setId(1L);

        doNothing().when(antivirusService).updateAntivirus(antivirus.getId(), antivirus);

        mockMvc.perform(put("/antivirusList/" + antivirus.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(antivirus)))
                .andExpect(status().isOk());

        ArgumentCaptor<Antivirus> antivirusCaptor = ArgumentCaptor.forClass(Antivirus.class);
        verify(antivirusService, times(1)).updateAntivirus(eq(antivirus.getId()), antivirusCaptor.capture());

        Antivirus capturedAntivirus = antivirusCaptor.getValue();
        assertEquals(antivirus.getId(), capturedAntivirus.getId());
        assertEquals(antivirus.getName(), capturedAntivirus.getName());
        assertEquals(antivirus.getProducer(), capturedAntivirus.getProducer());
        assertEquals(antivirus.getDescription(), capturedAntivirus.getDescription());
        assertEquals(antivirus.isSupportMultiPlatform(), capturedAntivirus.isSupportMultiPlatform());
        assertEquals(antivirus.getReleaseDate().toString(), capturedAntivirus.getReleaseDate().toString());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER", "ADMIN"})
    public void testUpdateAntivirusFailsValidator() throws Exception {
        Antivirus antivirus = new Antivirus("", "", "", true, new java.sql.Date(System.currentTimeMillis()));
        antivirus.setId(2L);

        doThrow(RepositoryException.class).when(antivirusService).updateAntivirus(antivirus.getId(), antivirus);

        mockMvc.perform(put("/antivirusList/" + antivirus.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(antivirus)))
                .andExpect(status().isBadRequest());
    }
    @Test
    @WithMockUser(username = "testUser", roles = {"USER", "ADMIN"})
    public void testDeleteAntivirus() throws Exception {
        Long antivirusId = 1L;

        doNothing().when(antivirusService).deleteAntivirus(antivirusId);

        mockMvc.perform(delete("/antivirusList/" + antivirusId))
                .andExpect(status().isOk());

        verify(antivirusService, times(1)).deleteAntivirus(antivirusId);
    }
}