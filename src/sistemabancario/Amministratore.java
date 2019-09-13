package sistemabancario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Amministratore implements  Login{
    public Amministratore(String username, String password) {
        //
        // se va bene il caricamento dei dati dal db allora

        this.username = username;
        this.password = password;
    }

    @Override
    public boolean Accedi(String user, String passwd) {
    	    	
    	boolean ris = false;
    	try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  
			Statement stmt=con.createStatement();  
			String query = "select * from amministratore where username = '" + user + "' AND password = '" + passwd + "'";
			System.out.println("query: " + query);
			ResultSet rs=stmt.executeQuery(query);  
			
			if(rs.next())  
				ris = true;
			else
				ris = false;
			
			con.close();	  
		} catch(Exception e) {System.out.println("errore login amministratore"); System.out.println(e);}
        System.out.println("sono un amministratore\n");
        
        System.out.println("ris  " + ris);
        
        return ris;
    }
    
    /*
     * 	DA STABILIRNE LA COLLOCAZIONE
     */
	private void registra() {
		try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  		
			 // the mysql insert statement
			  String query = " insert into amministratore (username, password)"
			+ " values (?, ?)";
			
			  // create the mysql insert preparedstatement
			  PreparedStatement preparedStmt = con.prepareStatement(query);
			  preparedStmt.setString (1, username);
			  preparedStmt.setString (2, password);
			  
		      // execute the preparedstatement
		      preparedStmt.execute();
		      
		      System.out.println("Amministratore registrato!\n");
			con.close();	  
		} catch(Exception e) {System.out.println("errore registrazione amministratore"); System.out.println(e);}
	}

    private String username, password;

}
