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
public class ArbolAVL {

    private NodoAVL raiz;

    public ArbolAVL() {
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

    private boolean perteneceAux(NodoAVL nodo, Comparable elem) {
        boolean exito = false;

        if (nodo != null) {
            //verifica igualdad
            if ((elem.compareTo(nodo.getElem()) == 0)) {
                exito = true;

                //verifica si es menor     
            } else if (elem.compareTo(nodo.getIzquierdo()) < 0) {
                exito = perteneceAux(nodo.getIzquierdo(), elem);

                //el elemento es mayor    
            } else {
                exito = perteneceAux(nodo.getDerecho(), elem);
            }
        }

        return exito;
    }

    //insertar
    public boolean insertar(Comparable elem) {
        boolean exito;

        if (!esVacio()) {
            exito = insertarAux(null, this.raiz, elem);
        } else {
            this.raiz = new NodoAVL(elem);
            exito = true;
        }

        return exito;
    }

    private boolean insertarAux(NodoAVL padre, NodoAVL nodo, Comparable elem) {
        boolean exito = true;

        if ((elem.compareTo(nodo.getElem()) == 0)) {
            // Si existe no lo insertamos
            exito = false;
        } else if (elem.compareTo(nodo.getElem()) < 0) {
            if (nodo.getIzquierdo() != null) {
                exito = insertarAux(nodo, nodo.getIzquierdo(), elem);
            } else {
                nodo.setIzquierdo(new NodoAVL(elem));
            }
        } else {
            if (nodo.getDerecho() != null) {
                exito = insertarAux(nodo, nodo.getDerecho(), elem);
            } else {
                nodo.setDerecho(new NodoAVL(elem));
            }
        }

        if (exito) {
            balancear(padre, nodo);
        }

        return exito;

    }

    //eliminar
    public boolean eliminar(Comparable elem) {
        return eliminarAux(null, this.raiz, elem);
    }

    private boolean eliminarAux(NodoAVL padre, NodoAVL hijo, Comparable elem) {
        boolean eliminado = false;

        if (hijo != null) {
            if (elem.compareTo(hijo.getElem()) == 0) {
                if (hijo.getIzquierdo() == null && hijo.getDerecho() == null) {
                    eliminarHoja(padre, elem);
                } else {
                    if (hijo.getIzquierdo() == null || hijo.getDerecho() == null) {
                        eliminarHijoUnico(padre, hijo, elem);
                    } else {
                        eliminarDosHijos(hijo);
                    }
                }
                eliminado = true;
            } else {
                if (elem.compareTo(hijo.getElem()) < 0) {
                    eliminado = eliminarAux(hijo, hijo.getIzquierdo(), elem);
                } else {
                    eliminado = eliminarAux(hijo, hijo.getDerecho(), elem);
                }
            }

            if (eliminado) {
                balancear(padre, hijo);
            }
        }

        return eliminado;
    }

    private void eliminarHoja(NodoAVL padre, Comparable elem) {

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

    private void eliminarHijoUnico(NodoAVL padre, NodoAVL hijo, Comparable elem) {

        NodoAVL subArbol = (hijo.getIzquierdo() != null) ? hijo.getIzquierdo() : hijo.getDerecho();

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

    private void eliminarDosHijos(NodoAVL hijo) {
//Reemplazo por Maximo
        NodoAVL padreNuevoNodo = buscarPadreNodoMaximo(hijo.getIzquierdo());
        NodoAVL nuevoNodo = padreNuevoNodo.getDerecho();
//Reemplazo por Minimo
//      NodoAVL padreNuevoNodo = buscarPadreNodoMinimo(hijo.getDerecho());
//      NodoAVL nuevoNodo = padreNuevoNodo.getIzquierdo();

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

    private NodoAVL buscarPadreNodoMaximo(NodoAVL nodo) {
        NodoAVL buscado = nodo;

        if (nodo != null) {
            if (nodo.getDerecho() != null && nodo.getDerecho().getDerecho() != null) {
                buscado = buscarPadreNodoMaximo(nodo.getDerecho());
            }
        }

        return buscado;
    }
    
    private NodoAVL buscarPadreNodoMinimo(NodoAVL nodo) {
        NodoAVL buscado = nodo;

        if (nodo != null) {
            if (nodo.getIzquierdo() != null && nodo.getIzquierdo().getIzquierdo() != null) {
                buscado = buscarPadreNodoMaximo(nodo.getIzquierdo());
            }
        }

        return buscado;
    }

    //balanceo y rotacion
    private void balancear(NodoAVL padre, NodoAVL nodo) {
        NodoAVL retorno;

        nodo.recalcularAltura();
        if (this.balance(nodo) < -1) {
            // Está desbalanceado hacia la derecha
            if (this.balance(nodo.getDerecho()) < 0) {
                // Si ambos están desbalanceados a la derecha es rotación simple izquierda
                retorno = rotarIzquierda(nodo);
            } else {
                // Si no rota derecha-izquierda
                nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
                retorno = rotarIzquierda(nodo);
            }
            if (padre != null) {
                padre.setDerecho(retorno);
            }
        } else if (this.balance(nodo) > 1) {
            // Está desbalanceado hacia la izquierda
            if (this.balance(nodo.getIzquierdo()) > 0) {
                // Si ambos están desbalanceados a la izquierda es rotación simple derecha
                retorno = rotarDerecha(nodo);
            } else {
                // Si no rota izquierda-derecha
                nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
                retorno = rotarDerecha(nodo);
            }
            if (padre != null) {
                padre.setIzquierdo(retorno);
            }
        }
        nodo.recalcularAltura();
    }

    private int balance(NodoAVL nodo) {
        int alturaDerecho = (nodo.getDerecho() == null) ? -1 : nodo.getDerecho().getAltura();
        int alturaIzquierdo = (nodo.getIzquierdo() == null) ? -1 : nodo.getIzquierdo().getAltura();
        return alturaIzquierdo - alturaDerecho;
    }

    private NodoAVL rotarDerecha(NodoAVL nodo) {
        NodoAVL hijoIzq = nodo.getIzquierdo();
        NodoAVL temp = hijoIzq.getDerecho();
        hijoIzq.setDerecho(nodo);
        nodo.setIzquierdo(temp);

        if (nodo == this.raiz) {
            this.raiz = hijoIzq;
        }

        return hijoIzq;
    }

    private NodoAVL rotarIzquierda(NodoAVL nodo) {
        NodoAVL hijoDer = nodo.getDerecho();
        NodoAVL temp = hijoDer.getIzquierdo();
        hijoDer.setIzquierdo(nodo);
        nodo.setDerecho(temp);

        if (nodo == this.raiz) {
            this.raiz = hijoDer;
        }

        return hijoDer;
    }

    //listar
    public Lista listar() {
        Lista lista = new Lista();

        listarAux(this.raiz, lista);

        return lista;
    }

    private void listarAux(NodoAVL nodo, Lista lista) {

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

    private void listarRangoAux(NodoAVL nodo, Comparable min, Comparable max, Lista lista) {

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

    private Comparable minimoElemAux(NodoAVL nodo) {
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

    private Comparable maximoElemAux(NodoAVL nodo) {
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
        return this.raiz == null;
    }

    //vaciar
    public void vaciar() {
        this.raiz = null;
    }

    //clone
    @Override
    public ArbolAVL clone() {
        ArbolAVL clon = new ArbolAVL();

        if (!esVacio()) {
            clon.raiz = clonAux(this.raiz);
        }

        return clon;
    }

    private NodoAVL clonAux(NodoAVL nodo) {

        NodoAVL nuevoClon = new NodoAVL(nodo.getElem(), null, null);

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

    private String stringAux(NodoAVL nodo, String texto) {
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
