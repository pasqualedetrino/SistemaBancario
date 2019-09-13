package sistemabancario;
public class ContoPremium extends ContoCorrente {
	private ContoPremium() {}
	private ContoPremium(int idUtente) {
		super(idUtente);
		super.saldo -= costo;
		super.salvaOperazioneDB();
	}
	
	public static ContoPremium getInstance()  {
		if(contoPremium == null)
			contoPremium = new ContoPremium();
		return contoPremium;
	}
	
	public static ContoPremium getInstance(int idUtente)  {
		if(contoPremium == null)
			contoPremium = new ContoPremium(idUtente);
		return contoPremium;
	}
	
	@Override
	public boolean versa(float importo) {
			super.saldo += importo;
			
			super.operazioni = new Operazioni("versamento", importo);
			super.salvaOperazione(operazioni);
			
		return true;
	}
	
	@Override
	public boolean preleva(float importo) {
		if(importo <= super.saldo) {
			super.saldo -= importo;
			System.out.println("sono un conto premium e faccio un prelievo");
			super.operazioni = new Operazioni("prelievo", importo);
			super.salvaOperazione(super.operazioni);
		}
		else
			return false;
		return true;
		
	}
	
	
	private static int costo = 50;
	private static ContoPremium contoPremium = null;
}