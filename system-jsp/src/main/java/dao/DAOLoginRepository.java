package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import connection.SingleConnection;
import model.ModelLogin;

public class DAOLoginRepository {
     private Connection conexao;
	 private HttpServletRequest requisicao;
     private String userLog;
     
     public DAOLoginRepository(HttpServletRequest requisicao) {
    	 this.conexao=SingleConnection.getConexao(); 
    	 this.requisicao=requisicao;
    	 this.userLog=(String) requisicao.getSession().getAttribute("usuario");
     }
     
     public boolean validarAutenticacao(ModelLogin user) {
    	 boolean validacao=false;
    	 try {
	    	 String sql="select * from model_login where login = ? and senha = ?;";
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
    	 String sql="insert into model_login(login,senha,email,usuario_id,perfil) values(?,?,?,?,?)";
    	 PreparedStatement stmt=conexao.prepareStatement(sql);
    	 ModelLogin m=this.buscarUserLogin(userLog);
    	 stmt.setString(1,user.getLogin());
    	 stmt.setString(2,user.getSenha());
    	 stmt.setString(3,user.getEmail());
    	 stmt.setInt(4,m.getId());
    	 stmt.setString(5,user.getPerfil());
    	 stmt.execute();
    	 conexao.commit(); 
    	 return this.consultarUser(user.getLogin());
    }
     
    public ModelLogin consultarUser(String login) throws SQLException {
    	String sql="select *from model_login where upper(login)=upper(?) and usuario_id=(?) and admim is false;";
    	PreparedStatement stmt=conexao.prepareStatement(sql);
    	ModelLogin m=this.buscarUserLogin(userLog);
    	stmt.setString(1,login);
    	stmt.setInt(2,m.getId());
    	ModelLogin user=new ModelLogin();
    	ResultSet resultado=stmt.executeQuery();
    	while(resultado.next()) {
    		user.setId(resultado.getInt("id"));
	        user.setLogin(resultado.getString("login"));
	        user.setEmail(resultado.getString("email"));
	        user.setSenha(resultado.getString("senha"));
	        user.setPerfil(resultado.getString("perfil"));
    	}    
        return user;
    }
    

	public boolean verificaUsuarioExistente(String login) throws SQLException {
		String sql="select *from model_login where upper(login)=upper(?) and admim is false;";
		PreparedStatement stmt=conexao.prepareStatement(sql);
		stmt.setString(1,login);
		ResultSet resultado=stmt.executeQuery();
		if(resultado.next()) {
			return true;
		}
		return false;
	}
	
	public ModelLogin atualizaUser(int id,ModelLogin user) throws SQLException {
		String sql="update model_login set senha=?,email=?,login=?,perfil=? where id="+id+" and admim is false";
		PreparedStatement stmt=conexao.prepareStatement(sql);
		stmt.setString(1,user.getSenha());
		stmt.setString(2,user.getEmail());
		stmt.setString(3,user.getLogin());
		stmt.setString(4,user.getPerfil());
		stmt.executeUpdate();
		conexao.commit();
		return this.consultarUser(user.getLogin());
	}
	
    public void deletarUser(int id) throws SQLException {
    	String sql="delete from model_login where id="+id+" and admim is false";
    	Statement st=conexao.createStatement();
    	st.execute(sql);
    	conexao.commit();
    }
    
    public ModelLogin buscarUser(int id) throws SQLException {
    	String sql="select *from model_login where id=? and admim is false";
    	PreparedStatement stmt=conexao.prepareStatement(sql);
    	stmt.setInt(1, id);
    	ResultSet resultado=stmt.executeQuery();
    	ModelLogin user=new ModelLogin();
    	if(resultado.next()) {
    		user.setId(resultado.getInt("id"));
    		user.setLogin(resultado.getString("login"));
    		user.setEmail(resultado.getString("email"));
    		user.setSenha(resultado.getString("senha"));
    		user.setPerfil(resultado.getString("perfil"));
    		return user;
    	}
    	return user;
    }
    
    public ModelLogin buscarUserL(String login) throws SQLException {
    	String sql="select *from model_login where id=? and admim is false";
    	PreparedStatement stmt=conexao.prepareStatement(sql);
    	stmt.setString(1,login);
    	ResultSet resultado=stmt.executeQuery();
    	ModelLogin user=new ModelLogin();
    	if(resultado.next()) {
    		user.setId(resultado.getInt("id"));
    		user.setLogin(resultado.getString("login"));
    		user.setEmail(resultado.getString("email"));
    		user.setSenha(resultado.getString("senha"));
    		user.setPerfil(resultado.getString("perfil"));
    		return user;
    	}
    	return user;
    }
    
    public ModelLogin buscarUserLogin(String login) throws SQLException {
    	String sql="select *from model_login where upper(login)=upper(?)";
    	PreparedStatement stmt=conexao.prepareStatement(sql);
    	stmt.setString(1,login);
    	ResultSet resultado=stmt.executeQuery();
    	ModelLogin user=new ModelLogin();
    	if(resultado.next()) {
    		user.setId(resultado.getInt("id"));
    		user.setLogin(resultado.getString("login"));
    		user.setEmail(resultado.getString("email"));
    		user.setSenha(resultado.getString("senha"));
    		user.setAdmim(resultado.getBoolean("admim"));
    		user.setPerfil(resultado.getString("perfil"));
    		return user;
    	}
    	return user;
    }
    
    public List<ModelLogin> buscarUser(String nome) throws SQLException {
    	String sql="select *from  model_login where upper(login) like upper(?) and usuario_id=?  and admim is false";
    	PreparedStatement stmt=conexao.prepareStatement(sql);
    	ModelLogin m=this.buscarUserLogin(userLog);
    	stmt.setString(1,"%"+nome+"%");
    	stmt.setInt(2,m.getId());
    	ResultSet resultado=stmt.executeQuery();
    	List<ModelLogin> user=new ArrayList<>();
    	while(resultado.next()) {
    		ModelLogin u=new ModelLogin();
    		u.setId(resultado.getInt("id"));
    		u.setLogin(resultado.getString("login"));
    		u.setEmail(resultado.getString("email"));
    		u.setPerfil(resultado.getString("perfil"));
    		user.add(u);
    	};
    	return user;
    }
    
    public List<ModelLogin> buscarUser() throws SQLException {
    	String sql="select *from  model_login where admim is false and usuario_id=?";
    	PreparedStatement stmt=conexao.prepareStatement(sql);
    	ModelLogin m=this.buscarUserLogin(userLog);
    	stmt.setInt(1,m.getId());
    	ResultSet resultado=stmt.executeQuery();
    	List<ModelLogin> user=new ArrayList<>();
    	while(resultado.next()) {
    		ModelLogin u=new ModelLogin();
    		u.setId(resultado.getInt("id"));
    		u.setLogin(resultado.getString("login"));
    		u.setEmail(resultado.getString("email"));
    		u.setPerfil(resultado.getString("perfil"));
    		user.add(u);
    	};
    	return user;
    }
	
}
