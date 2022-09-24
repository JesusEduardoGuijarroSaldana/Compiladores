/*
 * Jesús Eduardo Guijarro Saldaña 5CM1 2021670220
 * Héctor Isaac Román Vazquez 5CM1 2021670099
 * and open the template in the editor.
 */
package compiladores;
import java.io.BufferedReader; 
import java.io.FileNotFoundException; 
import java.io.FileReader; 
import java.io.IOException;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType;
import java.lang.String;
/**
 *
 * @author guija
 */

public class Fase1 {
            //char[] arregloIdent={'@','l','o','g','i','9'};
    public
//            char[] arregloNum={'1','0','a'}; //'1','0','.','0','9','.'
//            char[] arregloIdent={'@','1'};
//            char[] arregloKeyWord={'$','C','O','M','1'};
            char [] tempChar;
            int lengthNum;
            int lengthIdent;
            int lengthKW;
    public void imprimir(){
        //System.out.println(arregloIdent[3]);
        //System.out.println(lengthNum);
//        for(int i = 0; i < lengthNum; i++){
//            System.out.print(arregloNum[i]);
//        }
    };
    public void leerTxt() throws FileNotFoundException, IOException{
        String cadena;
        FileReader file = new FileReader("C:\\Users\\guija\\Desktop\\Cadenas.txt");
        BufferedReader buffered = new BufferedReader(file); 
        cadena = buffered.readLine();
        buffered.close();
        tempChar = cadena.toCharArray();
        lengthNum = tempChar.length;
        lengthIdent = tempChar.length;
        lengthKW = tempChar.length; 
    }
    // método para evaluar                      /////////////////
    public void evaluar(){
        //comenzamos con los números
        //boolean esNumero = false;
        int contPunto = 0;
        String numTemp = "";
        if(tempChar[0] == '.' || (Character.isDigit(tempChar[0]) == true)){
            for(int i=0; i < lengthNum; i++){
            if(tempChar[i] == '.'){
                contPunto++;
            }
            if((Character.isDigit(tempChar[i]) == true || tempChar[i]=='.')&& contPunto <= 1){ //hacer más grande la condición de un lado que digit sea true y contPunto menor o igual a 1, y del otro lado que digit sea false y el caracter sea '.'
                //esNumero = true;
                numTemp = numTemp+tempChar[i];
            }else{
                break;
            }
            //System.out.println(Character.isDigit(arregloIdent[i]));            
        }
            System.out.println(tempChar);
            System.out.println(numTemp);
        }
        
        
        //acaban la evaluación de los números /////////////////
        // EVALUAR IDENTIFICADORES /////**/*/*/*/*/*/*/*/*/*/*/
        //boolean esIdentificador = false;
        String identTemp = "";
        if(tempChar[0] == '@'){
            identTemp = identTemp+tempChar[0];
                for(int i=1; i< (lengthIdent); i++){
                    if(Character.isAlphabetic(tempChar[i]) == true){
                        //esIdentificador = true;
                        identTemp = identTemp+tempChar[i];
                    }else{
                        //esIdentificador = false;
                        break;
                    }                    
                }
                System.out.println(tempChar);
                System.out.println(identTemp);
            }
        
        // FIN DE EVALUAR IDENTIFICADORES /////**/*/*/*/*/*/*/*/*/*/*/    
        // EVALUAR PALABRAS RESERVADAS /////**/*/*/*/*/*/*/*/*/*/*/
        //boolean esKeyWord = false;
        String kwTemp = "";
        if(tempChar[0] == '$'){
            kwTemp = kwTemp+tempChar[0];            
            for(int i=1; i<lengthKW; i++){
                int tempASCII = tempChar[i];                
                    if(64 < tempASCII && tempASCII < 91){
                        //esKeyWord = true;
                        kwTemp = kwTemp+tempChar[i];
                    }else{
                        //esKeyWord = false;
                        break;
                    }
                }
            System.out.println(tempChar);
            System.out.println(kwTemp);
            } //else
            //esKeyWord = false;
        // FIN PALABRAS RESERVADAS /////**/*/*/*/*/*/*/*/*/*/*/
///////// ******** IF'S QUE EVALUAN SI SON O NO ALGUNA COSA
//        if(esNumero == true){
//            System.out.println("La cadena es un número.");
//        }else
//            System.out.println("La cadena no es un número.");
//        System.out.println("");
//        if(esIdentificador == true){
//            System.out.println("La cadena es un identificador.");
//        }else
//            System.out.println("La cadena no es un identificador.");
//        System.out.println("");
//        if(esKeyWord == true){
//            System.out.println("La cadena es un keyword.");
//        }else
//            System.out.println("La cadena no es un keyword.");
//
        
    };
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
//    public static void main(String[] args) throws IOException {
//        // TODO code application logic here
//        Compiladores objeto1;
//        objeto1 = new Compiladores();
//        objeto1.imprimir();
//        objeto1.leerTxt();
//        objeto1.evaluar();
//        
//    }
    
}

