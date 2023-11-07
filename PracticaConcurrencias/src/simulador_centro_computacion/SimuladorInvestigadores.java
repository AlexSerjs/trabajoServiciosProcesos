package simulador_centro_computacion;

public class SimuladorInvestigadores {
    public static void main(String[] args) {
        int numUsuarios = 10; // You can set the number of users as needed
        int núcleos = 16;     // Total number of CPU cores
        int ram = 32;         // Total amount of RAM in GiB

        Supercomputador supercomputador = new Supercomputador(numUsuarios, núcleos, ram);

        // Create and start user threads
        for (int i = 1; i <= numUsuarios; i++) {
            Usuario usuario = new Usuario(i, supercomputador);
            Thread userThread = new Thread(usuario);
            userThread.start();
        }

        // Monitor the usage until all users have been served
        long startTime = System.currentTimeMillis();
        long maxRuntime = 60000; // Tiempo máximo en milisegundos (60 segundos en este ejemplo)

        while (supercomputador.usuariosServidos() < numUsuarios && (System.currentTimeMillis() - startTime) < maxRuntime) {
            System.out.println("Núcleos disponibles: " + supercomputador.núcleosDisponibles());
            System.out.println("RAM disponible: " + supercomputador.ramDisponible());
            System.out.println("Usuarios actuales: " + supercomputador.usuariosActuales());
            System.out.println("Usuarios servidos: " + supercomputador.usuariosServidos());
            System.out.println("-----------------------------------");

            try {
                Thread.sleep(1000); // Intervalo de monitoreo de 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
