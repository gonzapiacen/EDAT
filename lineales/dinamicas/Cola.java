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
public class Cola {
    
    private Nodo frente, fin;
    
    public Cola(){
        this.frente = null;
        this.fin = null;
    }
    
    public boolean poner(Object nuevoElem){
        
        Nodo nuevoNodo = new Nodo(nuevoElem, null);
        
        if(this.frente != null){
            this.fin.setEnlace(nuevoNodo);
            this.fin = this.fin.getEnlace();
        }
        else{
            this.frente = nuevoNodo;
            this.fin = nuevoNodo;
        }
        
        return true;
    }
    
    public boolean sacar(){
        
        boolean exito = true;
        
        if(this.frente == null){
            exito = false;
        }
        else{
            this.frente = this.frente.getEnlace();
            if(this.frente == null)
                this.fin = null;
            
        }
        return exito;
    }
    
    public Object obtenerFrente(){
        
        Object frenteActual = null;
        if(this.frente != null){
            frenteActual = this.frente.getElem();
        }
        return frenteActual;
    }
    
    public boolean esVacia(){
        
        boolean vacia = false;
        
        if(this.frente == null){
            vacia = true;
        }
        
        return vacia;
    }
    
    public void vaciar(){
        this.frente = null;
        this.fin = null;
    }
    
    @Override
    public Cola clone(){
        
        Cola colaClon = new Cola();
        
        if(this.frente != null){
            
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
    
    @Override
    public String toString(){
        
        String texto;
        
        if(this.frente == null){
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
