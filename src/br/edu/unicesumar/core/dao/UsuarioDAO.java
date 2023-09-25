package br.edu.unicesumar.core.dao;

import br.edu.unicesumar.core.dao.conexao.ConexaoBD;
import br.edu.unicesumar.core.entity.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.awt.Component;

public class UsuarioDAO {
    
    public void cadastrarUsuario(Usuario usuario, Component rootPane) {
        
        String sql = "INSERT INTO USUARIO (NOME, LOGIN, SENHA, EMAIL) VALUES (?,?,?,?)";
        
        PreparedStatement ps = null;
        
        try {
            
            ps = ConexaoBD.getConexao().prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getLogin());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getEmail());
            
            ps.execute();
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(rootPane, "Erro de conexão com o banco de dados ao cadastrar usuário");
            e.printStackTrace();
        
        } finally {
            
            try {
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public boolean validarUsuario(String cmpLogin, String cmpSenha, Component rootPane) {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM USUARIO WHERE LOGIN = ? AND SENHA = ?";
        
        try {
            
            //Monta a declaração para executar no banco de dados
            ps = ConexaoBD.getConexao().prepareStatement(sql);
            ps.setString(1, cmpLogin);
            ps.setString(2, cmpSenha);
            
            //Executa a declaração, atribuindo o resultado ao ResultSet rs
            rs = ps.executeQuery();
            
            //Verifica se retornou algum resultado e valida o usuário.
            if(rs.next()) {
                JOptionPane.showMessageDialog(rootPane, "Acesso autorizado.\nBem vindo(a) " + rs.getString("nome") + "!", "Aviso", JOptionPane.PLAIN_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(rootPane, "Acesso negado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(rootPane, "Erro de conexão com o banco de dados ao validar o login.", "ERRO", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
            
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
