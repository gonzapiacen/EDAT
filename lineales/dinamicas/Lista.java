/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;

/**
 *
 * @author gonza
 */
public class Lista {
    
    private Nodo cabecera;
    
    public Lista(){
        
        this.cabecera = null;
    }
    
    public boolean insertar(Object nuevoElem, int pos){
        
        boolean exito = true;
        
        if(pos< 1 || pos > this.longitud() + 1){
            
            exito = false;
        }
        else{
            if(pos == 1){
                this.cabecera = new Nodo(nuevoElem, this.cabecera);
            }
            else{
                Nodo aux = this.cabecera;
                int i = 1;
                while(i < pos -1){
                    aux = aux.getEnlace();
                    i++;
                }
                
                Nodo nuevo = new Nodo(nuevoElem, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
        }
        
        return exito;
    }
    
    public boolean eliminar(int pos){
        
        boolean exito = false;
        
        if(1 <= pos && pos <= this.longitud()){
            Nodo aux  = this.cabecera;
            int iterador = 1;
            if(pos == 1){
                this.cabecera = this.cabecera.getEnlace();
            }
            else{
                while(iterador < pos -1){
                    aux = aux.getEnlace();
                    iterador++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            exito = true;
        }
        return exito;
    }
    
    public Object recuperar(int pos){
        Object elem = null;
        int iterador = 1;
        Nodo aux = this.cabecera;
        
        if(1 < pos && pos < this.longitud() + 1){
            while(iterador < pos){
                iterador++;
                aux = aux.getEnlace();
            }
            elem = aux.getElem();
        }
        return elem;
    }
    
    public int localizar(Object elem){
        int pos = -1, iterador = 1, largo = this.longitud();
        Nodo aux = this.cabecera;
        boolean encontrado = false;
        
        while(!encontrado && iterador <= largo){
            if(aux.getElem().equals(elem)){
                pos = iterador;
                encontrado = true;
            }
            else{
                aux = aux.getEnlace();
                iterador++;
            }
        }
        
        return pos;
    }
    
    public int longitud(){
        
        int longitud = 0;
        Nodo nodoAux = this.cabecera;
        
        while(nodoAux != null){
            longitud++;
            nodoAux = nodoAux.getEnlace();
        }
        return longitud;
    }
    
    public void vaciar(){
        
        this.cabecera = null;
    }
    
    public boolean esVacia(){
        
        return this.cabecera == null;
    }
    
    
    @Override
    public Lista clone(){
        Lista clon = new Lista();
        Nodo nodoDelante = this.cabecera;
        clon.cabecera = new Nodo(nodoDelante.getElem(), null);
        nodoDelante = nodoDelante.getEnlace();
        Nodo nodoDetras = clon.cabecera;
        
        while(nodoDelante != null){
            nodoDetras.setEnlace(new Nodo(nodoDelante.getElem(), null));
            nodoDetras = nodoDetras.getEnlace();
            nodoDelante = nodoDelante.getEnlace();
        }
        return clon;
    }
    
    @Override
    public String toString(){
        String texto;
        
        if(this.esVacia()){
            texto = "Lista Vacia";
        }
        else{
            Nodo aux = this.cabecera;
            texto = "";
            
            while(aux != null){
                texto += aux.getElem();
                aux = aux.getEnlace();
                
                if(aux != null){
                    texto += ", ";
                }
            }
        }
        return texto;
    }
    
}
