package modelo;

import java.util.Comparator;

public class ComparadorNumPlazas implements Comparator<Regular> {

    @Override
    public int compare(Regular o1, Regular o2) {
        return o2.getNumPlazasLibres() - o1.getNumPlazasLibres();
    }
}
