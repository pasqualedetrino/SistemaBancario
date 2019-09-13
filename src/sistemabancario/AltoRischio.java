package sistemabancario;
public class AltoRischio extends Investimento{
	@Override
	public void investi(float importo, String tipoInvestimento) {
		if(!tipoInvestimento.equals("alto")) 
			investimento.investi(importo, tipoInvestimento);
		
		else {
			/*
			 * tiro un numero random in un intervallo [0; 20] se tale numero è >= del richio aggiungo all'importo investito il rischio altrimenti lo sottraggo
			 */
			int investimento = (int)(Math.random() * 21);
			
			if(investimento >= rischio)
				importo += (importo*rischio/100);
			else
				importo -= (importo*rischio/100);
			
			
			risultato = importo;
		}
	}
	
	private int rischio = 10;
}
