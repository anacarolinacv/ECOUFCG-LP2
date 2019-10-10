package ECO.PropostasLegislativas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PLPTest {

    private PLP plp1;
    private PLP plp2;


    @Test
    void testeConstrutor(){
        plp1 = new PLP("12345-0", 2000, "P000", "Economia", "umsitequalquerai.com", "Prenderam o marquin!", "1111");

        assertEquals("12345-0", plp1.getDNIAutor());
        assertEquals(2000, plp1.getAno());
        assertEquals("P000", plp1.getEmenta());
        assertEquals("Economia", plp1.getInteressesRelacionados());
        assertEquals("Prenderam o marquin!", plp1.getArtigo());
        assertEquals("umsitequalquerai.com", plp1.getUrl());
        assertEquals("1111", plp1.getCodigo());
    }

    @Test
    void testToString() {
        plp1 = new PLP("12345-0", 2000, "P000", "Economia", "umsitequalquerai.com", "to com fome", "1111");
        assertEquals("Projeto de Lei Complementar - 1111 - 12345-0 - P000 - to com fome - EM VOTACAO (CCJC)", plp1.toString());

        plp2 = new PLP("1234-0", 1999, "P001", "Drogas", "site4i20.com", "Acharam 39kg de pó no meu voante, bixo", "2222");
        assertEquals("Projeto de Lei Complementar - 2222 - 1234-0 - P001 - Acharam 39kg de pó no meu voante,  bixo - EM VOTACAO (CCJC)", plp2.toString());
    }
}