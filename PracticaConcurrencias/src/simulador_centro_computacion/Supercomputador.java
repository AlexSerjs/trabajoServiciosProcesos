package simulador_centro_computacion;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Semaphore;

/**
 * Curso 2023-24 - Práctica PSP
 * 
 * @authores:
 *
 *            Supercomputador científico
 */
public class Supercomputador {

	private int núcleosDisponibles; // núcleos disponibles
	private int ramDisponible; // RAM disponible en GiB
	private AtomicInteger servidos; // usuarios servidos
	private Semaphore acceso; // control de acceso
	private final int maxUsuarios; // máximos usuarios concurrentes

	/**
	 * Constructor
	 * 
	 * @param numUsuarios número máximo de usuarios concurrentes
	 * @param núcleos     número de núcleos que tiene el Supercomputador
	 * @param ram         RAM que tiene el Supercomputador
	 */
	   public Supercomputador(int maxUsuarios, int núcleos, int ram) {
	        this.núcleosDisponibles = núcleos;
	        this.ramDisponible = ram * 1024; // pasamos de GiB a bytes
	        this.servidos = new AtomicInteger(0);
	        this.acceso = new Semaphore(maxUsuarios);
	        this.maxUsuarios = maxUsuarios;
	    }

	/**
	 * Método entrar() que deberá ser invocado por un Usuario para poder acceder a
	 * la sala del Supercomputador La acción finalizará cuando se haya completado el
	 * acceso
	 */
	public void entrar() throws InterruptedException {
		acceso.acquire();
	}

	/**
	 * Método salir() que deberá ser invocado por un Usuario para poder salir de la
	 * sala del Supercomputador La acción finalizará cuando se haya completado la
	 * salida
	 */
	public void salir() {
		acceso.release();
	}

	/**
	 * Método obtenerMV ( int núcleos, int ram ), que deberá ser invocado por un
	 * Usuario para solicitar una MV La acción finalizará cuando se pueda crear una
	 * MV con los núcleos y memoria solicitada
	 * 
	 * @param nucleos número de núcleos que deberá tener la MV solicitada
	 * @param ram     número de memoria RAM que deberá tener la MV solicitada
	 */
	public void obtenerMV(int núcleos, int memoria) throws InterruptedException {

		// Comprobar que hay suficientes recursos disponibles
		if (núcleos > núcleosDisponibles || memoria > ramDisponible) {
			throw new InterruptedException("No hay suficientes recursos disponibles");
		}

		// Reducir los recursos disponibles
		núcleosDisponibles -= núcleos;
		ramDisponible -= memoria;

		// Notificar a los usuarios que hay una máquina virtual disponible
		acceso.release();
	}

	/**
	 * Método devolverMV (int núcleos, int ram ) que deberá ser invocado por un
	 * Usuario para liberar una MV
	 * 
	 * @param nucleos número de núcleos de la MV que se libera
	 * @param ram     número de memoria RAM de la MV que se libera
	 */
	public void devolverMV(int núcleos, int memoria) {

		// Aumentar los recursos disponibles
		núcleosDisponibles += núcleos;
		ramDisponible += memoria;

		// Notificar a los usuarios que hay una máquina virtual disponible
		acceso.release();
	}

	/**
	 * Método núcleosDisponibles()
	 * 
	 * @return número de núcleos disponibles
	 */
	public int núcleosDisponibles() {
		return núcleosDisponibles;
	}

	/**
	 * Método ramDisponible()
	 * 
	 * @return RAM disponible
	 */
	public long ramDisponible() {
		return ramDisponible;
	}

	/**
	 * Método usuariosActuales()
	 * 
	 * @return número de usuarios actuales usando el Supercomputador
	 */
	public int usuariosActuales() {
		return servidos.get();
	}

	public int usuariosServidos() {
	    return servidos.get();
	}

	public int getMaxUsuarios() {
		return maxUsuarios;
	}


}
