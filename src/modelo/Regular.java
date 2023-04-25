package modelo;

public class Regular extends Vuelo{

    private int numPlazasLibres;

    public Regular(String destino, String modelo, int numPlazas, int numPlazasLibres) {
        super(destino, modelo, numPlazas);
        this.numPlazasLibres = numPlazasLibres;
    }

    public int getNumPlazasLibres() {
        return numPlazasLibres;
    }

    public void setNumPlazasLibres(int numPlazasLibres) {
        this.numPlazasLibres = numPlazasLibres;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Regular{");
        sb.append("numPlazasLibres=").append(numPlazasLibres);
        sb.append('}');
        return sb.toString();
    }
}
