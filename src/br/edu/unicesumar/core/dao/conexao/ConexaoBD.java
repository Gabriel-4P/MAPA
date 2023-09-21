package br.edu.unicesumar.core.dao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoBD {
    
    private static final String URL = "jdbc:mysql://localhost:3306/mapa";
    private static final String USER = "root";
    private static final String PASS = "root";
    
    public static Connection conn;
    
    public static Connection getConexao() {
        
        try {
            if (conn == null) {
                conn = DriverManager.getConnection(URL, USER, PASS);
                return conn;
            } else {
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao acessar o banco.");
            return null;
        }
        
    }
        
}
