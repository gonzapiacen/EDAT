package lineales.dinamica;

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
    
    public Pila(){
        this.tope = null;           
    }
    
    public boolean apilar(Object nuevoElem){   
        
        //crea un nuevo nodo delante de la antigua cabecera
        Nodo nuevo = new Nodo(nuevoElem, this.tope);
        
        //actualiza el tope para que apuente al nodo nuevo
        this.tope = nuevo;
        
        //nunca hay error de pila llena, entonces devuelve true
        return true;
    }
    
    public boolean desapilar(){
        
        boolean exito;
        
        if(this.tope == null){
            exito = false;
        }
        else{
            this.tope = this.tope.getEnlace();
            exito = true;
        }
        
        return exito;
    }
    
    public Object obtenerTope(){
        
        Object elem = null;
        
        if(this.tope != null){
            
            elem = this.tope.getElem();
        }
        
        return elem;
    }
    
    public boolean esVacia(){
        
        boolean vacio;
        
        if(this.tope == null){
            vacio = true;
        }
        else{
            vacio = false;
        }
        return vacio;
    }
    
    public void vaciar(){
        this.tope = null;
        
    }
    
    public Pila clon(){
        
        Pila arregloClon = new Pila();
        
        return arregloClon;
    }
    
    
}
