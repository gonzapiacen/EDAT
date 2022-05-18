/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.estaticas;

/**
 *
 * @author gonzalo.piacentini
 */
public class Cola {
    
    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;
    
    //crea y devuelve una cola vacia
    public Cola(){
        this.arreglo = new Object[this.TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }
    
    public boolean poner(Object nuevoElem){
        
        boolean exito = false;
        int nuevoFin = (this.fin + 1 )% TAMANIO;
        
        if(this.arreglo[nuevoFin] == null){
            this.arreglo[this.fin] = nuevoElem;
            this.fin = nuevoFin;
            exito = true;
        }
            
        return exito;
    }
    
    public boolean sacar(){
        
       boolean exito = true;
       
       if (this.frente == this.fin){
           exito = false;
       }
       else{
           this.arreglo[this.frente] = null;
           this.frente = (this.frente + 1) % TAMANIO;
       }
       return exito;
    }
    
    public Object obtenerFrente(){
        Object frenteActual = null;
        if(this.arreglo[this.frente] != null){
            frenteActual = this.arreglo[this.frente];
        }
            
        return frenteActual;
    }
    
    public boolean esVacia(){
        
        boolean vacia = false;
        
        if(this.frente == this.fin){
            vacia = true;
        }
        return vacia;
    }
    
    public void vaciar(){
        
        this.arreglo = new Object[this.TAMANIO];
        this.frente = 0;
        this.fin = 0;
        
    }
    
    @Override
    public Cola clone(){
        
        Cola arregloClon = new Cola();
        arregloClon.frente = this.frente;
        arregloClon.fin = this.fin;
        arregloClon.arreglo = this.arreglo.clone();
        
        return arregloClon;
    }
    
    @Override
	public String toString() {
		String texto;

		if (this.esVacia()) {
			texto = "Cola vacia";
		} else {
			int i = this.frente;
			texto = "";

			while (i != this.fin) {
				texto += arreglo[i];
				if (arreglo[(i + 1) % Cola.TAMANIO] != null) {
					texto += ",";
				}
				i = (i + 1) % Cola.TAMANIO;
			}

		}

		return texto;
	}
    
    
    
}
