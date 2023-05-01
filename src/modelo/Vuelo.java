package modelo;

public class Vuelo implements Comparable<Vuelo>{

    private String destino;
    private String modelo;
    private int numPlazas;
    private int precio;

    public Vuelo(String destino, String modelo, int numPlazas, int precio) {
        this.destino = destino;
        this.modelo = modelo;
        this.numPlazas = numPlazas;
        this.precio = precio;
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

    public int getNumPasajeros(){
        return 0;
    }

    public int getPrecio(){
        return precio;
    }

    public double calcularPrecio(){
        return 0.0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nDestino: ").append(destino);
        sb.append("\nAvion: ").append(modelo);
        sb.append("\nPlazas: ").append(numPlazas);
        return sb.toString();
    }

    @Override
    public int compareTo(Vuelo o){
        if (this.getDestino().compareTo(o.getDestino()) == 0){
            if (this.getModelo().compareTo(o.getModelo()) == 0){
                if (this.getNumPlazas() == o.getNumPlazas()){
                    return 0;
                }
                return (this.getNumPlazas() - o.getNumPlazas());
            }
            return (this.getModelo().compareTo(o.getModelo()));
        }
        return (this.getDestino().compareTo(o.getDestino()));
    }
}
