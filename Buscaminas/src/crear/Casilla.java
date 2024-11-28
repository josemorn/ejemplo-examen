package crear;


public class Casilla {
    private boolean mina;
    private boolean descubierta;
    private boolean marcada;
    private int minasAdyacentes;

    public Casilla() {
        this.mina = false;
        this.descubierta = false;
        this.marcada = false;
        this.minasAdyacentes = 0;
    }

    public boolean tieneMina() {
        return mina;
    }

    public void colocarMina() {
        this.mina = true;
    }

    public boolean estaDescubierta() {
        return descubierta;
    }

    public void descubrir() {
        this.descubierta = true;
    }

    public boolean estaMarcada() {
        return marcada;
    }

    public void marcar() {
        this.marcada = !this.marcada;
    }

    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    public void setMinasAdyacentes(int minasAdyacentes) {
        this.minasAdyacentes = minasAdyacentes;
    }

    @Override
    public String toString() {
        if (marcada) return "F";
        if (!descubierta) return "-";
        if (mina) return "X";
        return minasAdyacentes > 0 ? String.valueOf(minasAdyacentes) : " ";
    }
}
