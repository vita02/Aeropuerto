package modelo;

public class Charter extends Vuelo{

    private String NIF;
    private static final double COEFICIENTE = 1.25;
    private static final double PRECIO_PLAZAS = 50;



    public Charter(String destino, String modelo, int numPlazas, String NIF, int precio) {
        super(destino, modelo, numPlazas, precio);
        this.NIF = NIF;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    @Override
    public int getNumPasajeros() {
        return getNumPlazas();
    }

    @Override
    public double calcularPrecio(){
        double precioFinal = getPrecio() * COEFICIENTE;
        if (getNumPlazas() < 200){
            precioFinal += PRECIO_PLAZAS;
        }
        return precioFinal;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nVuelo Charter\n------------");
        sb.append(super.toString());
        sb.append("\nPrecio billete: ").append(calcularPrecio()).append(" €");
        sb.append("\nNIF Empresa: ").append(NIF);
        return sb.toString();
    }
}
