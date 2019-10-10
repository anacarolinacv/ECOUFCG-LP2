package ECO.PropostasLegislativas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PLTest {

    private PL pl1;
    private PL pl2;

    @Test
    void testeConstrutor(){
        pl1 = new PL("12345-0", 2000, "P000", "Economia", "umsitequalquerai.com", true, "1111");

        assertEquals("12345-0", pl1.getDNIAutor());
        assertEquals(2000, pl1.getAno());
        assertEquals("P000", pl1.getEmenta());
        assertEquals("Economia", pl1.getInteressesRelacionados());
        assertEquals("umsitequalquerai.com", pl1.getUrl());
        assertEquals("1111", pl1.getCodigo());
    }

    @Test
    void testToString() {
        pl1 = new PL("12345-0", 2000, "P000", "Economia", "umsitequalquerai.com", true, "1111");
        assertEquals("Projeto de Lei - 1111 - 12345-0 - P000 - Conclusiva - EM VOTACAO (CCJC)", pl1.toString());

        pl2 = new PL("1234-0", 1999, "P001", "Drogas", "site4i20.com", true, "2222");
        assertEquals("Projeto de Lei - 2222 - 1234-0 - P001 - Conclusiva - EM VOTACAO (CCJC)", pl2.toString());
    }
}