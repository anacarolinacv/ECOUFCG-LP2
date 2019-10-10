package ECO.Pessoa;

import ECO.Pessoa.Deputado;
import ECO.Pessoa.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {

    private Pessoa pessoa1;
    private Pessoa pessoa2;
    private Pessoa pessoa3;
    private Pessoa pessoa4;
    private Pessoa pessoa5;

    private Deputado deputado1;
    private Deputado deputado2;

    @BeforeEach
    void setUp() {
        pessoa1 = new Pessoa("Ana", "213292920-3", "PB", "saude, economia");
        pessoa2 = new Pessoa("Ana", "213292925-4", "PB", "saude, economia","PMDB");
        pessoa3 = new Pessoa("Ana", "213292925-5", "PB","");
        pessoa4 = new Pessoa("Ana", "213292925-6", "PB","","PT");
        pessoa5 = new Pessoa("Ana", "213292920-3", "PB", "saude, economia");
    }

    @Test
    void PessoaTestSemPartido() {
        assertEquals("Ana - 213292920-3 (PB) - Interesses: saude, economia", pessoa1.toString());
    }
    @Test
    void pessoaConstrutorComPartidoComInteresseTest() {
        assertEquals("Ana - 213292925-4 (PB) - PMDB - Interesses: saude, economia", pessoa2.toString());
    }
    @Test
    void pessoaConstrutorSemPartidoSemInteresseTest() {
        assertEquals("Ana - 213292925-5 (PB)", pessoa3.toString());
    }
    @Test
    void pessoaConstrutorComPartidoSemInteresseTest() {
        assertEquals("Ana - 213292925-6 (PB) - PT", pessoa4.toString());
    }
    @Test
    void toString1Test() {
        assertEquals("Ana - 213292920-3 (PB) - Interesses: saude, economia", pessoa1.toString());
    }
    @Test
    void toString2Test() {
        assertEquals("Ana - 213292925-4 (PB) - PMDB - Interesses: saude, economia", pessoa2.toString());
    }

    @Test
    void toString3Test() {
        assertEquals("Ana - 213292925-5 (PB)", pessoa3.toString());
    }

    @Test
    void toString4Test() {
        assertEquals("Ana - 213292925-6 (PB) - PT", pessoa4.toString());
    }

    @Test
    void equalsTest1() {
        assertNotEquals(pessoa1, pessoa2);
        assertNotEquals(pessoa1, pessoa3);
        assertNotEquals(pessoa1, pessoa4);

    }
    @Test
    void equalsTest2() {
        assertEquals(pessoa1, pessoa5);
    }
}