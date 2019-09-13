package servletClass;

public class RispostaRicerca {
	public RispostaRicerca(String dataNascita, String via, String citta, String nConto, float saldo, String tipoConto, int idUtente) {
		this.dataNascita = dataNascita;
		this.via = via;
		this.citta = citta;
		this.nConto = nConto;
		this.saldo = saldo;
		this.tipoConto = tipoConto;
		this.idUtente = idUtente;
	}
	
	public String getDataNascita() { return this.dataNascita; }
	public String getVia() { return this.via; }
	public String getCitta() { return this.citta; }
	public String getNConto() { return this.nConto; }
	public String getTipoConto() { return this.tipoConto; }
	public float getSaldo() { return this.saldo; }
	public int getIdUtente() {return this.idUtente; }
	
	private String dataNascita, via, citta, nConto, tipoConto;
	private float saldo;
	private int idUtente;

}
