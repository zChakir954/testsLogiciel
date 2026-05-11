package com.example.demo.service;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StatistiqueTests {

    @Test
    public void prixMoyen_avecDeuxVoitures_retourneLaMoyenne() {
        Voiture mockV1 = mock(Voiture.class);
        Voiture mockV2 = mock(Voiture.class);

        when(mockV1.getPrix()).thenReturn(10000);
        when(mockV2.getPrix()).thenReturn(5000);

        StatistiqueImpl stats = new StatistiqueImpl();

        stats.ajouter(mockV1);
        stats.ajouter(mockV2);

        assertEquals(7500,stats.prixMoyen());
        }
    
    @Test
    public void prixMoyen_avecUneVoiture_retourneLaMoyenne() {
        Voiture mockV1 = mock(Voiture.class);
        when(mockV1.getPrix()).thenReturn(10000);

        StatistiqueImpl stats = new StatistiqueImpl();

        stats.ajouter(mockV1);

        assertEquals(10000, stats.prixMoyen());
    }
}