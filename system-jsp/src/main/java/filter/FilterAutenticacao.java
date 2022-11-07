package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import connection.SingleConnection;

@WebFilter(urlPatterns= {"/principal/*"})
public class FilterAutenticacao implements Filter {
    private static Connection connection;
	
    public FilterAutenticacao() {
    	
    }
    
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			try {
				//RECUPERANDO O OBJETO REQUEST DA SESSÃO
				HttpServletRequest resq=(HttpServletRequest) request;
				//RECUPERANDO O ATRIBUTO DA SESSÃO SETADO DA SERVLETLOGIN...
				String userLogado=(String) resq.getSession().getAttribute("usuario");
				//RECUPERANDO A URL PASSADA PELO O NAVEGADOR E SETADA DENTRO DA SERVLETLOGIN
				String url=resq.getServletPath();
				/*VERIFICANDO SE O USUÁRIO REALIZOU LOGIN NO SISTEMA ANTES DE ACESSAR A URL SOLICITADA*/
				if(userLogado == null && !url.equalsIgnoreCase("/principal/ServletLogin") ||
				    userLogado==null&&
				    url.equalsIgnoreCase("/principal/principal.jsp")){
					
					RequestDispatcher redireciona=request.getRequestDispatcher("/index.jsp?url="+url);
					resq.setAttribute("msg","Por favor!Realize o login...");
					redireciona.forward(request, response);
					return;/*Para a execução e retorna para a tela de login*/
				}else {
					chain.doFilter(request, response);
				}
			}catch(Exception e){
					try {
						connection.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			}
			try {
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}//Se a execução ocorrer sem erros efetua as alterações no BD
		}catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar=request.getRequestDispatcher("error.jsp");
			request.setAttribute("msg",e.getMessage());
			redirecionar.forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		connection=SingleConnection.getConexao();
	}

}
