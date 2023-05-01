package modelo;

import java.util.*;

public class Aeropuerto {

    private Map<String, Set<Vuelo>> vuelos;

    public Aeropuerto() {
        vuelos = new TreeMap<>();
    }

    /**
     * A?ade un vuelo a la aerolinea correspondiente solo en el caso de que el vuelo
     * no estuviese ya introducido, si la aerolinea no existiese se a?ade tanto la
     * aerolinea como el vuelo.
     */
    public void addVuelo(String aerolinea, Vuelo vuelo) {
        if (vuelos.containsKey(aerolinea)) {
            vuelos.get(aerolinea).add(vuelo);
        } else {
            TreeSet<Vuelo> v = new TreeSet<>();
            v.add(vuelo);
            vuelos.put(aerolinea, v);
        }
    }

    /**
     * Imprime los vuelos por cada aerolinea ordenados por destino, tanto aerolineas
     * como vuelos estaran ordenados alfabeticamente (Ver resultados de ejecucion)
     */
    public void ordenAerolineasAlfabetico() {
        System.out.println(this);
    }

    /**
     * Muestra los vuelos regulares de la aerolinea pasada por parametro, se
     * visualizaran de mayor a menor segun el numero de plazas
     *
     * @param aerolinea Aerolinea de la que se imprimiran los vuelos regulares
     */
    public void regularPorPlazas(String aerolinea) {
        Set<Regular> lista = new TreeSet<>(new ComparadorNumPlazas());
        for (Vuelo vuelo : vuelos.get(aerolinea)) {
            if (vuelo instanceof Regular) {
                lista.add((Regular) vuelo);
            }
        }
        for (Regular r : lista) {
            System.out.println(r);
        }
    }

    /**
     * Devuelve una lista con vuelos regulares con plazas libres
     *
     * @return aerolina Aerolina del avion charter con m?s capacidad
     */
    public List<Vuelo> plazasLibres() {
        List<Vuelo> lista = new ArrayList<>();
        for (String str : vuelos.keySet()) {
            for (Vuelo vuelo : vuelos.get(str)) {
                if (vuelo instanceof Regular && ((Regular) vuelo).getNumPlazasLibres() != 0) {
                    lista.add(vuelo);
                }
            }
        }
        return lista;
    }

    /**
     * Muestra el numero de vuelos de cada aerolinea que llegan al destino pasado
     * por parametro, ver resultados de ejecucion
     *
     * @param destino Destino del que se debe sacar la estadistica
     */
    public void estadisticaDestino(String destino) {
        for (String aerolinea : vuelos.keySet()) {
            int llegan = 0;
            for (Vuelo vuelo : vuelos.get(aerolinea)) {
                if (vuelo.getDestino().equalsIgnoreCase(destino)) {
                    llegan++;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(llegan).append(" de cada ").append(vuelos.get(aerolinea).size());
            sb.append(" de la aerolinea ").append(aerolinea);
            sb.append(" vuelan a ").append(destino).append("\n");
            System.out.println(sb);
        }
    }

    /**
     * Borra los vuelos reservados por una empresa y devuelve el numero de vuelos
     * borrados, utiliza un conjunto de entradas
     *
     * @param nifEmpresa
     * @return numero de vuelos borrados
     */
    public int borrarVuelosEmpresa(String nifEmpresa) {
        int cont = 0;
        Iterator<Map.Entry<String, Set<Vuelo>>> it = vuelos.entrySet().iterator();
        while (it.hasNext()) {
            Iterator<Vuelo> it2 = it.next().getValue().iterator();
            while (it2.hasNext()) {
                Vuelo vuelo = it2.next();
                if (vuelo instanceof Charter && nifEmpresa.equalsIgnoreCase(((Charter) vuelo).getNIF())) {
                    it2.remove();
                    cont++;
                }
            }
        }
        return cont;
    }

    /**
     * Imprime la lista de vuelos pasada por parametro
     *
     * @param listaVuelos
     */
    public void imprimirListaVuelos(List<Vuelo> listaVuelos) {
        for (Vuelo vuelo : listaVuelos) {
            System.out.println(vuelo + "\n");
        }
    }

    /**
     * Imprime el número total de pasajeros de una aerolínea
     *
     * @param aerolinea
     */
    public void imprimirPasajerosPorAerolinea(String aerolinea) {
        int pasajeros = 0;
        for (Vuelo vuelo : vuelos.get(aerolinea)) {
            pasajeros += vuelo.getNumPasajeros();
        }
        System.out.println("La aerolinea " + aerolinea + " ha desplazado a "
                + pasajeros + " pasajeros.");
    }

    /**
     * Imprime, por cada línea, los vuelos cuyo número de plazas es igual o
     * superior al número de plazas de todos los vuelos de esa aerolínea.
     */
    public void imprimirVuelosMasPasajerosQueMedia() {
        for (String aerolinea : vuelos.keySet()) {
            double media = calcularMediaPlazas(aerolinea);
            List<Vuelo> lista = new ArrayList<>();
            for (Vuelo vuelo : vuelos.get(aerolinea)){
                if (vuelo.getNumPlazas() >= media){
                    lista.add(vuelo);
                }
            }
            System.out.println("La media de plazas de los vuelos de "
                    + aerolinea + " es de " + media);
            System.out.println("Los vuelos de " + aerolinea
                    + " con más plazas que la media son: ");
            imprimirListaVuelos(lista);
        }
    }

    private double calcularMediaPlazas(String aerolinea) {
        double suma = 0;
        for (Vuelo vuelo : vuelos.get(aerolinea)){
            suma += vuelo.getNumPlazas();
        }
        return suma / vuelos.get(aerolinea).size();
    }

    /**
     * Represetaci?n textual del mapa tal y como se muestra en el enunciado
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String aerolinea : vuelos.keySet()) {
            sb.append("\n" + aerolinea + "\n========\n");
            for (Vuelo vuelo : vuelos.get(aerolinea)) {
                sb.append(vuelo + "\n");
            }
        }
        return sb.toString();
    }

    /**
     * Rellena el mapa haciendo uso de un fichero de texto
     */
    public void leerFicheroCursos() {
        Scanner entrada = null;
        try {
            entrada = new Scanner(this.getClass().getResourceAsStream("/aviones.txt"));
            while (entrada.hasNextLine()) {
                String linea = entrada.nextLine();
                int pos = linea.indexOf(":");
                String aerolinea = linea.substring(0, pos);
                String[] vuelo = linea.substring(pos + 1).split(":");
                String destino = vuelo[1];
                String avion = vuelo[2];
                int plazas = Integer.parseInt(vuelo[3].trim());
                int precio = Integer.parseInt(vuelo[5].trim());
                if (vuelo[0].equals("R")) {
                    int plazasLibres = Integer.parseInt(vuelo[4].trim());
                    this.addVuelo(aerolinea, new Regular(destino, avion, plazas, plazasLibres, precio));
                } else {
                    String nifEmpresa = vuelo[4];
                    this.addVuelo(aerolinea, new Charter(destino, avion, plazas, nifEmpresa, precio));
                }
            }

        } finally {
            try {
                entrada.close();
            } catch (NullPointerException e) {
                System.out.println("Error en IO , no se ha creado el fichero");
            }
        }
    }

}
