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

@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ServletUsuarioController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login=request.getParameter("login");
		String email=request.getParameter("email");
		String senha=request.getParameter("senha");
		int identificador=Integer.parseInt(request.getParameter("identificador"));
		
		ModelLogin user=new ModelLogin(login,senha,email,identificador);
		
		try {
			DAOLoginRepository dao=new DAOLoginRepository();
			dao.cadastrarUsuario(user);
			RequestDispatcher redirecionar=request.getRequestDispatcher("principal/usuario.jsp");
			request.setAttribute("msg","Usuário cadastrado com sucesso!");
			redirecionar.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar=request.getRequestDispatcher("error.jsp");
			request.setAttribute("msg",e.getMessage());
			redirecionar.forward(request, response);
		}
		
	}

}
