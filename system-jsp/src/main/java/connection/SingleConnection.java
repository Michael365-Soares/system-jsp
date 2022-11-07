package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SingleConnection {
	private static Connection conexao=null;
	
	public static Connection getConexao() {
		Properties prop=getProperties();
		String url=prop.getProperty("banco.url");
		String user=prop.getProperty("banco.usuario");
		String password=prop.getProperty("banco.senha");
		if(conexao==null) {
			try {
				Class.forName("org.postgresql.Driver");
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			try {
				conexao=DriverManager.getConnection(url, user, password);
				conexao.setAutoCommit(false);
				System.out.println("Conexão estabelecida!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conexao;
		}
		System.out.println("Conexão estabelecida!");
		return conexao;
	}
    
	
	static Properties getProperties() {
		Properties prop=new Properties();
		try {
			FileInputStream file=new FileInputStream("C:\\Users\\micha\\git\\curso-jsp\\curso-jsp\\target\\arquivos\\conexao.properties");
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return prop;
	}
	
}
