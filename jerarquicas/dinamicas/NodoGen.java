/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas.dinamicas;

/**
 *
 * @author gonza
 */
public class NodoGen {
    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;
    
    //Constructor
    public NodoGen(Object elemento, NodoGen izquierdo, NodoGen derecho){
        this.elem = elemento;
        this.hijoIzquierdo = izquierdo;
        this.hermanoDerecho = derecho;
    }
    
    //Observadores
    public Object getElem(){
        return this.elem;
    }
    
    public NodoGen getHijoIzquierdo(){
        return this.hijoIzquierdo;
    }
    
    public NodoGen getHermanoDerecho (){
        return this.hermanoDerecho;
    }
    
    //Modificadores
    public void setElem (Object nuevoElem){
        this.elem = nuevoElem;
    }
    
    public void setHijoIzquierdo(NodoGen nuevoIzquierdo){
        this.hijoIzquierdo = nuevoIzquierdo;
    }
    
    public void setHermanoDerecho(NodoGen nuevoDerecho){
        this.hermanoDerecho = nuevoDerecho;
    }
}
