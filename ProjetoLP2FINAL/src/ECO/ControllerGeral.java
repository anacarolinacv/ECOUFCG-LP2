package ECO;

import ECO.Comissao.ControllerComissao;
import ECO.Pessoa.ControllerPessoa;
import ECO.PropostasLegislativas.ControllerPLS;
import ECO.Votacao.ControllerVotacao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static ECO.Util.Validador.validadorDni;
import static ECO.Util.Validador.validadorString;

/**
 * Classe responsavel por controller todos os controllers das classes mais especificas
 */

public class ControllerGeral implements Serializable {
    /**
     * Atributo do tipo ControllerPessoa responsavel por armazenar pessoas.
     */
    private ControllerPessoa controlePessoas;
    /**
     * Atributo do tipo ControllerComissao responsavel por armazenar comissoes.
     */
    private ControllerComissao controleComissao;
    /**
     * Atributo do tipo ControllerPessoa responsavel por armazenar propostas legislativas.
     */
    private ControllerPLS controllerPLS;

    /**
     * Atributo do tipo ControllerVotacao responsavel por armazenar algo a mudar!!!!!!!!!!!!!!!!!!!!!!!!.
     */
    private ControllerVotacao controllerVotacao;

    /**
     * Construtor da classe ControllerGeral onde todos os controllers sao inicializados
     */

    public ControllerGeral() {
        this.controlePessoas = new ControllerPessoa();
        this.controleComissao = new ControllerComissao();
        this.controllerPLS = new ControllerPLS();
        this.controllerVotacao = new ControllerVotacao();
    }

    /**
     * Retorna o atributo controlePessoas do objeto ControllerGeral relacionado
     * @return controle com objetos Pessoa armazenados
     */

    public ControllerPessoa getControlePessoas() {
        return controlePessoas;
    }
    /**
     * Retorna o atributo controleComissao do objeto ControllerGeral relacionado
     * @return controle com objetos Comissao armazenados
     */

    public ControllerComissao getControleComissao() {
        return controleComissao;
    }

	public ControllerPLS getControllerPLS() {
		return controllerPLS;
	}

	public ControllerVotacao getControllerVotacao() {
		return controllerVotacao;
	}

	/**
     * Cadastra o objeto Pessoa atraves dos parametros nome, dni, estado de origem e intesses
     * @param nome
     * @param dni
     * @param estadoOrigem
     * @param interesses
     */


    public void cadastrarPessoa(String nome, String dni, String estadoOrigem, String interesses) {
        this.controlePessoas.cadastraPessoa(nome, dni, estadoOrigem, interesses);
    }

    /**
     * Cadastra o objeto Pessoa atraves dos parametros nome, dni, estado de origem, interesses e partido.
     * @param nome
     * @param dni
     * @param estadoOrigem
     * @param interesses
     * @param partido
     */

    public void cadastrarPessoa(String nome, String dni, String estadoOrigem, String interesses, String partido) {
        this.controlePessoas.cadastraPessoa(nome, dni, estadoOrigem,interesses, partido);
    }

    /**
     * Cadastra o objeto Deputado a partir dos parametros dni e data de inicio de atuacao
     * @param dni
     * @param dataInicio
     */

    public void cadastrarDeputado(String dni, String dataInicio) {
        this.controlePessoas.cadastraDeputado(dni, dataInicio);
    }

    /**
     * Exibe o objeto Pessoa que possue o dni passado como parametro
     * @param dni
     * @return
     */

    public String exibePessoa(String dni) {
        return this.controlePessoas.exibirPessoa(dni);
    }

    /**
     * Cadastra a String partido passado como parametro
     * @param nomePartido
     */

    public void cadastrarPartido(String nomePartido) {
        this.controlePessoas.cadastrarPartido(nomePartido);
    }

    /**
     * Exibe todos as Strings partido ja cadastradas
     * @return
     */

    public String exibeBase() {
        return this.controlePessoas.exibirBase();
    }
    
    

    /**
     * Cadastra o objeto Comissao de acordo com exigencias estabelecidas como: tema ja cadastrado, tema ou String dniPoliticos nulo ou vazia.
     * @param tema meio de identificacao do objeto Comissao
     * @param dniPoliticos String com os dni dos politicos da comissao relacionada
     */

