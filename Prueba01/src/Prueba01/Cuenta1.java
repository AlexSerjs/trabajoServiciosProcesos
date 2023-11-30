package Prueba01;

public class Cuenta1 {
    private int saldo;

    public Cuenta1(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    // Método para realizar un reintegro
    public  void reintegro(int cantidad) throws SaldoInsuficienteException {
        if (cantidad > saldo) {
            throw new SaldoInsuficienteException();
        } else {
            saldo -= cantidad;
            System.out.println("Reintegro de " + cantidad + " realizado. Saldo actual: " + saldo);
        }
    }

    // Método para realizar un ingreso
    public  void ingreso(int cantidad) {
        saldo += cantidad;
        System.out.println("Ingreso de " + cantidad + " realizado. Saldo actual: " + saldo);
    }

    // Método para obtener el saldo actual
    public  int getSaldo() {
        return saldo;
    }
    
}
