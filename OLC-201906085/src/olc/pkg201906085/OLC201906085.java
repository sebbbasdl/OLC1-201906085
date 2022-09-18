/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc.pkg201906085;

import Utils.Analizador;


/**
 *
 * @author sebas
 */
public class OLC201906085 {

    
    public static void main(String[] args) {
        Analizador an = new Analizador();
        an.interpretar("C:\\Users\\sebas\\OneDrive\\Documentos\\Prueba.txt");
        Principal princ = new Principal();
        princ.setVisible(true);
    }
    
}
