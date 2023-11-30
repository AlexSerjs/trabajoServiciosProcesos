package pregunta2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cuenta2 {
    private int saldo;
    private final Lock lock = new ReentrantLock();
    private boolean embargada = false;

    public Cuenta2(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void reintegro(int cantidad) {
        lock.lock(); // Adquiere el cerrojo
        try {
            if (!embargada) {
                if (cantidad <= saldo) {
                    saldo -= cantidad;
                    System.out.println("Reintegro de " + cantidad + " realizado. Saldo actual: " + saldo);
                } else {
                    System.out.println("Saldo insuficiente para el reintegro.");
                }
            } else {
                System.out.println("La cuenta está embargada. No se puede realizar el reintegro.");
            }
        } finally {
            lock.unlock(); // Libera el cerrojo
        }
    }

    public void ingreso(int cantidad) {
        lock.lock(); // Adquiere el cerrojo
        try {
            saldo += cantidad;
            System.out.println("Ingreso de " + cantidad + " realizado. Saldo actual: " + saldo);
        } finally {
            lock.unlock(); // Libera el cerrojo
        }
    }

    public void embargar() {
        lock.lock(); // Adquiere el cerrojo
        try {
            embargada = true;
            System.out.println("La cuenta ha sido embargada. No se puede realizar reintegro.");
        } finally {
            lock.unlock(); // Libera el cerrojo
        }
    }

    public void desEmbargar() {
        lock.lock(); // Adquiere el cerrojo
        try {
            embargada = false;
            System.out.println("La cuenta ha sido desembargada. Se permite realizar reintegros.");
        } finally {
            lock.unlock(); // Libera el cerrojo
        }
    }

    public int getSaldo() {
        return saldo;
    }
}
