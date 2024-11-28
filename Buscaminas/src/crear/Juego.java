package crear;


import java.util.Scanner;

public class Juego {
    private Tablero tablero;
    private boolean juegoTerminado;

    public void iniciar() {
        tablero = new Tablero(); // Crea el tablero al inicio del juego
        Scanner scanner = new Scanner(System.in);
        juegoTerminado = false;

        while (!juegoTerminado) {
            tablero.mostrarTablero(); // Muestra el estado actual del tablero
            System.out.println("¿Qué acción deseas realizar?");
            System.out.println("[1] Descubrir casilla");
            System.out.println("[2] Marcar casilla");

            int opcion = scanner.nextInt(); // Captura la opción del jugador
            System.out.print("Introduce la coordenada (Ejemplo: A5): ");
            String entrada = scanner.next();

            int fila = entrada.charAt(0) - 'A'; // Convierte la fila (letra) a índice
            int columna = Integer.parseInt(entrada.substring(1)) - 1; // Convierte la columna (número)

            try {
                if (opcion == 1) {
                    tablero.descubrirCasilla(fila, columna); // Descubre la casilla
                } else if (opcion == 2) {
                    tablero.marcarCasilla(fila, columna); // Marca la casilla
                } else {
                    System.out.println("Opción inválida. Intenta de nuevo.");
                }

                // Verifica si el jugador ha ganado
                if (tablero.verificarVictoria()) {
                    System.out.println("¡Felicidades! ¡Ganaste!");
                    juegoTerminado = true;
                }
            } catch (MinaException e) {
                System.out.println(e.getMessage()); // Si pisa una mina
                juegoTerminado = true;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Gracias por jugar.");
    }
}

