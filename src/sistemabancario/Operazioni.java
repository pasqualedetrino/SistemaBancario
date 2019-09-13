package sistemabancario;
public class Operazioni {
	public Operazioni(String operazione, float importo) {
		this.operazione = operazione;
		this.importo = importo;
	}
	
	public String getOperazione() {return operazione;}
	public float getImporto() {return importo;}
	
	private float importo;
	private String operazione;

}

