package servletClass;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sistemabancario.CartaDiCredito;
import sistemabancario.CartaPrepagata;
import sistemabancario.ContoBasic;
import sistemabancario.ContoCorrente;
import sistemabancario.ContoMedium;
import sistemabancario.ContoPremium;
import sistemabancario.OperazioniAmministratore;

/**
 * Servlet implementation class RegistraUtente
 */
@WebServlet(description = "servlet per memorizzare un nuovo utente", urlPatterns = { "/RegistraUtente.do" })
public class RegistraUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistraUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("sto nella servlet");
		System.out.println("data di nascita letta " + request.getParameter("dataNascita"));
		OperazioniAmministratore operazioniAmministratore = new OperazioniAmministratore();
		
		idUtente = operazioniAmministratore.inserisciUtente(request.getParameter("password"), request.getParameter("nome"), request.getParameter("cognome"), request.getParameter("dataNascita"), request.getParameter("via"), request.getParameter("citta"));
		System.out.println("idUtente nella servlet: " + idUtente);
		// sto registrando un utente, ora qual'è il suo codice???
		if(idUtente == 0)
		{System.out.println("errore idUtente = 0");}
		else {
			ContoCorrente contoCorrente;
			int nConto = ContoCorrente.registraConto(Integer.valueOf(idUtente), request.getParameter("tipoConto"), Float.valueOf(request.getParameter("saldo")));	// crea conto corrente
			

			
			String tipoConto = request.getParameter("tipoConto");
			  
			   
			if(tipoConto.equals("basic"))
				contoCorrente = ContoBasic.getInstance(idUtente);
			else if(tipoConto.equals("medium"))
				contoCorrente = ContoMedium.getInstance(idUtente);
			else if(tipoConto.equals("premium")) 
				contoCorrente = ContoPremium.getInstance(idUtente);
			
			CartaDiCredito.creaCartaDiCredito(Integer.valueOf(idUtente), nConto);	// crea carta di credito
			
			CartaPrepagata.creaCartaPrepagata(Integer.valueOf(idUtente),  Float.valueOf(request.getParameter("saldoPrepagata")));	// crea carta prepagata
			
			response.sendRedirect("./idUtenteRegistrato.do");
			
		}
			
	}
	private static int idUtente;
	public static int getIdUtenteNuovo() { return idUtente; }

}
