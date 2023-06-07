/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas.dinamicas;

import lineales.dinamicas.*;

/**
 *
 * @author gonza
 */
public class ArbolGen {

    private NodoGen raiz;

    public ArbolGen() {
        this.raiz = null;
    }

    //insetar
    public boolean insertar(Object hijo, Object padre) {
        boolean exito;
        if (this.esVacio()) {
            //Arbol vacio
            this.raiz = new NodoGen(hijo, null, null);
            exito = true;
        } else {
            exito = insertarAux(this.raiz, hijo, padre);
        }
        return exito;
    }

    private boolean insertarAux(NodoGen actual, Object hijo, Object padre) {
        boolean exito = false;

        if (actual != null) {
            // Visita nodo actual
            if (actual.getElem().equals(padre)) {
                if (actual.getHijoIzquierdo() != null) {
                    // Si ya tiene hijos lo inserto al final
                    NodoGen aux = actual.getHijoIzquierdo();
                    while (aux.getHermanoDerecho() != null) {
                        aux = aux.getHermanoDerecho();
                    }
                    aux.setHermanoDerecho(new NodoGen(hijo, null, null));
                } else {
                    actual.setHijoIzquierdo(new NodoGen(hijo, null, null));
                }
                exito = true;
            } else {
                // Si no, llama a los hijos
                exito = insertarAux(actual.getHijoIzquierdo(), hijo, padre);
                // Si no, llama a los hermanos
                if (!exito) {
                    exito = insertarAux(actual.getHermanoDerecho(), hijo, padre);
                }
            }
        }

        return exito;
    }

    //pertenece
    public boolean pertenece(Object buscado) {
        return perteneceAux(this.raiz, buscado);
    }

    private boolean perteneceAux(NodoGen actual, Object buscado) {
        boolean encontrado = false;
        if (actual != null) {
            //Visita n
            if (actual.getElem().equals(buscado)) {
                encontrado = true;
            } else {
                //Si no llama a los hijos
                encontrado = perteneceAux(actual.getHijoIzquierdo(), buscado);
                if (!encontrado) {
                    encontrado = perteneceAux(actual.getHermanoDerecho(), buscado);
                }
            }
        }
        return encontrado;
    }

    //ancestros
    public Lista ancestros(Object elem) {
        Lista ancestros = new Lista();
        if (!this.esVacio()) {
            ancestrosAux(this.raiz, elem, ancestros);
        }
        return ancestros;
    }

