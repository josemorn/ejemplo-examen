package crear;

import java.util.Random;

public class Tablero {
    private final int tamaño = 10;
    private final int numMinas = 10;
    private Casilla[][] casillas;

    public Tablero() {
        casillas = new Casilla[tamaño][tamaño];
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                casillas[i][j] = new Casilla();
            }
        }
        colocarMinas();
        calcularMinasAdyacentes();
    }

    private void colocarMinas() {
        Random random = new Random();
        int minasColocadas = 0;

        while (minasColocadas < numMinas) {
            int fila = random.nextInt(tamaño);
            int columna = random.nextInt(tamaño);

            if (!casillas[fila][columna].tieneMina()) {
                casillas[fila][columna].colocarMina();
                minasColocadas++;
            }
        }
    }

    private void calcularMinasAdyacentes() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (!casillas[i][j].tieneMina()) {
                    int minas = contarMinasAdyacentes(i, j);
                    casillas[i][j].setMinasAdyacentes(minas);
                }
            }
        }
    }

    private int contarMinasAdyacentes(int fila, int columna) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        int minas = 0;

        for (int k = 0; k < 8; k++) {
            int nx = fila + dx[k];
            int ny = columna + dy[k];

            if (nx >= 0 && nx < tamaño && ny >= 0 && ny < tamaño && casillas[nx][ny].tieneMina()) {
                minas++;
            }
        }

        return minas;
    }

    public void descubrirCasilla(int fila, int columna) throws MinaException {
        if (casillas[fila][columna].estaDescubierta()) {
            throw new IllegalStateException("Casilla ya descubierta.");
        }

        casillas[fila][columna].descubrir();

        if (casillas[fila][columna].tieneMina()) {
            throw new MinaException("¡Has pisado una mina!");
        }

        if (casillas[fila][columna].getMinasAdyacentes() == 0) {
            descubrirAdyacentes(fila, columna);
        }
    }

    private void descubrirAdyacentes(int fila, int columna) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int k = 0; k < 8; k++) {
            int nx = fila + dx[k];
            int ny = columna + dy[k];

            if (nx >= 0 && nx < tamaño && ny >= 0 && ny < tamaño && !casillas[nx][ny].estaDescubierta()) {
                try {
                    descubrirCasilla(nx, ny);
                } catch (MinaException e) {
                    // Ignorar si hay una mina adyacente
                }
            }
        }
    }

    public void marcarCasilla(int fila, int columna) {
        casillas[fila][columna].marcar();
    }

    public void mostrarTablero() {
        System.out.print("   ");
        for (int i = 1; i <= tamaño; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < tamaño; i++) {
            System.out.print((char) ('A' + i) + "  ");
            for (int j = 0; j < tamaño; j++) {
                System.out.print(casillas[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean verificarVictoria() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (!casillas[i][j].estaDescubierta() && !casillas[i][j].tieneMina()) {
                    return false;
                }
            }
        }
        return true;
    }

	public boolean verificarVictoria1() {
		// TODO Auto-generated method stub
		return false;
	}
}
