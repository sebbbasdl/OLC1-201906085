/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.Instructions;


/**
 *
 * @author sebas
 */
public class Declaracion implements Instruccion{
    
    private  Object id;
    
    private Object valor;
    
    private Object tipo;
    
    
    public Declaracion(String a, String valor,String tipo){  
        this.id=a;
        this.valor=valor;
        this.tipo=tipo;
        
    }
    
    @Override
    public String traducirPY(){
        String aux ="";
        
        if(tipo=="string"){
            System.out.println("String "+this.id.toString()+"= "+this.valor.toString());
            aux= this.id.toString()+"= "+this.valor.toString() ;
        }else if(tipo=="int"){
            System.out.println("Int "+this.id.toString()+"= "+this.valor.toString());
            aux= this.id.toString()+"= "+this.valor.toString() ;
        }
        
        
        return aux;
        
    }

    @Override
    public String traducirGO() {
        String aux ="";
        if(tipo=="string"){
            System.out.println("String "+this.id.toString()+"= "+this.valor.toString());
            aux= "string -> "+this.id.toString()+"= "+this.valor.toString() ;
        }else if(tipo=="int"){
            System.out.println("Int "+this.id.toString()+"= "+this.valor.toString());
            aux= "int -> "+this.id.toString()+"= "+this.valor.toString() ;
        }
        return aux;
    }
    
}
