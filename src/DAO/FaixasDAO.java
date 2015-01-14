/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import apollo.Entidades.Faixa;
import SQL.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
                    return new Faixa(rs.getInt("id"), rs.getString("url"), rs.getInt("duracao"), rs.getString("nome"), rs.getString("autor"), rs.getFloat("valor"));
                }
            } catch (SQLException ex) {
                throw new SQLException (ex);
            }
            finally{
                Conexao.close(rs, ps, conn);
            }
        return null;   
    }
    
    public ArrayList<Faixa> getListFaixaByAutor(String nome) throws SQLException{
        Connection conn = Conexao.open();
            ArrayList<Faixa> lista;
            lista = new ArrayList<Faixa>();
            PreparedStatement ps  = null;
            ResultSet rs = null;
            try{
                ps = conn.prepareStatement("select * from faixa where autor like ?");
                ps.setString(1, "%"+nome+"%");
                rs = ps.executeQuery();
                
                while (rs.next()){
                    lista.add(new Faixa(rs.getInt("id"), rs.getString("url"), rs.getInt("duracao"), rs.getString("nome"), rs.getString("autor"), rs.getFloat("valor")));
                }
            } catch (SQLException ex) {
                throw new SQLException (ex);
            }
            finally{
                Conexao.close(rs, ps, conn);
            }
        return lista;   
    }
    
    public ArrayList<Faixa> getListFaixaByNome(String nome) throws SQLException{
     Connection conn = Conexao.open();
            ArrayList<Faixa> lista;
            lista = new ArrayList<Faixa>();
            PreparedStatement ps  = null;
            ResultSet rs = null;
            try{
                ps = conn.prepareStatement("select * from faixa where nome like ?");
                ps.setString(1, "%"+nome+"%");
                rs = ps.executeQuery();
                
                while (rs.next()){
                    lista.add(new Faixa(rs.getInt("id"), rs.getString("url"), rs.getInt("duracao"), rs.getString("nome"), rs.getString("autor"), rs.getFloat("valor")));
                }
            } catch (SQLException ex) {
                throw new SQLException (ex);
            }
            finally{
                Conexao.close(rs, ps, conn);
            }
        return lista;   
    }
    
    public void insere(Faixa faixa) throws SQLException {
        Connection conn = Conexao.open();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("insert into faixa (nome, url, duracao, autor, valor) values(?,?,?,?,?)");
            ps.setString(1, faixa.getNome());
            ps.setString(2, faixa.getUrl());
            ps.setInt(3, faixa.getDuracao());
            ps.setString(4, faixa.getAutor());
            ps.setFloat(5, faixa.getValor());
            
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
            ps = conn.prepareStatement("update faixa set nome = ?, url = ?, duracao = ?, autor = ?, valor = ? where id = ?");
            
            ps.setString(1, faixa.getNome());
            ps.setString(2, faixa.getUrl());
            ps.setInt(3, faixa.getDuracao());
            ps.setString(4, faixa.getAutor());
            ps.setFloat(5, faixa.getValor());
            ps.setInt(6, faixa.getId());

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
