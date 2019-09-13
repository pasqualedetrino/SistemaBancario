package sistemabancario;

public class DecoratorContoAvanzato extends DecoratorConto {
	
	public DecoratorContoAvanzato(ContoCorrente conto) {
		this.conto = conto;
	}
	
	@Override
	public boolean versa(float importo) {		
		return this.conto.versa(importo );
	}
	
	@Override
	public boolean preleva(float importo) {
		float sconto = importo/100 * 10;
		
		return this.conto.preleva(importo - sconto);
	}
	
	
	protected ContoCorrente conto;
	
}
