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

        Echantillon resultat = stats.prixMoyen();
        assertEquals(2, resultat.getNombreDeVoitures());
        assertEquals(7500, resultat.getPrixMoyen());
        }
    
    @Test
    public void prixMoyen_avecUneVoiture_retourneLaMoyenne() {
        Voiture mockV1 = mock(Voiture.class);
        when(mockV1.getPrix()).thenReturn(10000);

        StatistiqueImpl stats = new StatistiqueImpl();

        stats.ajouter(mockV1);

        Echantillon resultat = stats.prixMoyen();
        assertEquals(1, resultat.getNombreDeVoitures());  // cas spécifique à 1 voiture
        assertEquals(10000, resultat.getPrixMoyen());
    }

    @Test
    public void prixMoyen_sansVoiture_leveUneArithmeticException() {
        StatistiqueImpl stats = new StatistiqueImpl();

        assertThrows(ArithmeticException.class, () -> {
            stats.prixMoyen();
        });
    }
}