    public void cadastrarComissao(String tema, String dniPoliticos) {
        validadorString(tema, "Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
        validadorString(dniPoliticos, "Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");
        if (this.controleComissao.getMapaComissoes().containsKey(tema)) {
            throw new IllegalArgumentException("Erro ao cadastrar comissao: tema existente");
        }

        String[] listaDni = dniPoliticos.trim().split(",");
        for (String dni : listaDni) {
            validadorDni(dni, "Erro ao cadastrar comissao: dni invalido");

            if (!this.controlePessoas.getDeputados().containsKey(dni) && this.controlePessoas.getPessoas().containsKey(dni)) {
                throw new IllegalArgumentException("Erro ao cadastrar comissao: pessoa nao eh deputado");
            }
            if (!this.controlePessoas.getPessoas().containsKey(dni) && !this.controlePessoas.getDeputados().containsKey(dni)) {
                throw new IllegalArgumentException("Erro ao cadastrar comissao: pessoa inexistente");
            }
            else {
                this.controleComissao.cadastrarComissao(tema, dniPoliticos);
            }

        }
    }

    /**
     * Cadastra a proposta legislativa do tipo PL. Recebe os atributos da super classe e os especificos da classe PL. Caso o dni seja invalido/vazio/null, uma excecao sera lancada
     * @param dni
     * @param ano
     * @param ementa
     * @param interesses
     * @param url
     * @param conclusivo
     * @return a chamada do metodo
     */

    public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
        validadorString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
        validadorDni(dni, "Erro ao cadastrar projeto: dni invalido");
        this.controlePessoas.verificaDeputado(dni);
        return this.controllerPLS.cadastrarPL(dni, ano, ementa, interesses, url, conclusivo);
    }

    /**
     * Cadastra a proposta legislativa do tipo PLP. Recebe os atributos da super classe e os especificos da classe PL. Caso o dni seja invalido/vazio/null, uma excecao sera lancada
     * @param dni
     * @param ano
     * @param ementa
     * @param interesses
     * @param url
     * @param artigos
     * @return chamada do metodo
     */

    public String cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        validadorString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
        validadorDni(dni, "Erro ao cadastrar projeto: dni invalido");
        this.controlePessoas.verificaDeputado(dni);
        return this.controllerPLS.cadastrarPLP(dni, ano, ementa, interesses, url, artigos);
    }

    /**
     * Cadastra a proposta legislativa do tipo PEC. Recebe os atributos da super classe e os especificos da classe PL. Caso o dni seja invalido/vazio/null, uma excecao sera lancada
     * @param dni
     * @param ano
     * @param ementa
     * @param interesses
     * @param url
     * @param artigos
     * @return chamada do metodo
     */

    public String cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        validadorString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
        validadorDni(dni, "Erro ao cadastrar projeto: dni invalido");
        this.controlePessoas.verificaDeputado(dni);
        return this.controllerPLS.cadastrarPEC(dni, ano, ementa, interesses, url, artigos);
    }

    /**
     * Exibe o projeto relacionado ao condigo passado como parametro
     * @param codigo codigo relacionado a proposta legislativa
     * @return chamada do metodo
     */

    public String exibirProjeto(String codigo) {
        return this.controllerPLS.exibirProjeto(codigo);
    }



    public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {

        //        Verificacoes


        validadorString(statusGovernista, "Erro ao votar proposta: status invalido");
        if (!controleComissao.getMapaComissoes().containsKey("CCJC")) {
            throw new IllegalArgumentException("Erro ao votar proposta: CCJC nao cadastrada");
        }

        validadorString(proximoLocal, "Erro ao votar proposta: proximo local vazio");

        if (!statusGovernista.equals("GOVERNISTA") && !statusGovernista.equals("OPOSICAO")
                && !statusGovernista.equals("LIVRE")) {
            throw new IllegalArgumentException("Erro ao votar proposta: status invalido");
        }
        if (!controllerPLS.getPropostasDeLeis().containsKey(codigo)) {
            throw new IllegalArgumentException("Erro ao votar proposta: projeto inexistente");
        }

        if (controllerPLS.getPropostasDeLeis().get(codigo).getSituacaoAtual().equals("ARQUIVADO")
                || controllerPLS.getPropostasDeLeis().get(codigo).getSituacaoAtual().equals("APROVADO")) {
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
        }

        String[] situacaoAtual = controllerPLS.getPropostasDeLeis().get(codigo).getSituacaoAtual().split("VOTACAO");
        String localAtual = situacaoAtual[1].substring(2, situacaoAtual[1].length() - 1);

        if (localAtual.trim().equals("plenario")){
            throw new IllegalArgumentException("Erro ao votar proposta: proposta encaminhada ao plenario");
            
        }
        return controllerVotacao.votarComissao(codigo, statusGovernista, proximoLocal, this.controleComissao.getMapaComissoes(), this.controllerPLS.getPropostasDeLeis(),
                this.controlePessoas.getDeputados(), this.controlePessoas.exibirBase(),this.controllerPLS.getInteressesRelacionados(codigo));
    }

    public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {

        if (controllerPLS.getPropostasDeLeis().get(codigo).getSituacaoAtual().equals("ARQUIVADO")
                || controllerPLS.getPropostasDeLeis().get(codigo).getSituacaoAtual().equals("APROVADO")) {
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
        }
        String[] situacaoAtual = controllerPLS.getPropostasDeLeis().get(codigo).getSituacaoAtual().split("VOTACAO");
        String localAtual = situacaoAtual[1].substring(2, situacaoAtual[1].length() - 1);
        String tipoDeProposta = controllerPLS.getPropostasDeLeis().get(codigo).getCodigo().substring(0, 3).trim();

        if ((controleComissao.getMapaComissoes().containsKey(localAtual))) {
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao em comissao");
        }

        String[] deputadosPresentes = presentes.split(",");

        int DepPresentes = deputadosPresentes.length;

        controllerPLS.quorumMininimo(codigo, DepPresentes, controlePessoas.qtdDeputados());

        validadorString(codigo, "Erro ao votar proposta: projeto inexistente");
        validadorString(statusGovernista, "Erro ao votar proposta: status invalido");
        validadorString(presentes, "");

        if (!controllerPLS.getPropostasDeLeis().containsKey(codigo)) {
            throw new IllegalArgumentException("Erro ao votar proposta: projeto inexistente");
        }
        controleComissao.verificaComissao("CCJC", "Erro ao votar proposta: CCJC nao cadastrada");

        return controllerVotacao.votarPlenario(codigo,statusGovernista, presentes, this.controleComissao.getMapaComissoes(), this.controllerPLS.getPropostasDeLeis(),
                this.controlePessoas.getDeputados(),this.controlePessoas.exibirBase(), this.controllerPLS.getInteressesRelacionados(codigo));
    }


