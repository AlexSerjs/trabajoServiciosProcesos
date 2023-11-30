package pregunta2;


public class Cliente implements Runnable {
	
	private final Cuenta2 cc;
	
	public Cliente(Cuenta2 cc) {
		this.cc=cc;
	}
	@Override public void run() {
		espera();
		try {
			cc.reintegro(1);
		} catch (SaldoInsuficienteException e) {
			System.out.println("Saldo insuficiente");
		}
		espera ();
		cc.ingreso(1);
	}
	
	private void espera () {
		try {
		Thread.sleep((long) (100 * Math.random()));
		} catch (InterruptedException ignorada) { }
		}

}



