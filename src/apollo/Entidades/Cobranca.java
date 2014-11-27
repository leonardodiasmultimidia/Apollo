/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apollo.Entidades;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo Dias
 */
public class Cobranca implements Serializable{
    
    private int id = -1;
    private int idFaixa = -1;
    private int idUsuario = -1;
    private Calendar data = null;
    
    public Cobranca(int id, int idFaixa, int idUsuario, Calendar data){
        this.id = id;
        this.idFaixa = idFaixa;
        this.idUsuario = idUsuario;
        this.data = data;
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
        DAO.FaixasDAO daoFaixa = new DAO.FaixasDAO();
        try {
            return daoFaixa.getFaixaById(idFaixa);
        } catch (SQLException ex) {
            Logger.getLogger(Cobranca.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
