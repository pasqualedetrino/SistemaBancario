package sistemabancario;
public class LoginFactory {
    /* a seconda della modalità  di accesso selezionata si instanzia un oggetto della classe opportuna */
    public static Login getLogin(String username, String password, String accesso) {
    	    	
    	if(accesso == "utente")
            return new Utente(Integer.valueOf(username), password);
        else if(accesso == "amministratore")
            return new Amministratore(username, password);
        else
            return null;
    }
}
