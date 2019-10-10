package ECO.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;

/**
 * Classe Validador que serve para validar os parametros recebidos em alguns metodos do projeto.
 */
public class Validador {
    /**
     * Valida se  o atributo do tipo String compoe-se de String vazia ou representa-se por nulo.
     * @param parametro parametro recebido no metodo relacionado
     * @param mensagem sera enviada a partir da nao validacao do parametro
     */

    public static void validadorString(String parametro, String mensagem) {
        if (parametro == null || "".equals(parametro.trim())) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    /**
     * Verifica se o atributo dni compoe-se de digitos e tracos. Caso contrario, lanca-se uma excecao.
     * @param dni atributo a ser validado ou nao.
     * @param mensagem mensagem que sera exposta caso uma excessao for lancada.
     */

    public static void validadorDni(String dni, String mensagem) {
        for (int i = 0; i < dni.length(); i++) {
            char caractere = dni.charAt(i);
            if (!(Character.isDigit(caractere) || '-' == caractere) || ' ' == caractere) {
                throw new IllegalArgumentException(mensagem);
            }
        }
    }

    /**
     * Valida a data, verificando se compoe por digitos. Caso contrario, lanca-se uma excecao.
     * Se for composta por digitos, os numeros sao formatados a partir de um padrao.
     * Se nao estiver dentro do padrao, lanca-se uma excessao.
     * @param data recebida como parametro de verificacao
     * @param mensagem mensagem que sera exposta caso uma excessao for lancada.
     */

    public static void validadorData (String data, String mensagem) {
        for (int i = 0; i < data.length(); i++) {
            char caractere = data.charAt(i);
            if (!(Character.isDigit(caractere))){
                throw new IllegalArgumentException(mensagem);
            }
        }
        try {
            DateFormat format = new SimpleDateFormat("ddMMyyyy");
            format.setLenient(false);
            format.parse(data);

        } catch (ParseException e){
            throw new IllegalArgumentException(mensagem);
        }
    }

    /**
     * Verifica se a data recebida como parametro refere-se a uma data futura. Caso for, lanca-se uma excecao.
     * @param data data a ser validada.
     * @param mensagem mensagem que sera exposta caso a data for no futuro.
     */

    public static void validadorDataFutura (String data, String mensagem) {

        try {
            Date hoje = new Date();
            DateFormat format = new SimpleDateFormat("ddMMyyyy");
            Date date = format.parse(data);

            if (date.after(hoje)){
                throw new IllegalArgumentException(mensagem);
            }

        } catch (ParseException e){
            throw new IllegalArgumentException("Problemas nas conversao de datas.");
        }
    }

    public static void validadorAnoFuturo (int ano, String mensagem) {
        try {
            String anoDesejado = Integer.toString(ano);
            Date anoAtual = new Date();
            DateFormat formatoAno = new SimpleDateFormat("yyyy");
            Date year = formatoAno.parse(anoDesejado);

            if (year.after(anoAtual)){
                throw new IllegalArgumentException(mensagem);
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("Problemas nas conversao de anos.");
        }
    }

    public static void validadorAno (int ano, String mensagem) {
        if ( ano < 1998) {
            throw new IllegalArgumentException(mensagem);
        }
    }
}