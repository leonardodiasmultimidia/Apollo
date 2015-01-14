/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.Entidades;

import apollo.BD.BD;
import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Leonardo Dias
 */
public class Cobranca implements Serializable{
    
    private int id = -1;
    private int idFaixa = -1;
    private int idUsuario = -1;
    private Calendar data = null;
    boolean pago;
    
    public Cobranca(int id, int idFaixa, int idUsuario, Calendar data){
        this.id = id;
        this.idFaixa = idFaixa;
        this.idUsuario = idUsuario;
        this.data = data;
        this.pago = false;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getIdFaixa(){
        return idFaixa;
    }
    
    public int getIdUsuario(){
        return idUsuario;
    }
    
    public Calendar getData(){
        return data;
    }
    
    public Faixa getFaixa(){
        return BD.getFaixaById(idFaixa);
    }
    
    public boolean isPago(){
        return pago;
    }
    
    public void pagar(){
        pago = true;
    }
}
