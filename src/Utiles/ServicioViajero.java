package Utiles;

import Estructuras.Diccionario;
import Estructuras.Grafo;

public class ServicioViajero {

    private Diccionario diccionario;
    private Grafo conexiones;

    public ServicioViajero() {
        diccionario = new Diccionario();
        conexiones = new Grafo();
    }

    public void altaCiudad() {
        char aloj;
        boolean correcto = false;
        Ciudad nueva = new Ciudad();
        System.out.println("Ingrese Nombre: ");
        nueva.setNombre(TecladoIn.readLine().toUpperCase());
        System.out.println("Ingrese Prov: ");
        nueva.setProvincia(TecladoIn.readLine().toUpperCase());
        System.out.println("Ingrese hab: ");
        nueva.setHabitantes(TecladoIn.readLineInt());
        while (!correcto) {
            System.out.println("Tien alojamiento? (S/N): ");
            aloj = TecladoIn.readNonwhiteChar();
            if (aloj == 'S' || aloj == 's') {
                correcto = true;
                nueva.setAlojamientoDisp(true);
            } else if (aloj == 'N' || aloj == 'n') {
                nueva.setAlojamientoDisp(false);
            }
        }
        if (diccionario.insertar(nueva.getNombre(), nueva) && conexiones.insertarVertice(nueva.getNombre())) {
            System.out.println("Se cargo correctamente.");
        } else {
            System.out.println("La ciudad ya se encuentra cargada");
        }
    }

    public void bajaCiudad() {
        String bajaCiudad = "";
        System.out.println("Nombre ciudad para dar de baja: ");
        bajaCiudad = TecladoIn.readLine().toUpperCase();
        if(diccionario.eliminar(bajaCiudad) && conexiones.eliminarVertice(bajaCiudad)) {
            System.out.println("Se dio de baja correctamente.");
        } else {
            System.out.println("La ciudad no existe.");
        }

    }

    public void altaRuta() {
        String origen, destino;
        int km = 0;
        System.out.println("Ingrese origen: ");
        origen = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese destino: ");
        destino = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese Kilometraje: ");
        km = TecladoIn.readLineInt();

        if (conexiones.insertarArco(origen, destino, km)) {
            System.out.println("Se dio el alta correctamente.");
        } else {
            System.out.println("Esta ruta ya se encuentra cargada o no se encontro la ciudad de origen/destino.");
        }
    }

    public void bajaRuta() {
        String origen = "", destino = "";
        System.out.println("Nombre del origen de la ruta: ");
        origen = TecladoIn.readLine().toUpperCase();
        System.out.println("Nombre del destino de la ruta: ");
        destino = TecladoIn.readLine().toUpperCase();
        if(conexiones.eliminarAdyacente(origen, destino) ) {
            System.out.println("Se dio de baja correctamente.");
        } else {
            System.out.println("La ciudad origen/destino no existe o no existe la ruta.");
        }
    }

    public void informacionCiudad() {
        Ciudad ciudad;
        System.out.println("Ingrese ciudad: ");
        ciudad = diccionario.recuperarElemento(TecladoIn.readLine().toUpperCase());
        System.out.println(ciudad.toString());
    }

    public void listarRangoCiudades() {

    }
    
    public void caminoMasCorto() {
        
    }
    
    public void caminoMasCortoKilometros() {
        
    }
    
    public void caminoMenorCiudad() {
        
    }
    
    public void listarCiudadesAlfab() {
        
    }
    
    public void mostrarDic() {
        
    }
    
    public void mostrarGrafo() {
        
    }
    