    private boolean ancestrosAux(NodoGen nodo, Object elem, Lista ancestros) {
        boolean encontrado = false;
        int longitud = 0;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                encontrado = true;
            } else {
                //buscamos en preordem
                encontrado = ancestrosAux(nodo.getHijoIzquierdo(), elem, ancestros);
                if (encontrado) {
                    //Si nos retornan true estamos en un ancestro
                    ancestros.insertar(nodo.getElem(), longitud + 1);
                    longitud++;
                } else {
                    //Si no, el llamado de los hermanos al final para no ingresarlos si se encuentra
                    encontrado = ancestrosAux(nodo.getHermanoDerecho(), elem, ancestros);
                }
            }
        }
        return encontrado;
    }

    //esVacio
    public boolean esVacio() {

        return this.raiz == null;
    }

    //altura
    public int altura() {
        int alto = -1;
        if (!esVacio()) {
            alto = auxAltura(this.raiz, alto);
        }

        return alto;
    }

    private int auxAltura(NodoGen nodo, int alto) {

        if (nodo != null) {
            //mientras tenga hermanos mantenemos el nivel
            int hermano = auxAltura(nodo.getHermanoDerecho(), alto);
            //Cada vez que bajemos al hijo aumentamos la altura
            alto = auxAltura(nodo.getHijoIzquierdo(), alto + 1);

            if (hermano > alto) {
                alto = hermano;
            }
        }

        return alto;
    }

    //nivel
    public int nivel(Object elem) {

        int nivel = auxNivel(this.raiz, elem, 0);

        return nivel;
    }

    private int auxNivel(NodoGen nodo, Object elem, int nivel) {

        int retorna = -1;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                retorna = nivel;
            } else {
                //busca en el hijo
                retorna = auxNivel(nodo.getHijoIzquierdo(), elem, nivel + 1);
                //busca en el hermano
                if (retorna == -1) {
                    retorna = auxNivel(nodo.getHermanoDerecho(), elem, nivel);
                }
            }
        }

        return retorna;
    }

    //padre
    public Object padre(Object hijo) {
        Object padre = null;

        if (!esVacio()) {
            padre = auxPadre(this.raiz.getHijoIzquierdo(), this.raiz, hijo);

        }

        return padre;
    }

    private Object auxPadre(NodoGen nodo, NodoGen padre, Object hijo) {

        Object encontrado = null;
        if (nodo != null) {
            if (nodo.getElem().equals(hijo)) {
                encontrado = padre.getElem();
            } else {
                encontrado = auxPadre(nodo.getHijoIzquierdo(), nodo, hijo);

                if (encontrado == null) {
                    encontrado = auxPadre(nodo.getHermanoDerecho(), padre, hijo);
                }
            }
        }

        return encontrado;
    }

    //preorden
    public Lista listarPreorden() {
        Lista preorden = new Lista();

        if (!esVacio()) {
            auxPreorden(this.raiz, preorden, 0);
        }

        return preorden;
    }

    private int auxPreorden(NodoGen nodo, Lista preorden, int longitud) {
        if (nodo != null) {
            preorden.insertar(nodo.getElem(), longitud + 1);
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null) {
                    longitud = auxPreorden(hijo, preorden, longitud + 1);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return longitud;
    }

    //inorden
    public Lista listarInorden() {
        Lista inorden = new Lista();

        if (!esVacio()) {
            auxInorden(this.raiz, inorden, 0);
        }

        return inorden;
    }

    private int auxInorden(NodoGen nodo, Lista inorden, int longitud) {

        if (nodo != null) {
            longitud = auxInorden(nodo.getHijoIzquierdo(), inorden, longitud);
            inorden.insertar(nodo.getElem(), longitud + 1);
            longitud++;
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    longitud = auxInorden(hijo, inorden, longitud);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }

        return longitud;
    }

    //Posorden
    public Lista listarPosorden() {
        Lista posorden = new Lista();
        if (!esVacio()) {
            listarPosordenAux(this.raiz, posorden, 0);
        }
        return posorden;
    }

    private int listarPosordenAux(NodoGen nodo, Lista posorden, int longitud) {
        if (nodo != null) {
            // Llama a los demas
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null) {
                    longitud = listarPosordenAux(hijo, posorden, longitud);
                    hijo = hijo.getHermanoDerecho();
                }
            }
            // Visitar n
            posorden.insertar(nodo.getElem(), longitud + 1);
            longitud++;
        }
        return longitud;
    }

    //listarPorNiveles
    public Lista listarPorNiveles() {
        Lista niveles = new Lista();
        if (!esVacio()) {
            Cola cola = new Cola();
            cola.poner(this.raiz);
            NodoGen nodo;
            int longitud = 1;

            while (!cola.esVacia()) {
                nodo = (NodoGen) cola.obtenerFrente();
                cola.sacar();
                niveles.insertar(nodo.getElem(), longitud);
                longitud++;
                nodo = nodo.getHijoIzquierdo();
                while (nodo != null) {
                    cola.poner(nodo);
                    nodo = nodo.getHermanoDerecho();
                }
            }

        }
        return niveles;
    }

    //clone
    @Override
    public ArbolGen clone() {
        ArbolGen clon = new ArbolGen();
        if (!this.esVacio()) {
            //Clona la raiz primero
            NodoGen nodo = new NodoGen(this.raiz.getElem(), null, null);
            clon.raiz = nodo;
            cloneAux(clon.raiz, this.raiz);
        }
        return clon;
    }

    private void cloneAux(NodoGen actual, NodoGen copia) {
        if (copia != null) {
            //Si tiene hijo, lo clona y lo revisa recursivamente
            if (copia.getHijoIzquierdo() != null) {
                actual.setHijoIzquierdo(new NodoGen(copia.getHijoIzquierdo().getElem(), null, null));
                cloneAux(actual.getHijoIzquierdo(), copia.getHijoIzquierdo());
            }
            //Si tiene hermano lo clona y lo revisa recursivamente
            if (copia.getHermanoDerecho() != null) {
                actual.setHermanoDerecho(new NodoGen(copia.getHermanoDerecho().getElem(), null, null));
                cloneAux(actual.getHermanoDerecho(), copia.getHermanoDerecho());
            }
        }
    }

    //vaciar
    public void vaciar() {
        this.raiz = null;
        //HOLA GON, TE DEJE ESTO PARA QUE TE DIVIERTAS BUSCANDO EL ERROR, ATT: Jechu.
            while(true){
                System.out.println("Holi");
            }
    }

    //toString
    @Override
    public String toString() {
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoGen n) {
        String salida = "";
        if (n != null) {
            // Visita n
            salida += n.getElem().toString() + " -> ";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                salida += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }
            /* Recorre hijos de n recursivamente
            para que agregen su subcadena a la original*/
            hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                salida += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return salida;
    }

    //frontera
    public Lista frontera() {
        Lista front = new Lista();

        if (!esVacio()) {
            auxFrontera(this.raiz, front, 0);
        }

        return front;
    }

    private int auxFrontera(NodoGen nodo, Lista front, int longitud) {

        if (nodo != null) {
            if (nodo.getHijoIzquierdo() == null) {
                front.insertar(nodo.getElem(), longitud + 1);
                longitud++;
            }
            longitud = auxFrontera(nodo.getHijoIzquierdo(), front, longitud);
            longitud = auxFrontera(nodo.getHermanoDerecho(), front, longitud);

        }

        return longitud;
    }

    //grado
    public int grado() {
        int niveles = -1;

        if (!this.esVacio()) {
            niveles = gradoAux(this.raiz, 0);
        }

        return niveles;
    }

    private int gradoAux(NodoGen nodo, int mayor) {

        if (nodo != null) {
            // Cada vez que baja a un hijo "resetea" el contador
            int hijo = 0;
            if (nodo.getHijoIzquierdo() != null) {
                hijo = gradoAux(nodo.getHijoIzquierdo(), 1);
            }
            // Si tiene hermano sumamos uno a ese contador
            if (nodo.getHermanoDerecho() != null) {
                mayor = gradoAux(nodo.getHermanoDerecho(), mayor + 1);
            }
            // Si el contador de algun hijo es mayor usamos ese
            if (hijo > mayor) {
                mayor = hijo;
            }
        }

        return mayor;
    }

    //gradoSubarbol
    public int gradoSubarbol(Object buscado) {
        int grado = -1;

        if (!this.esVacio()) {
            NodoGen nodo = buscarNodo(this.raiz, buscado);
            // Sólo revisamos el nuevo subarbol si tiene hijos
            if (nodo != null) {
                NodoGen auxiliar = nodo.getHijoIzquierdo();
                int actual = 0;
                // Revisamos los hijos del nodo encontrado por separado para evitar que gradoAux
                // recorra los hermanos de este
                while (auxiliar != null) {
                    actual++;
                    auxiliar = auxiliar.getHermanoDerecho();
                }
                // Luego suponemos que el mayor está en los hijos de la raiz del subarbol
                grado = this.gradoAux(nodo.getHijoIzquierdo(), 0);
                // En caso contrario nos quedamos con el actual
                if (grado < actual) {
                    grado = actual;
                }
            }
        }

        return grado;
    }

    private NodoGen buscarNodo(NodoGen actual, Object buscado) {
        NodoGen encontrado = null;

        if (actual != null) {
            // Visita n
            if (actual.getElem().equals(buscado)) {
                encontrado = actual;
            } else {
                // Si no, llama a los hijos
                encontrado = buscarNodo(actual.getHijoIzquierdo(), buscado);
                // Si no, llama a los hermanos
                if (encontrado == null) {
                    encontrado = buscarNodo(actual.getHermanoDerecho(), buscado);
                }
            }
        }

        return encontrado;
    }

    //equals
    public boolean equals(ArbolGen unArbol) {
        boolean esIgual;
        if (this.esVacio() && unArbol.esVacio()) {
            esIgual = true;
        } else if (this.esVacio() && !unArbol.esVacio() || unArbol.esVacio() && !this.esVacio()) {
            esIgual = false;
        } else {
            esIgual = equalsAux(this.raiz, unArbol.raiz);
        }
        return esIgual;
    }

    private boolean equalsAux(NodoGen raiz, NodoGen aux) {
        boolean esIgual = true;
        if (aux != null && raiz != null) {
            //Si un nodo es null y el otro no, entonces son distintos
            //Si ambos son no nulos, compara los elementos.
            if (!aux.getElem().equals(raiz.getElem())) {
                esIgual = false;
            } else {

                //Si los elementos son iguales, continua recorriendo para comprar
                //recorre los hijos izquierdos en preorden
                NodoGen n1 = aux.getHijoIzquierdo();
                NodoGen n2 = raiz.getHijoIzquierdo();

                //Si continua siendo igual, recorre hermanos derechos
                while (n1 != null && n2 != null && esIgual) {
                    //Recorre hermanos derechos en preorden
                    esIgual = equalsAux(n1, n2);
                    n2 = n2.getHermanoDerecho();
                    n1 = n1.getHermanoDerecho();
                }
                if (n1 != null && n2 == null || n1 == null && n2 != null) {
                    esIgual = false;
                }

            }
        }
        return esIgual;
    }

    //frontera
    public boolean sonFrontera(Lista unaLista) {
        boolean esFrontera = false;
        if (!this.esVacio()) {
            esFrontera = sonFronteraAux(this.raiz, unaLista);

        }

        return esFrontera;
    }

    private boolean sonFronteraAux(NodoGen aux, Lista unaLista) {
        boolean esFrontera = true;
        if (aux != null) {
            if (aux.getHijoIzquierdo() == null) {
                int pos = unaLista.localizar(aux.getElem());
                if (pos == -1) {
                    esFrontera = false;
                }
            } else {

                NodoGen hijo = aux.getHijoIzquierdo();
                while (hijo != null && esFrontera) {
                    esFrontera = sonFronteraAux(hijo, unaLista);
                    hijo = hijo.getHermanoDerecho();
                }
            }
            
        }
        return esFrontera;
    }

    //verificarCamino 
    public boolean verificarCamino(Lista lista) {
        return verificarCaminoAux(this.raiz, lista);
       
    }

    private boolean verificarCaminoAux(NodoGen nodo, Lista lista) {
        boolean camino = false;

        if (nodo != null) {
            if (nodo.getElem().equals(lista.recuperar(1))) {
                lista.eliminar(1);
                if (lista.esVacia()) {
                    camino = true;
                } else {
                    camino = verificarCaminoAux(nodo.getHijoIzquierdo(), lista);
                }
            } else {
                camino = verificarCaminoAux(nodo.getHermanoDerecho(), lista);
            }
        }

        return camino;
    }


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
