/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

/**
 *
 * @author gonza
 */
public class NodoABB {
    
    private Comparable elem;
    private NodoABB izquierdo;
    private NodoABB derecho;
    
    //constructores 
    public NodoABB(Comparable elem){
        this.elem = elem;
        this.izquierdo = null;
        this.derecho = null;
    }
    
    public NodoABB(Comparable elem, NodoABB izq, NodoABB der){
        this.elem = elem;
        this.izquierdo = izq;
        this.derecho = der;
    }
    
    //Observadores
    public Comparable getElem(){
        return this.elem;
    }
    
    public NodoABB getIzquierdo(){
        return this.izquierdo;
    }
    
    public NodoABB getDerecho(){
        return this.derecho;
    }
    
    //Modificadores
    public void setElem(Comparable elem){
        this.elem = elem;
    }
    
    public void setIzquierdo(NodoABB izq){
        this.izquierdo = izq;
    }
    
    public void setDerecho(NodoABB der){
        this.derecho = der;
    }
}
