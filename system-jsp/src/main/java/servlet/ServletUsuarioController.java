package servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOLoginRepository;
import model.ModelLogin;

@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ServletUsuarioController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao=request.getParameter("acao");
		String msg="Exclusão mal sucedida!!!";
		DAOLoginRepository dao=new DAOLoginRepository();
		try {
			if(acao!=null&&!acao.isEmpty()&&acao.equalsIgnoreCase("deletar")) {
				    String idUser=request.getParameter("id");
					dao.deletarUser(Integer.parseInt(idUser));
					RequestDispatcher redirecionar=request.getRequestDispatcher("principal/usuario.jsp");
					msg="Usuário deletado com sucesso!!!";
					request.setAttribute("msg",msg);
					redirecionar.forward(request, response);
			}else if(acao!=null&&!acao.isEmpty()&&acao.equalsIgnoreCase("deletarComAjax")){
					String idUser=request.getParameter("id");
					dao.deletarUser(Integer.parseInt(idUser));
					msg="Exclusão realizada com êxito!";
					response.getWriter().write(msg);
			}else if(acao!=null&&!acao.isEmpty()&&acao.equalsIgnoreCase("buscarUserAjax")) {
				   String nomeUser=request.getParameter("nome");
				   ModelLogin user=dao.consultarUser(nomeUser);
				   
			}else {
				RequestDispatcher redirecionar=request.getRequestDispatcher("principal/usuario.jsp");
				request.setAttribute("msg",msg);
				redirecionar.forward(request, response);
			}
		} catch (Exception e) {
			RequestDispatcher redirecionar=request.getRequestDispatcher("error.jsp");
			msg="Ocorreu algum erro,entre em contato com o suporte:"+e.getMessage();
			request.setAttribute("msg",msg);
			redirecionar.forward(request, response);
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg="Usuário cadastrado com sucesso!!!";
		String login=request.getParameter("login");
		String email=request.getParameter("email");
		String senha=request.getParameter("senha");
		String id=request.getParameter("id");
		
		ModelLogin user=new ModelLogin(login,senha,email);
		user.setId(id!=null&&!id.isEmpty()?Integer.parseInt(id):null);
		
		try {
			DAOLoginRepository dao=new DAOLoginRepository();
			if(dao.verificaUsuarioExistente(user.getLogin()) && user.isNovo()){
				msg="Usuário já cadastrado na base...";
				RequestDispatcher redirecionar=request.getRequestDispatcher("principal/usuario.jsp");
				request.setAttribute("msg",msg);
				request.setAttribute("modelLogin",user);
				redirecionar.forward(request, response);
			}else {
				if(!user.isNovo()) {
					msg="Usuário alterado com sucesso!!!";
					user=dao.atualizaUser(user.getId(),new ModelLogin(login,email,senha));
					user.setId(null);
					RequestDispatcher redirecionar=request.getRequestDispatcher("principal/usuario.jsp");
					request.setAttribute("msg",msg);
					request.setAttribute("modelLogin",user);
					redirecionar.forward(request, response);
				}else {
					user=dao.cadastrarUsuario(user);
					RequestDispatcher redirecionar=request.getRequestDispatcher("principal/usuario.jsp");
					request.setAttribute("msg",msg);
					request.setAttribute("modelLogin",user);
					redirecionar.forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar=request.getRequestDispatcher("error.jsp");
			request.setAttribute("msg",e.getMessage());
			redirecionar.forward(request, response);
		}
		
	}

}
