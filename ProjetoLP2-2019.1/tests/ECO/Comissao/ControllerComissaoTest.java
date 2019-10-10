package ECO.Comissao;

import ECO.Pessoa.ControllerPessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerComissaoTest {

    private ControllerComissao controleComissao;
    private ControllerPessoa controlePessoa;

    @BeforeEach
    void setUp() {
        controleComissao = new ControllerComissao();
        controlePessoa = new ControllerPessoa();
    }

    @Test
    void cadastrarComissao() {

        this.controlePessoa.cadastraPessoa("Toin", "111", "CE", "Economia", "PTS");
        this.controlePessoa.cadastraPessoa("Nilso", "123", "PB", "Saúde", "PMLS");
        this.controlePessoa.cadastraPessoa("Zezin", "456", "PB", "Humor", "PMCA");
        this.controlePessoa.cadastraPessoa("Detinha", "789", "PB", "Gastronomia", "PFCF");

        this.controleComissao.cadastrarComissao("TURURU", "111");
        this.controleComissao.cadastrarComissao("MUSICA TRISTE NARUTO", "123,456,789");

        assertTrue(controleComissao.getMapaComissoes().containsKey("TURURU"));
        assertTrue(controleComissao.getMapaComissoes().containsKey("MUSICA TRISTE NARUTO"));
        assertEquals("111", this.controleComissao.getMapaComissoes().get("TURURU").getDniDeputados());
        assertEquals("123,456,789", this.controleComissao.getMapaComissoes().get("MUSICA TRISTE NARUTO").getDniDeputados());

        assertThrows(IllegalArgumentException.class, () -> this.controleComissao.cadastrarComissao("  ", "111,123,456,789"));
        assertThrows(IllegalArgumentException.class, () -> this.controleComissao.cadastrarComissao("QUALQUER", "   "));
    }

    @Test
    void verificaComissao() {
        this.controlePessoa.cadastraPessoa("Toin", "111", "CE", "Economia", "PTS");
        this.controlePessoa.cadastraPessoa("Nilso", "123", "PB", "Saúde", "PMLS");
        this.controleComissao.cadastrarComissao("Turminha da programação", "111,123");
        this.controleComissao.cadastrarComissao("Projeto", "111,123");
        try {
            this.controleComissao.verificaComissao("Turminha da programação", "nao sei");
            this.controleComissao.verificaComissao("Projeto", "nao sei");
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}