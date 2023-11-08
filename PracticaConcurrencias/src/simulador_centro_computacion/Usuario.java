package simulador_centro_computacion;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Usuario implements Runnable {

    private static final Random rand = new Random();
    private final int id;
    private final Supercomputador sc;

    private int núcleos;
    private int memoria;

    /**
     * @param supercomputador
     */
    public Usuario(int id, Supercomputador sc) {
        this.id = id;
        this.sc = sc;
    }

    /**
     * Método que permite simular el tiempo de una máquina virtual.
     * Condiciones de uso:
     * - El Usuario ha accedido a la sala del Supercomputador.
     * - El Usuario ha conseguido una MV con las características solicitadas
     */
    private void usarMV() {
        try {
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(3000));
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void run() {
        final int núcleos = rand.nextInt(4); /* núcleos de la MV */
        final int memoria = rand.nextInt(8); /* memoria de la MV */
        try {
            sc.entrar();
            System.out.println("el usuario " + id + " ha entrado");
            try {
                sc.obtenerMV(núcleos, memoria);
                this.núcleos = núcleos;
                this.memoria = memoria;
                System.out.println("usuario " + id + " usando MV");
                usarMV();
                sc.devolverMV(núcleos, memoria);
                System.out.println("usuario " + id + " devolvió MV");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } finally {
                sc.salir();
                System.out.println("usuario " + id + " salió");
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getNúcleos() {
        return núcleos;
    }

    public int getMemoria() {
        return memoria;
    }
}
