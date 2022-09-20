package Estructuras.Instructions;

/**
 *
 * @author teval
 */
public class Operacion implements Instruccion{

    
    
    public static enum Tipo_operacion{
        SUMA,
        RESTA,
        MULTIPLICACION,
        DIVISION,
        NEGATIVO,
        NUMERO,
        CARACTER,
        IDENTIFICADOR,
        CADENA,
        MAYOR_QUE,
        MENOR_QUE,
        CONCATENACION,
        MAYIG,
        MNIG,
        ES_IGUAL,
        ES_DIFERENTE
    }
    /**
     * Tipo de operación a ejecutar.
     */
    private final Tipo_operacion tipo;
    /**
     * Operador izquierdo de la operación.
     */
    private Operacion operadorIzq;
    /**
     * Operador derecho de la operación.
     */
    private Operacion operadorDer;
    
    private Object id;
    /**
     * Valor específico si se tratara de una literal, es decir un número o una 
     * cadena.
     */
    private Object valor;
    /**
     * Constructor de la clase para operaciones binarias (con dos operadores), estas
     * operaciones son:
     * SUMA, RESTA, MULTIPLICACION, DIVISION, CONCATENACION, MAYOR_QUE, MENOR_QUE
     * @param operadorIzq Operador izquierdo de la operación
     * @param operadorDer Opeardor derecho de la operación
     * @param tipo Tipo de la operación
     */
    public Operacion(Operacion operadorIzq, Operacion operadorDer, Tipo_operacion tipo) {
        this.tipo = tipo;
        this.operadorIzq = operadorIzq;
        this.operadorDer = operadorDer;
    }
    
    public Operacion(String id, Operacion operadorDer, Tipo_operacion tipo) {
        this.tipo = tipo;
        this.id=id;
        this.operadorDer = operadorDer;
    }
    /**
     * Constructor para operaciones unarias (un operador), estas operaciones son:
     * NEGATIVO
     * @param operadorIzq Único operador de la operación
     * @param tipo Tipo de operación
     */
    public Operacion(Operacion operadorIzq, Tipo_operacion tipo) {
        this.tipo = tipo;
        this.operadorIzq = operadorIzq;
    }
    /**
     * Constructor para operaciones unarias (un operador), cuyo operador es 
     * específicamente una cadena, estas operaciones son:
     * IDENTIFICADOR, CADENA , CARACTER
     * @param a Cadena que representa la operación a realizar
     * @param tipo Tipo de operación
     */
    public Operacion(String a, Tipo_operacion tipo) {
        this.valor=a;
        this.tipo = tipo;
    }

    @Override
    public String traducirPY() {
        /* ======== OPERACIONES ARITMETICAS ======== */
        if(tipo== Tipo_operacion.DIVISION){
            return operadorIzq.traducirPY() + "/" + operadorDer.traducirPY();
        }else if(tipo== Tipo_operacion.MULTIPLICACION){
            return operadorIzq.traducirPY() + "*" + operadorDer.traducirPY();
        }else if(tipo== Tipo_operacion.RESTA){
            return operadorIzq.traducirPY() + "-" + operadorDer.traducirPY();
        }else if(tipo== Tipo_operacion.SUMA){
            return operadorIzq.traducirPY() + "+" + operadorDer.traducirPY();
        }else if(tipo== Tipo_operacion.NEGATIVO){
            return "-" + operadorIzq.traducirPY();
        
        }
        /* ======== OPERACIONES UNARIOS ======== */
        else if(tipo == Tipo_operacion.NUMERO){
            return valor.toString();
        }else if(tipo == Tipo_operacion.IDENTIFICADOR){
            return valor.toString();
        }else if(tipo == Tipo_operacion.CADENA){
            return valor.toString();
        }else if(tipo == Tipo_operacion.CARACTER){
            return this.valor.toString();
        }
        /* ======== OPERACIONES RELACIONALES ======== */
        else if(tipo== Tipo_operacion.MAYOR_QUE){
            return this.id.toString()+ ">" + operadorDer.traducirPY();
        }else if(tipo== Tipo_operacion.MENOR_QUE){
            return this.id.toString()+ "<" +operadorDer.traducirPY();
        }else if(tipo== Tipo_operacion.CONCATENACION){
            return operadorIzq.traducirPY().toString() +"+"+operadorDer.traducirPY().toString();
        }else if(tipo== Tipo_operacion.MAYIG){
            return  this.id.toString()+ ">=" +operadorDer.traducirPY();
        }else if(tipo== Tipo_operacion.MNIG){
            return  this.id.toString()+ "<=" +operadorDer.traducirPY();
        }else if(tipo== Tipo_operacion.ES_IGUAL){
            return  this.id.toString()+ "==" +operadorDer.traducirPY();
        }else if(tipo== Tipo_operacion.ES_DIFERENTE){
            return  this.id.toString()+ "!=" +operadorDer.traducirPY();
        }else{
            return "";
        }
    }
    
    
    @Override
    public String traducirGO() {
        if(tipo== Tipo_operacion.DIVISION){
            return operadorIzq.traducirGO() + "/" + operadorDer.traducirGO();
        }else if(tipo== Tipo_operacion.MULTIPLICACION){
            return operadorIzq.traducirGO() + "*" + operadorDer.traducirGO();
        }else if(tipo== Tipo_operacion.RESTA){
            return operadorIzq.traducirGO() + "-" + operadorDer.traducirGO();
        }else if(tipo== Tipo_operacion.SUMA){
            return operadorIzq.traducirGO() + "+" + operadorDer.traducirGO();
        }else if(tipo== Tipo_operacion.NEGATIVO){
            return "-" + operadorIzq.traducirGO();
        
        }
        /* ======== OPERACIONES UNARIOS ======== */
        else if(tipo == Tipo_operacion.NUMERO){
            return valor.toString();
        }else if(tipo == Tipo_operacion.IDENTIFICADOR){
            return valor.toString();
        }else if(tipo == Tipo_operacion.CADENA){
            return valor.toString();
        }else if(tipo == Tipo_operacion.CARACTER){
            return this.valor.toString();
        }
        /* ======== OPERACIONES RELACIONALES ======== */
        else if(tipo== Tipo_operacion.MAYOR_QUE){
            return this.id.toString()+ ">" + operadorDer.traducirGO();
        }else if(tipo== Tipo_operacion.MENOR_QUE){
            return this.id.toString()+ "<" +operadorDer.traducirGO();
        }else if(tipo== Tipo_operacion.CONCATENACION){
            return operadorIzq.traducirGO().toString() +"+"+operadorDer.traducirGO().toString();
        }else if(tipo== Tipo_operacion.MAYIG){
            return  this.id.toString()+ ">=" +operadorDer.traducirGO();
        }else if(tipo== Tipo_operacion.MNIG){
            return  this.id.toString()+ "<=" +operadorDer.traducirGO();
        }else if(tipo== Tipo_operacion.ES_IGUAL){
            return  this.id.toString()+ "==" +operadorDer.traducirGO();
        }else if(tipo== Tipo_operacion.ES_DIFERENTE){
            return  this.id.toString()+ "!=" +operadorDer.traducirGO();
        }else{
            return "";
        }
    }
    
}