package sistemabancario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class ContoCorrente {
	public ContoCorrente() {}
	public ContoCorrente(int idUtente) {
		this.idUtente = idUtente;
    	try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  
			Statement stmt=con.createStatement();  
			String query = "select * from contoCorrente where idUtente = '" + idUtente + "'";
			System.out.println("query: " + query);
			ResultSet rs=stmt.executeQuery(query);  
			
			while(rs.next()) {
				nConto = rs.getInt(1);
				saldo = rs.getFloat(2);
			}
			
			con.close();	  
		} catch(Exception e) {System.out.println("l'utente non ha nessun conto corrente"); System.out.println(e);}
	}
	
	public float getSaldo() {return saldo;}
		
	public ArrayList storicoMovimenti() {
		try {
			String tipoOperazione = null;
			
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  
			Statement stmt=con.createStatement();  
			String query = "select * from movimenti where idUtente = '" + idUtente + "' AND nConto = '" + nConto + "'";
			System.out.println("query: " + query);
			ResultSet rs=stmt.executeQuery(query);  
			
			while(rs.next()) {
				
				int operazione = rs.getInt(2);
				
				if(operazione == 1)
					tipoOperazione = "versamento";
				else if(operazione == 0)
					tipoOperazione = "prelievo";
				else if(operazione == 2)
					tipoOperazione = "ripristino";
				else if(operazione == 3)
					tipoOperazione = "investimento riuscito";
				else if(operazione == 4)
					tipoOperazione = "investimento fallito";
				
				float importo = rs.getFloat(3);
				
				Operazioni nuovaOperazione = new Operazioni(tipoOperazione, importo);
				
				listaMovimenti.add(nuovaOperazione);
				
			}
			
			con.close();	  
		} catch(Exception e) {System.out.println("l'utente non ha effettuato alcun movimento! \n"); System.out.println(e);}
		
		return listaMovimenti;
	}
	
	protected boolean salvaOperazione(Operazioni operazioni) {
		
    	try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  		
			 // the mysql insert statement
			  String query = " insert into movimenti (Operazione, importo, idUtente, nConto)"
			+ " values (?, ?, ?, ?)";
			  
			  int tipoOperazione = -1;
			  if(operazioni.getOperazione().equals("versamento"))
				  tipoOperazione = 1;
			  else if(operazioni.getOperazione().equals("prelievo"))
				  tipoOperazione = 0;
			  else if(operazioni.getOperazione().equals("ripristino"))
				  tipoOperazione = 2;
			  else if(operazioni.getOperazione().equals("riuscito"))
				  tipoOperazione = 3;
			  else if(operazioni.getOperazione().equals("fallito"))
				  tipoOperazione = 4;
			  
			  // create the mysql insert preparedstatement
			  PreparedStatement preparedStmt = con.prepareStatement(query);
			  preparedStmt.setInt (1, tipoOperazione);
			  preparedStmt.setFloat (2, operazioni.getImporto());
			  preparedStmt.setInt (3, idUtente);
			  preparedStmt.setInt (4, nConto);
			  
		      // execute the preparedstatement
		      preparedStmt.execute();
		      
		      System.out.println("Operazione eseguita!\n");
		      
		      con.close();
		      
		      salvaOperazioneDB(operazioni);
		      
		      return true;
				  
		} catch(Exception e) {System.out.println("errore inserimento movimento utente"); System.out.println(e); return false;}
	}
	
	private void salvaOperazioneDB(Operazioni operazioni) {
		System.out.println("operazioni.getOperazione " + operazioni.getOperazione());
		System.out.println("operazioni.getImporto " + operazioni.getImporto());
		System.out.println("saldo prima della modifica " + this.saldo);
		
		float saldoSalva = -1.2f;
		if(operazioni.getOperazione().equals("versamento")) 
			saldoSalva = this.saldo + operazioni.getImporto();
		else if(operazioni.getOperazione().equals("riuscito"))
			saldoSalva = this.saldo + operazioni.getImporto();
		else if(operazioni.getOperazione().equals("prelievo"))
			  saldoSalva = this.saldo - operazioni.getImporto();
		else if(operazioni.getOperazione().equals("fallito"))
			  saldoSalva = this.saldo - operazioni.getImporto();
		
	    try
	    {
	    	Class.forName("com.mysql.cj.jdbc.Driver");  
	    	Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
		      Statement stmt=conn.createStatement();
		      // create the java mysql update preparedstatement
		      String query = "update contoCorrente set saldo = '" + saldo + "' where nConto = '" + nConto + "'";
		      System.out.println("query per update: " + query);
		      // execute the java preparedstatement
		      stmt.executeUpdate(query);
		      
		      conn.close();
	    }
	    catch (Exception e) { System.err.println("Got an exception! "); System.err.println(e.getMessage()); }
	}
	
	public void createMemento() {
		System.out.println("creo il memento con il saldo: " + saldo);
		contoMemento = new ContoMemento(saldo);
		
	}
	
	public void annullaOperazione() {
		this.saldo = contoMemento.annullaOperazione();
		
		operazioni = new Operazioni("ripristino", this.saldo);
		salvaOperazione(operazioni);
		
		salvaOperazioneDB();
	}
	
	public static int registraConto(int idUtente, String tipoConto, float saldo) {
		
		int nConto = -1;
		
		System.out.println("registraConto :: idUtente " + idUtente + " tipoConto " + tipoConto + " saldo " + saldo);
		try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  		
			 // the mysql insert statement
			  String query = " insert into contoCorrente (saldo, tipoConto, idUtente)"
			+ " values (?, ?, ?)";
			
			  // create the mysql insert preparedstatement
			  PreparedStatement preparedStmt = con.prepareStatement(query);
			  preparedStmt.setFloat (1, saldo);
			  preparedStmt.setString (2, tipoConto);
			  preparedStmt.setInt (3, idUtente);

		      // execute the preparedstatement
		      preparedStmt.execute();
		      
		      System.out.println("Conto corrente utente inserito!\n");
			con.close();	  
		} catch(Exception e) {System.out.println("errore inserimento conto corrente utente"); System.out.println(e);}
		
    	try {  
    		
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  
			Statement stmt=con.createStatement();  
			String query = "select max(nConto) from contoCorrente";
			System.out.println("query: " + query);
			ResultSet rs=stmt.executeQuery(query);  
			
			// per ogni utente carico le informazioni e le inserisco in una hashMap
			if(rs.next()) 
				nConto = rs.getInt(1);

			con.close();	  
		} catch(Exception e) {System.out.println("errore login utente"); System.out.println(e);}
    	
    	return nConto;
	}
	
	protected void salvaOperazioneDB() {
	    try
	    {
	    	Class.forName("com.mysql.cj.jdbc.Driver");  
	    	Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
		      Statement stmt=conn.createStatement();
		      // create the java mysql update preparedstatement
		      String query = "update contoCorrente set saldo = '" + saldo + "' where nConto = '" + nConto + "'";
		      System.out.println("query per update: " + query);
		      // execute the java preparedstatement
		      stmt.executeUpdate(query);
		      
		      conn.close();
	    }
	    catch (Exception e) { System.err.println("Got an exception! "); System.err.println(e.getMessage()); }
	}
	
	public abstract boolean versa(float importo);
	public abstract boolean preleva(float importo);
	
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente; 
		
    	try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  
			Statement stmt=con.createStatement();  
			String query = "select * from contoCorrente where idUtente = '" + idUtente + "'";
			System.out.println("query: " + query);
			ResultSet rs=stmt.executeQuery(query);  
			
			while(rs.next()) {
				nConto = rs.getInt(1);
				saldo = rs.getFloat(2);
			}
			
			con.close();	  
		} catch(Exception e) {System.out.println("l'utente non ha nessun conto corrente"); System.out.println(e);}
	}
	
	public int getIdUtente() {return this.idUtente; }
	
	protected int nConto;
	protected int idUtente;
	protected float saldo;
	protected ArrayList<Operazioni> listaMovimenti = new ArrayList<Operazioni>();
	protected Operazioni operazioni;
	private ContoMemento contoMemento;
	
}
