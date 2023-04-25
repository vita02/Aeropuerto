package modelo;

public class Charter extends Vuelo{

    private String NIF;

    public Charter(String destino, String modelo, int numPlazas, String NIF) {
        super(destino, modelo, numPlazas);
        this.NIF = NIF;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Charter{");
        sb.append("NIF='").append(NIF).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
