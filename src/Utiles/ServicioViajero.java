package Utiles;

public class ServicioViajero {
    
    public static void menu() {
        final String MENU = "-SERVICIOS DEL VIAJERO- v1.0\n"
                + " 1 - Alta Ciudad.\n"
                + " 2 - Baja Ciudad.\n"
                + " 3 - Alta Arco.\n"
                + " 4 - Baja Arco.\n"
                + " 5 - Información Ciudad.\n"
                + " 6 - Listar rango de ciudades.\n"
                + " 7 - Camino más corto.\n"
                + " 8 - Camino más corto que x kilometros.\n"
                + " 9 - Camino que pasa por la menor cantidad de ciudades.\n"
                + " 10 - Camino a traves de ciudades con alojamiento.\n"
                + " 11 - Listar ciudades por orden alfabetico.\n"
                + " 12 - Mostrar diccionario.\n"
                + " 13 - Mostrar grafo.\n"
                + "\n"
                + " 0 - SALIR.\n";
        System.out.println(MENU);
    }
}
