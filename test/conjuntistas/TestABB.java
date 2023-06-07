/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.conjuntistas;
import conjuntistas.ArbolBB;
/**
 *
 * @author gonza
 */
public class TestABB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArbolBB a = new ArbolBB();
        ArbolBB a2 = new ArbolBB();
        
        a.insertar(32);
        a.insertar(9);
        a.insertar(56);
        a.insertar(5);
        a.insertar(19);
        a.insertar(43);
        a.insertar(72);
        a.insertar(1);
        a.insertar(8);
        a.insertar(17);
        a.insertar(23);
        a.insertar(41);
        a.insertar(53);
        a.insertar(64);
        a.insertar(80);
        
        a2 = a.clone();
        
        //System.out.println(a.toString());
        //System.out.println(a.pertenece(32));
        //System.out.println(a.eliminar(32));
        //System.out.println(a.toString());
        //System.out.println(a.listar().toString());
        //System.out.println(a.listarRango(3, 57));
        //System.out.println(a.minimoElem());
        //System.out.println(a.maximoElem());
        //System.out.println(a2.toString());
    }
    
}
