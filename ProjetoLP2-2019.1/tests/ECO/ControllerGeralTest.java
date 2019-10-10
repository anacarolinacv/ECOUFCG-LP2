package ECO;

import ECO.Comissao.ControllerComissao;
import ECO.Pessoa.ControllerPessoa;
import ECO.PropostasLegislativas.ControllerPLS;
import ECO.Votacao.ControllerVotacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerGeralTest {
    private ControllerGeral controlador;
    private ControllerComissao controleComissao;
    private ControllerPessoa controlePessoa;
    private ControllerPLS controlePLS;
    private ControllerVotacao controleVotacao;

    @BeforeEach
    void setUp() {
        controlador = new ControllerGeral();
        controleComissao = new ControllerComissao();
        controlePessoa = new ControllerPessoa();
        controlePLS = new ControllerPLS();
        controleVotacao = new ControllerVotacao();
    }

    @Test
    void cadastrarPessoaSemPartido() {
        controlador.cadastrarPessoa("Joao", "123564789-0", "PB", "");
        controlador.cadastrarPessoa("Maria", "987654321-0", "CE", "politica, novela");
        controlador.cadastrarPessoa("Pedro", "135797531-1", "RO", "futebol");
        assertEquals("Joao - 123564789-0 (PB)", controlador.exibePessoa("123564789-0"));
        assertEquals("Maria - 987654321-0 (CE) - Interesses: politica, novela", controlador.exibePessoa("987654321-0"));
        assertEquals("Pedro - 135797531-1 (RO) - Interesses: futebol", controlador.exibePessoa("135797531-1"));
        assertThrows(IllegalArgumentException.class, () -> controlador.cadastrarPessoa("", "132321300-0", "PB", ""));
        assertThrows(IllegalArgumentException.class, () -> controlador.cadastrarPessoa("Luciano", "", "PB", ""));
        assertThrows(IllegalArgumentException.class, () -> controlador.cadastrarPessoa("Thayane", "132321300-4", "", "nao sei"));
        assertThrows(IllegalArgumentException.class, () -> controlador.cadastrarPessoa("Joao", "132321300,0", "PB", ""));
    }

    @Test
    void cadastrarPessoaComPartido() {
        controlador.cadastrarPessoa("Daniel", "123645468-0", "SP", "", "PSTS");
        controlador.cadastrarPessoa("Paulo", "123456789-0", "RJ", "Social", "PRTS");
        controlador.cadastrarPessoa("Joaquim", "123321456-9", "PB", "", "JPM");
        assertEquals("Daniel - 123645468-0 (SP) - PSTS", controlador.exibePessoa("123645468-0"));
        assertEquals("Paulo - 123456789-0 (RJ) - PRTS - Interesses: Social", controlador.exibePessoa("123456789-0"));
        assertEquals("Joaquim - 123321456-9 (PB) - JPM", controlador.exibePessoa("123321456-9"));
        assertThrows(IllegalArgumentException.class, () -> controlador.cadastrarPessoa(" ", "132321300-1", "PB", "", "EU"));
        assertThrows(IllegalArgumentException.class, () -> controlador.cadastrarPessoa("Ana", "   ", "PB", "", "PSOL"));
        assertThrows(IllegalArgumentException.class, () -> controlador.cadastrarPessoa("Luciano", "132321300-3", "    ", "", "EU"));
    }

    @Test
    void cadastrarDeputado() {
        controlador.cadastrarPessoa("José", "123564789-0", "PB", "");
        assertEquals ("José - 123564789-0 (PB)", controlador.exibePessoa("123564789-0"));
        controlador.cadastrarPessoa("Daniel", "123645468-0", "SP", "", "PSTS");
        assertEquals ("Daniel - 123645468-0 (SP) - PSTS", controlador.exibePessoa("123645468-0"));
        controlador.cadastrarPessoa("Paulo", "123456789-0", "RJ", "Social", "PRTS");
        assertEquals ("Paulo - 123456789-0 (RJ) - PRTS - Interesses: Social", controlador.exibePessoa("123456789-0"));
        controlador.cadastrarPessoa("Joaquim", "123321456-9", "PB", "", "JPM");
        assertEquals ("Joaquim - 123321456-9 (PB) - JPM", controlador.exibePessoa("123321456-9"));

        controlador.cadastrarDeputado("123645468-0", "09102000");
        assertEquals ("POL: Daniel - 123645468-0 (SP) - PSTS - 09/10/2000 - 0 Leis", controlador.exibePessoa("123645468-0"));
        controlador.cadastrarDeputado("123456789-0", "13051986");
        assertEquals ("POL: Paulo - 123456789-0 (RJ) - PRTS - Interesses: Social - 13/05/1986 - 0 Leis", controlador.exibePessoa("123456789-0"));

        assertThrows(IllegalArgumentException.class, () -> controlador.cadastrarDeputado("bvdbv", "10111000"));
        assertThrows(IllegalArgumentException.class, () -> controlador.cadastrarDeputado("b8928b", "10111001"));
        assertThrows(IllegalArgumentException.class, () -> controlador.cadastrarDeputado("9999999-9", "10111002"));
        assertThrows(IllegalArgumentException.class, () -> controlador.cadastrarDeputado("123321456-9", "evhhu"));
        assertThrows(IllegalArgumentException.class, () -> controlador.cadastrarDeputado("123321456-9", "11111111111"));
        assertThrows(IllegalArgumentException.class, () -> controlador.cadastrarDeputado("123321456-9", "35102000"));
        assertThrows(IllegalArgumentException.class, () -> controlador.cadastrarDeputado("123321456-9", "20112020"));
    }

    @Test
    void exibePessoa() {
        this.controlador.cadastrarPessoa("Luciano", "11", "PB", "");
        this.controlador.cadastrarPessoa("Ana", "12", "PB", "nada", "PT");
        this.controlador.cadastrarPessoa("Artur", "13", "PB", "nada");
        this.controlador.cadastrarPessoa("Gutemberg", "14", "PB", "");

        assertEquals("Luciano - 11 (PB)", this.controlador.exibePessoa("11"));
        assertEquals("Ana - 12 (PB) - PT - Interesses: nada", this.controlador.exibePessoa("12"));
        assertEquals("Artur - 13 (PB) - Interesses: nada" , this.controlador.exibePessoa("13"));
        assertEquals("Gutemberg - 14 (PB)", this.controlador.exibePessoa("14"));
    }

    @Test
    void cadastrarPartido() {
        this.controlador.cadastrarPartido("PT");
        this.controlador.cadastrarPartido("PSDF");
        this.controlador.cadastrarPartido("PSOL");
        this.controlador.cadastrarPartido("PSTS");

        assertEquals("PSDF,PSOL,PSTS,PT", this.controlador.exibeBase());

        this.controlador.cadastrarPartido("AA");
        this.controlador.cadastrarPartido("ZZ");

        assertEquals("AA,PSDF,PSOL,PSTS,PT,ZZ", this.controlador.exibeBase());
    }

    @Test
    void cadastrarComissao() {
        this.controlador.cadastrarPessoa("Toin", "111", "CE", "Economia", "PTS");
        this.controlador.cadastrarPessoa("Nilso", "123", "PB", "Saúde", "PMLS");
        this.controlador.cadastrarPessoa("Zezin", "456", "PB", "Humor", "PMCA");
        this.controlador.cadastrarPessoa("Detinha", "789", "PB", "Gastronomia", "PFCF");

        this.controlador.cadastrarDeputado("111", "11102010");
        this.controlador.cadastrarDeputado("123", "10112013");
        this.controlador.cadastrarDeputado("456", "03062015");
        this.controlador.cadastrarDeputado("789", "01012000");

        this.controlador.cadastrarComissao("TURURU", "111");
        this.controlador.cadastrarComissao("MUSICA TRISTE NARUTO", "123,456,789");

        assertTrue(controlador.getControleComissao().getMapaComissoes().containsKey("TURURU"));
        assertTrue(controlador.getControleComissao().getMapaComissoes().containsKey("MUSICA TRISTE NARUTO"));
        assertEquals("111", this.controlador.getControleComissao().getMapaComissoes().get("TURURU").getDniDeputados());
        assertEquals("123,456,789", this.controlador.getControleComissao().getMapaComissoes().get("MUSICA TRISTE NARUTO").getDniDeputados());

        assertThrows(IllegalArgumentException.class, () -> this.controlador.cadastrarComissao("  ", "111,123,456,789"));
        assertThrows(IllegalArgumentException.class, () -> this.controlador.cadastrarComissao("QUALQUER", "   "));
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
    void votarComissao() {
        controlador.cadastrarPessoa("Luciano", "118110400", "PB", "Jogos,Tecnologia", "PLS");
        controlador.cadastrarPessoa("Ana", "116161056", "PB", "Moda,Tecnologia", "PT");
        controlador.cadastrarPessoa("Artur", "13210361", "PB", "Medicina,Tecnologia", "PNR");
        controlador.cadastrarPessoa("Gutemberg", "36146311", "PB", "Medicina,Educação", "PTS");

        controlador.cadastrarPartido("PLS");
        controlador.cadastrarPartido("PT");
        controlador.cadastrarPartido("PNR");

        controlador.cadastrarDeputado("118110400", "11102011");
        controlador.cadastrarDeputado("116161056", "09072006");
        controlador.cadastrarDeputado("13210361", "02012015");
        controlador.cadastrarDeputado("36146311", "03082014");

        controlador.cadastrarComissao("CCJC", "118110400,116161056");
        controlador.cadastrarComissao("Medicina", "13210361,36146311");

        controlador.cadastrarPL("118110400", 2015, "terminar esse projeto","Tecnologia", "www.canvas.com", true);

        assertEquals("EM VOTACAO (CCJC)", this.controlador.exibirTramitacao("PL 1/2015"));
        assertTrue(this.controleVotacao.votarComissao("PL 1/2015", "OPOSICAO", "Plenario", this.controlador.getControleComissao().getMapaComissoes(), this.controlador.getControllerPLS().getPropostasDeLeis(), this.controlador.getControlePessoas().getDeputados(), "PLS,PT,PNR", "Tecnologia"));
        assertEquals("APROVADO (CCJC), EM VOTACAO (Plenario)", this.controlador.exibirTramitacao("PL 1/2015"));

        assertEquals("APROVADO (CCJC), EM VOTACAO (Plenario)", this.controlador.exibirTramitacao("PL 1/2015"));
        assertThrows(IllegalArgumentException.class, () -> this.controleVotacao.votarComissao("PL 1/2015", "LIVRE", "Plenario", this.controlador.getControleComissao().getMapaComissoes(), this.controlador.getControllerPLS().getPropostasDeLeis(), this.controlador.getControlePessoas().getDeputados(), "PLS,PT,PNR", "Tecnologia"));
        assertEquals("APROVADO (CCJC), EM VOTACAO (Plenario)", this.controlador.exibirTramitacao("PL 1/2015"));
    }

    @Test
    void votarPlenario() {
        controlador.cadastrarPessoa("Luciano", "118110400", "PB", "Jogos,Tecnologia", "PLS");
        controlador.cadastrarPessoa("Ana", "116161056", "PB", "Moda,Tecnologia", "PT");
        controlador.cadastrarPessoa("Artur", "13210361", "PB", "Medicina,Tecnologia", "PNR");
        controlador.cadastrarPessoa("Gutemberg", "36146311", "PB", "Medicina,Educação", "PTS");

        controlador.cadastrarPartido("PLS");
        controlador.cadastrarPartido("PT");
        controlador.cadastrarPartido("PNR");

        controlador.cadastrarDeputado("118110400", "11102011");
        controlador.cadastrarDeputado("116161056", "09072006");
        controlador.cadastrarDeputado("13210361", "02012015");
        controlador.cadastrarDeputado("36146311", "03082014");

        controlador.cadastrarComissao("CCJC", "118110400,116161056");
        controlador.cadastrarComissao("Medicina", "13210361,36146311");

        controlador.cadastrarPL("116161056", 2012, "Terminar o projeto", "Tecnologia", "www.plmdds_acaba.com", false);

        assertEquals("EM VOTACAO (CCJC)", this.controlador.exibirTramitacao("PL 1/2012"));
        assertTrue(controleVotacao.votarPlenario("PL 1/2012", "OPOSICAO", "118110400,116161056", this.controlador.getControleComissao().getMapaComissoes(), this.controlador.getControllerPLS().getPropostasDeLeis(), this.controlador.getControlePessoas().getDeputados(),"PLS,PT,PNR","Tecnologia,Educacao"));
        assertEquals("APROVADO (Plenario)", this.controlador.exibirTramitacao("PL 1/2012"));

        assertEquals("APROVADO (Plenario)", this.controlador.exibirTramitacao("PL 1/2012"));
        assertThrows(IllegalArgumentException.class, () -> this.controleVotacao.votarPlenario("PL 1/2012", "GOVERNISTA", "118110400,116161056,13210361", this.controlador.getControleComissao().getMapaComissoes(), this.controlador.getControllerPLS().getPropostasDeLeis(), this.controlador.getControlePessoas().getDeputados(), "PT","Educação"));

    }
  /**
    @Test
    void configurarEstrategiaPropostaRelacionada() {
    }

    @Test
    void pegarPropostaRelacionada() {
    }*/
}