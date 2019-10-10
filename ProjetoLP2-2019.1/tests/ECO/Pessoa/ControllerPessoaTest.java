package ECO.Pessoa;

import ECO.Pessoa.ControllerPessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerPessoaTest {

    private ControllerPessoa controle;

    @BeforeEach
    void setUp() {
        this.controle = new ControllerPessoa();
    }

    @Test
    void testCadastraPessoaSemPartido() {
        controle.cadastraPessoa("Joao", "123564789-0", "PB", "");
        controle.cadastraPessoa("Maria", "987654321-0", "CE", "politica, novela");
        controle.cadastraPessoa("Pedro", "135797531-1", "RO", "futebol");
        assertEquals("Joao - 123564789-0 (PB)", controle.exibirPessoa("123564789-0"));
        assertEquals("Maria - 987654321-0 (CE) - Interesses: politica, novela", controle.exibirPessoa("987654321-0"));
        assertEquals("Pedro - 135797531-1 (RO) - Interesses: futebol", controle.exibirPessoa("135797531-1"));
        assertThrows(IllegalArgumentException.class, () -> controle.cadastraPessoa("", "132321300-0", "PB", ""));
        assertThrows(IllegalArgumentException.class, () -> controle.cadastraPessoa("Luciano", "", "PB", ""));
        assertThrows(IllegalArgumentException.class, () -> controle.cadastraPessoa("Thayane", "132321300-4", "", "nao sei"));
        assertThrows(IllegalArgumentException.class, () -> controle.cadastraPessoa("Joao", "132321300,0", "PB", ""));
    }

    @Test
    void testCadastraPessoaComPartido() {
        controle.cadastraPessoa("Daniel", "123645468-0", "SP", "", "PSTS");
        controle.cadastraPessoa("Paulo", "123456789-0", "RJ", "Social", "PRTS");
        controle.cadastraPessoa("Joaquim", "123321456-9", "PB", "", "JPM");
        assertEquals("Daniel - 123645468-0 (SP) - PSTS", controle.exibirPessoa("123645468-0"));
        assertEquals("Paulo - 123456789-0 (RJ) - PRTS - Interesses: Social", controle.exibirPessoa("123456789-0"));
        assertEquals("Joaquim - 123321456-9 (PB) - JPM", controle.exibirPessoa("123321456-9"));
        assertThrows(IllegalArgumentException.class, () -> controle.cadastraPessoa(" ", "132321300-1", "PB", "", "EU"));
        assertThrows(IllegalArgumentException.class, () -> controle.cadastraPessoa("Ana", "   ", "PB", "", "PSOL"));
        assertThrows(IllegalArgumentException.class, () -> controle.cadastraPessoa("Luciano", "132321300-3", "    ", "", "EU"));
    }

    @Test
    void testCadastraDeputado() {

        controle.cadastraPessoa("José", "123564789-0", "PB", "");
        assertEquals ("José - 123564789-0 (PB)", controle.exibirPessoa("123564789-0"));
        controle.cadastraPessoa("Daniel", "123645468-0", "SP", "", "PSTS");
        assertEquals ("Daniel - 123645468-0 (SP) - PSTS", controle.exibirPessoa("123645468-0"));
        controle.cadastraPessoa("Paulo", "123456789-0", "RJ", "Social", "PRTS");
        assertEquals ("Paulo - 123456789-0 (RJ) - PRTS - Interesses: Social", controle.exibirPessoa("123456789-0"));
        controle.cadastraPessoa("Joaquim", "123321456-9", "PB", "", "JPM");
        assertEquals ("Joaquim - 123321456-9 (PB) - JPM", controle.exibirPessoa("123321456-9"));

        controle.cadastraDeputado("123645468-0", "09102000");
        assertEquals ("POL: Daniel - 123645468-0 (SP) - PSTS - 09/10/2000 - 0 Leis", controle.exibirPessoa("123645468-0"));
        controle.cadastraDeputado("123456789-0", "13051986");
        assertEquals ("POL: Paulo - 123456789-0 (RJ) - PRTS - Interesses: Social - 13/05/1986 - 0 Leis", controle.exibirPessoa("123456789-0"));

        assertThrows(IllegalArgumentException.class, () -> controle.cadastraDeputado("bvdbv", "10111000"));
        assertThrows(IllegalArgumentException.class, () -> controle.cadastraDeputado("b8928b", "10111001"));
        assertThrows(IllegalArgumentException.class, () -> controle.cadastraDeputado("9999999-9", "10111002"));
        assertThrows(IllegalArgumentException.class, () -> controle.cadastraDeputado("123321456-9", "evhhu"));
        assertThrows(IllegalArgumentException.class, () -> controle.cadastraDeputado("123321456-9", "11111111111"));
        assertThrows(IllegalArgumentException.class, () -> controle.cadastraDeputado("123321456-9", "35102000"));
        assertThrows(IllegalArgumentException.class, () -> controle.cadastraDeputado("123321456-9", "20112020"));
    }

    @Test
    void testExibePessoa() {
        this.controle.cadastraPessoa("Luciano", "11", "PB", "");
        this.controle.cadastraPessoa("Ana", "12", "PB", "nada", "PT");
        this.controle.cadastraPessoa("Artur", "13", "PB", "nada");
        this.controle.cadastraPessoa("Gutemberg", "14", "PB", "");

        assertEquals("Luciano - 11 (PB)", this.controle.exibirPessoa("11"));
        assertEquals("Ana - 12 (PB) - PT - Interesses: nada", this.controle.exibirPessoa("12"));
        assertEquals("Artur - 13 (PB) - Interesses: nada" , this.controle.exibirPessoa("13"));
        assertEquals("Gutemberg - 14 (PB)", this.controle.exibirPessoa("14"));
    }

    @Test
    void testCadastrarPartido() {
        this.controle.cadastrarPartido("PT");
        this.controle.cadastrarPartido("PSDF");
        this.controle.cadastrarPartido("PSOL");
        this.controle.cadastrarPartido("PSTS");

        assertEquals("PSDF,PSOL,PSTS,PT", this.controle.exibirBase());

        this.controle.cadastrarPartido("AA");
        this.controle.cadastrarPartido("ZZ");

        assertEquals("AA,PSDF,PSOL,PSTS,PT,ZZ", this.controle.exibirBase());
    }
    @Test
    void testQtdDeputados() {
        this.controle.cadastraPessoa("Seu pereira", "112", "PE", "","PL");
        this.controle.cadastraPessoa("Seu joão", "3214", "CE", "", "PT");

        this.controle.cadastraDeputado("112", "12121999");
        this.controle.cadastraDeputado("3214", "09012004");

        assertEquals(2, this.controle.qtdDeputados());
    }
}
