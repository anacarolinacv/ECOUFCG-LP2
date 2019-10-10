package ECO.Votacao;

import ECO.ControllerGeral;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerVotacaoTest {
    private ControllerVotacao controllerVotacao;
    private ControllerGeral controllerGeral;

    @BeforeEach
    void setUp() {
        controllerVotacao = new ControllerVotacao();
        controllerGeral = new ControllerGeral();
    }

    @Test
    void votarComissaoAprovada() {
        controllerGeral.cadastrarPessoa("Luciano", "118110400", "PB", "Jogos,Tecnologia", "PLS");
        controllerGeral.cadastrarPessoa("Ana", "116161056", "PB", "Moda,Tecnologia", "PT");
        controllerGeral.cadastrarPessoa("Artur", "13210361", "PB", "Medicina,Tecnologia", "PNR");
        controllerGeral.cadastrarPessoa("Gutemberg", "36146311", "PB", "Medicina,Educação", "PTS");

        controllerGeral.cadastrarPartido("PLS");
        controllerGeral.cadastrarPartido("PT");
        controllerGeral.cadastrarPartido("PNR");

        controllerGeral.cadastrarDeputado("118110400", "11102011");
        controllerGeral.cadastrarDeputado("116161056", "09072006");
        controllerGeral.cadastrarDeputado("13210361", "02012015");
        controllerGeral.cadastrarDeputado("36146311", "03082014");

        controllerGeral.cadastrarComissao("CCJC", "118110400,116161056");
        controllerGeral.cadastrarComissao("Medicina", "13210361,36146311");

        controllerGeral.cadastrarPL("118110400", 2015, "terminar esse projeto","Tecnologia", "www.canvas.com", true);

        assertEquals("EM VOTACAO (CCJC)", this.controllerGeral.exibirTramitacao("PL 1/2015"));
        assertTrue(this.controllerVotacao.votarComissao("PL 1/2015", "OPOSICAO", "Plenario", this.controllerGeral.getControleComissao().getMapaComissoes(), this.controllerGeral.getControllerPLS().getPropostasDeLeis(), this.controllerGeral.getControlePessoas().getDeputados(), "PLS,PT,PNR", "Tecnologia"));
        assertEquals("APROVADO (CCJC), EM VOTACAO (Plenario)", this.controllerGeral.exibirTramitacao("PL 1/2015"));
    }

    @Test
    void votarComissaoRejeitada() {
        controllerGeral.cadastrarPessoa("Luciano", "118110400", "PB", "Jogos,Tecnologia", "PLS");
        controllerGeral.cadastrarPessoa("Ana", "116161056", "PB", "Moda,Tecnologia", "PT");
        controllerGeral.cadastrarPessoa("Artur", "13210361", "PB", "Medicina,Tecnologia", "PNR");
        controllerGeral.cadastrarPessoa("Gutemberg", "36146311", "PB", "Medicina,Educação", "PTS");

        controllerGeral.cadastrarPartido("PLS");
        controllerGeral.cadastrarPartido("PT");
        controllerGeral.cadastrarPartido("PNR");

        controllerGeral.cadastrarDeputado("118110400", "11102011");
        controllerGeral.cadastrarDeputado("116161056", "09072006");
        controllerGeral.cadastrarDeputado("13210361", "02012015");
        controllerGeral.cadastrarDeputado("36146311", "03082014");

        controllerGeral.cadastrarComissao("CCJC", "118110400,116161056");
        controllerGeral.cadastrarComissao("Medicina", "13210361,36146311");

        controllerGeral.cadastrarPL("118110400", 2015, "terminar esse projeto","Tecnologia", "www.canvas.com", true);
        assertEquals("EM VOTACAO (CCJC)", this.controllerGeral.exibirTramitacao("PL 1/2015"));
        assertFalse(this.controllerVotacao.votarComissao("PL 1/2015", "GOVERNISTA", "Plenario", this.controllerGeral.getControleComissao().getMapaComissoes(), this.controllerGeral.getControllerPLS().getPropostasDeLeis(), this.controllerGeral.getControlePessoas().getDeputados(), "PLS,PT,PNR", "Tecnologia"));
        assertEquals("REJEITADO (CCJC)", this.controllerGeral.exibirTramitacao("PL 1/2015"));
    }

    @Test
    void votarComissaoStatusLivre() {
        controllerGeral.cadastrarPessoa("Luciano", "118110400", "PB", "Jogos,Tecnologia", "PLS");
        controllerGeral.cadastrarPessoa("Ana", "116161056", "PB", "Moda,Tecnologia", "PT");
        controllerGeral.cadastrarPessoa("Artur", "13210361", "PB", "Medicina,Tecnologia", "PNR");
        controllerGeral.cadastrarPessoa("Gutemberg", "36146311", "PB", "Medicina,Educação", "PTS");

        controllerGeral.cadastrarPartido("PLS");
        controllerGeral.cadastrarPartido("PT");
        controllerGeral.cadastrarPartido("PNR");

        controllerGeral.cadastrarDeputado("118110400", "11102011");
        controllerGeral.cadastrarDeputado("116161056", "09072006");
        controllerGeral.cadastrarDeputado("13210361", "02012015");
        controllerGeral.cadastrarDeputado("36146311", "03082014");

        controllerGeral.cadastrarComissao("CCJC", "118110400,116161056");
        controllerGeral.cadastrarComissao("Medicina", "13210361,36146311");

        controllerGeral.cadastrarPL("118110400", 2015, "terminar esse projeto","Tecnologia", "www.canvas.com", true);
        assertEquals("EM VOTACAO (CCJC)", this.controllerGeral.exibirTramitacao("PL 1/2015"));
        assertTrue(this.controllerVotacao.votarComissao("PL 1/2015", "LIVRE", "Plenario", this.controllerGeral.getControleComissao().getMapaComissoes(), this.controllerGeral.getControllerPLS().getPropostasDeLeis(), this.controllerGeral.getControlePessoas().getDeputados(), "PLS,PT,PNR", "Tecnologia"));
        assertEquals("APROVADO (CCJC), EM VOTACAO (Plenario)", this.controllerGeral.exibirTramitacao("PL 1/2015"));
    }

    @Test
    void votarComissaoErros() {
        controllerGeral.cadastrarPessoa("Luciano", "118110400", "PB", "Jogos,Tecnologia", "PLS");
        controllerGeral.cadastrarPessoa("Ana", "116161056", "PB", "Moda,Tecnologia", "PT");
        controllerGeral.cadastrarPessoa("Artur", "13210361", "PB", "Medicina,Tecnologia", "PNR");
        controllerGeral.cadastrarPessoa("Gutemberg", "36146311", "PB", "Medicina,Educação", "PTS");

        controllerGeral.cadastrarPartido("PLS");
        controllerGeral.cadastrarPartido("PT");
        controllerGeral.cadastrarPartido("PNR");

        controllerGeral.cadastrarDeputado("118110400", "11102011");
        controllerGeral.cadastrarDeputado("116161056", "09072006");
        controllerGeral.cadastrarDeputado("13210361", "02012015");
        controllerGeral.cadastrarDeputado("36146311", "03082014");

        controllerGeral.cadastrarComissao("CCJC", "118110400,116161056");
        controllerGeral.cadastrarComissao("Medicina", "13210361,36146311");

        controllerGeral.cadastrarPL("118110400", 2015, "terminar esse projeto","Tecnologia", "www.canvas.com", true);
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.cadastrarPL("231", 2015, "terminar esse projeto","Tecnologia", "www.canvas.com", true));

        assertThrows(IllegalArgumentException.class, () -> this.controllerVotacao.votarComissao("   ", "GOVERNISTA", "Plenario", this.controllerGeral.getControleComissao().getMapaComissoes(), this.controllerGeral.getControllerPLS().getPropostasDeLeis(), this.controllerGeral.getControlePessoas().getDeputados(), "PLS,PT,PNR", "Tecnologia"));
        assertThrows(IllegalArgumentException.class, () -> this.controllerVotacao.votarComissao("PL 16351616519818/2016", "GOVERNISTA", "Plenario", this.controllerGeral.getControleComissao().getMapaComissoes(), this.controllerGeral.getControllerPLS().getPropostasDeLeis(), this.controllerGeral.getControlePessoas().getDeputados(), "PLS,PT,PNR", "Tecnologia"));
        assertThrows(IllegalArgumentException.class, () -> this.controllerVotacao.votarComissao("PL 1/2015", "QUALQUER UM AFIM DE DAR ERRO AQUI", "Plenario", this.controllerGeral.getControleComissao().getMapaComissoes(), this.controllerGeral.getControllerPLS().getPropostasDeLeis(), this.controllerGeral.getControlePessoas().getDeputados(), "PLS,PT,PNR", "Tecnologia"));
        assertThrows(IllegalArgumentException.class, () -> this.controllerVotacao.votarComissao("PL 1/2015", "    ", "Plenario", this.controllerGeral.getControleComissao().getMapaComissoes(), this.controllerGeral.getControllerPLS().getPropostasDeLeis(), this.controllerGeral.getControlePessoas().getDeputados(), "PLS,PT,PNR", "Tecnologia"));
        assertThrows(IllegalArgumentException.class, () -> this.controllerVotacao.votarComissao("PL 1/2015", "GOVERNISTA", "    ", this.controllerGeral.getControleComissao().getMapaComissoes(), this.controllerGeral.getControllerPLS().getPropostasDeLeis(), this.controllerGeral.getControlePessoas().getDeputados(), "PLS,PT,PNR", "Tecnologia"));
        assertThrows(NullPointerException.class, () -> this.controllerVotacao.votarComissao("PL 1/2015", "GOVERNISTA", "Plenario", null , this.controllerGeral.getControllerPLS().getPropostasDeLeis(), this.controllerGeral.getControlePessoas().getDeputados(), "PLS,PT,PNR", "Tecnologia"));
        assertThrows(NullPointerException.class, () -> this.controllerVotacao.votarComissao("PL 1/2015", "GOVERNISTA", "Plenario", this.controllerGeral.getControleComissao().getMapaComissoes(), null, this.controllerGeral.getControlePessoas().getDeputados(), "PLS,PT,PNR", "Tecnologia"));
        assertThrows(NullPointerException.class, () -> this.controllerVotacao.votarComissao("PL 1/2015", "GOVERNISTA", "Plenario", this.controllerGeral.getControleComissao().getMapaComissoes(), this.controllerGeral.getControllerPLS().getPropostasDeLeis(), null, "PLS,PT,PNR", "Tecnologia"));
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.exibirTramitacao("PLP 1/1500"));
    }

    @Test
    void votarPlenarioAprovada() {
        controllerGeral.cadastrarPessoa("Luciano", "118110400", "PB", "Jogos,Tecnologia", "PLS");
        controllerGeral.cadastrarPessoa("Ana", "116161056", "PB", "Moda,Tecnologia", "PT");
        controllerGeral.cadastrarPessoa("Artur", "13210361", "PB", "Medicina,Tecnologia", "PNR");
        controllerGeral.cadastrarPessoa("Gutemberg", "36146311", "PB", "Medicina,Educação", "PTS");

        controllerGeral.cadastrarPartido("PLS");
        controllerGeral.cadastrarPartido("PT");
        controllerGeral.cadastrarPartido("PNR");

        controllerGeral.cadastrarDeputado("118110400", "11102011");
        controllerGeral.cadastrarDeputado("116161056", "09072006");
        controllerGeral.cadastrarDeputado("13210361", "02012015");
        controllerGeral.cadastrarDeputado("36146311", "03082014");

        controllerGeral.cadastrarComissao("CCJC", "118110400,116161056");
        controllerGeral.cadastrarComissao("Medicina", "13210361,36146311");

        controllerGeral.cadastrarPL("116161056", 2012, "Terminar o projeto", "Tecnologia", "www.plmdds_acaba.com", false);
        assertEquals("EM VOTACAO (CCJC)", this.controllerGeral.exibirTramitacao("PL 1/2012"));
        assertTrue(controllerVotacao.votarPlenario("PL 1/2012", "OPOSICAO", "118110400,116161056", this.controllerGeral.getControleComissao().getMapaComissoes(), this.controllerGeral.getControllerPLS().getPropostasDeLeis(), this.controllerGeral.getControlePessoas().getDeputados(),"PLS,PT,PNR","Tecnologia,Educacao"));
        assertEquals("APROVADO (Plenario)", this.controllerGeral.exibirTramitacao("PL 1/2012"));
    }

    @Test
    void votarPlenarioRejeitada() {
        controllerGeral.cadastrarPessoa("Luciano", "118110400", "PB", "Jogos,Tecnologia", "PLS");
        controllerGeral.cadastrarPessoa("Ana", "116161056", "PB", "Moda,Tecnologia", "PT");
        controllerGeral.cadastrarPessoa("Artur", "13210361", "PB", "Medicina,Tecnologia", "PNR");
        controllerGeral.cadastrarPessoa("Gutemberg", "36146311", "PB", "Medicina,Educação", "PTS");

        controllerGeral.cadastrarPartido("PLS");
        controllerGeral.cadastrarPartido("PT");
        controllerGeral.cadastrarPartido("PNR");

        controllerGeral.cadastrarDeputado("118110400", "11102011");
        controllerGeral.cadastrarDeputado("116161056", "09072006");
        controllerGeral.cadastrarDeputado("13210361", "02012015");
        controllerGeral.cadastrarDeputado("36146311", "03082014");

        controllerGeral.cadastrarComissao("CCJC", "118110400,116161056");
        controllerGeral.cadastrarComissao("Medicina", "13210361,36146311");

        controllerGeral.cadastrarPLP("118110400", 2014, "Terminar o projeto", "Tecnologia", "www.plmdds_acaba.com", "US7,US8,US9");
        assertEquals("EM VOTACAO (CCJC)", this.controllerGeral.exibirTramitacao("PLP 1/2014"));
        assertFalse(this.controllerVotacao.votarPlenario("PLP 1/2014", "GOVERNISTA", "118110400,116161056,13210361", this.controllerGeral.getControleComissao().getMapaComissoes(), this.controllerGeral.getControllerPLS().getPropostasDeLeis(), this.controllerGeral.getControlePessoas().getDeputados(), "PT","Educação"));
        assertEquals("REJEITADO (Plenario - 2o turno)", this.controllerGeral.exibirTramitacao("PLP 1/2014"));
    }

    @Test
    void votarPlenarioStatusLivre() {
        controllerGeral.cadastrarPessoa("Luciano", "118110400", "PB", "Jogos,Tecnologia", "PLS");
        controllerGeral.cadastrarPessoa("Ana", "116161056", "PB", "Moda,Tecnologia", "PT");
        controllerGeral.cadastrarPessoa("Artur", "13210361", "PB", "Medicina,Tecnologia", "PNR");
        controllerGeral.cadastrarPessoa("Gutemberg", "36146311", "PB", "Medicina,Educação", "PTS");

        controllerGeral.cadastrarPartido("PLS");
        controllerGeral.cadastrarPartido("PT");
        controllerGeral.cadastrarPartido("PNR");

        controllerGeral.cadastrarDeputado("118110400", "11102011");
        controllerGeral.cadastrarDeputado("116161056", "09072006");
        controllerGeral.cadastrarDeputado("13210361", "02012015");
        controllerGeral.cadastrarDeputado("36146311", "03082014");

        controllerGeral.cadastrarComissao("CCJC", "118110400,116161056");
        controllerGeral.cadastrarComissao("Medicina", "13210361,36146311");

        controllerGeral.cadastrarPEC("118110400", 2014, "Terminar o projeto", "Tecnologia", "www.plmdds_acaba.com", "US7,US8,US9");
        assertEquals("EM VOTACAO (CCJC)", this.controllerGeral.exibirTramitacao("PEC 1/2014"));
        assertTrue(this.controllerVotacao.votarPlenario("PEC 1/2014", "LIVRE", "118110400,116161056,13210361", this.controllerGeral.getControleComissao().getMapaComissoes(), this.controllerGeral.getControllerPLS().getPropostasDeLeis(), this.controllerGeral.getControlePessoas().getDeputados(), "PT","Educação"));
        assertEquals("APROVADO (Plenario - 2o turno)", this.controllerGeral.exibirTramitacao("PEC 1/2014"));
    }

    @Test
    void votarPlenarioErros() {
        controllerGeral.cadastrarPessoa("Luciano", "118110400", "PB", "Jogos,Tecnologia", "PLS");
        controllerGeral.cadastrarPessoa("Ana", "116161056", "PB", "Moda,Tecnologia", "PT");
        controllerGeral.cadastrarPessoa("Artur", "13210361", "PB", "Medicina,Tecnologia", "PNR");
        controllerGeral.cadastrarPessoa("Gutemberg", "36146311", "PB", "Medicina,Educação", "PTS");

        controllerGeral.cadastrarPartido("PLS");
        controllerGeral.cadastrarPartido("PT");
        controllerGeral.cadastrarPartido("PNR");

        controllerGeral.cadastrarDeputado("118110400", "11102011");
        controllerGeral.cadastrarDeputado("116161056", "09072006");
        controllerGeral.cadastrarDeputado("13210361", "02012015");
        controllerGeral.cadastrarDeputado("36146311", "03082014");

        controllerGeral.cadastrarComissao("CCJC", "118110400,116161056");
        controllerGeral.cadastrarComissao("Medicina", "13210361,36146311");

        controllerGeral.cadastrarPEC("118110400", 2014, "Terminar o projeto", "Tecnologia", "www.plmdds_acaba.com", "US7,US8,US9");
        assertThrows(NullPointerException.class, () -> this.controllerVotacao.votarPlenario("    ", "LIVRE", "118110400,116161056,13210361", this.controllerGeral.getControleComissao().getMapaComissoes(), this.controllerGeral.getControllerPLS().getPropostasDeLeis(), this.controllerGeral.getControlePessoas().getDeputados(), "PT","Educação"));
        assertThrows(NullPointerException.class, () -> this.controllerVotacao.votarPlenario("PEC 3/2014", "LIVRE", "118110400,116161056,13210361", this.controllerGeral.getControleComissao().getMapaComissoes(), this.controllerGeral.getControllerPLS().getPropostasDeLeis(), this.controllerGeral.getControlePessoas().getDeputados(), "PT","Educação"));
        assertThrows(IllegalArgumentException.class, () -> this.controllerGeral.exibirTramitacao("PLP 1/1500"));
    }
}