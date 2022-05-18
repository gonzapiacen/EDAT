package lineales.dinamicas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gonzalo.piacentini
 */
public class Pila {

    private Nodo tope;

    public Pila() {
        this.tope = null;
    }

    public boolean apilar(Object nuevoElem) {

        //crea un nuevo nodo delante de la antigua cabecera
        Nodo nuevo = new Nodo(nuevoElem, this.tope);

        //actualiza el tope para que apuente al nodo nuevo
        this.tope = nuevo;

        //nunca hay error de pila llena, entonces devuelve true
        return true;
    }

    public boolean desapilar() {

        boolean exito;

        //verfica que el tope no sea nulo
        if (this.tope == null) {
            exito = false;
        //actualiza el tope para que apunte al nodo siguiente    
        } else {
            this.tope = this.tope.getEnlace();
            exito = true;
        }

        return exito;
    }

    public Object obtenerTope() {

        Object elem = null;
        
        //verifica que el tope no sea nulo y le asigna el valor del tope no nulo
        if (this.tope != null) {

            elem = this.tope.getElem();
        }

        return elem;
    }

    public boolean esVacia() {

        boolean vacio;
        
        //verifica si es vacio o no
        if (this.tope == null) {
            vacio = true;
        } else {
            vacio = false;
        }
        return vacio;
    }

    public void vaciar() {
        this.tope = null;

    }

    @Override
    public Pila clone() {
        
        //crea una pila clon vacia
        Pila arregloClon = new Pila();
        //asigna un nodo  para recorrer la pila original
        Nodo nodoOriginal = this.tope;
        //crea el tope clon con el valor del tope original
        arregloClon.tope = new Nodo(nodoOriginal.getElem(), null);
        //se desplaza al otro nodo
        nodoOriginal = nodoOriginal.getEnlace();
        //asigna un nodo para recorrer la pila clon
        Nodo nodoClon = arregloClon.tope;

        //mientras hayan elementos en la original la recorre 
        while (nodoOriginal != null) {
            //al ultimo nodo clon le enlaza una copia del siguiente nodo del original
            nodoClon.setEnlace(new Nodo(nodoOriginal.getElem(), null));
            //se desplazan los nodos
            nodoOriginal = nodoOriginal.getEnlace();
            nodoClon = nodoClon.getEnlace();
        }

        return arregloClon;
    }

    @Override
    public String toString() {
        String s = "";

        if (this.tope == null) {
            s = "Pila vacia";
        } else {
            //se ubica para recorrer la pila
            Nodo aux = this.tope;
            s = "[";

            while (aux != null) {
                //agrega texto del elem y avanza
                s += aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    s += ",";
                }
                
            }
            s += "]";
        }

        return s;
    }

}
