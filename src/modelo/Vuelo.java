package modelo;

public class Vuelo implements Comparable<Vuelo>{

    private String destino;
    private String modelo;
    private int numPlazas;

    public Vuelo(String destino, String modelo, int numPlazas) {
        this.destino = destino;
        this.modelo = modelo;
        this.numPlazas = numPlazas;
    }

    public String getDestino() {
        return destino;
    }

    public String getModelo() {
        return modelo;
    }

    public int getNumPlazas() {
        return numPlazas;
    }

    @Override
    public int compareTo(Vuelo o) {
        return this.destino.compareToIgnoreCase(o.getDestino());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vuelo{");
        sb.append("destino='").append(destino).append('\'');
        sb.append(", modelo='").append(modelo).append('\'');
        sb.append(", numPlazas=").append(numPlazas);
        sb.append('}');
        return sb.toString();
    }
}
