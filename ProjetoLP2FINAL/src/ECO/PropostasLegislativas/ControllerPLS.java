package ECO.PropostasLegislativas;

import ECO.Comissao.Comissao;
import ECO.Pessoa.Deputado;
import ECO.Pessoa.Pessoa;

import java.io.*;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import static ECO.Util.Validador.*;

public class ControllerPLS implements Serializable {

//    private int numeroPL = 1;
//    private int numeroPLP = 1;
//    private int numeroPEC = 1;

	private HashMap<String, PropostaLegislativa> propostasDeLeis;

	private HashMap<Integer, Integer> numeroPL;
	private HashMap<Integer, Integer> numeroPLP;
	private HashMap<Integer, Integer> numeroPEC;

	public ControllerPLS() {
		this.propostasDeLeis = new HashMap<>();
		this.numeroPL = new HashMap<>();
		this.numeroPLP = new HashMap<>();
		this.numeroPEC = new HashMap<>();

	}

	public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		validadorString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		validadorDni(dni, "Erro ao cadastrar projeto: dni invalido");
		validadorString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		validadorString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		validadorString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		validadorAnoFuturo(ano, "Erro ao cadastrar projeto: ano posterior ao ano atual");
		validadorAno(ano, "Erro ao cadastrar projeto: ano anterior a 1988");

