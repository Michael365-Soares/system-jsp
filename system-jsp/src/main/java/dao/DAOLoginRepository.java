package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;
import model.ModelLogin;

public class DAOLoginRepository {
     private Connection conexao;
     
     public DAOLoginRepository() {
    	 this.conexao=SingleConnection.getConexao();     
     }
     
     public boolean validarAutenticacao(ModelLogin user) {
    	 boolean validacao=false;
    	 try {
	    	 String sql="select * from model_login where login = ? and senha = ?";
	    	 PreparedStatement stmt=conexao.prepareStatement(sql);
	    	 stmt.setString(1,user.getLogin());
	    	 stmt.setString(2,user.getSenha());
	    	 ResultSet resultado=stmt.executeQuery();
	    	 validacao=resultado.next()?true:false;
    	 }catch(SQLException e) {
    		 e.printStackTrace();
    	 }
    	 return validacao;
     }
     
     public void cadastrarUsuario(ModelLogin user) throws SQLException {
    	 String sql="insert into model_login(login,senha,email,identificador) values(?,?,?,?)";
    	 PreparedStatement stmt=conexao.prepareStatement(sql);
    	 stmt.setString(1,user.getLogin());
    	 stmt.setString(2,user.getSenha());
    	 stmt.setString(3,user.getEmail());
    	 stmt.setInt(4,user.getIdentificador());
    	 stmt.execute();
     }
     
}
