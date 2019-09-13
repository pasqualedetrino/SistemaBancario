package servletClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ScegliInvestimento
 */
@WebServlet(description = "servlet per scegliere che investimento effettuare", urlPatterns = { "/ScegliInvestimento.do" })
public class ScegliInvestimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScegliInvestimento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUtente = AccediUtente.getId();
		String tipoInvestimento = "nessuno";
		
		try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  
			Statement stmt=con.createStatement();  
			String query = "select * from investimento where idUtente = '" + idUtente + "'";
			System.out.println("query: " + query);
			ResultSet rs=stmt.executeQuery(query);  
			
			if(rs.next())  
				tipoInvestimento = rs.getString(2);
						
			con.close();	  
		} catch(Exception e) {System.out.println("nessun investimento proposto"); System.out.println(e);}
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter(); 
	
		out.println("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"utf-8\">\r\n" + 
				"    <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\r\n" + 
				"    <title>Scegli Investimento</title>\r\n" + 
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
				"                    <h2>Scegli Investimento</h2>\r\n" + 
				"                    <ul class=\"d-flex\">\r\n" + 
				"                        <li><a href=\"./tema/coinbuzz/../../index.html\">Home</a></li>\r\n" + 
				"                        <li><i class=\"fa fa-angle-double-right\"></i></li>\r\n" + 
				"                        <li><span><a href=\"AccediUtente.do\">Info Utente</span></a></li>\r\n" + 
				"						<li><i class=\"fa fa-angle-double-right\"></i></li>\r\n" + 
				"                        <li><span>Scegli Investimento</span></li>\r\n" + 
				"                    </ul>\r\n" + 
				"                </div>\r\n" + 
				"            </div>\r\n" + 
				"        </div>\r\n" + 
				"<script type=\"text/javascript\">\r\n" + 
				"            function rb_controllo(){\r\n" + 
				"                var rb_scelto = false;\r\n" + 
				"                \r\n" + 
				"                for (counter = 0; counter < document.myform.tipoInvestimento.length; counter++) {\r\n" + 
				"                    if (document.myform.tipoInvestimento[counter].checked) \r\n" + 
				"                        rb_scelto = true;\r\n" + 
				"                }\r\n" + 
				"                \r\n" + 
				"                if (!rb_scelto) {\r\n" + 
				"                    alert(\"Selezionare almeno un'opzione\");\r\n" + 
				"                    return (false);\r\n" + 
				"                }\r\n" + 
				"                return (true);\r\n" + 
				"            }\r\n" + 
				"        </script>" +
				 
				"    </div>"); 
		out.println("    <br><br><br><div class=\"container\">"+"<h1> tipi di investimento </h1> <form method=\"post\" action=\"EffettuaInvestimento.do\" onsubmit=\"return rb_controllo()\" name=\"myform\">");
		
		if(tipoInvestimento.equals("nessuno"))
			out.println("		Investimenti disponibili <br/>"
					+ "<input type=\"radio\" name=\"tipoInvestimento\" value=\"basso\"/> Basso rischio <br/>\r\n" + 
					"		<input type=\"radio\" name=\"tipoInvestimento\" value=\"medio\"/> Medio rischio <br/>\r\n" + 
					"		<input type=\"radio\" name=\"tipoInvestimento\" value=\"alto\"/> Alto rischio <br/>");
		
		else if(tipoInvestimento.equals("basso")) {
			out.println("		Investimenti disponibili <br/>" +
					"		<input type=\"radio\" name=\"tipoInvestimento\" value=\"medio\"/> Medio rischio <br/>\r\n" + 
					"		<input type=\"radio\" name=\"tipoInvestimento\" value=\"alto\"/> Alto rischio <br/>" +
					"investimento proposto <br/> "
					+ "<input type=\"radio\" name=\"tipoInvestimento\" value=\"basso\"/> Basso rischio <br/>\r\n");
		}
		
		else if(tipoInvestimento.equals("medio")) {
			out.println("		Investimenti disponibili <br/>"
					+ "<input type=\"radio\" name=\"tipoInvestimento\" value=\"basso\"/> Basso rischio <br/>\r\n" + 
					"		<input type=\"radio\" name=\"tipoInvestimento\" value=\"alto\"/> Alto rischio <br/>" +
					"investimento proposto <br/> "+     "<input type=\"radio\" name=\"tipoInvestimento\" value=\"medio\"/> Medio rischio <br/>\r\n");
		}
		
		else if(tipoInvestimento.equals("alto")) {
			out.println("		Investimenti disponibili <br/>"
					+ "<input type=\"radio\" name=\"tipoInvestimento\" value=\"basso\"/> Basso rischio <br/>\r\n" + 
					"		<input type=\"radio\" name=\"tipoInvestimento\" value=\"medio\"/> Medio rischio <br/>\r\n" + 
					"investimento proposto <br/> " +
					"		<input type=\"radio\" name=\"tipoInvestimento\" value=\"alto\"/> Alto rischio <br/>");
		}

		out.println("importo: <input type=\"number\" class=\"form-control\" id=\"importo\" placeholder=\"Enter importo\" name=\"importo\" min=\"1\" max=\"999999\"  step=0.001 required/><br/> <button type=\"submit\" class=\"btn btn-primary\">Submit</button>\r\n" +
		"</div><br><br><br><!-- footer-area start -->\r\n" + 
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
