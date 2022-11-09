package model;

import java.io.Serializable;
import java.util.Objects;

public class ModelLogin implements Serializable {
	private static final long serialVersionUID = 1L;
	private String login;
	private String senha;
	private String email;
	private int identificador;

	public ModelLogin(String login, String senha,String email,int identificador) {
		this.login = login;
		this.senha = senha;
		this.email=email;
		this.identificador=identificador;
	}
	
	public ModelLogin(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelLogin other = (ModelLogin) obj;
		return Objects.equals(login, other.login) && Objects.equals(senha, other.senha);
	}
	
	
    
}
