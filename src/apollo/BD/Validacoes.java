/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.BD;

import apollo.Entidades.Usuario;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Leonardo Dias
 */
public class Validacoes {
    
    public static boolean emailValido(String email){
        if (email != null) {  
            String emailp[] = email.split("@");
            try{
                if(emailp[0].length() > 0 && emailp[1].length() > 0){
                    if(!(emailp[1].contains("@") || emailp[0].contains("@")))
                        return false;
                    for(Usuario usuario: BD.getAllUsuarios()){
                        if(usuario.getEmail().equals(email))
                            return false;
                    }
                }
            }catch(NullPointerException e){
                return false;
            }
            return true;
        }
        return false;
    }
}
