package sistemabancario;

public abstract class Investimento {
	public abstract void investi(float importo, String tipoInvestimento);
	
	public void setSuccessor(Investimento investimento) {
		this.investimento = investimento;
	}
	
	public static float getRisultato() { return risultato; }
	
	protected Investimento investimento;
	protected static float risultato;
}
