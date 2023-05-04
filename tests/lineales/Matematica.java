/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.lineales;
import lineales.dinamicas.*;

/**
 *
 * @author gonza
 */
public class Matematica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Cola c1 = new Cola();
        
        c1.poner('{');
        c1.poner('5');
        c1.poner('+');
        c1.poner('[');
        c1.poner('8');
        c1.poner('*');
        c1.poner('9');
        c1.poner('-');
        c1.poner('(');
        c1.poner('4');
        c1.poner('/');
        c1.poner('2');
        c1.poner(')');
        c1.poner('+');
        c1.poner('7');
        c1.poner(']');
        c1.poner('-');
        c1.poner('1');
        c1.poner('}');
        
        System.out.println(verificarBalanceo(c1));
    }
    
    public static boolean verificarBalanceo(Cola c1){
        boolean balanceado = false;
        Cola clon = c1.clone();
        Pila aux = new Pila();
        boolean seguir = true;
        while(!clon.esVacia() && seguir){
            switch ((char)clon.obtenerFrente()) {
                case '(':
                case '[':
                case '{':
                    aux.apilar(clon.obtenerFrente());
                    clon.sacar();
                    break;
                case ')':
                case ']':
                case '}':
                    seguir = auxVerificar((char)clon.obtenerFrente(), aux);
                    clon.sacar();
                    break;
                default:
                    clon.sacar();
                    break;
            }
        }
        if(seguir){
            balanceado = true;
        }
        if(!aux.esVacia()){
            balanceado = false;
        }
        
        return balanceado;
    }
        
        private static boolean auxVerificar(char caracter, Pila aux){
            
            boolean cierre = false;
            
            if(aux.obtenerTope() != null){
            switch(caracter){
                case ')': if((char) aux.obtenerTope() == '('){
                    cierre = true;
                    aux.desapilar();
                    break;
                }
                case ']': if((char) aux.obtenerTope() == '['){
                    cierre = true;
                    aux.desapilar();
                    break;
                }
                case '}': if((char) aux.obtenerTope() == '{'){
                    cierre = true;
                    aux.desapilar();
                    break;
                }
            }
            }
            
            return cierre;
        }
    
}
