package sistemabancario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CartaDiCredito implements Pagamento{
	public CartaDiCredito(int idUtente) {
		this.idUtente = idUtente;
		// preso in input l'id dell'utente ritorna la carta che esso possiende
    	try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  
			Statement stmt=con.createStatement();  
			String query = "select * from cartaDiCredito where idUtente = '" + idUtente + "'";
			System.out.println("query: " + query);
			ResultSet rs=stmt.executeQuery(query);  
			
			if(rs.next()) {
				numeroCarta = rs.getString(1);
				cvv2 = rs.getString(2);
				scadenza = rs.getString(3);
			}
			
			con.close();	  
		} catch(Exception e) {System.out.println("l'utente non ha nessuna carta prepagata"); System.out.println(e);}
        System.out.println("sono un Utente\n");
            
    }
			
	@Override
	public boolean paga(float importo) {
		
		float saldo = -1;
		
		try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  
			Statement stmt=con.createStatement();  
			String query = "select * from contoCorrente where idUtente = '" + idUtente + "'";
			System.out.println("query: " + query);
			ResultSet rs=stmt.executeQuery(query);  
			
			if(rs.next())
				saldo = rs.getInt(2);
			
			con.close();	  
		} catch(Exception e) {System.out.println("l'utente non ha nessuna carta prepagata"); System.out.println(e);}
		
		
		System.out.println("il saldo letto vale " + saldo);
		
		if(importo <= saldo)
			saldo -= importo;
		else
			return false;
		return true;
	}
	
	public String getCvv2() {return cvv2; }
	public String getScadenza() {return scadenza; }
	public String getnumeroCarta() {return numeroCarta; }
	
	public static void creaCartaDiCredito(int idUtente, int nConto) {
		int cvv2 = (int)(Math.random()*9999);
		int giorno = (int)(Math.random()*30);
		int mese = (int)(Math.random()*12);
		int anno = 2024;
		
		String scadenza = Integer.toString(giorno) + "/"+ Integer.toString(mese) + "/" + Integer.toString(anno);
		
    	try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  		
			 // the mysql insert statement
			  String query = " insert into cartaDiCredito (cvv2, scadenza, idUtente, nConto)" + " values (?, ?, ?, ?)";
			
			  // create the mysql insert preparedstatement
			  PreparedStatement preparedStmt = con.prepareStatement(query);
			  preparedStmt.setInt (1, cvv2);
			  preparedStmt.setString (2, scadenza);
			  preparedStmt.setInt (3, idUtente);
			  preparedStmt.setInt (4, nConto);
			  
		      // execute the preparedstatement
		      preparedStmt.execute();
		      
		      System.out.println("carta di credito inserita!\n");
			con.close();	  
		} catch(Exception e) {System.out.println("errore inserimento carta di credito"); System.out.println(e);}
	}
	
	private String cvv2, scadenza, numeroCarta;
	private int idUtente;
	 
}
