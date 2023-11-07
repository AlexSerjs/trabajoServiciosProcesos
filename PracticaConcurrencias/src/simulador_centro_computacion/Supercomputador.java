package simulador_centro_computacion;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Supercomputador {
    private int núcleosDisponibles; // Núcleos disponibles
    private int ramDisponible; // RAM disponible en GiB
    private AtomicInteger servidos; // Usuarios servidos
    private Semaphore acceso; // Control de acceso
    private final int maxUsuarios; // Máximos usuarios concurrentes

    public Supercomputador(int numUsuarios, int núcleos, int ram) {
        this.núcleosDisponibles = núcleos;
        this.ramDisponible = ram;
        this.servidos = new AtomicInteger(0);
        this.acceso = new Semaphore(numUsuarios);
        this.maxUsuarios = numUsuarios;
    }

    public void entrar() throws InterruptedException {
        // Implement the logic for a user to enter the computer center's room
        acceso.acquire();
    }

    public void salir() throws InterruptedException {
        // Implement the logic for a user to leave the computer center's room
        acceso.release();
    }

    public void obtenerMV(int nucleos, int ram) throws InterruptedException {
        // Implement the logic to request and obtain a virtual machine
        if (nucleos <= núcleosDisponibles && ram <= ramDisponible) {
            núcleosDisponibles -= nucleos;
            ramDisponible -= ram;
        } else {
            // If resources are not available, the user should wait
            TimeUnit.SECONDS.sleep(1); // You can adjust the sleep duration as needed
        }
    }

    public void devolverMV(int nucleos, int ram) {
        // Implement the logic to return a virtual machine and release its resources
        núcleosDisponibles += nucleos;
        ramDisponible += ram;
    }

    public int núcleosDisponibles() {
        return núcleosDisponibles;
    }

    public int ramDisponible() {
        return ramDisponible;
    }

    public int usuariosActuales() {
        return maxUsuarios - acceso.availablePermits();
    }

    public int usuariosServidos() {
        return servidos.get();
    }
}
