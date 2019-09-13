package sistemabancario;

public class DecoratorContoMedio extends DecoratorConto {
	
	public DecoratorContoMedio(ContoCorrente conto) {
		this.conto = conto;
	}
	
	@Override
	public boolean versa(float importo) {		
		return this.conto.versa(importo );
	}
		
	@Override
	public boolean preleva(float importo) {
		float sconto = importo/100 * 5;
		
		return this.conto.preleva(importo - sconto);
	}
	
	
	protected ContoCorrente conto;
	
}
