package ECO.Pessoa;

import ECO.Util.Conversoes;

import java.io.Serializable;

import static ECO.Util.Validador.*;
import static ECO.Util.Conversoes.*;


/**
 * Classe Deputado que tem Funcao como interface.
 */
public class Deputado extends Pessoa implements Serializable {
    /**
     * Atributo do tipo Date que refere a data de inicio do objeto relacionado
     */
    private String dataDeInicio;
    /**
     * Atributo do tipo String que refere a quantidade de leis aprovadas pelo objeto Deputado.
     */
    private int quantidadeDeLeis;

    /**
     * Construtor da classe Deputado  utilizando o dni e a data de inicio como essenciais para a caracterizacao do objeto Deputado.
     * @param dni codigo de identificacao do objeto.
     * @param dataDeInicio data de inicio na vida politica.
     */

    public Deputado(String nome, String dni, String estadoOrigem, String interesses, String partido, String dataDeInicio){
        super(nome, dni, estadoOrigem, interesses, partido);

        validadorString(dni, "");
        validadorString(dataDeInicio, "");

        this.dataDeInicio = converteData(dataDeInicio);
        this.quantidadeDeLeis = 0;
    }
    /**
     * Converte a data recebida para um padrão válido tradicional dd/mm/AAAA.
     * @param dataDeInicio String que contém a data que será padronizada.
     * @return a data em formato padronizado.
     */

    public String converteData(String dataDeInicio) {
        return Conversoes.converteData(dataDeInicio);
    }

    /**
     * Retorna a data de inicio de atuacao do objeto Deputado relacionado
     * @return a data de inicio do objeto Deputado relacionado
     */

    public String getDataDeInicio() {
        return dataDeInicio;
    }

    /**
     * Retorna a quantidade de leis cadastradas do objeto Deputado relacionado
     * @return
     */

    public int getQuantidadeDeLeis() {
        return quantidadeDeLeis;
    }





    /**
     * Representacao textual do objeto Deputado.
     * @return representacao textual.
     */

    @Override
    public String toString() {
        String interesses = getInteresses().equals("")  ?  ""  : " - Interesses: " + getInteresses();

        return "POL: " + getNome()
                + " - " + getDni() +
                " (" + getEstadoOrigem() + ")"
                + " - " + getPartido()
                + interesses + " - "  + getDataDeInicio() + " - "
                + getQuantidadeDeLeis() + " Leis";

    }


    public void adicionaLei() {
        this.quantidadeDeLeis += 1;
    }


}