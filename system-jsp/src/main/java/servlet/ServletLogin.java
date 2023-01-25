package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOLoginRepository;
import model.ModelLogin;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet(urlPatterns={"/principal/ServletLogin","/ServletLogin"})
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ServletLogin() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOLoginRepository daoLogin=new DAOLoginRepository(request);
		String acao=request.getParameter("acao");
		if(acao!=null&&!acao.isEmpty()&&acao.equalsIgnoreCase("logout")){
			request.getSession().invalidate();//Inválida a sessão atual
		    RequestDispatcher redirecionar=request.getRequestDispatcher("index.jsp");
		    redirecionar.forward(request, response);
		}else {
			doPost(request,response);
		} 	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOLoginRepository daoLogin=new DAOLoginRepository(request);
	    String login=request.getParameter("login");
	    String senha=request.getParameter("senha");
	    String url=request.getParameter("url");
	    
	    System.out.println(url);
	    try {
		    /*Verificando se login e senha são diferentes de vazio ou nulo*/
		    if(login!=null&&!login.isEmpty()&&senha!=null&&!senha.isEmpty()) {
		    	//Setando Login e senha no obj ModelLogin
		    	ModelLogin userLogado=new ModelLogin(login, senha);
		    	//Verificando se login e senha passsados pela url estão corretos
		    	if(daoLogin.validarAutenticacao(userLogado)) {
		    		ModelLogin admim=daoLogin.buscarUserLogin(userLogado.getLogin());
		    		//Setando login do usuario ao atributo da sessão 
		    		request.getSession().setAttribute("usuario",userLogado.getLogin());
		    		request.getSession().setAttribute("isAdmim",admim.isAdmim());
	
		    		//Verificando se o parâmetro url está preenchido se não seta o valor padrão
		    		if(url == null || url.equals("null")) {
		    			url="principal/principal.jsp";
		    		}
		    		//Fazendo redirecionamento para a url solicitada
		    		RequestDispatcher redirecionar=request.getRequestDispatcher(url);
		    		redirecionar.forward(request, response);
		    	}else {/*SE LOGIN OU SENHA ESTIVEREM INCORRETOS REDIRECIONA PARA A TELA
		    	       DE LOGIN NOVAMENTE*/
		    		RequestDispatcher redirecionar=request.getRequestDispatcher("/index.jsp");
			    	request.setAttribute("msg","Campos login e senha estão incorretos...");
			    	redirecionar.forward(request, response);
		    	}
		    }else {/*SE CAMPOS LOGIN E SENHA FOREM NULOS
		           REDIRECIONA PARA A PÁGINA DE LOGIN NOVAMENTE*/
		    	RequestDispatcher redirecionar=request.getRequestDispatcher("index.jsp");
		    	request.setAttribute("msg","Campos login e senha estão nulos...");
		    	redirecionar.forward(request, response);
		    }
	    }catch(Exception e) {
	    	e.printStackTrace();
			RequestDispatcher redirecionar=request.getRequestDispatcher("error.jsp");
			request.setAttribute("msg",e.getMessage());
			redirecionar.forward(request, response);
	    }
	}

}
