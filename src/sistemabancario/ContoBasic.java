package sistemabancario;
public class ContoBasic extends ContoCorrente{
	private  ContoBasic() {}
	private ContoBasic(int idUtente) {
		super(idUtente);
		super.salvaOperazioneDB();
	}
	
	public static ContoBasic getInstance()  {
		if(contoBasic == null)
			contoBasic = new ContoBasic();
		return contoBasic;
	}
	
	public static ContoBasic getInstance(int idUtente)  {
		if(contoBasic == null)
			contoBasic = new ContoBasic(idUtente);
		return contoBasic;
	}
	
	@Override
	public boolean versa(float importo) {
		
			super.saldo += importo;
			
			super.operazioni = new Operazioni("versamento", importo);
			super.salvaOperazione(super.operazioni);
	
		return true;
	}
	
	@Override
	public boolean preleva(float importo) {
		if(importo <= super.saldo && importo < limite) {
			super.saldo -= importo;
			
			super.operazioni = new Operazioni("prelievo", importo);
			super.salvaOperazione(super.operazioni);
		}
		
		else
			return false;
		return true;
		
	}
	
	private float limite = 200.0f;
	
	private static ContoBasic contoBasic = null;
	
}
