package sistemabancario;
public class ContoMedium extends ContoCorrente{
		
		private ContoMedium() {}
		private ContoMedium(int idUtente) {
			super(idUtente);
			super.saldo -= costo;
			super.salvaOperazioneDB();
		}
		
		public static ContoMedium getInstance()  {
			if(contoMedium == null)
				contoMedium = new ContoMedium();
			return contoMedium;
		}
		
		public static ContoMedium getInstance(int idUtente)  {
			if(contoMedium == null)
				contoMedium = new ContoMedium(idUtente);
			return contoMedium;
		}
		
		@Override
		public boolean versa(float importo) {
				super.saldo += importo;
				super.saldo -= importo/100;  // commissione dell' 1%
				
				super.operazioni = new Operazioni("versamento", importo);
				super.salvaOperazione(operazioni);

			return true;
		}
		
		@Override
		public boolean preleva(float importo) {
			if(importo <= super.saldo) {
				super.saldo -= importo;
				super.saldo -= importo/100;	// commissione dell' 1%
				
				super.operazioni = new Operazioni("prelievo", importo);
				super.salvaOperazione(super.operazioni);
			}
			else
				return false;
			return true;
			
		}
		
		private static int costo = 20;
		private static ContoMedium contoMedium = null;
	}
