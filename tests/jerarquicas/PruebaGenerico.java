/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.jerarquicas;
import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;

/**
 *
 * @author gonza
 */
public class PruebaGenerico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ArbolGen a1 = new ArbolGen();
        ArbolGen a2 = new ArbolGen();
        a1.insertar('A', 1);
        a1.insertar('B', 'A' );
        a1.insertar('E', 'B' );
        a1.insertar('F', 'B' );
        a1.insertar('J', 'F' );
        a1.insertar('K', 'F' );
        a1.insertar('L', 'F' );
        a1.insertar('C', 'A' );
        a1.insertar('D', 'A' );
        a1.insertar('G', 'D' );
        a1.insertar('H', 'D' );
        a1.insertar('I', 'D' );
        a1.insertar('M', 'G' );
        a1.insertar('P', 'M' );
        a1.insertar('Q', 'M' );
        a1.insertar('N', 'I' );
        a1.insertar('O', 'I' );
        
        
        System.out.println(a1.toString());
        System.out.println(a1.pertenece('O'));
        System.out.println(a1.pertenece('Z'));
        System.out.println(a1.ancestros('Q').toString());
        System.out.println(a1.altura());
        System.out.println(a1.nivel('Q'));
        System.out.println(a1.padre('O'));
        System.out.println(a1.listarPreorden().toString());
        System.out.println(a1.listarInorden().toString());
        System.out.println(a1.listarPosorden().toString());
        System.out.println(a1.listarPorNiveles().toString());
        System.out.println(a1.grado());
        System.out.println(a1.frontera().toString());
        System.out.println(a2.padre(20));
    }
    
}
