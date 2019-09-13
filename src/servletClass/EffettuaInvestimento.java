package servletClass;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sistemabancario.AltoRischio;
import sistemabancario.BassoRischio;
import sistemabancario.Investimento;
import sistemabancario.MedioRischio;
import sistemabancario.Utente;

/**
 * Servlet implementation class EffettuaInvestimento
 */
@WebServlet(description = "servlet per eseguire un investimento", urlPatterns = { "/EffettuaInvestimento.do" })
public class EffettuaInvestimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EffettuaInvestimento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tipoInvestimento = request.getParameter("tipoInvestimento");
		
		int importo = Integer.valueOf(request.getParameter("importo"));
		
		System.out.println("zio ha detto che vuole: " + tipoInvestimento);
		
		
		
		String responso = "vuoto";
		
		BassoRischio bassoRischio = new BassoRischio();
		MedioRischio medioRischio = new MedioRischio();
		AltoRischio  altoRischio  = new AltoRischio();
		
		
		bassoRischio.setSuccessor(medioRischio);
		medioRischio.setSuccessor(altoRischio);
		
		
		
		System.out.println("idUtente trasmesso " + AccediUtente.getId());
		Utente utente = new Utente(AccediUtente.getId());
		
		
		bassoRischio.investi(importo, tipoInvestimento);
		
		float risultato = Investimento.getRisultato();
		
		System.out.println("risultato dell'investimento: " + risultato);
		
response.setContentType("text/html");
		
		PrintWriter out = response.getWriter(); 
	
		out.println("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"utf-8\">\r\n" + 
				"    <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\r\n" + 
				"    <title>Efettua Investimento</title>\r\n" + 
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
				"                    <ul class=\"d-flex\">\r\n" + 
				"                        <li><a href=\"./tema/coinbuzz/../../index.html\">Home</a></li>\r\n" + 
				"                        <li><i class=\"fa fa-angle-double-right\"></i></li>\r\n" + 
				"                        <li><span><a href=\"AccediUtente.do\">Info Utente</span></a></li>\r\n" + 
				"						<li><i class=\"fa fa-angle-double-right\"></i></li>\r\n" + 
				"                        <li><span>Efettua Investimento</span></li>\r\n" + 
				"                    </ul>\r\n" + 
				"                </div>\r\n" + 
				"            </div>\r\n" + 
				"        </div>\r\n" + 
				"    </div>");
		
		if(risultato > importo) {
			responso = "riuscito";
			out.println("<h1> Investimento riuscito! </h1>");
		}
		else {
			responso = "fallito";
			out.println("<h1> Investimento non riuscito! </h1>");
		}
		
		System.out.println("responso " + responso);
		
		utente.Investe(risultato, responso);
		
		out.println("<br><br><br><!-- footer-area start -->\r\n" + 
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

}
