package ECO.Util;


public class  Conversoes {

    public static String converteData(String dataDeInicio) {
        String aux = "";
        char[] array = dataDeInicio.toCharArray();
        for(int i=0; i<array.length; i++) {
            if(i == 1 || i == 3) {
                aux += array[i] + "/";
            }else {
                aux += array[i];
            }
        }
        dataDeInicio=aux;
        return dataDeInicio;
    }

}
