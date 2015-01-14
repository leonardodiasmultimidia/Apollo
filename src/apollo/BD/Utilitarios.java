/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.BD;

import apollo.Entidades.Cobranca;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Leonardo Dias
 */
public class Utilitarios {
    
    public static String formataDuracao(int duracao){
        String retorno = "";
        if(duracao/3600>0)
            retorno += duracao/3600+":";
        duracao = duracao%3600;
        if(duracao/60<10)
            retorno += "0";
        retorno += duracao/60+":";
        duracao = duracao%60;
        if(duracao<10)
            retorno += "0";
        retorno += duracao;
        return retorno;
    }
    
    public static String getData(Calendar Data){
        String retorno = "";
        retorno += Data.get(Calendar.DAY_OF_MONTH)+"/";
        retorno += (Data.get(Calendar.MONTH)+1)+"/";
        retorno += Data.get(Calendar.YEAR);
        return retorno;
    }
}
