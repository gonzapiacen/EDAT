/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas.dinamicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

/**
 *
 * @author gonza
 */
public class ArbolBin {

    private NodoArbol raiz;

    public ArbolBin() {
        raiz = null;
    }

    //insertar
    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {

        boolean exito = true;

        if (this.raiz == null) {
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre);

            if (nPadre != null) {
                if (lugar == 'I' && nPadre.getIzquierdo() == null) {
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (lugar == 'D' && nPadre.getDerecho() == null) {
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    exito = false;
                }

            } else {
                exito = false;
            }
        }

        return exito;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {

        NodoArbol resultado = null;

        if (n != null) {
            if (n.getElem().equals(buscado)) {
                resultado = n;
            } else {
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    //esVacio
    public boolean esVacio() {

        return this.raiz == null;
    }

    //altura
    public int altura() {
        int altura = 0;

        altura = obtenerAltura(this.raiz, altura);

        return altura;
    }

    private int obtenerAltura(NodoArbol nodo, int altura) {
        if (nodo != null) {
            altura = Math.max(this.obtenerAltura(nodo.getIzquierdo(), altura + 1), this.obtenerAltura(nodo.getDerecho(), altura + 1));
        } else {
            altura--;
        }

        return altura;
    }

    //nivel
    public int nivel(Object elem) {
        int nivel;

        nivel = buscarNivel(this.raiz, 0, elem);

        return nivel;
    }

    private int buscarNivel(NodoArbol nodo, int nivel, Object elem) {

        int retorna = -1;

        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                retorna = nivel;
            } else {
                retorna = buscarNivel(nodo.getIzquierdo(), nivel + 1, elem);
                if (retorna == -1) {
                    retorna = buscarNivel(nodo.getDerecho(), nivel + 1, elem);
                }
            }
        }

        return retorna;
    }

    //padre
    public Object padre(Object hijo) {

        Object elemPadre = null;

        elemPadre = buscarPadre(this.raiz, hijo, elemPadre);

        return elemPadre;
    }

    private Object buscarPadre(NodoArbol nodo, Object hijo, Object padre) {
        Object buscado = null;

        if (nodo != null) {
            if (nodo.getElem().equals(hijo)) {
                buscado = padre;
            } else {
                buscado = buscarPadre(nodo.getIzquierdo(), hijo, nodo.getElem());
                if (buscado == null) {
                    buscado = buscarPadre(nodo.getDerecho(), hijo, nodo.getElem());
                }
            }
        }
        return buscado;
    }

    //listarPreorden
    public Lista listarPreorden() {
        Lista lis = new Lista();
        listarPreordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPreordenAux(NodoArbol nodo, Lista lis) {

        int longitud = 1;

        if (nodo != null) {
            lis.insertar(nodo.getElem(), longitud + 1);

            listarPreordenAux(nodo.getIzquierdo(), lis);
            listarPreordenAux(nodo.getDerecho(), lis);
        }

    }

    //posorden
    public Lista listarPosorden() {

        Lista lis = new Lista();
        listarPosordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPosordenAux(NodoArbol nodo, Lista lis) {

        if (nodo != null) {
            listarPosordenAux(nodo.getIzquierdo(), lis);
            listarPosordenAux(nodo.getDerecho(), lis);

            lis.insertar(nodo.getElem(), lis.longitud() + 1);
        }
    }

    //inorden
    public Lista listarInorden() {

        Lista lis = new Lista();
        listarInordenAux(this.raiz, lis);
        return lis;
    }

    private void listarInordenAux(NodoArbol nodo, Lista lis) {

        if (nodo != null) {
            listarInordenAux(nodo.getIzquierdo(), lis);
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
            listarInordenAux(nodo.getDerecho(), lis);
        }
    }

    //niveles
    public Lista listarPorNiveles() {

        Lista lis = new Lista();
        if (!esVacio()) {
            Cola cola = new Cola();
            NodoArbol nodoActual;
            cola.poner(this.raiz);

            while (!cola.esVacia()) {
                // Me coloco en el nivel
                nodoActual = (NodoArbol) cola.obtenerFrente();
                cola.sacar();
                lis.insertar(nodoActual.getElem(), lis.longitud() + 1);

                // Cargo en la lista los elementos
                if (nodoActual.getIzquierdo() != null) {
                    cola.poner(nodoActual.getIzquierdo());
                }
                if (nodoActual.getDerecho() != null) {
                    cola.poner(nodoActual.getDerecho());
                }
            }
        }

        return lis;
    }

    //clone
    @Override
    public ArbolBin clone() {
        ArbolBin clon = new ArbolBin();

        if (!esVacio()) {
            clon.raiz = clonAux(this.raiz);
        }

        return clon;
    }

    private NodoArbol clonAux(NodoArbol nodo) {

        NodoArbol nuevoClon = new NodoArbol(nodo.getElem(), null, null);

        if (nodo.getIzquierdo() != null) {
            nuevoClon.setIzquierdo(clonAux(nodo.getIzquierdo()));
        }

        if (nodo.getDerecho() != null) {
            nuevoClon.setDerecho(clonAux(nodo.getDerecho()));
        }

        return nuevoClon;
    }

    //vaciar
    public void vaciar() {

        this.raiz = null;
    }

    //toString
    @Override
    public String toString() {
        String texto = "Arbol vacio";

        if (!esVacio()) {
            texto = "";
            texto = stringAux(this.raiz, texto);
        }

        return texto;
    }

    private String stringAux(NodoArbol nodo, String texto) {
        String textoAux = texto;
        textoAux += "Nodo: " + nodo.getElem();

        if (nodo.getIzquierdo() != null) {
            textoAux += "HI: " + nodo.getIzquierdo().getElem();
        } else {
            textoAux += " HI:-";
        }

        if (nodo.getDerecho() != null) {
            textoAux += " HD:" + nodo.getDerecho().getElem() + "\n";
        } else {
            textoAux += " HD:- \n";
        }

        if (nodo.getIzquierdo() != null) {
            textoAux = stringAux(nodo.getIzquierdo(), textoAux);
        }

        if (nodo.getDerecho() != null) {
            textoAux = stringAux(nodo.getDerecho(), textoAux);
        }

        return textoAux;
    }

    //frontera
    public Lista frontera() {
        Lista front = new Lista();
        fronteraAux(this.raiz, front);
        return front;
    }

    private void fronteraAux(NodoArbol nodo, Lista front) {

        if (nodo != null) {
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                // Si estamos en una hoja lo agrega a la lista
                front.insertar(nodo.getElem(), front.longitud() + 1);
            } else {
                fronteraAux(nodo.getIzquierdo(), front);
                fronteraAux(nodo.getDerecho(), front);
            }
        }

    }

    //obtenerAncestros
    public Lista obtenerAncestros(Object elem) {
        Lista ancestros = new Lista();
        if (!esVacio()) {
            obtenerAncestrosAux(this.raiz, elem, ancestros);
            ancestros.eliminar(1);
        }
        return ancestros;
    }

    private void obtenerAncestrosAux(NodoArbol nodo, Object elem, Lista lis) {

        if (nodo != null) {
            lis.insertar(nodo.getElem(), 1);
            if (!lis.recuperar(1).equals(elem)) {
                obtenerAncestrosAux(nodo.getIzquierdo(), elem, lis);
                if (!lis.recuperar(1).equals(elem)) {
                    obtenerAncestrosAux(nodo.getDerecho(), elem, lis);
                }
                if (!lis.recuperar(1).equals(elem)) {
                    lis.eliminar(1);
                }
            }
        }

    }

    //obtenerDescendientes
    public Lista obtenerDescendientes(Object elem) {
        Lista descendientes = new Lista();
        NodoArbol nodoAux;
        if (!esVacio()) {
            nodoAux = obtenerNodo(raiz, elem);
            if (nodoAux != null) {
                listarPreordenAux(nodoAux, descendientes);
                descendientes.eliminar(1);
            }
        }
        return descendientes;
    }

    //clonarInvertido
    public ArbolBin clonarInvetido() {

        ArbolBin invertido = new ArbolBin();

        if (!esVacio()) {
            invertido.raiz = clonarInvertido(this.raiz);
        }

        return invertido;
    }

    private NodoArbol clonarInvertido(NodoArbol nodo) {

        NodoArbol nuevoClon = new NodoArbol(nodo.getElem(), null, null);

        if (nodo.getIzquierdo() != null) {
            nuevoClon.setDerecho(clonarInvertido(nodo.getIzquierdo()));
        }

        if (nodo.getDerecho() != null) {
            nuevoClon.setIzquierdo(clonarInvertido(nodo.getDerecho()));
        }

        return nuevoClon;
    }

}
