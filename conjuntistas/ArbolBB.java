/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

import lineales.dinamicas.Lista;

/**
 *
 * @author gonza
 */
public class ArbolBB {

    //Atributos
    private NodoABB raiz;

    //Constructor
    public ArbolBB() {
        this.raiz = null;
    }

    //pertenece
    public boolean pertenece(Comparable elem) {
        boolean exito = false;
        if (!esVacio()) {
            exito = perteneceAux(this.raiz, elem);
        }

        return exito;
    }

    private boolean perteneceAux(NodoABB nodo, Comparable elem) {
        boolean exito = false;

        if (nodo != null) {
            //verifica igualdad
            if ((elem.compareTo(nodo.getElem()) == 0)) {
                exito = true;

                //verifica si es menor     
            } else if (elem.compareTo(nodo.getElem()) < 0) {
                exito = perteneceAux(nodo.getIzquierdo(), elem);

                //el elemento es mayor    
            } else {
                exito = perteneceAux(nodo.getDerecho(), elem);
            }
        }

        return exito;
    }

    //Insertar
    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if (!esVacio()) {
            exito = insertarAux(this.raiz, elem);
        } else {
            this.raiz = new NodoABB(elem);
        }

        return exito;
    }

    private boolean insertarAux(NodoABB nodo, Comparable elem) {
        //precondicion n no es nulo
        boolean exito = true;

        if ((elem.compareTo(nodo.getElem()) == 0)) {
            //error: elemento repetido
            exito = false;

        } else if ((elem.compareTo(nodo.getElem()) < 0)) {
            //elemento menor, verifica si tiene HI
            if (nodo.getIzquierdo() != null) {
                exito = insertarAux(nodo.getIzquierdo(), elem);
            } else {
                nodo.setIzquierdo(new NodoABB(elem));
            }
        } else {
            //elemento mayor, verfica si tiene HD
            if (nodo.getDerecho() != null) {
                exito = insertarAux(nodo.getDerecho(), elem);
            } else {
                nodo.setDerecho(new NodoABB(elem));
            }
        }

        return exito;
    }

    //eliminar
    public boolean eliminar(Comparable elem) {
        boolean exito;

        if (esVacio()) {
            exito = false;
        } else {
            exito = eliminarAux(null, this.raiz, elem);
        }

        return exito;
    }

    private boolean eliminarAux(NodoABB padre, NodoABB hijo, Comparable elem) {
        boolean exito = false;

        if (hijo != null) {
            if ((elem.compareTo(hijo.getElem())) == 0) {

                if (hijo.getIzquierdo() == null && hijo.getDerecho() == null) {
                    eliminarHoja(padre, elem);
                } else {
                    if (hijo.getIzquierdo() == null || hijo.getDerecho() == null) {
                        eliminarHijoUnico(padre, hijo, elem);
                    } else {
                        eliminarDosHijos(hijo);
                    }
                }
                exito = true;

            } else if (elem.compareTo(hijo.getElem()) < 0) {
                exito = eliminarAux(hijo, hijo.getIzquierdo(), elem);

            } else {
                exito = eliminarAux(hijo, hijo.getDerecho(), elem);
            }
        }

        return exito;
    }

    private void eliminarHoja(NodoABB padre, Comparable elem) {

        if (padre != null) {

            if (padre.getIzquierdo() != null && elem.compareTo(padre.getIzquierdo().getElem()) == 0) {
                padre.setIzquierdo(null);
            } else {
                padre.setDerecho(null);
            }

        } else {
            this.raiz = null;
        }
    }

    private void eliminarHijoUnico(NodoABB padre, NodoABB hijo, Comparable elem) {

        NodoABB subArbol = (hijo.getIzquierdo() != null) ? hijo.getIzquierdo() : hijo.getDerecho();

        if (padre != null) {
            if (hijo.getIzquierdo() != null && elem.compareTo(padre.getIzquierdo().getElem()) == 0) {
                padre.setIzquierdo(subArbol);

            } else {
                padre.setDerecho(subArbol);
            }
        } else {
            this.raiz = subArbol;
        }
    }

    private void eliminarDosHijos(NodoABB hijo) {
//Reemplazo por Maximo
        NodoABB padreNuevoNodo = buscarPadreNodoMaximo(hijo.getIzquierdo());
        NodoABB nuevoNodo = padreNuevoNodo.getDerecho();
//Reemplazo por Minimo
//      NodoABB padreNuevoNodo = buscarPadreNodoMinimo(hijo.getDerecho());
//      NodoABB nuevoNodo = padreNuevoNodo.getIzquierdo();

        //Candidato con el mayor hijo del subArbol Izquierdo
        if (nuevoNodo != null) {
            hijo.setElem(nuevoNodo.getElem());
            //Elimina el candidato
            eliminarAux(padreNuevoNodo, nuevoNodo, nuevoNodo.getElem());
        } else {
            hijo.setElem(padreNuevoNodo.getElem());
            //Eliminamos el candidato
            eliminarAux(hijo, padreNuevoNodo, padreNuevoNodo.getElem());
        }
    }

    private NodoABB buscarPadreNodoMaximo(NodoABB nodo) {
        NodoABB buscado = nodo;

        if (nodo != null) {
            if (nodo.getDerecho() != null && nodo.getDerecho().getDerecho() != null) {
                buscado = buscarPadreNodoMaximo(nodo.getDerecho());
            }
        }

        return buscado;
    }

    private NodoABB buscarPadreNodoMinimo(NodoABB nodo) {
        NodoABB buscado = nodo;

        if (nodo != null) {
            if (nodo.getIzquierdo() != null && nodo.getIzquierdo().getIzquierdo() != null) {
                buscado = buscarPadreNodoMaximo(nodo.getIzquierdo());
            }
        }

        return buscado;
    }

    //listar
    public Lista listar() {
        Lista lista = new Lista();

        listarAux(this.raiz, lista);

        return lista;
    }

    private void listarAux(NodoABB nodo, Lista lista) {

        if (nodo != null) {
            listarAux(nodo.getIzquierdo(), lista);

            lista.insertar(nodo.getElem(), 1);
            listarAux(nodo.getDerecho(), lista);
        }
    }

    //listarRango
    public Lista listarRango(Comparable min, Comparable max) {
        Lista lista = new Lista();

        listarRangoAux(this.raiz, min, max, lista);

        return lista;
    }

    private void listarRangoAux(NodoABB nodo, Comparable min, Comparable max, Lista lista) {

        if (nodo != null) {
            if (nodo.getElem().compareTo(min) > 0) {
                listarRangoAux(nodo.getIzquierdo(), min, max, lista);
            }

            if (nodo.getElem().compareTo(min) >= 0 && nodo.getElem().compareTo(max) <= 0) {
                lista.insertar(nodo.getElem(), 1);
            }

            if (nodo.getElem().compareTo(max) < 0) {
                listarRangoAux(nodo.getDerecho(), min, max, lista);
            }
        }
    }

    //minimoElem
    public Comparable minimoElem() {
        return minimoElemAux(this.raiz);
    }

    private Comparable minimoElemAux(NodoABB nodo) {
        Comparable minimo = null;

        if (nodo != null) {
            if (nodo.getIzquierdo() != null) {
                minimo = minimoElemAux(nodo.getIzquierdo());
            } else {
                minimo = nodo.getElem();
            }
        }

        return minimo;
    }

    //maximoElem
    public Comparable maximoElem() {
        return maximoElemAux(this.raiz);
    }

    private Comparable maximoElemAux(NodoABB nodo) {
        Comparable maximo = null;

        if (nodo != null) {
            if (nodo.getDerecho() != null) {
                maximo = maximoElemAux(nodo.getDerecho());
            } else {
                maximo = nodo.getElem();
            }
        }

        return maximo;
    }

    //esVacio
    public boolean esVacio() {
        return raiz == null;
    }

    //vaciar
    public void vaciar() {
        this.raiz = null;
    }

    //clone
    @Override
    public ArbolBB clone() {
        ArbolBB clon = new ArbolBB();

        if (!esVacio()) {
            clon.raiz = clonAux(this.raiz);
        }

        return clon;
    }

    private NodoABB clonAux(NodoABB nodo) {

        NodoABB nuevoClon = new NodoABB(nodo.getElem(), null, null);

        if (nodo.getIzquierdo() != null) {
            nuevoClon.setIzquierdo(clonAux(nodo.getIzquierdo()));
        }

        if (nodo.getDerecho() != null) {
            nuevoClon.setDerecho(clonAux(nodo.getDerecho()));
        }

        return nuevoClon;
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

    private String stringAux(NodoABB nodo, String texto) {
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
}
