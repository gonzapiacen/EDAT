/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;

/**
 *
 * @author gonzalo.piacentini
 */
public class Pila {

    //atributos de clase
    private Nodo tope;

    public Pila() {
        this.tope = null;
    }

    //metodo apilar
    public boolean apilar(Object nuevoElem) {

        //crea un nuevo nodo delante de la antigua cabecera
        Nodo nuevo = new Nodo(nuevoElem, this.tope);

        //actualiza el tope para que apuente al nodo nuevo
        this.tope = nuevo;

        //nunca hay error de pila llena, entonces devuelve true
        return true;
    }

    //metodo desapilar
    public boolean desapilar() {

        boolean exito = false;

        //verifica que el punto no sea nulo
        if (this.tope != null) {
            //actualiza el tope para que apunte a la antigua cabecera 
            this.tope = this.tope.getEnlace();
            exito = true;
        }

        return exito;
    }

    //metodo obtenerTope
    public Object obtenerTope() {

        Object elem = null;

        if (this.tope != null) {
            elem = this.tope.getElem();
        }

        return elem;
    }

    //metodo esVacia
    public boolean esVacia() {

        boolean vacia = true;

        if (this.tope != null) {
            vacia = false;
        }

        return vacia;
    }

    //metodo vaciar
    public void vaciar() {
        //solo basta con actualizar el tope, el garbage collector elimna los nodos
        this.tope = null;
    }

    //metodo clone
    @Override
    public Pila clone() {

        //crea una pila clon vacia
        Pila pilaClone = new Pila();

        //asigna un nodo para recorrer la pila original
        Nodo nodoOriginal = this.tope;

        //crea un tope clon con el valor del tope original
        pilaClone.tope = new Nodo(nodoOriginal.getElem(), null);

        //se desplaza al otro nodo
        nodoOriginal = nodoOriginal.getEnlace();

        //asigna un nodo para recorrer la pila clon
        Nodo nodoClone = pilaClone.tope;

        //mientras hayan elementos en la original la recorre
        while (nodoOriginal != null) {
            //al ultimo nodo clon le enlaza una copia del siguiente nodo del original
            nodoClone.setEnlace(new Nodo(nodoOriginal.getElem(), null));

            //desplaza los nodos
            nodoOriginal = nodoOriginal.getEnlace();
            nodoClone = nodoClone.getEnlace();
        }

        return pilaClone;
    }

    //metodo to String
    @Override
    public String toString() {
        String texto;

        if (this.esVacia()) {
            texto = "Pila vacia";
        } else {
            Nodo nodoAuxiliar = this.tope;
            texto = "]";

            while (nodoAuxiliar != null) {
                //concatena el elemento por delante para quedar ordenado de menor a mayor tope 
                texto = nodoAuxiliar.getElem() + texto;
                nodoAuxiliar = nodoAuxiliar.getEnlace();
                if (nodoAuxiliar != null) {
                    texto = "," + texto;
                }
            }

            texto = "[" + texto;
        }

        return texto;
    }
}
