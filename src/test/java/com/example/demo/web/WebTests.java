package com.example.demo.web;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import com.example.demo.service.StatistiqueImpl;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebTests {

    @MockBean
    StatistiqueImpl statistiqueImpl;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getStatistiques_avecDeuxVoitures_retourneOketJson() throws Exception {        
        when(statistiqueImpl.prixMoyen())
        .thenReturn(new Echantillon(2, 7500));

        mockMvc.perform(get("/statistique"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreDeVoitures").value(2))
                .andExpect(jsonPath("$.prixMoyen").value(7500));
    }

    @Test
    public void getStatistiques_sansVoiture_retourneErreur() throws Exception {
        when(statistiqueImpl.prixMoyen())
            .thenThrow(new ArithmeticException);

        mockMvc.perform(get("/statistique"))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

     @Test
    public void creerVoiture_avecJsonValide_appelleAjouter() throws Exception {

        String voitureJson = "{\"marque\":\"Audi\",\"prix\":10000}";

        mockMvc.perform(post("/voiture")
                .contentType("application/json")
                .content(voitureJson))
               .andDo(print())
               .andExpect(status().isOk());                             

        verify(statistiqueImpl, times(1)).ajouter(any());
    }

}
