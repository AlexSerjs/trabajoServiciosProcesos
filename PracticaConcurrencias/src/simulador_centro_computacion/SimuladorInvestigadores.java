package simulador_centro_computacion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimuladorInvestigadores {

    private static final int NUM_USUARIOS = 100;
    private static final int NUM_NUCLEOS = 16;
    private static final int RAM_TOTAL = 32;

    private final Supercomputador sc;
    private final List<Usuario> usuarios;
    public static void main(String[] args) {
        SimuladorInvestigadores simulador = new SimuladorInvestigadores();
        simulador.iniciar();
    }

    public SimuladorInvestigadores() {
        this.sc = new Supercomputador(NUM_USUARIOS, NUM_NUCLEOS, RAM_TOTAL);
        this.usuarios = new ArrayList<>();
    }

    public void iniciar() {
        for (int i = 0; i < NUM_USUARIOS; i++) {
            usuarios.add(new Usuario(i, sc));
        }

        for (Usuario usuario : usuarios) {
            new Thread(usuario).start();
        }

        while (sc.usuariosServidos() < NUM_USUARIOS) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
               e.getMessage();
            }
            System.out.println(".".repeat(100));
            System.out.println("Estado del supercomputador:");
            System.out.println("Núcleos disponibles: " + sc.núcleosDisponibles());
            System.out.println("RAM disponible: " + sc.ramDisponible());
            System.out.println("Usuarios actuales: " + sc.usuariosActuales());
            System.out.println("Usuarios servidos: " + sc.usuariosServidos());
            System.out.println(".".repeat(100));
        }

        System.out.println("Fin de la simulación");
    }

}
