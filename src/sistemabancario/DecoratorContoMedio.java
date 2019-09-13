package sistemabancario;

public class DecoratorContoMedio extends ContoCorrente implements DecoratorConto {
	
	public DecoratorContoMedio(ContoCorrente conto) {
		this.conto = conto;
	}
	
	@Override
	public boolean versa(float importo) {
		float sconto = importo/100 * 5;
		return this.conto.versa(importo + sconto);
	}
	
	@Override
	public boolean preleva(float importo) {
		float sconto = importo/100 * 5;
		System.out.println("\n\ndecoro------------------");
		return this.conto.preleva(importo - sconto);
	}
	
	
	protected ContoCorrente conto;
	
}
