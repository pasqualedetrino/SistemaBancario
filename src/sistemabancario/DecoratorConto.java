package sistemabancario;

public class DecoratorConto extends ContoCorrente{
	
	public DecoratorConto(ContoCorrente conto) {
		this.conto = conto;
	}
	
	@Override
	public boolean versa(float importo) {
		float sconto = importo/100 * 3;
		return this.conto.versa(importo + sconto);
	}
	
	@Override
	public boolean preleva(float importo) {
		float sconto = importo/100 * 3;
		System.out.println("\n\ndecoro------------------");
		return this.conto.preleva(importo - sconto);
	}
	
	
	protected ContoCorrente conto;
	
}
