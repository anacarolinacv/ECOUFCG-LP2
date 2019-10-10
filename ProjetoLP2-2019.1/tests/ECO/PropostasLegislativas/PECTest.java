package ECO.PropostasLegislativas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PECTest {

    private PEC pec1;
    private PEC pec2;

        @Test
        void testeConstrutor(){
            pec1 = new PEC("12345-0", 2000, "P000", "Economia", "umsitequalquerai.com", "Prenderam o marquin!", "1111");

            assertEquals("12345-0", pec1.getDNIAutor());
            assertEquals(2000, pec1.getAno());
            assertEquals("P000", pec1.getEmenta());
            assertEquals("Economia", pec1.getInteressesRelacionados());
            assertEquals("Prenderam o marquin!", pec1.getArtigo());
            assertEquals("umsitequalquerai.com", pec1.getUrl());
            assertEquals("1111", pec1.getCodigo());
        }

        @Test
        void testToString() {
            pec1 = new PEC("12345-0", 2000, "P000", "Economia", "umsitequalquerai.com", "to com fome", "1111");
            assertEquals("Projeto de Emenda Constitucional - 1111 - 12345-0 - P000 - to com fome - EM VOTACAO (CCJC)", pec1.toString());

            pec2 = new PEC("1234-0", 1999, "P001", "Drogas", "site4i20.com", "Acharam 39kg de pó no meu voante, bixo", "2222");
            assertEquals("Projeto de Emenda Constitucional - 2222 - 1234-0 - P001 - Acharam 39kg de pó no meu voante,  bixo - EM VOTACAO (CCJC)", pec2.toString());
        }
    }