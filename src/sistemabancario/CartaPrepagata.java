package sistemabancario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CartaPrepagata implements Pagamento{
	public CartaPrepagata(int idUtente) {
		// preso in input l'id dell'utente ritorna la carta che esso possiende
    	try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  
			Statement stmt=con.createStatement();  
			String query = "select * from cartaPrepagata where idUtente = '" + idUtente + "'";
			System.out.println("query: " + query);
			ResultSet rs=stmt.executeQuery(query);  
			
			while(rs.next()) {
				numeroCarta = rs.getString(1);
				iban = rs.getString(2);
				cvv2 = rs.getString(3);
				scadenza = rs.getString(4);
				saldo = rs.getFloat(5);
				this.idUtente = idUtente;
			}
			
			con.close();	  
		} catch(Exception e) {System.out.println("l'utente non ha nessuna carta prepagata"); System.out.println(e);}
        System.out.println("sono un Utente\n");
            
    }
	
	public static void creaCartaPrepagata(int idUtente, float saldo) {
		int cvv2 = (int)(Math.random()*9999);
		int giorno = (int)(Math.random()*30);
		int mese = (int)(Math.random()*12);
		int anno = 2024;
		int iban = (int)(Math.random()*99999);
		
		String scadenza = Integer.toString(giorno) + "/"+ Integer.toString(mese) + "/" + Integer.toString(anno);
		
    	try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  		
			 // the mysql insert statement
			  String query = " insert into cartaprepagata (iban, cvv2, scadenza, saldo, idUtente)" + " values (?, ?, ?, ?, ?)";
			
			  // create the mysql insert preparedstatement
			  PreparedStatement preparedStmt = con.prepareStatement(query);
			  preparedStmt.setInt (1, iban);
			  preparedStmt.setInt (2, cvv2);
			  preparedStmt.setString (3, scadenza);
			  preparedStmt.setFloat (4, saldo);
			  preparedStmt.setInt (5, idUtente);
			  
		      // execute the preparedstatement
		      preparedStmt.execute();
		      
		      System.out.println("carta prepagata inserita!\n");
			con.close();	  
		} catch(Exception e) {System.out.println("errore inserimento carta prepagata"); System.out.println(e);}
	}
	
	
	
	public float getSaldo() { return saldo;}
	public void versa(float importo) {
		saldo += importo;
		aggiornaSaldo();
	}
		
	@Override
	public boolean paga(float importo) {
		if(importo <= saldo)  {
			saldo -= importo;
			aggiornaSaldo();
		}
		else
			return false;
		return true;
	}
	
	private void aggiornaSaldo() {
	    try
	    {
	    	Class.forName("com.mysql.cj.jdbc.Driver");  
	    	Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
		      Statement stmt=conn.createStatement();
		      // create the java mysql update preparedstatement
		      String query = "update cartaPrepagata set saldo = '" + saldo + "' where idUtente = '" + idUtente + "'";
		      System.out.println("query per update: " + query);
		      // execute the java preparedstatement
		      stmt.executeUpdate(query);
		      
		      conn.close();
	    }
	    catch (Exception e) { System.err.println("Got an exception! "); System.err.println(e.getMessage()); }
	}
	private String iban, cvv2, scadenza, numeroCarta;
	private float saldo;
	private int idUtente;
}
