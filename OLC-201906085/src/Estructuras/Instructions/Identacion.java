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
public class Identacion {
    public static String iden(int conta){
        
        String str_iden="";
        
        for (int i = 0; i <= conta; i++) {
            str_iden+="\t";
        }
        return  str_iden;
    }
}