//    US8

    public String exibirTramitacao(String codigo) {
        validadorString(codigo, "");
        if (!controllerPLS.getPropostasDeLeis().containsKey(codigo)) {
            throw new IllegalArgumentException("Erro ao exibir tramitacao: projeto inexistente");

        }
        return controllerPLS.exibirTramitacao(codigo);
    }


//	  US9
    
    
    public void configurarEstrategiaPropostaRelacionada(String dni, String estrategia) {
    	if(dni.trim().equals("")) {
			throw new IllegalArgumentException("Erro ao configurar estrategia: pessoa nao pode ser vazia ou nula");
		}
    	validadorDni(dni, "Erro ao configurar estrategia: dni invalido");
    	if(estrategia.trim().equals("")) {
			throw new IllegalArgumentException("Erro ao configurar estrategia: estrategia vazia");
		}
    	
    	controllerPLS.configuraEstrategiaProposta(dni, estrategia);
    }

    public String pegarPropostaRelacionada(String dni) {
    	if(dni.trim().equals("")) {
			throw new IllegalArgumentException("Erro ao pegar proposta relacionada: pessoa nao pode ser vazia ou nula");
		}
    	validadorDni(dni, "Erro ao pegar proposta relacionada: dni invalido");
    	
        return controllerPLS.pegarPropostaRelacionada(dni,  this.controlePessoas.getPessoas(), this.controllerPLS.getPropostasDeLeis());
    }

    /**
     * Metodo responsavel por iniciar o sistema, fazendo a leitura dos arquivos
     * nos controladores de comissao, pls e pessoa.
     */

    public void iniciaSistema() {
        this.controlePessoas.lerArquivosPessoa("Pessoa.dat");
        this.controlePessoas.lerArquivosDeputado("Deputado.dat");
        this.controlePessoas.lerArquivosPartido("Partido.dat");
        this.controleComissao.lerArquivos("Comissao.dat");
        this.controllerPLS.lerArquivos("Propostas.dat");
    }

    /**
     * Metodo responsavel por finalizar o sistema, escrevendo arquivos
     * nos controladores de comissao, pls e pessoa.
     */
    public void finalizaSistema() {
        this.controlePessoas.escreverArquivosPessoa(controlePessoas.getPessoas(), "Pessoa.dat");
        this.controlePessoas.escreverArquivosDeputado(controlePessoas.getDeputados(), "Deputado.dat");
        this.controlePessoas.escreverArquivosPartido(controlePessoas.getPartidos(), "Partido.dat");
        this.controleComissao.escreverArquivos(controleComissao.getMapaComissoes(), "Comissao.dat");
        this.controllerPLS.escreverArquivos(controllerPLS.getPropostasDeLeis(), "Propostas.dat");
    }

    public void limparSistema() {
        this.controlePessoas.limpar();
        this.controleComissao.limpar();
        this.controllerPLS.limpar();
    }





}