		String codigo = "PL " + contadorPL(ano) + "/" + ano;
		propostasDeLeis.put(codigo, new PL(dni, ano, ementa, interesses, url, conclusivo, codigo));
		return codigo;
	}

	public String cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		validadorString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		validadorDni(dni, "Erro ao cadastrar projeto: dni invalido");
		validadorString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		validadorString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		validadorString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		validadorString(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
		validadorAnoFuturo(ano, "Erro ao cadastrar projeto: ano posterior ao ano atual");
		validadorAno(ano, "Erro ao cadastrar projeto: ano anterior a 1988");

		String codigo = "PLP " + contadorPLP(ano) + "/" + ano;
		propostasDeLeis.put(codigo, new PLP(dni, ano, ementa, interesses, url, artigos, codigo));
		return codigo;

	}

	public String cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
		validadorString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		validadorDni(dni, "Erro ao cadastrar projeto: dni invalido");
		validadorString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		validadorString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		validadorString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		validadorString(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
		validadorAnoFuturo(ano, "Erro ao cadastrar projeto: ano posterior ao ano atual");
		validadorAno(ano, "Erro ao cadastrar projeto: ano anterior a 1988");

		String codigo = "PEC " + contadorPEC(ano) + "/" + ano;
		propostasDeLeis.put(codigo, new PEC(dni, ano, ementa, interesses, url, artigos, codigo));
		return codigo;
	}

	private Integer contadorPL(Integer ano) {
		if (numeroPL.containsKey(ano)) {
			numeroPL.put(ano, numeroPL.get(ano) + 1);
			return numeroPL.get(ano);
		} else {
			numeroPL.put(ano, 0);
			return contadorPL(ano);
		}
	}

	private Integer contadorPLP(Integer ano) {
		if (numeroPLP.containsKey(ano)) {
			numeroPLP.put(ano, numeroPLP.get(ano) + 1);
			return numeroPLP.get(ano);
		} else {
			numeroPLP.put(ano, 0);
			return contadorPLP(ano);
		}
	}

	private Integer contadorPEC(Integer ano) {
		if (numeroPEC.containsKey(ano)) {
			numeroPEC.put(ano, numeroPEC.get(ano) + 1);
			return numeroPEC.get(ano);
		} else {
			numeroPEC.put(ano, 0);
			return contadorPEC(ano);
		}
	}

	/**
	 * Exibe o projeto que se refere a String codigo passada como parametro
	 * 
	 * @param codigo meio de identificacao do projeto
	 * @return o toString do projeto relacionado
	 */
	public String exibirProjeto(String codigo) {
		return propostasDeLeis.get(codigo).toString();
	}

	public boolean verificaBooleanConclusivo(String codigo) {
		return this.propostasDeLeis.get(codigo).verificaBooleanConclusivo();
	}

	public HashMap<String, PropostaLegislativa> getPropostasDeLeis() {
		return propostasDeLeis;
	}

	public String getInteressesRelacionados(String codigo) {
		return propostasDeLeis.get(codigo).getInteressesRelacionados();
	}

	public void alteraSituacaoAtual(String codigo, String alteracao) {
		propostasDeLeis.get(codigo).setSituacaoAtual(alteracao);
	}

	public void quorumMininimo(String codigo, int deputadosPresentes, int totalDeDeputados) {
		propostasDeLeis.get(codigo).quorumMininimo(deputadosPresentes, totalDeDeputados);
	}

	public String exibirTramitacao(String codigo) {
		String complemento = "";

		if (!(propostasDeLeis.get(codigo).getSituacaoAtual().contains("ARQUIVADO"))
				&& !(propostasDeLeis.get(codigo).getSituacaoAtual().contains("APROVADO"))
				&& !(propostasDeLeis.get(codigo).exibirTramitacao(codigo).equals("EM VOTACAO (CCJC)"))) {
			complemento = ", " + propostasDeLeis.get(codigo).getSituacaoAtual();
//            proximoLocal = plenario, mas tramitacao tem que ser Plenario.
			String complemento1[] = complemento.split("plenario");
			if (complemento1.length == 2) {
				complemento = ", EM VOTACAO (Plenario)";
			}
		}
		return propostasDeLeis.get(codigo).exibirTramitacao(codigo) + complemento;
	}
	
	public Map<String, String> estrategia ;
	
	public void configuraEstrategiaProposta(String dni, String estrategia) {
		switch (estrategia) {
		case "CONSTITUCIONAL":
			break;
		case "CONCLUSAO":
			break;
		case "APROVACAO":
			break;
		default:
			throw new IllegalArgumentException("Erro ao configurar estrategia: estrategia invalida");
		}

	}

	public String pegarPropostaRelacionada(String dni, Map<String, Pessoa> pessoa,Map<String, PropostaLegislativa> leis) {
		estrategia = new HashMap<>();
		for(Map.Entry<String,Pessoa> entry2 : pessoa.entrySet()) {
			this.estrategia.put(entry2.getKey(),"CONSTITUCIONAL");
		}
		
		String retorno = "";
		String[] interesses = pessoa.get(dni).getInteresses().trim().split(",");
		if(this.estrategia.get(dni).equals("CONSTITUCIONAL")) {
		for (Map.Entry<String, PropostaLegislativa> entry : leis.entrySet()) {
			for (int i = 0; i < interesses.length; i++) {
				if (interesses[i].equals(entry.getValue().getInteressesRelacionados())) {
					if (entry.getKey().substring(0, 3).equals("PEC") ) {
						return entry.getValue().getCodigo();
					}
					else if (entry.getKey().substring(0, 3).equals("PLP")) {
						retorno = entry.getValue().getCodigo();
					} 
					else if (entry.getKey().substring(0, 2).equals("PL")) {
						retorno = entry.getValue().getCodigo();
					}
				}
			}
			
		}
	}
		if(this.estrategia.get(dni).equals("CONCLUSAO")) {
			
		}
	return retorno;

	}

	public void escreverArquivos(Map<String, PropostaLegislativa> map, String arquivo) {

		FileOutputStream arquivoPLS;

		try {
			arquivoPLS = new FileOutputStream(arquivo);
			ObjectOutputStream gravarPLS = new ObjectOutputStream(arquivoPLS);
			gravarPLS.writeObject(map);
			gravarPLS.flush();
			gravarPLS.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Map<String, PropostaLegislativa> lerArquivos(String arquivo) {
		File arquivoPLS = null;
		arquivoPLS = new File(arquivo);
		Map<String, PropostaLegislativa> map = new HashMap<>();
		FileInputStream fis;

		try {
			if (!arquivoPLS.exists()) {
				arquivoPLS.createNewFile();
			} else if (arquivoPLS.length() == 0) {
				System.out.println("ARQUIVO VAZIO");

			} else {
				fis = new FileInputStream(arquivo);
				ObjectInputStream ois = new ObjectInputStream(fis);
				map = (Map<String, PropostaLegislativa>) ois.readObject();
				ois.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;

	}

	public void limpar() {
		this.propostasDeLeis = new HashMap<>();
	}
}
