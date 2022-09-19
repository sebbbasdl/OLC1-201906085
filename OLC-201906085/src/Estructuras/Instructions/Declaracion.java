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
    
    private Operacion val;
    
    
    public Declaracion(String a, String valor,String tipo){  
        this.id=a;
        this.valor=valor;
        this.tipo=tipo;
        
        
    }
    
    public Declaracion(String a, Operacion val,String tipo){  
        this.id=a;
        this.val=val;
        this.tipo=tipo;
        
    }
    
    
    
    @Override
    public String traducirPY(){
        String aux ="";
        
        Boolean flag_float=false;
        Boolean flag_int=false;
        
        
        
        //System.out.println("HOLA XD"+val.traducirPY());
        
        
        //System.out.println(auxval.length());
        
        if(this.val!=null){
            String auxval=val.traducirPY();
            for (int i = 0; i < auxval.length(); i++) {
                if(auxval.charAt(i)=='.'){
                    //System.out.println("soy float en go");
                    flag_float=true;
                    flag_int=false;
                    break;
                }else{
                    flag_int=true;
                    //System.out.println("soy int");
                }
            }
        }

        
        if(tipo=="string"){
            //System.out.println("String "+this.id.toString()+"= "+this.valor.toString());
            aux= this.id.toString()+"= "+this.valor.toString()+"\n" ;
        }else if(tipo=="int" && flag_int==true){
            //System.out.println("Int "+this.id.toString()+"= "+this.valor.toString());
            aux= this.id.toString()+"= "+val.traducirPY()+"\n" ;
        }else if(tipo=="char"){
            //System.out.println("Int "+this.id.toString()+"= "+this.valor.toString());
            aux= this.id.toString()+"= "+this.valor.toString()+"\n" ;
        }else if(tipo=="bool"){
            //System.out.println("Int "+this.id.toString()+"= "+this.valor.toString());
            aux= this.id.toString()+"= "+this.valor.toString()+"\n" ;
        }else if(tipo=="int"&& flag_float==true){
            //System.out.println("Int "+this.id.toString()+"= "+this.valor.toString());
            aux= this.id.toString()+"= "+val.traducirPY()+"\n" ;
        }else if(tipo=="asig" && this.val!=null){
            //System.out.println("Int "+this.id.toString()+"= "+this.valor.toString());
            aux= this.id.toString()+" = "+val.traducirPY()+"\n" ;
        }else if(tipo=="asig" && this.val==null){
            //System.out.println("Int "+this.id.toString()+"= "+this.valor.toString());
            aux= this.id.toString()+" = "+this.valor.toString()+"\n" ;
        }
        
        
        return aux;
        
    }

    @Override
    public String traducirGO() {
        Boolean flag_float=false;
        Boolean flag_int=false;
        if(this.val!=null){
            String auxval=val.traducirGO();
            for (int i = 0; i < auxval.length(); i++) {
                if(auxval.charAt(i)=='.'){
                    //System.out.println("soy float en go");
                    flag_float=true;
                    flag_int=false;
                    break;
                }else{
                    flag_int=true;
                    //System.out.println("soy int");
                }
            }
        }
        //System.out.println(flag_float);
        //System.out.println(flag_int);
        String aux ="";
        if(tipo=="string"){
            //System.out.println("String "+this.id.toString()+"= "+this.valor.toString());
            aux= "var "+this.id.toString()+" string = "+this.valor.toString()+"\n" ;
        }else if(tipo=="int"&& flag_int==true){
            //System.out.println("Int "+this.id.toString()+"= "+this.valor.toString());
            aux= "var "+this.id.toString()+" int = "+val.traducirGO()+"\n" ;
        }else if(tipo=="char"){
            //System.out.println("Int "+this.id.toString()+"= "+this.valor.toString());
            aux= "var "+this.id.toString()+" byte = "+this.valor.toString()+"\n" ;
        }else if(tipo=="bool"){
            //System.out.println("Int "+this.id.toString()+"= "+this.valor.toString());
            aux= "var "+this.id.toString()+" bool = "+this.valor.toString()+"\n" ;
        }else if(tipo=="int"&& flag_float==true){
            //System.out.println("Int "+this.id.toString()+"= "+this.valor.toString());
            aux= "var "+this.id.toString()+" float = "+val.traducirGO()+"\n" ;
        }else if(tipo=="asig" && this.val!=null){
            //System.out.println("Int "+this.id.toString()+"= "+this.valor.toString());
            aux= this.id.toString()+" = "+val.traducirGO()+"\n" ;
        }else if(tipo=="asig" && this.val==null){
            //System.out.println("Int "+this.id.toString()+"= "+this.valor.toString());
            aux= this.id.toString()+" = "+this.valor.toString()+"\n" ;
        }
        
        return aux;
    }
    
}
