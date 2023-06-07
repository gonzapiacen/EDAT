/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.conjuntistas;
import conjuntistas.ArbolAVL;
/**
 *
 * @author gonza
 */
public class TestAVL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArbolAVL a = new ArbolAVL();
        ArbolAVL b = new ArbolAVL();
        a.insertar(8);
        a.insertar(5);
        a.insertar(15);
        a.insertar(13);
        a.insertar(20);
        a.insertar(29);
        
        b.insertar(10);
        b.insertar(5);
        b.insertar(15);
        b.insertar(12);
        b.insertar(17);
        b.insertar(13);
        
        System.out.println(b.toString());
        //System.out.println(a.toString());
    }
    
}
