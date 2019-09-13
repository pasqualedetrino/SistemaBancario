package servletClass;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sistemabancario.ContoCorrente;

/**
 * Servlet implementation class MementoAcquisti
 */
@WebServlet(description = "servlet per richiamare il memento", urlPatterns = { "/MementoAcquisti.do" })
public class MementoAcquisti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MementoAcquisti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ContoCorrente conto = FaiAcquisti.getConto();
		
		conto.annullaOperazione();	// ripristina il saldo e fa l'aggiornamento al db
		response.sendRedirect("./tema/coinbuzz/acquista.html");
		
	}
	
}
