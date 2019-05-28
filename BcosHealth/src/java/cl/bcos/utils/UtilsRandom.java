/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bcos.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author aacantero
 */
public class UtilsRandom {
    
     public static String getRandom() {
        Random r = new SecureRandom();
        byte[] salt = new byte[256];        
        r.nextBytes(salt);
        String cadena = "";
        for (int i = 1; i <= 6; i++) {
            cadena = cadena + salt[i];
        }
        return cadena.replace("-", "").trim();
    }
    
    
}
