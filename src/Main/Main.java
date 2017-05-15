package Estructuras.Main;

import Estructuras.Diccionario;
import Utiles.ServicioViajero;
import Estructuras.Grafo;
import Utiles.Ciudad;
import Utiles.TecladoIn;
import Estructuras.ListaStr;

public class Main {

    public static void main(String[] args) {
        int opcion;
        boolean corriendo = true;
        ServicioViajero sv = new ServicioViajero();
        sv.cargaTesting();
        
        while (corriendo) {
            ServicioViajero.menu();
            opcion = TecladoIn.readLineInt();
            /*
            1 - Alta Ciudad.
            2 - Baja Ciudad.
            3 - Alta Ruta.
            4 - Baja Ruta.
            5 - Información Ciudad.
            6 - Listar rango de ciudades.
            7 - Camino más corto.
            8 - Camino más corto que x kilometros.
            9 - Camino que pasa por la menor cantidad de ciudades.
            10 - Camino a traves de ciudades con alojamiento.
            11 - Listar ciudades por orden alfabetico.
            12 - Mostrar diccionario.
            13 - Mostrar grafo.
            */
            switch (opcion) {
                //1-13
                case 1: sv.altaCiudad();
                    break;
                case 2: sv.bajaCiudad();
                    break;
                case 3: sv.altaRuta();
                    break;
                case 4: sv.bajaRuta();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 0:
                    corriendo = false;
                    System.out.println("¡Buen Viaje!");
                    break;
                default:
                    System.out.println("Existian MUCHAS opciones, no seleccionaste ninguna :( ");
                    break;

            }
        }

    }

    public static void Pruebas() {

        Grafo g = new Grafo();
        g.insertarVertice("A");
        g.insertarVertice("B");
        g.insertarVertice("C");
        g.insertarVertice("D");
        g.insertarVertice("E");

        g.insertarArco("A", "B", 2);
        g.insertarArco("A", "D", 3);
        g.insertarArco("B", "C", 1);
        g.insertarArco("C", "E", 3);
        g.insertarArco("D", "E", 5);

        ListaStr list = g.caminoMasCorto("A", "E");
        System.out.println(list.toString());
    }
}