    public static void menu() {
        final String MENU = "-SERVICIOS DEL VIAJERO- v1.0\n"
                + " 1 - Alta Ciudad.\n"
                + " 2 - Baja Ciudad.\n"
                + " 3 - Alta Ruta.\n"
                + " 4 - Baja Ruta.\n"
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

    public void cargaTesting() {
        diccionario.insertar("CORDOBA", new Ciudad("CORDOBA", "CORDOBA", 157010, false));
        conexiones.insertarVertice("CORDOBA");
        diccionario.insertar("ROSARIO", new Ciudad("ROSARIO", "SANTA FE", 748312, false));
        conexiones.insertarVertice("ROSARIO");
        diccionario.insertar("RESISTENCIA", new Ciudad("RESISTENCIA", "CHACO", 290723, false));
        conexiones.insertarVertice("RESISTENCIA");
        diccionario.insertar("PARANA", new Ciudad("PARANA", "ENTRE RIOS", 247863, false));
        conexiones.insertarVertice("PARANA");
        diccionario.insertar("NEUQUEN", new Ciudad("NEUQUEN", "NEUQUEN", 201868, true));
        conexiones.insertarVertice("NEUQUEN");
        diccionario.insertar("CIPOLLETI", new Ciudad("CIPOLLETI", "RIO NEGRO", 66472, true));
        conexiones.insertarVertice("CIPOLLETI");
        diccionario.insertar("VIEDMA", new Ciudad("VIEDMA", "RIO NEGRO", 46767, true));
        conexiones.insertarVertice("VIEDMA");
        diccionario.insertar("TRELEW", new Ciudad("TRELEW", "CHUBUT", 103656, true));
        conexiones.insertarVertice("TRELEW");
        diccionario.insertar("RAWSON", new Ciudad("RAWSON", "CHUBUT", 31787, false));
        conexiones.insertarVertice("RAWSON");
        diccionario.insertar("CONCEPCION", new Ciudad("CONCEPCION", "TUCUMAN", 46194, false));
        conexiones.insertarVertice("CONCEPCION");
        diccionario.insertar("POSADAS", new Ciudad("POSADAS", "MISIONES", 354719, false));
        conexiones.insertarVertice("POSADAS");
        diccionario.insertar("AZUL", new Ciudad("AZUL", "BUENOS AIRES", 53054, false));
        conexiones.insertarVertice("AZUL");
        diccionario.insertar("RECONQUISTA", new Ciudad("RECONQUISTA", "SANTA FE", 66187, false));
        conexiones.insertarVertice("RECONQUISTA");
        diccionario.insertar("MENDOZA", new Ciudad("MENDOZA", "MENDOZA", 890312, false));
        conexiones.insertarVertice("MENDOZA");
        diccionario.insertar("USHUAIA", new Ciudad("USHUAIA", "TIERRA DEL FUEGO", 56825, false));
        conexiones.insertarVertice("USHUAIA");

        conexiones.insertarArco("VIEDMA", "TRELEW", 363);
        conexiones.insertarArco("CONCEPCION", "VIEDMA", 552);
        conexiones.insertarArco("CONCEPCION", "AZUL", 709);
        conexiones.insertarArco("RECONQUISTA", "USHUAIA", 279);
        conexiones.insertarArco("AZUL", "ROSARIO", 9.8);
        conexiones.insertarArco("AZUL", "CIPOLLETI", 689);
        conexiones.insertarArco("CORDOBA", "ROSARIO", 404.6);
        conexiones.insertarArco("CORDOBA", "AZUL", 531);
        conexiones.insertarArco("POSADAS", "RESISTENCIA", 626);
        conexiones.insertarArco("RESISTENCIA", "PARANA", 465);
        conexiones.insertarArco("RESISTENCIA", "CIPOLLETI", 1265);
        conexiones.insertarArco("NEUQUEN", "villa maria", 268);
        conexiones.insertarArco("NEUQUEN", "RESISTENCIA", 954);
        conexiones.insertarArco("NEUQUEN", "POSADAS", 408);
        conexiones.insertarArco("TRELEW", "POSADAS", 128);
        conexiones.insertarArco("TRELEW", "NEUQUEN", 375);
        conexiones.insertarArco("TRELEW", "RAWSON", 1068);
        conexiones.insertarArco("ROSARIO", "NEUQUEN", 428);
        conexiones.insertarArco("MENDOZA", "RESISTENCIA", 1604);
        conexiones.insertarArco("RAWSON", "ROSARIO", 724);
        conexiones.insertarArco("RAWSON", "AZUL", 403);
        conexiones.insertarArco("PARANA", "CONCEPCION", 323);
        conexiones.insertarArco("PARANA", "NEUQUEN", 453);
        conexiones.insertarArco("USHUAIA", "AZUL", 527);
        conexiones.insertarArco("USHUAIA", "NEUQUEN", 184);
    }
}
