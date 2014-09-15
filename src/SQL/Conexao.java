/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SQL;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author Leonardo Dias
 */
public class Conexao {
    
    private static String usuario = "root";
    private static String senha = "";
    private static String url = "jdbc:mysql://127.0.0.1/apollo";

    public static Connection open() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException ex) {
            System.err.println("SQL Exception... Não conectado.\r\n");
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            System.err.println("Classe não encontrada, adicione o driver nas bibliotecas.\r\n");
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void close(ResultSet rs, Statement st, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            }
        }
        if (st != null) {
            try {   
                st.close();
            } catch (SQLException e) {
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public static void close(Statement st, Connection conn) {
        close(null, st, conn);
    }

    public static void close(Connection conn) {
        close(null, null, conn);
    }
    
}
