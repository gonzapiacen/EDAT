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
public class Cola {

    private Nodo frente, fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    //metodo poner
    public boolean poner(Object nuevoElem) {

        Nodo nuevoNodo = new Nodo(nuevoElem, null);
        if (!this.esVacia()) {
            this.fin.setEnlace(nuevoNodo);
            this.fin = this.fin.getEnlace();
        } else {
            this.frente = nuevoNodo;
            this.fin = nuevoNodo;
        }

        return true;
    }

    //metodo sacar
    public boolean sacar() {

        boolean exito = false;

        if (!esVacia()) {
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
            exito = true;
        }

        return exito;
    }
    
    //obtenerFrente
    public Object obtenerFrente(){
        
        Object frenteActual = null;
        
        if(!esVacia()){
            frenteActual = this.frente.getElem();
        }
        
        return frenteActual;
    }

    //metodo esVacia
    public boolean esVacia() {
        boolean vacia = false;

        if (this.frente == null) {
            vacia = true;
        }

        return vacia;
    }
    
    //metodo vaciar
    public void vaciar(){
        
        this.frente = null;
        this.fin = null;
    }
    
    //metodo clon
    @Override
    public Cola clone(){
        
        Cola colaClon = new Cola();
        
        if(!esVacia()){
            
            colaClon.frente = new Nodo(this.frente.getElem(), null);
            colaClon.fin = colaClon.frente;
            Nodo nuevoNodo = this.frente.getEnlace();
            
            while(nuevoNodo != null){
                
                colaClon.fin.setEnlace(new Nodo(nuevoNodo.getElem(), null));
                nuevoNodo = nuevoNodo.getEnlace();
                colaClon.fin = colaClon.fin.getEnlace();
            }
        }
        
        return colaClon;
    }
    
    //metodo toString
    @Override
    public String toString(){
        
        String texto;
        
        if(esVacia()){
            texto = "Cola vacia";
        }
        else{
            Nodo nodoAux = this.frente;
            texto = "";
            
            while(nodoAux != null){
                texto += nodoAux.getElem();
                nodoAux = nodoAux.getEnlace();
                if(nodoAux != null){
                    texto += ",";
                }
            }
        }
        
        return texto;
    }

}
