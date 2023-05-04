/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apuntes;

/**
 *
 * @author gonza
 */
public class Fecha {
    
    //atributos
    
    private int dia;
    private int mes;
    private int anio;
    
    // constructor
    
    public Fecha(int unDia, int unMes,int unAnio){
        this.dia = unDia;
        this.mes = unMes;
        this.anio = unAnio;
        
    }
    
    //observadores
    
    public int getDia(){
        return dia;
    }
    
    public int getMes(){
        return mes;
    }
    
    public int getAnio(){
        return anio;
    }
    
    //modificadores
    
    public void setDia(int unDia){
        this.dia = unDia;
    }
    
    public void setMes(int unMes){
        this.mes = unMes;
    }
    
    public void setAnio(int unAnio){
        this.anio = unAnio;
    }
    
    
}
