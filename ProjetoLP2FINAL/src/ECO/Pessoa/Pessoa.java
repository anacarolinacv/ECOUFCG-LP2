package ECO.Pessoa;

import java.io.Serializable;
import java.util.Objects;
import static ECO.Util.Validador.*;

/**
 * Classe Pessoa onde o objeto Pessoa se forma a partir dos atributos relacionados.
 */
public class Pessoa implements Serializable {
    /**
     * Atributo dni que se torna o codigo de identificacao do objeto Pessoa.
     */

    private String dni;
    /**
     * Atributo que se relaciona ao nome do objeto Pessoa.
     */
    private String nome;
    /**
     * Atributo que relaciona ao estado de origem do objeto Pessoa.
     */
    private String estadoOrigem;
    /**
     * Atributo que relaciona aos interesses do objeto Pessoa.
     */
    private String interesses;
    /**
     * Atributo que relaciona ao partido do objeto Pessoa.
     */
    private String partido;


    /**
     * Contrutor da classe Pessoa a partir dos atributos dela, menos o partido. Caso os parametros forem invalidos, lança-se uma excecao.
     * @param dni codigo de identificacao da objeto Pessoa
     * @param nome nome do objeto Pessoa.
     * @param estadoOrigem estado de origem do objeto Pessoa.
     * @param interesses intesses relacionados ao objeto Pessoa.
     */

    public Pessoa(String nome, String dni, String estadoOrigem, String interesses) {
        validadorString(dni, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
        validadorString(nome, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
        validadorString(estadoOrigem, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
        this.dni = dni;
        this.nome = nome;
        this.estadoOrigem = estadoOrigem;
        this.interesses = interesses;
    }

    /**
     * Contrutor da classe Pessoa a partir dos atributos da mesma incluindo partido. Caso os parametros forem invalidos, lanca-se uma excecao.
     * @param dni codigo de identificacao do objeto Pessoa.
     * @param nome nome do objeto Pessoa.
     * @param estadoOrigem estado de origem do objeto Pessoa.
     * @param interesses intesses relacionados ao objeto Pessoa.
     * @param partido partido relaciona ao objeto Pessoa.
     */

    public Pessoa(String nome, String dni, String estadoOrigem, String interesses, String partido) {
        validadorString(dni, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
        validadorString(nome, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
        validadorString(estadoOrigem, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");

        this.dni = dni;
        this.nome = nome;
        this.estadoOrigem = estadoOrigem;
        this.interesses = interesses;
        this.partido = partido;
    }

    /**
     * Retorna o atributo dni do objeto Pessoa.
     * @return dni
     */

    public String getDni() {
        return dni;
    }

    /**
     * Retorna o atributo nome do objeto Pessoa.
     * @return nome
     */

    public String getNome() {
        return nome;
    }

    /**
     * Retorna o atributo estadoOrigem do objeto Pessoa.
     * @return estadoOrigem
     */

    public String getEstadoOrigem() {
        return estadoOrigem;
    }

    /**
     * Retorna o atributo interesses do objeto Pessoa.
     * @return interesses
     */

    public String getInteresses() {
        return interesses;
    }


    /**
     * Retorna o atributo partido do objeto Pessoa.
     * @return partido
     */

    public String getPartido() {
        return partido;
    }


    /**
     * Representacao textual do objeto Pessoa
     * @return a representacao textual do objeto Pessoa relacionado.
     */

    @Override
    public String toString() {
        String interesses = this.interesses.equals("")  ?  ""  : " - Interesses: " + this.interesses;
        String partido = (this.partido == null || this.partido.equals("")) ? ""  : " - " +  this.partido;

        return this.nome
                + " - " + this.dni +
                " (" + this.estadoOrigem + ")"
                + partido
                + interesses;
    }


    /**
     * Compara dois objetos e retorna um boolean afirmando se sao ou nao iguais.
     * @param o o objeto a ser comparado
     * @return boolean relacionado a confirmacao ou nao da igualdade entre os dois objetos
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(getDni(), pessoa.getDni());
    }

    /**
     * Retorna o endereco de memoria do objeto Pessoa relacionado.
     * @return endereco do memoria do objeto Pessoa.
     */

    @Override
    public int hashCode() {
        return Objects.hash(getDni());
    }

    /**
     * Cadastra deputado a partir do seu dni. Caso o parametro for invalido, uma excessao sera lancada.
     * @param dataDeInicio data em que o objeto comecou sua vida atuante politicamente.
     * @return um boolean relacionando a confirmacao do cadastro
     */

}