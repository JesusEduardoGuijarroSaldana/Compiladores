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
public class Fase3{
    public
        // variables
        char [] tempRenglon;            
        int lengthRenglon;
        int posicion=0;
        ArrayList<String> Keywords = new ArrayList<String>();
        ArrayList<String> palabrasList = new ArrayList<String>();
        ArrayList<String> errorsList = new ArrayList<String>();
        boolean validarIdent = false; // para el método auxConst
        boolean validarNum = false; // para el método Num
        // métodos
        
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
        public void imprimir(char [] tempRenglon, String cadenaFinal){
            System.out.println(tempRenglon);  
            System.out.println(cadenaFinal);
        };
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
        public void palabrasLista(String palabra){
            palabrasList.add(palabra);
        }
        public void palabrasListaImprimir(){
            for(int i=0; i<palabrasList.size(); i++){
                System.out.println(palabrasList.get(i));
            }            
        }
        public String evaluar(){            
            String cadenaFinal = "";   
            validarIdent = false;
            validarNum = false;
            // identificadores    
            if(posicion < lengthRenglon){ //general
                int asciiBlanco = tempRenglon[posicion];
                if((asciiBlanco == 32) || (asciiBlanco == 10) || (asciiBlanco == 13)){ //saltar espacios en blanco, etc.
                    posicion++;
                    return evaluar();
                }
                if(tempRenglon[posicion] == '@'){ // IDENTIFICADORES 
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
                    validarIdent = true;
                    imprimir(tempRenglon, cadenaFinal);                 
                } // if @
                else{
                    // Keywords
                    if(tempRenglon[posicion] == '$'){ // KEYWORDS
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
                        if(tempRenglon[posicion] == '.' || (Character.isDigit(tempRenglon[posicion]) == true)){ // NUMEROS 
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
                                validarNum = true;
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
        palabrasLista(cadenaFinal);
    return cadenaFinal;
    };
        public void defErrors(){ // definición de errores
            errorsList.add("No se encontró la palabra 'Programa'"); // error 1
            errorsList.add("Se esperaba ':' o 'Final'.");           // error 2
            errorsList.add("Se esperada la palabra '$CONST'");      // error 3
            errorsList.add("Se esperada un '@Ident'");              // error 4
            errorsList.add("Se esperaba '='");                      // error 5
            errorsList.add("Se esperaba ':'");                      // error 6
            errorsList.add("Se esperaba '('");                      // error 7
            errorsList.add("Se esperaba ')'");                      // error 8
            errorsList.add("Se esperaba la palabra '$ENTONCES'");   // error 9
            errorsList.add("Se esperaba '{'");                      // error 10
            errorsList.add("Se esperaba '}'");                      // error 11
            errorsList.add("Se esperaba ';'");                      // error 12
            errorsList.add("Se esperaba un Número");                // error 13
            errorsList.add("Se esperaba un tipo de dato válido.");  // error 14
            errorsList.add("Se esperadaba un número o un identificador."); // error 15
            errorsList.add("Se esperaba la palabra '$FUNC'");       // error 16
            errorsList.add("Se esperaba la palabra '$RETORNO'");       // error 17
            errorsList.add("Se esperaba la palabra '$ELSE'");       // error 18
            errorsList.add("Se esperaba '+' o un '-'");       // error 19            
            errorsList.add("Se esperaba un símbolo relacional");       // error 20            
        };
        public void programa(){
            String token = evaluar(); 
            if(token.equals("Programa")){
                encabezado();
                instrucciones();
                auxInstrucciones();
            }else
                System.out.println(errorsList.get(0));            
        }
        public void encabezado(){
            auxConst();
            auxVar();
            auxFunc();
        }
        public void instrucciones(){
            String token = evaluar(); 
            switch (token){
                case "$IF":{
                    token = evaluar();
                    if(token.equals('(')){
                        condicion();
                        token = evaluar();
                        if(token.equals(')')){
                            token = evaluar();
                            if(token.equals("$ENTONCES")){
                                token = evaluar();
                                if(token.equals('{')){
                                    auxIF();
                                    auxInstrucciones();
                                    token = evaluar();
                                    if(token.equals('}')){
                                        auxElse();
                                    }else
                                        System.out.println(errorsList.get(10));//error 11
                                }else
                                    System.out.println(errorsList.get(9));//error 10
                            }else
                                System.out.println(errorsList.get(8));//error 9
                        }else
                            System.out.println(errorsList.get(7));//error 8
                    }else
                        System.out.println(errorsList.get(6));// error 7
                }
                case "$FOR":{
                    token = evaluar();
                    if(token.equals('(')){
                        token = evaluar();
                        if(validarIdent == true){
                            token = evaluar();
                            if(token.equals('=')){
                                auxFor1();
                                token = evaluar();
                                if(token.equals(';')){
                                    token = evaluar();
                                    if(validarIdent == true){
                                        simbRel();
                                        auxFor1();
                                        token = evaluar();
                                        if(token.equals(';')){
                                            token = evaluar();
                                            if(validarIdent == true){
                                                token = evaluar();
                                                if(token.equals('=')){
                                                    token = evaluar();
                                                    if(validarIdent == true){
                                                        auxFor2();
                                                        token = evaluar();
                                                        if(validarNum == true){
                                                            token = evaluar();
                                                            if(token.equals(')')){
                                                                token = evaluar();
                                                                if(token.equals("$ENTONCES")){
                                                                    token = evaluar();
                                                                    if(token.equals('{')){
                                                                        auxInstrucciones();
                                                                        token = evaluar();
                                                                        if(token.equals('}')){
                                                                        }else
                                                                            System.out.println(errorsList.get(10));
                                                                    }else
                                                                        System.out.println(errorsList.get(9));
                                                                }else
                                                                    System.out.println(errorsList.get(8));
                                                            }else
                                                                System.out.println(errorsList.get(7));
                                                        }else
                                                            System.out.println(errorsList.get(12));
                                                    }else
                                                        System.out.println(errorsList.get(3));
                                                }else
                                                    System.out.println(errorsList.get(4));
                                            }else
                                                System.out.println(errorsList.get(3));
                                        }else
                                            System.out.println(errorsList.get(11));
                                    }else
                                        System.out.println(errorsList.get(3));
                                }else 
                                    System.out.println(errorsList.get(11));
                            }else
                                System.out.println(errorsList.get(4));
                        }else
                            System.out.println(errorsList.get(3));
                    }else
                        System.out.println(errorsList.get(6));
                }
                case "$LLAMAR":{
                    token = evaluar();
                    if(token.equals('(')){
                        parametros();
                        auxParametros();
                        token = evaluar();
                        if(token.equals(')')){                            
                        }else
                            System.out.println(errorsList.get(7));
                    }else
                        System.out.println(errorsList.get(6));
                }
                default:{                   
                    if(validarIdent == true){
                        token = evaluar();
                        if(token.equals('=')){
                            auxIdent1();
                            auxIdent2();
                            token = evaluar();
                            if(token.equals(':')){                                
                            }else
                                System.out.println(errorsList.get(4));
                        }else
                            System.out.println(errorsList.get(4));
                    }else
                        System.out.println(errorsList.get(3));
                }                                                         
            }
        }
        public void auxInstrucciones(){
            String token = evaluar(); 
            if(token.equals(':')){
                instrucciones();
                auxInstrucciones();
            }
            else if(token.equals("Final")){                
            }else
                System.out.println(errorsList.get(1));
        }
        public void auxConst(){
            String token = evaluar();             
            if(token.equals("$CONST")){
                auxTipo();
                token = evaluar();
                if(validarIdent == true){
                    token = evaluar();
                    if(token.equals('=')){ //
                        aux1(); 
                        token = evaluar();
                        if(token.equals(':')){
                            auxConst();
                        }else
                            System.out.println(errorsList.get(5)); // error 6
                    }else
                        System.out.println(errorsList.get(4));// error 5
                }else
                    System.out.println(errorsList.get(3)); // error 4
            }else
                System.out.println(errorsList.get(2)); // error 3
        }
        public void auxVar(){
            String token = evaluar();
            if(token.equals("$VAR")){
                auxTipo();
                token = evaluar();
                if(validarIdent == true){
                    token = evaluar();
                    if(token.equals(':')){
                        auxVar();
                    }else 
                        System.out.println(errorsList.get(5));
                }else
                    System.out.println(errorsList.get(3));
            }// falta el epsilon y el error 
        }
        public void auxFunc(){
            String token = evaluar();
            if(token.equals("$FUNC")){
                tipoRetorno();
                token = evaluar();
                if(validarIdent == true){
                    parametros();
                    token = evaluar();
                    if(token.equals('(')){
                        token = evaluar();
                        if(validarIdent == true){
                            auxFuncIdent();
                            token = evaluar();
                            if(token.equals(')')){
                                token = evaluar();
                                if(token.equals('{')){
                                    instrucciones();
                                    auxFuncIntrucciones();
                                    token = evaluar();
                                    if(token.equals("$RETORNO")){
                                        token = evaluar();
                                        if(validarIdent == true){
                                            token = evaluar();
                                            if(token.equals(':')){
                                                token = evaluar();
                                                if(token.equals('}')){
                                                    auxFunc();
                                                }else
                                                    System.out.println(errorsList.get(10));
                                            }else
                                                System.out.println(errorsList.get(5));
                                        }else
                                            System.out.println(errorsList.get(3));
                                    }else
                                        System.out.println(errorsList.get(16));
                                }else
                                    System.out.println(errorsList.get(9));
                            }else
                                System.out.println(errorsList.get(7));
                        }else
                            System.out.println(errorsList.get(3));
                    }else
                        System.out.println(errorsList.get(6));
                }else
                    System.out.println(errorsList.get(3));
            }else
                System.out.println(errorsList.get(15));
            // falta epsilon
        }
        public void auxTipo(){
            String token = evaluar(); 
            if(token.equals("$ENTERO")){                
            }else if(token.equals("$DECIMAL")){
            }else if(token.equals("$FLOTANTE")){
            }else if(token.equals("$BOOLEANO")){
            }else if(token.equals("$CARACTER")){
            }else
                System.out.println(errorsList.get(13));
        }
        public void aux1(){
            String token = evaluar(); 
            if(validarNum == true){
                token = evaluar();
                if(token.equals(':')){
                }else
                    System.out.println(errorsList.get(5));
            }else if(validarIdent == true){
                token = evaluar();
                if(token.equals(':')){
                }else
                    System.out.println(errorsList.get(5));
            }else
                System.out.println(errorsList.get(14));
        }
        public void condicion(){
            auxCond1();
            simbRel();
            auxCond1();
            auxCond2();
        }
        public void auxIF(){}
        public void auxElse(){
            String token = evaluar();
            if(token.equals("$ELSE")){
                token = evaluar();
                if(token.equals('{')){
                    auxInstrucciones();
                    token = evaluar();                    
                    if(token.equals('}')){
                    }else
                        System.out.println(errorsList.get(10));
                }else
                    System.out.println(errorsList.get(9));
            }else
                System.out.println(errorsList.get(17));
            // falta epsilon 
        }
        public void auxFor1(){
            String token = evaluar();
            if(validarIdent == true){
            }else if(validarNum == true){
            }
        }
        public void simbRel(){
            String token = evaluar();
            if(token.equals("<")){
            }else if(token.equals('>')){
            }else if(token.equals("$DIF")){
            }else if(token.equals("$IGUAL")){
            }else
                System.out.println(errorsList.get(19));
        }
        public void auxFor2(){
            String token = evaluar();
            if(token.equals('+')){
            }else if(token.equals('-')){
            }else
                System.out.println(errorsList.get(18));
        }
        public void parametros(){}
        public void auxParametros(){}
        public void auxIdent1(){}
        public void auxIdent2(){}
        public void auxCond1(){}
        public void auxCond2(){}
        public void tipoRetorno(){}
        public void auxFuncIdent(){
            String token = evaluar();
            if(token.equals(',')){
                token = evaluar();
                if(validarIdent == true){
                    auxFuncIdent();
                }else
                    System.out.println(errorsList.get(3));
            }
            // FALTA EPSILON Y ERROR
        }
        public void auxFuncIntrucciones(){
            String token = evaluar();
            if(token.equals(':')){
                token = evaluar();
                if(validarIdent == true){
                    auxFuncIntrucciones();
                }else
                    System.out.println(errorsList.get(3));
            }
            // agregar epsilon y error 
        }
    public Fase3(){ // constructor
    }
    public static void main(String[] args) throws IOException {
        Fase3 objeto1;
        objeto1 = new Fase3();
        objeto1.leerTxt();
//        objeto1.evaluar();
//        objeto1.evaluar();
//        objeto1.evaluar();
//        objeto1.evaluar();               
//        objeto1.evaluar();               
//        objeto1.evaluar();               
//        objeto1.evaluar();  
        objeto1.palabrasListaImprimir();
    }
}
