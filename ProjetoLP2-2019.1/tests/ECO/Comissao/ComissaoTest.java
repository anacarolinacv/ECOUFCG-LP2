package ECO.Comissao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComissaoTest {
    private Comissao comissao1;
    private Comissao comissao2;
    private Comissao comissao3;


    @Test
    void testeConstrutor() {

        comissao1 = new Comissao("CCJC", "1111-0,2222-0,3333-0");
        assertEquals("CCJC" ,comissao1.getTema());
        assertEquals("1111-0,2222-0,3333-0", comissao1.getDniDeputados());

        comissao2 = new Comissao("SEILA", "4444-0,5555-0,6666-0");
        assertEquals("SEILA", comissao2.getTema());
        assertEquals("4444-0,5555-0,6666-0", comissao2.getDniDeputados());

        assertThrows(IllegalArgumentException.class, () -> comissao3 = new Comissao(" ", "123-0"));
        assertThrows(IllegalArgumentException.class, () -> comissao3 = new Comissao("a", "    "));
    }


    @Test
    void testeEquals() {
        comissao1 = new Comissao("CCJC", "1111-0,2222-0,3333-0");
        comissao2 = new Comissao("SEILA", "123-0,234-0");
        comissao3 = new Comissao("CCJC", "0000-0");

        assertNotEquals(comissao1, comissao2);
        assertNotEquals(comissao2, comissao3);
        assertEquals(comissao1, comissao3);
    }
}