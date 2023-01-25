package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
		String msg="Exclus�o mal sucedida!!!";
		DAOLoginRepository dao=new DAOLoginRepository(request);
		try {
			if(acao!=null&&!acao.isEmpty()&&acao.equalsIgnoreCase("deletar")) {
				    String idUser=request.getParameter("id");
					dao.deletarUser(Integer.parseInt(idUser));
					List<ModelLogin> models=new ArrayList<>();
					models=dao.buscarUser();
					RequestDispatcher redirecionar=request.getRequestDispatcher("principal/usuario.jsp");
					request.setAttribute("modelsLogin",models);
					msg="Usu�rio deletado com sucesso!!!";
					request.setAttribute("msg",msg);
					redirecionar.forward(request, response);
			}else if(acao!=null&&!acao.isEmpty()&&acao.equalsIgnoreCase("deletarComAjax")){
					String idUser=request.getParameter("id");
					dao.deletarUser(Integer.parseInt(idUser));
					msg="Exclus�o realizada com �xito!";
					response.getWriter().write(msg);
			}else if(acao!=null&&!acao.isEmpty()&&acao.equalsIgnoreCase("buscarUserAjax")) {
				   String nomeUser=request.getParameter("nome");
				   List<ModelLogin> user=dao.buscarUser(nomeUser);
				   Gson gson=new GsonBuilder().setPrettyPrinting().create();
				   String jsonCriado=gson.toJson(user);
				   response.getWriter().write(jsonCriado);
			}else if(acao!=null&&!acao.isEmpty()&&acao.equalsIgnoreCase("visualizarUser")) {
				   int idUser=Integer.parseInt(request.getParameter("id"));
				   ModelLogin user=dao.buscarUser(idUser);
				   List<ModelLogin> models=new ArrayList<>();
				   models=dao.buscarUser();
				   msg="Usu�rio em edi�ao";
				   RequestDispatcher redirecionar=request.getRequestDispatcher("principal/usuario.jsp");
				   request.setAttribute("modelsLogin",models);
				   request.setAttribute("msg",msg);
				   request.setAttribute("modelLogin",user);
				   redirecionar.forward(request, response);
			}else if(acao!=null&&!acao.isEmpty()&&acao.equalsIgnoreCase("ListarUsers")) {
				   List<ModelLogin> models=new ArrayList<>();
				   models=dao.buscarUser();
				   RequestDispatcher redirecionar=request.getRequestDispatcher("principal/usuario.jsp");
				   msg="Usu�rios Carregados...";
				   request.setAttribute("msg",msg);
				   request.setAttribute("modelsLogin",models);
				   redirecionar.forward(request, response);
			}else {
				RequestDispatcher redirecionar=request.getRequestDispatcher("principal/usuario.jsp");
				request.setAttribute("msg",msg);
				List<ModelLogin> models=new ArrayList<>();
				models=dao.buscarUser();
				request.setAttribute("modelsLogin",models);
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
		String msg="Usu�rio cadastrado com sucesso!!!";
		String login=request.getParameter("login");
		String email=request.getParameter("email");
		String senha=request.getParameter("senha");
		String id=request.getParameter("id");
		
		ModelLogin user=new ModelLogin(login,senha,email);
		user.setId(id!=null&&!id.isEmpty()?Integer.parseInt(id):null);
		
		try {
			DAOLoginRepository dao=new DAOLoginRepository(request);
			if(dao.verificaUsuarioExistente(user.getLogin()) && user.isNovo()){
				msg="Usu�rio j� cadastrado na base...";
				List<ModelLogin> models=new ArrayList<>();
				models=dao.buscarUser();
				RequestDispatcher redirecionar=request.getRequestDispatcher("principal/usuario.jsp");
				request.setAttribute("msg",msg);
				request.setAttribute("modelLogin",user);
				request.setAttribute("modelsLogin",models);
				redirecionar.forward(request, response);
			}else {
				if(!user.isNovo()) {
					msg="Usu�rio alterado com sucesso!!!";
					user=dao.atualizaUser(user.getId(),new ModelLogin(login,email,senha));
					user.setId(null);
					List<ModelLogin> models=new ArrayList<>();
					models=dao.buscarUser();
					RequestDispatcher redirecionar=request.getRequestDispatcher("principal/usuario.jsp");
					request.setAttribute("msg",msg);
					request.setAttribute("modelLogin",user);
					request.setAttribute("modelsLogin",models);
					redirecionar.forward(request, response);
				}else {
					user=dao.cadastrarUsuario(user);
					List<ModelLogin> models=new ArrayList<>();
					models=dao.buscarUser();
					RequestDispatcher redirecionar=request.getRequestDispatcher("principal/usuario.jsp");
					request.setAttribute("msg",msg);
					request.setAttribute("modelLogin",user);
					request.setAttribute("modelsLogin",models);
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
