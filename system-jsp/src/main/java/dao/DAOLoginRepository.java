package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
     
     public ModelLogin cadastrarUsuario(ModelLogin user) throws SQLException {
    	 String sql="insert into model_login(login,senha,email) values(?,?,?)";
    	 PreparedStatement stmt=conexao.prepareStatement(sql);
    	 stmt.setString(1,user.getLogin());
    	 stmt.setString(2,user.getSenha());
    	 stmt.setString(3,user.getEmail());
    	 stmt.execute();
    	 conexao.commit(); 
    	 return this.consultarUser(user.getLogin());
    }
     
    public ModelLogin consultarUser(String login) throws SQLException {
    	String sql="select *from model_login where upper(login)=upper(?);";
    	PreparedStatement stmt=conexao.prepareStatement(sql);
    	stmt.setString(1,login);
    	ModelLogin user=new ModelLogin();
    	ResultSet resultado=stmt.executeQuery();
    	while(resultado.next()) {
    		user.setId(resultado.getInt("id"));
	        user.setLogin(resultado.getString("login"));
	        user.setEmail(resultado.getString("email"));
	        user.setSenha(resultado.getString("senha"));
    	}    
        return user;
    }
    

	public boolean verificaUsuarioExistente(String login) throws SQLException {
		String sql="select *from model_login where upper(login)=upper(?);";
		PreparedStatement stmt=conexao.prepareStatement(sql);
		stmt.setString(1,login);
		ResultSet resultado=stmt.executeQuery();
		if(resultado.next()) {
			return true;
		}
		return false;
	}
	
	public ModelLogin atualizaUser(int id,ModelLogin user) throws SQLException {
		String sql="update model_login set login=?,email=?,senha=? where id="+id;
		PreparedStatement stmt=conexao.prepareStatement(sql);
		stmt.setString(1,user.getLogin());
		stmt.setString(2,user.getEmail());
		stmt.setString(3,user.getSenha());
		stmt.executeUpdate();
		conexao.commit();
		return this.consultarUser(user.getLogin());
	}
	
    public void deletarUser(int id) throws SQLException {
    	String sql="delete from model_login where id="+id;
    	Statement st=conexao.createStatement();
    	st.execute(sql);
    	conexao.commit();
    }
    
    public ModelLogin buscarUser(String nome) throws SQLException {
    	String sql="select *from  model_login where login=?";
    	PreparedStatement stmt=conexao.prepareStatement(sql);
    	stmt.setString(1,nome);
    	ResultSet resultado=stmt.executeQuery();
    	ModelLogin user=new ModelLogin();
    	if(resultado.next()) {
    		user.setId(resultado.getInt("id"));
    		user.setLogin(resultado.getString("login"));
    	};
    	return user;
    }
	
}
