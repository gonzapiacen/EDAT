/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

/**
 *
 * @author gonzalo.piacentini
 */
public class Heap {
    
    private static final int TAMANIO = 10;
    private Comparable[] heap;
    private int ultimo;
    
    //constructor
    public Heap(){
        heap = new Comparable[TAMANIO];
        ultimo = 0;
    }
    
    //modificadores
    public boolean insertar(Comparable elem){
        boolean exito = false;
        
        if(this.ultimo + 1 < this.heap.length){
            exito = true;
            this.ultimo++;
            this.heap[this.ultimo] = elem;
            subir(this.ultimo);
        }
        return exito;
    }
    
    private void subir(int posHijo){
        int posPadre;
        Comparable temp = this.heap[posHijo];
        boolean exito = true;
        while(exito){
            posPadre = posHijo/2;
            if(posPadre >= 1){
                if(this.heap[posPadre].compareTo(temp) > 0){
                    this.heap[posHijo] = this.heap[posPadre];
                    this.heap[posPadre] = temp;
                    posHijo = posPadre;
                }
                else{
                    exito = false;
                }
            }
            else{
                exito = false;
            }
        }
    }
    
    public boolean eliminarCima(){
        boolean exito;
        
        if(this.esVacio()){
            exito = false;
        }
        else{
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }
    
    private void hacerBajar(int posPadre){
        int posHijo;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;
        
        while(!salir){
            posHijo = posPadre *2;
            if(posHijo <= this.ultimo){
                if(posHijo < this.ultimo){
                    if(this.heap[posHijo + 1].compareTo(this.heap[posHijo]) < 0){
                        posHijo++;
                    }
                }
                if(this.heap[posHijo].compareTo(temp) < 0){
                    this.heap[posPadre] = this.heap[posHijo];
                    this.heap[posHijo] = temp;
                    posPadre = posHijo;
                }
                else{
                    salir = true;
                }
            }
            else{
                salir = true;
            }
        }
    }
    
    //Oberservadores
    
    public Object recuperarCima(){
        return this.heap[1];
    }
    
    public boolean esVacio(){
        return this.ultimo == 0;
    }
    
    //Propias del tipo
    @Override
    public Heap clone(){
        Heap clon = new Heap();
        
        clon.heap = this.heap.clone();
        clon.ultimo = this.ultimo;
        
        return clon;
    }
    
    @Override
    public String toString(){
        String retorno = "Arbol vacio";
        
        if(!this.esVacio()){
            int pos = 1;
            retorno = "";
            while(pos <= this.ultimo){
                retorno += this.heap[pos] + "-> HI: ";
                if(pos * 2 <= this.ultimo){
                    retorno += this.heap[pos * 2];
                }
                else{
                    retorno += "-";
                }
                retorno += " HD: ";
                if(pos * 2 + 1 <= this.ultimo){
                    retorno += this.heap[pos * 2 + 1];
                }
                else{
                    retorno += "-";
                }
                retorno += "\n";
                pos++;
            }
        }
        return retorno;
    }
}
