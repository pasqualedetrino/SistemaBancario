package sistemabancario;
public class ContoMemento implements Memento {
	public ContoMemento(float saldo) {
		this.mem_saldo = saldo;
	
	}
	
	@Override
	public float annullaOperazione() {
		return this.mem_saldo;
	}
	
	private float mem_saldo;

}
