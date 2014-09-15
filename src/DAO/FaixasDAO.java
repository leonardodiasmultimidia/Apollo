/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entidades.Faixa;
import SQL.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Leonardo Dias
 */
public class FaixasDAO {
    
    public Faixa getFaixaById(int id) throws SQLException{
     Connection conn = Conexao.open();
            PreparedStatement ps  = null;
            ResultSet rs = null;
            try{
                ps = conn.prepareStatement("select * from faixa where id = ?");
                ps.setInt(1, id);
                rs = ps.executeQuery();
                
                if (rs.next()){
                    return new Faixa(rs.getInt("id"), rs.getString("url"), rs.getInt("duracao"), rs.getString("nome"), rs.getString("autor"));
                }
            } catch (SQLException ex) {
                throw new SQLException (ex);
            }
            finally{
                Conexao.close(rs, ps, conn);
            }
        return null;   
    }
    
    public void insere(Faixa faixa) throws SQLException {
        Connection conn = Conexao.open();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("insert into faixa (nome, url, duracao, autor) values(?,?,?,?)");
            ps.setString(1, faixa.getNome());
            ps.setString(2, faixa.getUrl());
            ps.setInt(3, faixa.getDuracao());
            ps.setString(4, faixa.getAutor());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        finally{
            Conexao.close(ps, conn);
        }
    }
    
    public void update(Faixa faixa) throws SQLException {
        Connection conn = Conexao.open();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("update faixa set nome = ?, url = ?, duracao = ?, autor = ? where id = ?");
            
            ps.setString(1, faixa.getNome());
            ps.setString(2, faixa.getUrl());
            ps.setInt(3, faixa.getDuracao());
            ps.setString(4, faixa.getAutor());
            ps.setInt(5, faixa.getId());

            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        finally{
            Conexao.close(ps, conn);
        }
    }

    public void delete(Faixa faixa) throws SQLException {
        Connection conn = Conexao.open();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("delete from faixa where id = ?");
            ps.setInt(1, faixa.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        finally{
            Conexao.close(ps, conn);
        }
    }
}
