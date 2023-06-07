/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.conjuntistas;
import conjuntistas.HeapMin;
/**
 *
 * @author gonza
 */
public class TestConjuntista {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        HeapMin a1 = new HeapMin();
        
        a1.insertar(1);
        a1.insertar(2);
        a1.insertar(3);
        a1.insertar(17);
        a1.insertar(19);
        a1.insertar(36);
        a1.insertar(7);
        a1.insertar(25);
        a1.insertar(100);
        
        System.out.println(a1.toString());
        
    }
    
}
