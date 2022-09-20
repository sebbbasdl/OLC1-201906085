package Estructuras;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 * @author teval
 */
public class Arbol {
    private Node raiz;

    public Arbol() {
    }

    public Arbol(Node raiz) {
        this.raiz = raiz;
    }

    public Node getRaiz() {
        return raiz;
    }

    public void setRaiz(Node raiz) {
        this.raiz = raiz;
    }    
    
    public String graficar(){
        StringBuilder s = new StringBuilder();
        s.append("digraph G{\n");
        s.append("\"node").append(raiz.hashCode()).append("\" [ label = \"").append(raiz.getValor()).append("\",];\n");
        graficar(raiz, s);
        s.append("}");
        System.out.println(s.toString());
        return s.toString();
        
    }
    
    public void graficar(Node raiz, StringBuilder s){        
        LinkedList<Node> hijos = raiz.getHijos();
        if(hijos != null){
            for(Node hijo: hijos){
                s.append("\"node").append(hijo.hashCode()).append("\" [ label = \"").append(hijo.getValor()).append("\",];\n");
                graficar(hijo, s);
                s.append("\"node").append(raiz.hashCode()).append("\" -> \"node").append(hijo.hashCode()).append("\" [ label = \"\",];\n");
            }
        }
    }
    
    public void crearArchivo(String ruta, String datos){
        FileWriter fw =null;

        PrintWriter pw = null ;
        try {
            fw = new FileWriter ( ruta ) ;
            pw = new PrintWriter ( fw ) ;
            pw.write ( datos ) ;
            pw.close ( ) ;
            fw.close ( ) ;
        } catch ( Exception ex ) {
        System.out.println ( ex.getMessage ( ) ) ;
        }finally {
            if ( pw  != null )
                pw.close ( ) ;
        }
            
          

    }
    
    public void generarGraf(){
        try {
            crearArchivo("file.dot" , graficar() ) ; 
            ProcessBuilder pb ;
            pb = new ProcessBuilder ( "dot" , "-Tpng" ,"-o" ,"Arbol.png" , "file.dot" ) ;
            pb.redirectErrorStream ( true ) ;
            pb.start ( ) ;
        } catch ( Exception e ) {
                e.printStackTrace ( ) ;
        }
    }
}