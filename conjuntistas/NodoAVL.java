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
public class NodoAVL {

    //Atributos
    private Comparable elem;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    //Constructores
    public NodoAVL(Comparable elem) {
        this.elem = elem;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 0;
    }

    public NodoAVL(Comparable elem, NodoAVL izquierdo, NodoAVL derecho) {
        this.elem = elem;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.altura = 0;
    }

    public Comparable getElem() {
        return this.elem;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }

    public int getAltura() {
        return this.altura;
    }

    public void recalcularAltura() {
        int alturaDerecho = (this.derecho == null) ? -1 : this.derecho.getAltura();
        int alturaIzquierdo = (this.izquierdo == null) ? -1 : this.izquierdo.getAltura();
        this.altura = Math.max(alturaIzquierdo, alturaDerecho) + 1;
    }

    public void setIzquierdo(NodoAVL izq) {
        this.izquierdo = izq;
    }

    public NodoAVL getIzquierdo() {
        return this.izquierdo;
    }

    public void setDerecho(NodoAVL der) {
        this.derecho = der;
    }

    public NodoAVL getDerecho() {
        return this.derecho;
    }
}
