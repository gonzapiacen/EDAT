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
public class TestArbolGen {

    /**
     * @param args the command line arguments
     */
        public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        Lista l1 = new Lista();
        l1.insertar(1, 1);
        l1.insertar(2, 1);
        l1.insertar(3, 1);
        System.out.println("BIENVENIDOS AL TEST DE LOS METODOS \"sonFrontera(unaLista)\" Y \"equals\"");
        System.out.println("PROBAMOS METODO sonFrontera: ");

        System.out.println();
        System.out.println("PROBAMOS CON ARBOL VACIO:");
        System.out.println("LISTA:" + l1.toString());
        System.out.println("ARBOL:");
        System.out.println(arbol.toString());
        System.out.println("ESPERA FALSE -----> " + arbol.sonFrontera(l1));
        System.out.println("VACIAMOS LA LISTA:");
        l1.vaciar();
        System.out.println();
        System.out.println("LISTA: " + l1.toString());
        System.out.println("ARBOL:\n" + arbol.toString());
        System.out.println("ESPERA FALSE -----> " + arbol.sonFrontera(l1));
        System.out.println();

        System.out.println("INSERTAMOS UN ELEMENTO EN EL ARBOL Y UN ELEMENTO EN LA LISTA");
        l1.insertar(1, 1);
        arbol.insertar(1, 1);
        System.out.println("LISTA: " + l1.toString());
        System.out.println("ARBOL: \n" + arbol.toString());

        System.out.println("ES FRONTERA: ");
        System.out.println("ESPERA TRUE ----> " + arbol.sonFrontera(l1));
        System.out.println();

        System.out.println("AGREGAMOS ELEMENTOS A LA LISTA:");
        l1.insertar(2, 1);
        l1.insertar(3, 1);
        l1.insertar(4, 1);
        l1.insertar(5, 1);
        l1.insertar(6, 1);
        System.out.println("LISTA: " + l1.toString());
        System.out.println("ARBOL: \n" + arbol.toString());
        System.out.println("ES FRONTERA: ");
        System.out.println("ESPERA TRUE ----> " + arbol.sonFrontera(l1));
        System.out.println();
        System.out.println("AGREGAREMOS EL ELEMENTO 7 COMO HIJO DE 1");
        arbol.insertar(7, 1);
        System.out.println("LISTA: " + l1.toString());
        System.out.println("ARBOL: \n" + arbol.toString());
        System.out.println("ES FRONTERA: ");
        System.out.println("ESPERA FALSE ----> " + arbol.sonFrontera(l1));
        System.out.println();
        System.out.println("AGREGAREMOS LOS ELEMENTOS 1,2 y 3 COMO HIJOS DE 7");
        arbol.insertar(1, 7);
        arbol.insertar(2, 7);
        arbol.insertar(3, 7);
        System.out.println("LISTA: " + l1.toString());
        System.out.println("ARBOL: \n" + arbol.toString());
        System.out.println("ES FRONTERA: ");
        System.out.println("ESPERA TRUE ----> " + arbol.sonFrontera(l1));
        System.out.println();

        System.out.println("AGREGAMOS A 10 COMO ELEMENTO HIJO DE 3:");
        arbol.insertar(10, 3);
        System.out.println("LISTA: " + l1.toString());
        System.out.println("ARBOL: \n" + arbol.toString());
        System.out.println("ES FRONTERA: ");
        System.out.println("ESPERA FALSE ----> " + arbol.sonFrontera(l1));

        System.out.println();
        arbol.vaciar();
        System.out.println("A CONTINUACION PROBAREMOS EL METODO equals:");
        System.out.println();
        ArbolGen otro = new ArbolGen();

        System.out.println("PROBAMOS CON ARBOLES VACIOS: ");
        System.out.println("ARBOL 1:");
        System.out.println(arbol.toString());
        System.out.println("ARBOL 2:");
        System.out.println(otro.toString());

        System.out.println("ESPERA TRUE ----> " + arbol.equals(otro));
        System.out.println();
        System.out.println("AGREGAMOS EL ELEMENTO 1 AL ARBOL 1:");
        arbol.insertar(1, 1);
        System.out.println("ARBOL 1:");
        System.out.println(arbol.toString());
        System.out.println("ARBOL 2:");
        System.out.println(otro.toString());
        System.out.println("ESPERA FALSE ----> " + arbol.equals(otro));
        System.out.println();
        System.out.println("AGREGAMOS ELEMENTO 1 AL ARBOL 2: ");
        otro.insertar(1, 1);
        System.out.println("ARBOL 1:");
        System.out.println(arbol.toString());
        System.out.println("ARBOL 2:");
        System.out.println(otro.toString());
        System.out.println("ESPERA TRUE ----> " + arbol.equals(otro));
        System.out.println("");
        
        System.out.println("AGREGAMOS A 2 COMO HIJO DE 1 EN AMBOS ARBOLES");
        otro.insertar(2, 1);
        arbol.insertar(2, 1);
        System.out.println("ARBOL 1:");
        System.out.println(arbol.toString());
        System.out.println("ARBOL 2:");
        System.out.println(otro.toString());
        System.out.println("ESPERA TRUE ----> "+arbol.equals(otro));
        System.out.println();
        
        System.out.println("AGREGAMOS A 3 COMO HIJO DE 2 EN EL ARBOL 1 ");
        System.out.println("AGREGAMOS A 3 COMO HIJO DE 1 EN EL ARBOL 2 ");
        arbol.insertar(3, 2);
        otro.insertar(3, 1);
        System.out.println("ARBOL 1:");
        System.out.println(arbol.toString());
        System.out.println("ARBOL 2:");
        System.out.println(otro.toString());
        System.out.println("ESPERA FALSE ---->"+arbol.equals(otro));
        System.out.println();
        System.out.println("AGREGAMOS 3 COMO HIJO DE 1 EN ARBOL 1 y 3 COMO HIJO DE 2 EN ARBOL 2 PARA RECUPERAR IGUALDAD");
        arbol.insertar(3, 1);
        otro.insertar(3, 2);
        System.out.println("ARBOL 1:");
        System.out.println(arbol.toString());
        System.out.println("ARBOL 2:");
        System.out.println(otro.toString());
        System.out.println("ESPERA TRUE ---->"+arbol.equals(otro));
    }
    
}
