package modelo;

public class Regular extends Vuelo {

    private int numPlazasLibres;
    private static final double COEFICIENTE = 1.1;
    private static final int PRECIO_PLAZA = 5;


    public Regular(String destino, String modelo, int numPlazas, int numPlazasLibres, int precio) {
        super(destino, modelo, numPlazas, precio);
        this.numPlazasLibres = numPlazasLibres;
    }

    public int getNumPlazasLibres() {
        return numPlazasLibres;
    }

    public void setNumPlazasLibres(int numPlazasLibres) {
        this.numPlazasLibres = numPlazasLibres;
    }

    @Override
    public int getNumPasajeros() {
        return getNumPlazas() - getNumPlazasLibres();
    }

    @Override
    public double calcularPrecio(){
        return getPrecio() * COEFICIENTE + (PRECIO_PLAZA * getNumPlazasLibres());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nVuelo Regular\n------------");
        sb.append(super.toString());
        sb.append("\nPrecio billete: ").append(calcularPrecio()).append(" €");
        sb.append("\nPlazas Libres: ").append(numPlazasLibres);
        return sb.toString();
    }
}
