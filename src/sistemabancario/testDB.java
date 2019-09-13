package sistemabancario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class testDB {
	public static void ConfiguraDB() {
    	try {  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useTimezone=true&serverTimezone=UTC","root","ciao");  
			  
			Statement stmt=con.createStatement();  
			String query = "";
			System.out.println("query: " + query);
			ResultSet rs = stmt.executeQuery(query);  
			
			con.close();	  
		} catch(Exception e) {System.out.println(""); System.out.println(e);}
	}
}

