package ECO.PropostasLegislativas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ECO.Util.Validador.*;
import static ECO.Util.Validador.validadorAno;
import static org.junit.jupiter.api.Assertions.*;

class ControllerPLSTest {
    private ControllerPLS controlePLS;

    @BeforeEach
    void setUp() {
        controlePLS = new ControllerPLS();
    }

    @Test
    void cadastrarPL() {
        assertEquals("PL 1/2000", controlePLS.cadastrarPL("111", 2000, "231", "Acessibilidade", "eu_faco_coisas_sociais_importantes.com", true));
        assertEquals("PL 1/2010", controlePLS.cadastrarPL("222", 2010, "232", "Transporte", "www.google.com", false));

        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPL("  ", 2000, "21231", "Nada", "aeho.com", false));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPL("123**00000", 1999, "21231", "Nada", "aeho.com", false));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPL("123", 1997, "21231", "Nada", "aeho.com", false));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPL("123", 2000, "   ", "Nada", "aeho.com", false));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPL("123", 2000, "21231", "    ", "aeho.com", false));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPL("123", 2000, "21231", "Nada", "   ", false));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPL("123", 2077, "21231", "Nada", "   ", false));
    }

    @Test
    void cadastrarPLP() {
        assertEquals("PLP 1/1998", controlePLS.cadastrarPLP("123", 1998, "13212", "Educação", "eu_me_importo_com_a_educacao.com", "qualquer um"));
        assertEquals("PLP 1/2015", controlePLS.cadastrarPLP("666", 2015, "333", "Cultura", "EIEIEEJEJE", "Quero umas musiquinha"));

        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPLP("  ", 2000, "21231", "Nada", "aeho.com", "nada"));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPLP("123**00000", 1999, "21231", "Nada", "aeho.com", "nada"));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPLP("123", 1997, "21231", "Nada", "aeho.com", "nada"));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPLP("123", 2000, "   ", "Nada", "aeho.com", "nada"));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPLP("123", 2000, "21231", "    ", "aeho.com", "nada"));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPLP("123", 2000, "21231", "Nada", "   ", "nada"));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPLP("123", 2090, "21231", "Nada", "   ", "nada"));
    }

    @Test
    void cadastrarPEC() {
        assertEquals("PEC 1/2019", controlePLS.cadastrarPEC("456", 2019, "13213", "ÓDIO", "prende_todo_mundo_ae.com", "Prende geral."));
        assertEquals("PEC 1/2018", controlePLS.cadastrarPEC("999", 2018, "1321", "Programação", "Programar_eh_top.com", "P2 EH TOP"));

        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPLP("  ", 2000, "21231", "Nada", "aeho.com", "nada"));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPLP("123**00000", 1999, "21231", "Nada", "aeho.com", "nada"));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPLP("123", 1997, "21231", "Nada", "aeho.com", "nada"));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPLP("123", 2000, "   ", "Nada", "aeho.com", "nada"));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPLP("123", 2000, "21231", "    ", "aeho.com", "nada"));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPLP("123", 2000, "21231", "Nada", "   ", "nada"));
        assertThrows(IllegalArgumentException.class, () -> controlePLS.cadastrarPLP("123", 2030, "21231", "Nada", "   ", "nada"));

    }

    @Test
    void exibirProjeto() {

        controlePLS.cadastrarPL("123", 2000, "1", "Nenhum", "naosei.com", true);
        controlePLS.cadastrarPLP("456", 2019, "2", "Não", "aaaaaaaaaaaa", "Ble");
        controlePLS.cadastrarPEC("789", 2018, "3", "Nops", "aaaahn não", "Necas");

        assertEquals("Projeto de Lei - PL 1/2000 - 123 - 1 - Conclusiva - EM VOTACAO (CCJC)", controlePLS.exibirProjeto("PL 1/2000"));
        assertEquals("Projeto de Lei Complementar - PLP 1/2019 - 456 - 2 - Ble - EM VOTACAO (CCJC)", controlePLS.exibirProjeto("PLP 1/2019"));
        assertEquals("Projeto de Emenda Constitucional - PEC 1/2018 - 789 - 3 - Necas - EM VOTACAO (CCJC)", controlePLS.exibirProjeto("PEC 1/2018"));
    }

    @Test
    void verificaBooleanConclusivo() {
        controlePLS.cadastrarPL("111", 2000, "231", "Acessibilidade", "eu_faco_coisas_sociais_importantes.com", true);
        assertTrue(controlePLS.verificaBooleanConclusivo("PL 1/2000"));

        controlePLS.cadastrarPL("222", 2010, "232", "Transporte", "www.google.com", false);
        assertFalse(controlePLS.verificaBooleanConclusivo("PL 1/2010"));

        controlePLS.cadastrarPL("333", 2005, "1231561", "Alimentação", "ifood.com", true);
        assertTrue(controlePLS.verificaBooleanConclusivo("PL 1/2005"));

    }

    @Test
    void exibirTramitacao(){
        controlePLS.cadastrarPL("123", 2000, "1", "Nenhum", "naosei.com", true);
        controlePLS.cadastrarPLP("456", 2019, "2", "Não", "aaaaaaaaaaaa", "Ble");
        controlePLS.cadastrarPEC("789", 2018, "3", "Nops", "aaaahn não", "Necas");

        assertEquals("EM VOTACAO (CCJC)", this.controlePLS.exibirTramitacao("PL 1/2000"));
        assertEquals("EM VOTACAO (CCJC)", this.controlePLS.exibirTramitacao("PLP 1/2019"));
        assertEquals("EM VOTACAO (CCJC)", this.controlePLS.exibirTramitacao("PEC 1/2018"));
    }
}