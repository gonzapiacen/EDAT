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
public class Pila {

    //atributos de clase
    private Object[] arreglo;
    private int tope;
    private static final int TAMANIO = 10;

    public Pila() {
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }

    //metodo apilar
    public boolean apilar(Object nuevoElem) {

        boolean exito;

        //verifica que el tope sea menor al tamanio para poder apilar
        if (this.tope + 1 >= this.TAMANIO) {
            exito = false;
        //apila    
        } else {
            this.tope++;
            this.arreglo[tope] = nuevoElem;
            exito = true;
        }

        return exito;
    }

    //metodo desapilar
    public boolean desapilar() {

        boolean exito;
        
        //verifica que el tope no sea nulo para desapilar
        if (this.tope < 0) {
            exito = false;
        //desapila    
        } else {
            this.tope--;
            exito = true;
        }

        return exito;
    }

    //metodo obtener tope
    public Object obtenerTope() {

        Object elem = null;
        
        //verifica que el tope no sea nulo y asigna el valor del tope actual
        if (this.tope >= 0) {
            elem = this.arreglo[tope];
        }

        return elem;
    }

    //metodo esVacia
    public boolean esVacia() {

        boolean vacio;
        
        //verifca si el tope es nulo o no
        if (this.tope < 0) {
            vacio = true;
        } else {
            vacio = false;
        }

        return vacio;
    }

    //metodo vaciar
    public void vaciar() {
         
        //sobrescribe la pila anterior creando una nueva vacia
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }
    
    //Metodo Clon
     @Override
    public Pila clone() {
        //Crea nueva pila vacia, le asigna el tope
        Pila arregloClon = new Pila();
        arregloClon.tope = this.tope;
        //Uso un metodo propio de java llamado clone para clonar
        arregloClon.arreglo = this.arreglo.clone();
        return arregloClon;

    }
    
    
    //metodo toString
     @Override
    public String toString() {

        String arregloString = null;

        if (tope >= 0) {
            for (int i = 0; i <= tope; i++) {
                // \n = salto de linea
              arregloString = arregloString + this.arreglo[i];
            }
        }
        else{
            arregloString = "Pila Vacia";
        }
        
        return arregloString;
    }
}
