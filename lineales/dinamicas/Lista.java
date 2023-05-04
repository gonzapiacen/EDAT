/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;

/**
 *
 * @author gonza
 */
public class Lista {

    private Nodo cabecera;
    //private int longitud;

    public Lista() {

        this.cabecera = null;
        //this.longitud = 0;
    }

    //metodo insertar
    public boolean insertar(Object unElem, int pos) {

        boolean exito = true;

        if (1 > pos || pos > this.longitud() + 1) {
            exito = false;
        } else {
            if (pos == 1) {
                this.cabecera = new Nodo(unElem, this.cabecera);
                //this.longitud = this.longitud();
                //this.longitud++;
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(unElem, aux.getEnlace());
                aux.setEnlace(nuevo);
                //this.longitud = this.longitud();
                //this.longitud++;
            }

        }

        return exito;
    }

    //metodo eliminar
    public boolean eliminar(int pos) {

        boolean exito = false;

        if (1 <= pos && pos <= this.longitud()) {
            Nodo aux = this.cabecera;
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
                //this.longitud = this.longitud();
            } else {
                int iterador = 1;
                while (iterador < pos - 1) {
                    aux = aux.getEnlace();
                    iterador++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
                //this.longitud = this.longitud();
            }
            exito = true;
        }
        return exito;
    }

    //metodo recuperar
    public Object recuperar(int pos) {
        Object elem = null;

        if (1 <= pos && pos <= longitud()) {
            int iterador = 1;
            Nodo aux = this.cabecera;

            while (iterador < pos) {
                iterador++;
                aux = aux.getEnlace();
            }
            elem = aux.getElem();
        }

        return elem;
    }

    //metodo localizar
    public int localizar(Object elem) {
        int pos = -1, iterador = 1;
        Nodo aux = this.cabecera;
        boolean encontrado = false;
        int longitud = this.longitud();

        while (!encontrado && iterador <= longitud) {
            if (aux.getElem().equals(elem)) {
                encontrado = true;
                pos = iterador;
            } else {
                aux = aux.getEnlace();
                iterador++;
            }
        }

        return pos;
    }

    //metodo vaciar
    public void vaciar() {
        this.cabecera = null;
    }

    //metodo esVacia
    public boolean esVacia() {

        return this.cabecera == null;
    }

    //metodo clone
    @Override
    public Lista clone() {
        Lista clon = new Lista();
        
        if(!this.esVacia()){
        Nodo nodoDelante = this.cabecera;
        clon.cabecera = new Nodo(nodoDelante.getElem(), null);
        nodoDelante = nodoDelante.getEnlace();
        Nodo nodoDetras = clon.cabecera;

        while (nodoDelante != null) {
            nodoDetras.setEnlace(new Nodo(nodoDelante.getElem(), null));
            nodoDetras = nodoDetras.getEnlace();
            nodoDelante = nodoDelante.getEnlace();
        }
        }
        return clon;
    }

    //metodo longitud
    public int longitud() {

        int largo = 0;
        Nodo nodoAux = this.cabecera;

        while (nodoAux != null) {
            largo++;
            nodoAux = nodoAux.getEnlace();
        }

        return largo;
    }

    //metodo toString
    @Override
    public String toString() {
        String texto;

        if (this.esVacia()) {
            texto = "Lista Vacia";
        } else {
            Nodo aux = this.cabecera;
            texto = "";

            while (aux != null) {
                texto += aux.getElem();
                aux = aux.getEnlace();

                if (aux != null) {
                    texto += ", ";
                }
            }
        }
        return texto;
    }

    //metodo invertir
    public void invertir() {

        Nodo puntero = null;
        Nodo detras = this.cabecera;
        Nodo delante;
            while(detras != null){
                delante = detras.getEnlace();
                detras.setEnlace(puntero);
                puntero = detras;
                detras = delante;
                }
            this.cabecera = puntero;
        }

    
    //metodo eliminarApariciones
    
    //iterativo
    public void eliminarApariciones(Object elem) {

        while (this.cabecera.getElem().equals(elem)) {
            this.cabecera = this.cabecera.getEnlace();

        }

        Nodo auxDetras = this.cabecera;
        Nodo auxDelante = this.cabecera.getEnlace();

        while (auxDelante != null) {
            if (auxDelante.getElem().equals(elem)) {
                auxDetras.setEnlace(auxDetras.getEnlace().getEnlace());
                auxDelante = auxDelante.getEnlace();
            } else {
                auxDetras = auxDetras.getEnlace();
                auxDelante = auxDelante.getEnlace();
            }

        }
    }
    
    //recursivo
//    public void eliminarApariciones(Object elem){
//        
//        if(!esVacia()){
//            while(!esVacia() && this.cabecera.getElem().equals(elem)){
//                this.cabecera = this.cabecera.getEnlace();
//            }
//            if(!esVacia()){
//                eliminarAparicionesAux(this.cabecera, this.cabecera.getEnlace(), elem);
//            }
//        }
//    }
//    
//    private void eliminarAparicionesAux(Nodo atras, Nodo delante, Object elem){
//        if(delante != null){
//            if(delante.getElem().equals(elem)){
//                delante = delante.getEnlace();
//                atras.setEnlace(delante);
//                eliminarAparicionesAux(atras, delante, elem);
//            } else{
//                eliminarAparicionesAux(atras.getEnlace(), delante.getEnlace(), elem);
//            }
//        }
//    }
    
    //obtenerMultiplos
    public Lista obtenerMultiplos(int num){
        Lista multiplos = new Lista();
        
        if(!esVacia() && num > 0){
            int i = 1;
            Nodo aux = this.cabecera;
            while(i % num != 0 && aux != null){
                aux = aux.getEnlace();
                i++;
            }
            
            if(i % num == 0 && aux != null){
                multiplos.cabecera = new Nodo(aux.getElem(), null);
                aux = aux.getEnlace();
                i++;
                Nodo auxMulti = multiplos.cabecera;
                
                while(aux != null){
                    if(i % num == 0){
                        auxMulti.setEnlace(new Nodo(aux.getElem(), null));
                        aux = aux.getEnlace();
                        auxMulti = auxMulti.getEnlace();
                        i++;
                    }else{
                        aux = aux.getEnlace();
                        i++;
                    }
                }
            }
        }
        
        return multiplos;
    }
}
