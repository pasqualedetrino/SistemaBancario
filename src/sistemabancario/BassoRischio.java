package sistemabancario;
public class BassoRischio extends Investimento{
	@Override
	public void investi(float importo, String tipoInvestimento) {
		if(!tipoInvestimento.equals("basso"))
			investimento.investi(importo, tipoInvestimento);
		
		/*
		 * tiro un numero random in un intervallo [0; 10] se tale numero è >= del richio aggiungo all'importo investito il rischio altrimenti lo sottraggo
		 */
		else {
			int investimento = (int)(Math.random() * 11);
			
			if(investimento >= rischio)
				importo += (importo*rischio/100);
			else
				importo -= (importo*rischio/100);
			
			System.out.println("importo dopo investito : " + importo);
			risultato = importo;
		}
	}
	
	private int rischio = 2;
}
