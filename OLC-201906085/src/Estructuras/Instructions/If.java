/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.Instructions;

import java.util.LinkedList;

/**
 *
 * @author sebas
 */
public class If implements Instruccion{
    /**
     * Condición de la instrucción si..entonces.
     */
    private final Operacion condicion;
    /**
     * Lista de instrucciones que serán ejecutadas si la condición se cumple.
     */
    private final LinkedList<Instruccion> listaInstrucciones;
    /**
     * Lista de instrucciones que serán ejecutadas si la condición ELSE IF se cumple.
     */
    private LinkedList<Instruccion> listaElseIfInstrucciones;
    /**
     * Lista de instrucciones que se ejecutarán si la condición no se cumple,
     * esta lista existirá solo si la instrucción posee la clausula ELSE, de lo
     * contrario la lista será null.
     */
    private LinkedList<Instruccion> listaInsElse;
    /**
     * Primer constructor de la clase, este se utiliza cuando la instrucción no 
     * tiene clausula ELSE.
     * @param a Condición del si..entonces
     * @param b Lista de instrucciones que deberían ejecutarse si la condición se cumple
     * 
     */
    private Object conta;
    public If(Operacion a, LinkedList<Instruccion> b,int conta) {
        condicion=a;
        listaInstrucciones=b;
        this.conta=conta;
    }
    /**
     * Segundo constructor de la clase, este se utiliza cuando la instrucción tiene
     * clausula ELSE.
     * @param a Condición del si..entonces
     * @param b Lista de instrucciones que deberían ejecutarse si la condición se cumple
     * @param c Lista de instrucciones que deberían ejecutarse si la condición no se cumple
     */
    public If(Operacion a, LinkedList<Instruccion> b, LinkedList<Instruccion> c,int conta) {
        condicion=a;
        listaInstrucciones=b;
        listaInsElse=c;
        this.conta=conta;
    }
    /**
     * Tercer constructor de la clase, este se utiliza cuando la instrucción tiene
     * clausula IF (ELSE IF/ ELSE).
     * @param a Condición del si..entonces
     * @param b Lista de instrucciones que deberían ejecutarse si la condición se cumple
     * @param l Lista de instrucciones que deberían ejecutarse si la condición ElSE IF se cumple
     * @param c Lista de instrucciones que deberían ejecutarse si la condición no se cumple
     */
    public If(Operacion a, LinkedList<Instruccion> b, LinkedList<Instruccion> l, LinkedList<Instruccion> c,int conta) {
        condicion=a;
        listaInstrucciones=b;
        listaElseIfInstrucciones = l;
        listaInsElse=c;
        this.conta=conta;
    }
    public String iden(int conta){
        System.out.println("SOYY CONTA"+conta);
        String str_iden="";
        
        for (int i = 1; i <= conta; i++) {
            str_iden+="\t";
        }
        return  str_iden;
    }
    
    /**
     * Método que ejecuta la instrucción si..entonces, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param ts tabla de símbolos del ámbito padre de la sentencia.
     * @return Estra instrucción retorna nulo porque no produce ningún valor en 
     * su ejecución
     */
    @Override
    public String traducirPY() {
        
        String traduccion = "if "+this.condicion.traducirPY()+":\n";
        if(listaInstrucciones != null)
            for(Instruccion ins: listaInstrucciones){
                traduccion += iden((int) this.conta)+ins.traducirPY();
            }
        if(listaElseIfInstrucciones != null){
            //System.out.println("ESTOY ACA");
            this.conta=((int)this.conta)-1;
            traduccion += "el";
            for(Instruccion ins: listaElseIfInstrucciones){
                traduccion += ins.traducirPY();
            }
        }
        if(listaInsElse != null){
            traduccion += "else: \n";
            
            for(Instruccion ins: listaInsElse){
                traduccion += iden((int) this.conta)+ins.traducirPY();
            }
        }
        return traduccion;
    }
    
    @Override
    public String traducirGO() {
        int cont=0;
        String traduccion = "if "+this.condicion.traducirGO()+"{\n";
        if(listaInstrucciones != null)
            for(Instruccion ins: listaInstrucciones){
                traduccion += ins.traducirGO();
            }
        if(listaElseIfInstrucciones != null){
            traduccion += "el";
            for(Instruccion ins: listaElseIfInstrucciones){
                traduccion += ins.traducirGO();
            }
        }
        if(listaInsElse != null){
            traduccion += "else{ \n";
            for(Instruccion ins: listaInsElse){
                traduccion += ins.traducirGO();
            }
        }
        return traduccion;
    }
    
    
}

