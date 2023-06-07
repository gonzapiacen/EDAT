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
public class HeapMin {

    //atributos
    private static final int TAMANIO = 20;
    private Comparable[] heap;
    private int ultimo;

    //constructor
    public HeapMin() {
        this.heap = new Comparable[TAMANIO];
        this.ultimo = 0;
    }

    //insertar
    public boolean insertar(Comparable nuevo) {
        boolean exito = false;

        if (this.ultimo + 1 < this.TAMANIO - 1) {
            exito = true;
            this.ultimo++;
            this.heap[this.ultimo] = nuevo;
            subir(this.ultimo);
        }

        return exito;
    }

    private void subir(int posHijo) {

        int posPadre;
        Comparable temp = this.heap[posHijo];
        boolean exito = true;

        while (exito) {
            posPadre = posHijo / 2;

            if (posPadre >= 1) {
                if (this.heap[posPadre].compareTo(temp) > 0) {
                    //intercambio
                    this.heap[posHijo] = this.heap[posPadre];
                    this.heap[posPadre] = temp;
                    posHijo = posPadre;
                } else {
                    exito = false;
                }
            } else {
                exito = false;
            }
        }
    }

    //eliminarCima
    public boolean eliminarCima() {
        boolean exito;

        if (!esVacio()) {
            exito = false;
        } else {
            exito = true;
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            hacerBajar(1);
        }

        return exito;
    }

    private void hacerBajar(int posPadre) {
        int posHijo;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;

        while (!salir) {
            posHijo = posPadre * 2;

            if (posHijo <= this.ultimo) {
                //temp tiene al menos un hijo (izq) y lo considera menor

                if (posHijo < this.ultimo) {
                    //hijo menor tiene hermano derecho

                    if (this.heap[posHijo + 1].compareTo(this.heap[posHijo]) > 0) {
                        posHijo++;
                    }
                }

                //compara al hijo menor con el padre
                if (this.heap[posHijo].compareTo(temp) > 0) {
                    //el hijo es menor que el padre, los intercambia
                    this.heap[posPadre] = this.heap[posHijo];
                    this.heap[posPadre] = temp;
                    posPadre = posHijo;
                } else {
                    //el padre es menor que sus hijos, esta bien ubicado
                    salir = true;
                }
            } else {
                //el temp es hoja, esta bien ubicado 
                salir = true;
            }
        }
    }
    
    //Observadores
    
    //recuperarCima
    public Object recuperarCima(){
        return this.heap[1];
    }

    //esVacio
    public boolean esVacio() {
        return this.ultimo == 0;
    }

    //toString
    @Override
    public String toString() {
        String texto = "Arbol Vacio";

        if (!esVacio()) {
            int pos = 1;
            texto = "";

            while (pos <= this.ultimo) {

                texto += this.heap[pos] + "-> HI: ";
                if (pos * 2 <= this.ultimo) {
                    texto += this.heap[pos * 2];
                } else {
                    texto += "-";
                }
                texto += " HD: ";
                if (pos * 2 + 1 <= this.ultimo) {
                    texto += this.heap[pos * 2 + 1];
                } else {
                    texto += "-";
                }
                texto += "\n";
                pos++;
            }
        }

        return texto;
    }
}
