/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;
import java.io.BufferedReader; 
import java.io.File;
import java.io.FileNotFoundException; 
import java.io.FileReader; 
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author guija
 */
public class Fase2{
    public
        // variables
        char [] tempRenglon;            
        int lengthRenglon;
        int posicion=0;
        ArrayList<String> Keywords = new ArrayList<String>();
        
        // métodos
        public void imprimir(char [] tempRenglon, String cadenaFinal){
            System.out.println(tempRenglon);  
            System.out.println(cadenaFinal);
        };
        public void leerTxt() throws FileNotFoundException, IOException{
            File archivo = null;
            FileReader fr = null;
            BufferedReader br = null;
            archivo = new File ("C:\\Users\\guija\\OneDrive\\Escritorio\\Cadenas.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            String cadenaTemp;
            String cadena="";
            while((cadenaTemp=br.readLine())!=null)
                cadena = cadena+cadenaTemp;
            tempRenglon = cadena.toCharArray();
            lengthRenglon = tempRenglon.length;             
        }
        public void evaluarKeywords(String tempCadenaFinal){
            Keywords.add("$CONST"); Keywords.add("$VAR"); Keywords.add("$ENTERO");
            Keywords.add("$DECIMAL"); Keywords.add("$CARACTER"); Keywords.add("$CADENA");
            Keywords.add("$BOOLEANO"); Keywords.add("$VOID"); Keywords.add("$LLAMAR");
            Keywords.add("$IF"); Keywords.add("$ELSE"); Keywords.add("$FOR");
            Keywords.add("$AND"); Keywords.add("$OR"); Keywords.add("$DIF");
            Keywords.add("$IGUAL"); Keywords.add("$FLOTANTE"); Keywords.add("$ENTONCES");
            Keywords.add("$RETORNO");
            Keywords.add("+"); Keywords.add("*"); Keywords.add("-"); Keywords.add("/"); 
            Keywords.add("<"); Keywords.add(">"); Keywords.add("="); Keywords.add(":");
            boolean valido = false;
            for(int i=0; i<27; i++){
                if(tempCadenaFinal.equals(Keywords.get(i))){                    
                    valido = true;
                    break;
                }                       
            }            
            if(valido == true){
                    System.out.println(tempCadenaFinal+" es válido.");
                }else{
                System.out.println(tempCadenaFinal+" no es válido.");
            }
        }
        public String evaluar(){            
            String cadenaFinal = "";          
            // identificadores    
            if(posicion < lengthRenglon){ //general
                int asciiBlanco = tempRenglon[posicion];
                if((asciiBlanco == 32) || (asciiBlanco == 10) || (asciiBlanco == 13)){
                    posicion++;
                    return evaluar();
                }
                if(tempRenglon[posicion] == '@'){ // if @
                    cadenaFinal = ""; //limpiamos cadena
                    cadenaFinal = cadenaFinal+tempRenglon[posicion];
                    posicion++;
                    while(posicion < lengthRenglon){
                        if(Character.isAlphabetic(tempRenglon[posicion]) == true){
                            cadenaFinal = cadenaFinal+tempRenglon[posicion];                   
                            posicion++;                                                 
                        }else
                            break;
                    }
                    imprimir(tempRenglon, cadenaFinal);                 
                } // if @
                else{
                    // Keywords
                    if(tempRenglon[posicion] == '$'){ // if $
                        cadenaFinal = "";//limpiamos cadena
                        cadenaFinal = cadenaFinal+tempRenglon[posicion];
                        posicion++;
                        while(posicion < lengthRenglon){
                            int tempASCII = tempRenglon[posicion];
                            if(64 < tempASCII && tempASCII < 91){
                                cadenaFinal = cadenaFinal+tempRenglon[posicion];
                                posicion++; 
                            }else                            
                                break;
                        }
                        
                        imprimir(tempRenglon, cadenaFinal);   
                        evaluarKeywords(cadenaFinal);
//                        if(cadenaFinal.equals("$CONST")) {
//                            int hola = 0;
//                        }
                    } // if $
                    else{
                        // Numeros
                        int contPunto = 0;
                        if(tempRenglon[posicion] == '.' || (Character.isDigit(tempRenglon[posicion]) == true)){
                                cadenaFinal = "";//limpiamos cadena
                                while(posicion < lengthRenglon){
                                    if(tempRenglon[posicion] == '.'){
                                        contPunto++;
                                    }
                                    if((Character.isDigit(tempRenglon[posicion]) == true || tempRenglon[posicion]=='.')&& contPunto <= 1){ //hacer más grande la condición de un lado que digit sea true y contPunto menor o igual a 1, y del otro lado que digit sea false y el caracter sea '.'
                                        //esNumero = true;
                                        cadenaFinal = cadenaFinal+tempRenglon[posicion];
                                        posicion++;
                                    }else{
                                        break;
                                    }
                                //System.out.println(Character.isDigit(arregloIdent[i]));            
                                }
                                imprimir(tempRenglon, cadenaFinal);                 
                        }
                        else{
                            //evaluarSimbolos(cadenaFinal);
                            if((tempRenglon[posicion] != '@') && (tempRenglon[posicion] != '$') && (Character.isDigit(tempRenglon[posicion]) == false)){
                                cadenaFinal = cadenaFinal+tempRenglon[posicion];
                                imprimir(tempRenglon, cadenaFinal);                
                                posicion++;
                                evaluarKeywords(cadenaFinal);
                            }
                       }
                    }
                }
            } //general            
    return cadenaFinal;
    }; 
    public Fase2(){ // constructor
    }
    public static void main(String[] args) throws IOException {
        Fase2 objeto1;
        objeto1 = new Fase2();
        objeto1.leerTxt();
        objeto1.evaluar();
        objeto1.evaluar();
        objeto1.evaluar();
        objeto1.evaluar();               
        objeto1.evaluar();               
        objeto1.evaluar();               
        objeto1.evaluar();               
    }
    
}
