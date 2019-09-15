package sistemabancario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import servletClass.RispostaRicerca;

public class OperazioniAmministratore {
	// il costruttore carica la hashmap per velocizzare l'accesso ai dati
	public OperazioniAmministratore() {
    	try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  
			Statement stmt=con.createStatement();  
			String query = "select * from utente";
			System.out.println("query: " + query);
			ResultSet rs=stmt.executeQuery(query);  
			
			// per ogni utente carico le informazioni e le inserisco in una hashMap
			while(rs.next()) {
				int idUtente = rs.getInt(1);
				String nome = rs.getString(3);
				String cognome = rs.getString(4);		
				dataNascita = rs.getString(5);
				via = rs.getString(6);
				citta = rs.getString(7);
				
				utente = new Utente(idUtente, nome, cognome, dataNascita, via, citta);
				
				utenteMap.put(idUtente, utente);
				
				proponi(ricercaCorrentista(idUtente));	// ricerca l'idUtente per caricare un oggetto RispostaRicerca che sarà usato per proporre un investimento
			}
				
			
			con.close();	  
		} catch(Exception e) {System.out.println("errore login utente"); System.out.println(e);}
	}
	
	// ricerca per idUtente
	public RispostaRicerca ricercaCorrentista(int idConto) {
		RispostaRicerca rispostaRicerca;
		
		Utente trovato = utenteMap.get(idConto);
		
		int id = -1;
		String nConto = "vuoto", tipoConto = "vuoto";
		float saldo = 0.f;
		
		if(trovato != null) {
			String nome = trovato.getNome();
			id = trovato.getIdUtente();	// solo per veirificare
			
			dataNascita = trovato.getDataNascita();
			via = trovato.getVia();
			citta = trovato.getCitta();
						
			// dato l'id dell'utente vado a leggere le info relative ai dati anagrafici e al contocorrente
	    	try {  
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
				  
				Statement stmt=con.createStatement();  
				String query = "select * from contocorrente where idUtente = '" + idConto + "'";
				System.out.println("query: " + query);
				ResultSet rs=stmt.executeQuery(query);  
				
				// per ogni utente carico le informazioni e le inserisco in una hashMap
				while(rs.next()) {
					nConto = rs.getString(1);
					saldo= rs.getFloat(2);
					tipoConto = rs.getString(3);
				}
					
				con.close();	  
			} catch(Exception e) {System.out.println("nessun conto corrente"); System.out.println(e);}
		    	
		}
		else {
			dataNascita ="nonEsiste";
			System.out.println("RICERCA FALLITA...\n");
		}
		
		rispostaRicerca = new RispostaRicerca(dataNascita, via, citta, nConto, saldo, tipoConto, id);
		return rispostaRicerca;
}
	
	// ricerca per nome e cognome
	public ArrayList<RispostaRicerca> ricercaCorrentista(String nome, String cognome) {
		ArrayList<RispostaRicerca> arrayRisposta = new ArrayList<RispostaRicerca>();
		boolean trovato = false;
		boolean notexists = false;
		
		int id = 0;
		RispostaRicerca rispostaRicerca;
		
		for(Utente u : utenteMap.values()) {
			if(u.getNome().equals(nome) && u.getCognome().equals(cognome)) {
				trovato = true;	// se trovo prendo le restanti info 
				notexists = true;	// se trovo non faccio scattare l'errore
				id = u.getIdUtente();	// solo per veirificare
				dataNascita = u.getDataNascita();
				via = u.getVia();
				citta = u.getCitta();
		
				String nConto = "vuoto", tipoConto = "vuoto";
				float saldo = 0.f;
		
				if(trovato) {
		
					// dato l'id dell'utente vado a leggere le info relative ai dati anagrafici e al contocorrente
			    	try {  
						Class.forName("com.mysql.cj.jdbc.Driver");  
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
						  
						Statement stmt=con.createStatement();  
						String query = "select * from contocorrente where idUtente = '" + id + "'";
						System.out.println("query: " + query);
						ResultSet rs=stmt.executeQuery(query);  
						
						// per ogni utente carico le informazioni e le inserisco in una hashMap
						if(rs.next()) {
							nConto = rs.getString(1);
							saldo= rs.getFloat(2);
							tipoConto = rs.getString(3);
							
							RispostaRicerca risposta = new RispostaRicerca(dataNascita, via, citta, nConto, saldo, tipoConto, id);
							
							arrayRisposta.add(risposta);
						}
							
						
						con.close();	  
					} catch(Exception e) {System.out.println("nessun conto corrente"); System.out.println(e);}
			    	trovato = false;
		    	
				}
		    	
			}
		}
		// se non trovo almeno un utente
		if(!notexists){
				dataNascita ="nonEsiste";
				System.out.println("utente non presente!");
				RispostaRicerca risposta = new RispostaRicerca("nonEsiste", via, citta, "3", 3.2f, "nessuno", id);
				arrayRisposta.add(risposta);
			
			}

		return arrayRisposta;
	}
		
	public int inserisciUtente(String password, String nome, String cognome, String dataNascita, String via, String citta) {
		int idUtente;
				
		Utente nuovo = new Utente(password, nome, cognome, dataNascita, via, citta);
				
    	try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  		
			 // the mysql insert statement
			  String query = " insert into utente (password, nome, cognome, dataNascita, via, città)"
			+ " values (?, ?, ?, ?, ?, ?)";
			
			  // create the mysql insert preparedstatement
			  PreparedStatement preparedStmt = con.prepareStatement(query);
			  preparedStmt.setString (1, password);
			  preparedStmt.setString (2, nome);
			  preparedStmt.setString (3, cognome);
			  preparedStmt.setString (4, dataNascita);
			  preparedStmt.setString (5, via);
			  preparedStmt.setString (6, citta);
			  
		      // execute the preparedstatement
		      preparedStmt.execute();
		      
		      System.out.println("Utente inserito!\n");
			con.close();	  
		} catch(Exception e) {System.out.println("errore inserimento utente"); System.out.println(e);}
    	
    	// trovo l'id dell'utente appena inserito
    	try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  
			Statement stmt=con.createStatement();  
			String query = "select max(idUtente) from utente";
			System.out.println("query: " + query);
			ResultSet rs=stmt.executeQuery(query);  
			
			// per ogni utente carico le informazioni e le inserisco in una hashMap
			if(rs.next()) {
				idUtente = rs.getInt(1);
				nuovo.setIdUtente(idUtente);
				return idUtente;
			}
				
			
			con.close();	  
		} catch(Exception e) {System.out.println("nessun conto corrente"); System.out.println(e);}
    	return 0;
	}
	
	private void proponi(RispostaRicerca rispostaRicerca) throws Exception {
			
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

		Date date = sdf.parse(rispostaRicerca.getDataNascita());

		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		
		float saldo = rispostaRicerca.getSaldo();
		
		String tipoConto = rispostaRicerca.getTipoConto();
		
		int annoAttuale = Calendar.getInstance().get(Calendar.YEAR);
		int annoNascita = cal.getWeekYear();
		/*
		 
		se il saldo > 10000
		se eta < 50
			altoRichio
		else
			medioRischio

	else
		se eta > 50
			bassoRischio
		else
			medioRischio
		*/

		System.out.println("annoAttuale " + annoAttuale);
		System.out.println("annoNascita " + annoNascita);
		String tipoInvestimento = "nessuno";
		
		if(saldo > 10000)
			if( annoAttuale - annoNascita > 50)
				tipoInvestimento = "alto";
			else
				tipoInvestimento = "medio";
		else if( annoAttuale - annoNascita > 50)
			tipoInvestimento = "basso";
		else
			tipoInvestimento = "medio";
		
		// memorizziamo nel db il tipo di investimento da proporre
		try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  		
			 // the mysql insert statement
			  String query = " insert into investimento (idUtente, tipo)"
			+ " values (?, ?)";
			
			  // create the mysql insert preparedstatement
			  PreparedStatement preparedStmt = con.prepareStatement(query);
			  preparedStmt.setInt (1, rispostaRicerca.getIdUtente());
			  preparedStmt.setString (2, tipoInvestimento);
			  
		      // execute the preparedstatement
		      preparedStmt.execute();
		      
		      System.out.println("investimento inserito!\n");
			con.close();	  
		} catch(Exception e) {System.out.println("inserimento non investimento"); System.out.println(e);}
	}
		
	private Utente utente;
	private HashMap<Integer, Utente> utenteMap = new HashMap<Integer, Utente>(); 
	
	private String dataNascita, via, citta;
}
