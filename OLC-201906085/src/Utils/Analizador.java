/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import Estructuras.Instructions.Instruccion;
import java.util.LinkedList;
import java.io.File;
import java.io.FileInputStream;
public class Analizador {
    
    public Analizador() {
        
    }
    
    public String interpretar(String text, String trad) {
        //System.out.println("hola");
        File file = new File("./public/parse.txt");  
        (new Files()).crearArchivo(file, text);
        analizadores.Sintactico pars;
        LinkedList<Instruccion> AST_arbolSintaxisAbstracta = null;
        try {
            pars=new analizadores.Sintactico(new analizadores.Lexico(new FileInputStream(file)));
            pars.parse();        
            AST_arbolSintaxisAbstracta=pars.getAST();
            //arbol = pars.getArbol();
        } catch (Exception ex) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println("Causa: "+ex);
        } 
        return ejecutarAST(AST_arbolSintaxisAbstracta,trad);
    }
    
    
    
    public String ejecutarAST(LinkedList<Instruccion> ast, String trad) {
        if(ast==null){
            return("No es posible ejecutar las instrucciones porque\r\n"
                    + "el árbol no fue cargado de forma adecuada por la existencia\r\n"
                    + "de errores léxicos o sintácticos.");
        }
        //Se ejecuta cada instruccion en el ast, es decir, cada instruccion de 
        //la lista principal de instrucciones.
        
        String traduccion = "";
        
        for(Instruccion ins:ast){
            //Si existe un error léxico o sintáctico en cierta instrucción esta
            //será inválida y se cargará como null, por lo tanto no deberá ejecutarse
            //es por esto que se hace esta validación.
            if(ins!=null)
                if(trad=="py"){
                    traduccion += ins.traducirPY();
                    System.out.println(ins);
                }else if(trad=="go"){
                
                    traduccion += ins.traducirGO();
                    System.out.println(ins);
                }
        }
        
        return traduccion;
    }
}
