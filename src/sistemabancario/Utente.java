package sistemabancario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

public class Utente implements Login{
	public Utente() {}
	
	// ritorna tutte le info delle tabelle esterne
	public Utente(int idUtente) {
		this.idUtente = idUtente;
		try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  
			Statement stmt=con.createStatement();  
			String query = "select * from contoCorrente where idUtente = '" + idUtente +  "'";
			System.out.println("query: " + query);
			ResultSet rs=stmt.executeQuery(query);  
			
			if(rs.next()) {
				nConto = rs.getInt(1);
				saldo  = rs.getFloat(2);
				tipoConto = rs.getString(3);
			}
				
			
			con.close();	  
		} catch(Exception e) {System.out.println("errore login utente"); System.out.println(e);}
		
	}
    public Utente(int idUtente, String password) {   // costruttore per accedere alle info

        this.idUtente = idUtente;
        this.password = password;
    }

    public Utente(String password, String nome, String cognome, String dataNascita, String via, String citta) {    // costruttore per la fase di registrazione
  	
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.via = via;
        this.citta = citta;
    }
    
    public Utente(int idUtente, String nome, String cognome, String dataNascita, String via, String citta) {    // costruttore per la creazione di oggetti nella HashMap

        this.idUtente = idUtente; 
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.via = via;
        this.citta = citta;
    }

    @Override
    public boolean Accedi(String user, String passwd) {
    	boolean ris = false;
    	try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  
			Statement stmt=con.createStatement();  
			String query = "select * from utente where idUtente = '" + user + "' AND password = '" + passwd + "'";
			System.out.println("query: " + query);
			ResultSet rs=stmt.executeQuery(query);  
			
			if(rs.next())  
				ris = true;
			else
				ris = false;
			
			con.close();	  
		} catch(Exception e) {System.out.println("errore login utente"); System.out.println(e);}
        System.out.println("sono un Utente\n");
        
        return ris;
    }
    
    public void getCarte() {
    	CartaDiCredito cartadicredito = new CartaDiCredito(idUtente);
    	
    	
    }
    
    public String getNome() {return this.nome;}
    public String getCognome() {return this.cognome;}
    public String getDataNascita() {return this.dataNascita;}
    public String getVia() {return this.via;}
    public String getCitta() {return this.citta;}
    public int getIdUtente() {return this.idUtente;}
    public String getPassword() {return this.password;}
    
    public String getTipoConto() { return this.tipoConto; }
    public float getSaldo() { return this.saldo; }
    
    public int getNConto() {return this.nConto;}
    
    public float getSaldoPrepagata() {
    	float saldoP = -1.1f;;
    	try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  
			Statement stmt=con.createStatement();  
			String query = "select * from cartaPrepagata where idUtente = '" + idUtente + "'";
			System.out.println("query: " + query);
			ResultSet rs=stmt.executeQuery(query);  
			
			
			if(rs.next())  
				saldoP = rs.getFloat(5);
			
			con.close();	  
		} catch(Exception e) {System.out.println("errore query saldo prepagata"); System.out.println(e);}
    	return saldoP;
    }
    
    public void setIdUtente(int idUtente) {
    	this.idUtente = idUtente;
    }
    
    public boolean Acquista(Pagamento carta, float importo) {
    	boolean pagato = carta.paga(importo);
    	
    	return pagato;
    }
    
    public void Investe(float investimento, String responso) {
    	// salva il movimento
    	// aggiorna il saldo nel conto corrente
    	// elimina l'investimento consigliato
    	
    	try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  		
			 // the mysql insert statement
			  String query = " insert into movimenti (Operazione, importo, idUtente, nConto)"
			+ " values (?, ?, ?, ?)";
			  
			  int tipoOperazione = -1;
			  if(responso.equals("riuscito"))
				  tipoOperazione = 3;
			  else if(responso.equals("fallito"))
				  tipoOperazione = 4;
			  
			  // create the mysql insert preparedstatement
			  PreparedStatement preparedStmt = con.prepareStatement(query);
			  preparedStmt.setInt (1, tipoOperazione);
			  preparedStmt.setFloat (2, investimento);
			  preparedStmt.setInt (3, idUtente);
			  preparedStmt.setInt (4, nConto);
			  
		      // execute the preparedstatement
		      preparedStmt.execute();
		      
		      System.out.println("Operazione eseguita!\n");
		      
		      con.close();
    	} catch(Exception e) {System.out.println("errore inserimento movimento utente"); System.out.println(e); }
    	
    	float nuovoSaldo = -1f;
		  if(responso.equals("riuscito"))
			  nuovoSaldo = this.saldo + investimento;
		  else if(responso.equals("fallito"))
			  nuovoSaldo = this.saldo - investimento;
    	try
 	    {
 	    	Class.forName("com.mysql.cj.jdbc.Driver");  
 	    	Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
 		      Statement stmt=conn.createStatement();
 		      // create the java mysql update preparedstatement
 		      String query = "update contoCorrente set saldo = '" + nuovoSaldo + "' where nConto = '" + nConto + "'";
 		      System.out.println("query per update: " + query);
 		      // execute the java preparedstatement
 		      stmt.executeUpdate(query);
 		      
 		      conn.close();
 	    }
 	    catch (Exception e) { System.err.println("Got an exception! "); System.err.println(e.getMessage()); }
    	
    	try
 	    {
 	    	Class.forName("com.mysql.cj.jdbc.Driver");  
 	    	Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
 		      Statement stmt=conn.createStatement();
 		      // create the java mysql update preparedstatement
 		      String query = "delete from investimento where idUtente = " + idUtente;
 		      System.out.println("query per update: " + query);
 		      // execute the java preparedstatement
 		      stmt.executeUpdate(query);
 		      
 		      conn.close();
 	    }
 	    catch (Exception e) { System.err.println("Got an exception! "); System.err.println(e.getMessage()); }
    	 
    	 
    }

    public void test() {System.out.println("funzione di test dell'utente");}

    private String password, nome, cognome, dataNascita, via, citta;
    private int idUtente;
    private Pagamento listaCarte;
    private float saldo;
    private String tipoConto;
    private int nConto;
}
