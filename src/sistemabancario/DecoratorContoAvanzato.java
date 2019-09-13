package sistemabancario;

public class DecoratorContoAvanzato extends ContoCorrente implements DecoratorConto {
	
	public DecoratorContoAvanzato(ContoCorrente conto) {
		this.conto = conto;
	}
	
	@Override
	public boolean versa(float importo) {
		float sconto = importo/100 * 10;
		return this.conto.versa(importo + sconto);
	}
	
	@Override
	public boolean preleva(float importo) {
		float sconto = importo/100 * 10;
		System.out.println("\n\ndecoro------------------");
		return this.conto.preleva(importo - sconto);
	}
	
	
	protected ContoCorrente conto;
	
}
