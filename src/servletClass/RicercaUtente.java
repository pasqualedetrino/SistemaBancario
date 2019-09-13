package servletClass;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sistemabancario.OperazioniAmministratore;
import sistemabancario.Utente;

/**
 * Servlet implementation class RicercaUtente
 */
@WebServlet(description = "servlet per la ricerca di un utente", urlPatterns = { "/RicercaUtente.do" })
public class RicercaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OperazioniAmministratore operazioniAmministratore = new OperazioniAmministratore();
		
		rispostaRicerca = operazioniAmministratore.ricercaCorrentista(request.getParameter("nome"), request.getParameter("cognome"));
		Utente u = new Utente(rispostaRicerca.getIdUtente());
		idUtente = rispostaRicerca.getIdUtente();
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter(); 
	
		out.println("<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"<head>\r\n" + 
					"    <meta charset=\"utf-8\">\r\n" + 
					"    <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\r\n" + 
					"    <title>RicercaUtente</title>\r\n" + 
					"    <meta name=\"description\" content=\"\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
					"    <link rel=\"shortcut icon\" type=\"image/png\" href=\"./tema/coinbuzz/assets/images/favicon.png\">\r\n" + 
					"    <!-- Place favicon.ico in the root directory -->\r\n" + 
					"    <!-- all css here -->\r\n" + 
					"    <!-- bootstrap v3.3.7 css -->\r\n" + 
					"    <link rel=\"stylesheet\" href=\"./tema/coinbuzz/assets/css/bootstrap.min.css\">\r\n" + 
					"    <!-- animate css -->\r\n" + 
					"    <link rel=\"stylesheet\" href=\"./tema/coinbuzz/assets/css/animate.css\">\r\n" + 
					"    <!-- owl.carousel.2.0.0-beta.2.4 css -->\r\n" + 
					"    <link rel=\"stylesheet\" href=\"./tema/coinbuzz/assets/css/owl.carousel.min.css\">\r\n" + 
					"    <!-- swiper.min.css -->\r\n" + 
					"    <link rel=\"stylesheet\" href=\"./tema/coinbuzz/assets/css/swiper.min.css\">\r\n" + 
					"    <!-- font-awesome v4.6.3 css -->\r\n" + 
					"    <link rel=\"stylesheet\" href=\"./tema/coinbuzz/assets/css/font-awesome.min.css\">\r\n" + 
					"    <!-- flaticon.css -->\r\n" + 
					"    <link rel=\"stylesheet\" href=\"./tema/coinbuzz/assets/css/flaticon.css\">\r\n" + 
					"    <!-- magnific-popup.css -->\r\n" + 
					"    <link rel=\"stylesheet\" href=\"./tema/coinbuzz/assets/css/magnific-popup.css\">\r\n" + 
					"    <!-- metisMenu.min.css -->\r\n" + 
					"    <link rel=\"stylesheet\" href=\"./tema/coinbuzz/assets/css/metisMenu.min.css\">\r\n" + 
					"    <!-- style css -->\r\n" + 
					"    <link rel=\"stylesheet\" href=\"./tema/coinbuzz/assets/css/styles.css\">\r\n" + 
					"    <!-- responsive css -->\r\n" + 
					"    <link rel=\"stylesheet\" href=\"./tema/coinbuzz/assets/css/responsive.css\">\r\n" + 
					"    <!-- modernizr css -->\r\n" + 
					"    <script src=\"./tema/coinbuzz/assets/js/vendor/modernizr-2.8.3.min.js\"></script>\r\n" + 
					"</head>\r\n" + 
					"\r\n" + 
					"<body>\r\n" + 
					"    <div class=\"breadcumb-area flex-style  black-opacity\">\r\n" + 
					"        <div class=\"container\">\r\n" + 
					"            <div class=\"row\">\r\n" + 
					"                <div class=\"col-12\">\r\n" + 
					"                    <h2>Login</h2>\r\n" + 
					"                    <ul class=\"d-flex\">\r\n" + 
					"                        <li><a href=\"./tema/coinbuzz/../../index.html\">Home</a></li>\r\n" + 
					"                        <li><i class=\"fa fa-angle-double-right\"></i></li>\r\n" + 
					"                        <li><span><a href=\"./tema/coinbuzz/OperazioniAmministratore.html\">OperazioniAmministratore</span></a></li>\r\n" + 
					"						<li><i class=\"fa fa-angle-double-right\"></i></li>\r\n" + 
					"                        <li><span>RicercaUtente</span></li>\r\n" + 
					"                    </ul>\r\n" + 
					"                </div>\r\n" + 
					"            </div>\r\n" + 
					"        </div>\r\n" + 
					"    </div>");
		
		if(!rispostaRicerca.getDataNascita().equals("nonEsiste")) {	
			out.println( 
					"  <table class=\"table table-striped\">\r\n" + 
					"    <thead>\r\n" + 
					"      <tr>\r\n" + 
					"        <th>dataNascita</th>\r\n" + 
					"        <th>via</th>\r\n" +
					"        <th>citta'</th>\r\n" + 
					"        <th>numeroConto</th>\r\n" +
					"        <th>tipoConto</th>\r\n" + 
					"        <th>saldo</th>\r\n" + 
					"        <th>saldoPrepagata</th>\r\n" +
					"      </tr>\r\n" + 
					"    </thead>\r\n" + 
					"    <tbody>\r\n");
			
			out.println("<tr>\r\n" + 
					" <td>" + rispostaRicerca.getDataNascita() + "</td>\r\n" + 
					" <td>" + rispostaRicerca.getVia() + "</td>\r\n" +
					" <td>" + rispostaRicerca.getCitta() + "</td>\r\n" +
					" <td>" + rispostaRicerca.getNConto() + "</td>\r\n" +
					" <td>" + rispostaRicerca.getTipoConto() + "</td>\r\n" +
					" <td>" + rispostaRicerca.getSaldo() + "</td>\r\n" +
					" <td>" + u.getSaldoPrepagata() + "</td>\r\n" +
					"<form method=\"POST\" action=\"./tema/coinbuzz/VersaPrepagata.html\"><td><button type=\"submit\" class=\"btn btn-primary\">RicaricaPrepagata</button><td></form>" +
					"<form method=\"POST\" action=\"./tema/coinbuzz/VersaConto.html\"><td><button type=\"submit\" class=\"btn btn-primary\">RicaricaConto</button><td></form>" +
					
					" </tr>\r\n");
			
			out.println("    </tbody>\r\n" + 
					"  </table>\r\n" + 
					"</div>\r\n" + 
					"\r\n" + 
					"</body>\r\n" + 
					"</html>");
			
		}
		else {
			out.println("<br/><br/><br/>utente non trovato!");
			out.println("<a href = \"./tema/coinbuzz/RegistraUtente.html\">Registra Utente</a><br/><br/><br/>");
			
		}
		
		out.println("    <!-- footer-area start -->\r\n" + 
				"    <footer class=\"footer-area\">\r\n" + 
				"        <div class=\"footer-top\">\r\n" + 
				"            <div class=\"container\">\r\n" + 
				"                <div class=\"row\">\r\n" + 
				"				\r\n" + 
				"                    <div class=\"col\">\r\n" + 
				"                        <div class=\"footer-widget footer-logo\">\r\n" + 
				"                            \r\n" + 
				"                            <p>	Corso di Programmazione 3 Progetto Esame <br/>\r\n" + 
				"								Docente: Angelo Ciaramella A.A 2018/2019 <br/>\r\n" + 
				"								Traccia - Sistema Bancario</p>\r\n" + 
				"                        </div>\r\n" + 
				"                    </div>\r\n" + 
				"					\r\n" + 
				"					<div class=\"col\">\r\n" + 
				"                        <div class=\"footer-widget footer-logo\">\r\n" + 
				"                            \r\n" + 
				"                            <p>	Studente <br/>\r\n" + 
				"								Pasquale De Trino <br>\r\n" + 
				"								Matricola: 012400/1637</p>\r\n" + 
				"                        </div>\r\n" + 
				"                    </div>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"            </div>\r\n" + 
				"        </div>\r\n" + 
				"        <div class=\"footer-bottom\">\r\n" + 
				"            <div class=\"container\">\r\n" + 
				"                <div class=\"row\">\r\n" + 
				"                    <div class=\"col-12 copyright\">\r\n" + 
				"                        <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->\r\n" + 
				"Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class=\"fa fa-heart-o\" aria-hidden=\"true\"></i> by <a href=\"https://colorlib.com\" target=\"_blank\" class=\"text-primary\">Colorlib</a>\r\n" + 
				"<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->\r\n" + 
				"</p>\r\n" + 
				"                    </div>\r\n" + 
				"                </div>\r\n" + 
				"            </div>\r\n" + 
				"        </div>\r\n" + 
				"    </footer>\r\n" + 
				"    \r\n" + 
				"</body>\r\n" + 
				"\r\n" + 
				"</html>");
		
		
	}
	
	private static RispostaRicerca rispostaRicerca;
	public static RispostaRicerca getRisposta() {return rispostaRicerca;}
	private static int idUtente = 0;
	public static int getIdUtente() { return idUtente; }
	public static void setIdUtente(int id) {idUtente = id; }

}