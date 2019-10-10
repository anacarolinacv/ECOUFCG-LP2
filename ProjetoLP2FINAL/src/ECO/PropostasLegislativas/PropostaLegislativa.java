package ECO.PropostasLegislativas;

import java.io.Serializable;

/**
 * Super Classe responsavel por padronizar os distintos objetos relacionados a proposta legislativa com os atributos e metodos em comum as classes filhas
 */

public abstract class PropostaLegislativa implements Serializable {
    /**
     * Atributo String que armazena o dni do autor da proposta legislativa
     */

    private String DNIAutor;
    /**
     * Atributo int que armazena o ano que a proposta legislativa foi lancada
     */

    private int ano;
    /**
     * Atributo String com o codigo da proposta legislativa
     */

    private String codigo;
    /**
     * Atributo String com a ementa da proposta legislativa
     */

    private String ementa;
    /**
     * Atributo String com os interesses relacionados a proposta legislativa
     */

    private String interessesRelacionados;
    /**
     * Atributo String que armazena a situacao atual da proposta legislativa
     */

    private String situacaoAtual;
    /**
     * Atributo String que armazena a tramitacao da proposta legislativa (caminhio ao qual a proposta passou em suas votacoes)
     */

    private String tramitacao;
    /**
     * Atributo String que armazena o endereco do documento
     */

    private String url;

    /**
     * Construtor da classe Proposta Legislativa atraves dos atributos da propria classe.
     * @param DNIAutor
     * @param ano
     * @param ementa
     * @param interessesRelacionados
     * @param url
     * @param codigo
     */

    public PropostaLegislativa(String DNIAutor, int ano, String ementa, String interessesRelacionados, String url, String codigo) {
        this.DNIAutor = DNIAutor;
        this.interessesRelacionados = interessesRelacionados;
        this.ano = ano;
        this.ementa = ementa;
        this.url = url;
        this.codigo = codigo;
        this.situacaoAtual = "EM VOTACAO (CCJC)";
        this.tramitacao = "EM VOTACAO (CCJC)";
    }



    /**
     * Retorna o ano que a proposta legislativa foi inicializada
     * @return ano de inicio
     */

    public int getAno() {
        return ano;
    }

    /**
     * Representacao textual da classe PropostaLegislativa
     * @return String com a representacao textual
     */

    public abstract String toString();

    /**
     * Retorna a String com o dni do autor da proposta legislativa
     * @return o dni do autor
     */

    public String getDNIAutor() {
        return DNIAutor;
    }

    /**
     * Retorna a String com o codigo da proposta legislativa
     * @return codigo
     */

    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Retorna a String com o ementa da proposta legislativa
     * @return ementa
     */

    public String getEmenta() {
        return ementa;
    }
    /**
     * Retorna a String com a tramitacao da proposta legislativa (O caminho e os diferentes tipos de resultados de votações em comissões e plenários)
     * @return situacao tramitacao
     */

    public String getTramitacao() {
        return tramitacao;
    }




    public void setTramitacao(String tramitacao) {
        this.tramitacao = tramitacao;
    }

    /**
     * Retorna a String com a situacao atual da proposta legislativa
     * @return situacao atual
     */



    public String getSituacaoAtual() {
        return situacaoAtual;
    }

    /**
     * Retorna a String com o endereço eletronico da proposta.
     * @return a url da proposta
     */

    public String getUrl() {
        return url;
    }

    //    criado para a votacao, nao sei se vai continuar
    public abstract boolean verificaBooleanConclusivo();

    public void setSituacaoAtual(String novaSituacao) {
        this.situacaoAtual = novaSituacao;
    }

    public String getInteressesRelacionados() {
        return interessesRelacionados;
    }

    public abstract void quorumMininimo (int deputadosPresentes, int totalDeDeputados);

    public abstract String exibirTramitacao(String codigo);
}
