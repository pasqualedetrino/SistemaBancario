package servletClass;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sistemabancario.Login;
import sistemabancario.LoginFactory;
import sistemabancario.OperazioniAmministratore;
import sistemabancario.testDB;

/**
 * Servlet implementation class AccediAmministratore
 */
@WebServlet(description = "servlet per l'accesso in modalità amministratore", urlPatterns = { "/AccediAmministratore.do" })
public class AccediAmministratore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccediAmministratore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Login connesso = LoginFactory.getLogin(request.getParameter("username"), request.getParameter("password"), "amministratore");
				
		if(connesso.Accedi(request.getParameter("username").toString(), request.getParameter("password").toString())) {
			OperazioniAmministratore OpA = new OperazioniAmministratore();
			
			response.sendRedirect("./tema/coinbuzz/OperazioniAmministratore.html"); 
		
		}
		
	}

}
