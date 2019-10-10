package ECO.Pessoa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeputadoTest {

    private Pessoa pessoa1;
    private Pessoa pessoa2;
    private Pessoa pessoa3;
    private Deputado deputado1;
    private Deputado deputado2;
    private Deputado deputado3;

    @BeforeEach
    void setUp() {
        pessoa1 = new Pessoa("Ana", "213292925-4", "PB", "saude, economia","PMDB");
        pessoa2 = new Pessoa("Ana", "213292925-6", "PB","","PT");
        pessoa3 = new Pessoa("Ana", "213292925-5", "PB","");
        deputado1 = new Deputado("Ana", "213292925-4", "PB", "saude, economia", "PMDB", "09082007");
        deputado2 = new Deputado("Ana", "213292925-6", "PB", "", "PT",  "10052015");
        deputado3 = new Deputado("Aécio", "14071997","MG", "Cocaina", "P$DB", "06062006");
    }

    @Test
    void converteData() {
        assertEquals("09/08/2007", deputado1.converteData("09082007"));
        assertEquals("Ana - 213292925-4 (PB) - PMDB - Interesses: saude, economia", pessoa1.toString());
        assertEquals("Ana - 213292925-6 (PB) - PT", pessoa2.toString());
    }

    @Test
    void getDataDeInicio() {
        assertEquals("09/08/2007", deputado1.getDataDeInicio());
        assertEquals("10/05/2015", deputado2.getDataDeInicio());

    }

    @Test
    void getQuantidadeDeLeis() {
        assertEquals(0, deputado1.getQuantidadeDeLeis());
        assertEquals(0, deputado2.getQuantidadeDeLeis());
    }

    @Test
    void toString1() {
        assertEquals("POL: Ana - 213292925-4 (PB) - PMDB - Interesses: saude, economia - 09/08/2007 - 0 Leis", deputado1.toString());
        assertEquals("POL: Ana - 213292925-6 (PB) - PT - 10/05/2015 - 0 Leis", deputado2.toString());
        assertEquals("POL: Aécio - 14071997 (MG) - P$DB - Interesses: Cocaina - 06/06/2006 - 0 Leis", deputado3.toString());
    }
}