package servletClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sistemabancario.Login;
import sistemabancario.LoginFactory;
import sistemabancario.Utente;

/**
 * Servlet implementation class AccediUtente
 */
@WebServlet(description = "servlet per l'accesso di un utente", urlPatterns = { "/AccediUtente.do" })
public class AccediUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccediUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	idutente = request.getParameter("idUtente");
    	password = request.getParameter("password");
    	
    	doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter(); 
			
		out.println("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"utf-8\">\r\n" + 
				"    <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\r\n" + 
				"    <title>Info Utente</title>\r\n" + 
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
				"                    <h2>Info Utente</h2>\r\n" + 
				"                    <ul class=\"d-flex\">\r\n" + 
				"                        <li><a href=\"./tema/coinbuzz/../../index.html\">Home</a></li>\r\n" + 
				"                        <li><i class=\"fa fa-angle-double-right\"></i></li>\r\n" + 
				"                        <li><span><a href=\"./tema/coinbuzz/AccediUtente.html\">AccediUtente</span></a></li>\r\n" + 
				"						<li><i class=\"fa fa-angle-double-right\"></i></li>\r\n" + 
				"                        <li><span>InfoUtente</span></li>\r\n" + 
				"                    </ul>\r\n" + 
				"                </div>\r\n" + 
				"            </div>\r\n" + 
				"        </div>\r\n" + 
				"    </div>");
	
		
		Login connesso = LoginFactory.getLogin(idutente, password, "utente");
		
		if(connesso.Accedi(idutente.toString(), password)) {
			
			idUtente = Integer.valueOf(idutente);
			
			Utente utente = (Utente) connesso;
			
			utente = new Utente(Integer.valueOf(idutente));
				
			
			out.println("<div class=\"featured-area featured-area2\">\r\n" + 
					"        <div class=\"container\">\r\n" + 
					"            <div class=\"row\">\r\n" + 
					"                <div class=\"col-12\">\r\n" + 
					"                    <div class=\"featured-wrapper\">\r\n" + 
					"                        <div class=\"row\">\r\n" + 
					"                            <div class=\"col-lg-3 col-sm-4 col-8\">\r\n" + 
					"                                <div class=\"featured-wrap\">\r\n" + 
					"                                    <div class=\"featured-img\">\r\n" + 
					"                                        <img src=\"./tema/coinbuzz/assets/images/featured/1.jpg\" alt=\"\">\r\n" + 
					"                                    </div>\r\n" + 
					"                                    <h2><a href=\"./tema/coinbuzz/acquista.html\">Acquista</a></h2>\r\n" + 
					"                                    <p>Effettua un acquisto utilizzando come metodo di pagamento la carta di credito o la tua carta prepagata</p>\r\n" + 
					"                                </div>\r\n" + 
					"                            </div>\r\n" + 
					"                            <div class=\"col-lg-3 col-sm-4 col-8\">\r\n" + 
					"                                <div class=\"featured-wrap\">\r\n" + 
					"                                    <div class=\"featured-img\">\r\n" + 
					"                                        <img src=\"./tema/coinbuzz/assets/images/featured/2.jpg\" alt=\"\">\r\n" + 
					"                                    </div>\r\n" + 
					"                                    <h2><a href=\"OperazioniUtente.do\">Visualizza le operazioni</a></h2>\r\n" + 
					"                                    <p>Controlla le operazioni effettuate fin ora e lo status dei tuoi investimenti </p>\r\n" + 
					"                                </div>\r\n" + 
					"                            </div>\r\n" + 
					"                            <div class=\"col-lg-3 col-sm-4 col-8\">\r\n" + 
					"                                <div class=\"featured-wrap mb-0\">\r\n" + 
					"                                    <div class=\"featured-img\">\r\n" + 
					"                                        <img src=\"./tema/coinbuzz/assets/images/featured/3.jpg\" alt=\"\">\r\n" + 
					"                                    </div>\r\n" + 
					"                                    <h2><a href=\"ScegliInvestimento.do\">Scegli una forma di investimento</a></h2>\r\n" + 
					"                                    <p>Scegli una forma di investimento tra quelle disponibili nel sistema oppure quella suggerita da noi</p>\r\n" + 
					"                                </div>\r\n" + 
					"                            </div>\r\n" + 
					"							<div class=\"col-lg-3 col-sm-4 col-8\">\r\n" + 
					"                                <div class=\"featured-wrap mb-0\">\r\n" + 
					"                                    <div class=\"featured-img\">\r\n" + 
					"                                        <img src=\"./tema/coinbuzz/assets/images/featured/6.jpg\" alt=\"\">\r\n" + 
					"                                    </div>\r\n" + 
					"                                    <h2><a href=\"./tema/coinbuzz/OttieniSconto.html\">Ottieni sconto</a></h2>\r\n" + 
					"                                    <p>Utilizza il tuo coupon per ottenere fino al 10% di sconto sui tuoi acquisti</p>\r\n" + 
					"                                </div>\r\n" + 
					"                            </div>\r\n" + 
					"                        </div>\r\n" + 
					"                    </div>\r\n" + 
					"                </div>\r\n" + 
					"            </div>\r\n" + 
					"            \r\n" + 
					"        </div>\r\n" + 
					"    </div>");
			 
			out.println("<div class=\"container\"><h2 text-center>numero conto:  " + utente.getNConto() );
			out.println("saldo:   " + utente.getSaldo());
			out.println("tipo conto:    " + utente.getTipoConto() );
			out.println("saldo prepagata:    " + utente.getSaldoPrepagata() + "</h2></div>");
			
		}
		
		else 
			out.println("utente non presente! ");
		
		out.println("<!-- footer-area start -->\r\n" + 
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
	
	private String idutente;
	private String password;
	public static int idUtente; 
	public static int getId() {return idUtente;}

}
