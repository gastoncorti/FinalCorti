package Estructuras;

import Estructuras.Nodo.NodoAdy;
import Estructuras.Nodo.NodoVert;

public class Grafo {

    private NodoVert inicio;

    public Grafo() {
        this.inicio = null;
    }

    public boolean insertarVertice(String elem) {
        boolean seInserto = false;
        NodoVert aux = ubicarVertice(elem);
        if (aux == null) {
            this.inicio = new NodoVert(elem, this.inicio);
            seInserto = true;
        }
        return seInserto;
    }

    private NodoVert ubicarVertice(String buscado) {
        NodoVert aux = inicio;
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean insertarArco(String origen, String destino, double etiqueta) {
        boolean seInserto = false;
        NodoVert o = ubicarVertice(origen);
        if (o != null) {
            NodoVert d = ubicarVertice(destino);
            if (d != null) {
                NodoAdy aux = o.getPrimerAdy();
                if (aux != null) {
                    while (aux.getSigAdyacente() != null) {
                        aux = aux.getSigAdyacente();
                    }
                    aux.setSigAdyacente(new NodoAdy(d, etiqueta, null));
                } else {
                    o.setPrimerAdy(new NodoAdy(d, etiqueta, null));
                }
                seInserto = true;
            }
        }

        return seInserto;
    }

    public boolean eliminarVertice(String elem) {
        boolean seElimino = false;
        NodoVert anterior = inicio;
        //Borro todas las referencias que tengan los ady
        seElimino = eliminarVerticesAdy(elem);
        
        //ahora lo borro de la lista de Vertices
        if (anterior.getElem().equals(elem)) {//Si es el inicial
            inicio.setSigVertice(inicio.getSigVertice());
            seElimino = true;
        } else {//no es el inicial loopeo por los vertices
            while (anterior.getSigVertice() != null) {
                if (anterior.getSigVertice().getElem().equals(elem)) {
                    //Si encontre el anterior y borro el elemento.
                    anterior.setSigVertice(anterior.getSigVertice());
                    seElimino = true;
                } else {
                    //sigo buscando
                    anterior = anterior.getSigVertice();
                }
            }
        }

        return seElimino;
    }

    private boolean eliminarVerticesAdy(String elem) {
        boolean seElimino = false;
        NodoVert vertAux = inicio;
        NodoAdy adyAux;
        //por cada vertice elimino todos los adyacentes que sean igual al 'elem'
        while (vertAux != null) {
            // borrarAdy(vertAux.getPrimerAdy(), elem);
            adyAux = vertAux.getPrimerAdy();
            if (adyAux != null) {
                while (adyAux.getSigAdyacente() != null) {
                    if (adyAux.getSigAdyacente().getVertice().equals(elem)) {
                        adyAux.setSigAdyacente(adyAux.getSigAdyacente());
                        seElimino = true;
                    } else {
                        adyAux = adyAux.getSigAdyacente();
                    }
                }
            }
            vertAux = vertAux.getSigVertice();
        }
        return seElimino;
    }

    /*private void borrarAdy(NodoAdy ady, String elem) {
        if (ady != null) {
            while (ady.getSigAdyacente() != null) {
                if (ady.getSigAdyacente().getVertice().equals(elem)) {
                    ady.setSigAdyacente(ady.getSigAdyacente());
                } else {
                    ady = ady.getSigAdyacente();
                }
            }
        }
    }*/

    /*
    public boolean eliminarVertice(String elem) {
        boolean res = false;
        Vertice vertice = inicio, anterior = null, verif;
        Adyacente aux, prox;
        verif = obtenerVertice(elem);
        if (verif != null) {
            while (vertice != null) {
                if (vertice.getSiguienteVertice() != null) {
                    if (vertice.getSiguienteVertice().getElemento().equals(elem)) {
                        anterior = vertice;
                    }
                }
                if (vertice.getPrimerAdyacente() != null) {
                    if (vertice.getPrimerAdyacente().getVerticeAdyacente().getElemento().equals(elem)) {
                        vertice.setPrimerAdyacente(vertice.getPrimerAdyacente().getProximoAdyacente());
                    }
                }
                aux = vertice.getPrimerAdyacente();
                while (aux != null) {
                    prox = aux.getProximoAdyacente();
                    if (prox != null && prox.getVerticeAdyacente().getElemento().equals(elem)) {
                        aux.setProximoAdyacente(prox.getProximoAdyacente());
                    }
                    aux = prox;
                }
                vertice = vertice.getSiguienteVertice();
            }
            if (inicio.getElemento().equals(elem)) {
                inicio = inicio.getSiguienteVertice();
                res = true;
            } else {
                if (anterior != null) {
                    anterior.setSiguienteVertice(anterior.getSiguienteVertice().getSiguienteVertice());
                    res = true;
                }
            }
        }
        return res;
    }

     */
    public ListaStr listarProfundidad() {
        ListaStr visitados = new ListaStr();
        NodoVert aux = this.inicio;
        while (aux != null) {
            if (!visitados.pertenece(aux.getElem())) {
                profundidadDesde(aux, visitados);
            }
            aux = aux.getSigVertice();
        }

        return visitados;
    }

    private void profundidadDesde(NodoVert nodov, ListaStr visitados) {
        if (nodov != null) {
            visitados.insertar(nodov.getElem());
            NodoAdy ady = nodov.getPrimerAdy();
            while (ady != null) {
                if (!visitados.pertenece(ady.getVertice().getElem())) {
                    profundidadDesde(ady.getVertice(), visitados);
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    public boolean existeCamino(String origen, String destino) {
        boolean existe = false;

        NodoVert o = ubicarVertice(origen);
        NodoVert d = ubicarVertice(destino);

        if (o != null && d != null) {
            ListaStr visitados = new ListaStr();
            existe = existeCaminoAux(o, destino, visitados);
        }

        return existe;
    }

    private boolean existeCaminoAux(NodoVert origen, String destino, ListaStr visitados) {
        boolean existe = false;
        if (origen != null) {
            if (origen.getElem().equals(destino)) {
                existe = true;
            } else {
                visitados.insertar(origen.getElem());
                NodoAdy ady = origen.getPrimerAdy();
                while (!existe && ady != null) {
                    if (!visitados.pertenece(ady.getVertice().getElem())) {
                        existe = existeCaminoAux(ady.getVertice(), destino, visitados);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        return existe;
    }

    public ListaStr listarAnchura() {
        ListaStr visitados = new ListaStr();
        NodoVert aux = this.inicio;
        while (aux != null) {
            if (!visitados.pertenece(aux.getElem())) {
                anchuraDesde(aux, visitados);
            }
            aux = aux.getSigVertice();
        }

        return visitados;
    }

    private void anchuraDesde(NodoVert inicial, ListaStr visitados) {
        ColaStr cola = new ColaStr();
        cola.poner(inicial.getElem());
        while (!cola.esVacia()) {
            NodoVert auxVert = new NodoVert(cola.sacar());
            visitados.insertar(auxVert.getElem());
            NodoAdy auxAdy = auxVert.getPrimerAdy();
            while (auxAdy != null) {
                if (!visitados.pertenece(auxVert.getElem())) {
                    cola.poner(auxVert.getElem());
                }
                auxAdy = auxAdy.getSigAdyacente();
            }
        }
    }

    public ListaStr caminoMasCorto(String partida, String llegada) {
        ListaStr visitados = new ListaStr();
        ListaStr menor = new ListaStr();
        NodoVert vPartida = ubicarVertice(partida);
        NodoVert vLlegada = ubicarVertice(llegada);
        if (vPartida != null && vLlegada != null) {
            menor = caminoMasCortoAux(vPartida, visitados, menor, llegada);
        }
        return menor;
    }

    private ListaStr caminoMasCortoAux(NodoVert partida, ListaStr visitados, ListaStr menor, String llegada) {
        NodoAdy aux;
        ListaStr menorAux;
        if (!visitados.pertenece(partida.getElem())) {
            visitados.insertar(partida.getElem(), visitados.longitud() + 1);
            if (partida.getElem().equals(llegada)) {
                if (menor.esVacia()) {
                    menor = visitados.clonar();
                } else {
                    if (visitados.longitud() < menor.longitud()) {
                        menor = visitados.clonar();
                    }
                }
            } else {
                aux = partida.getPrimerAdy();
                while (aux != null) {
                    menorAux = caminoMasCortoAux(aux.getVertice(), visitados, menor, llegada);
                    if (!menorAux.esVacia()) {
                        if (!menor.esVacia()) {
                            if (menorAux.longitud() < menor.longitud()) {
                                menor = menorAux.clonar();
                            }
                        } else {
                            menor = menorAux.clonar();
                        }
                    }
                    aux = aux.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
        }
        return menor;
    }
}


/*
package trabajofinalestructuras.Estructuras;

public class Grafo {

    private Vertice inicio;

    public Grafo() {
        inicio = null;
    }

    public boolean esVacio() {
        return inicio == null;
    }

    public void vaciar() {
        inicio = null;
    }

    public Grafo clonar() {
        Grafo nuevo = new Grafo();
        if (inicio != null) {
            nuevo.inicio = new Vertice();
            copiar(inicio, nuevo.inicio);
        }
        return nuevo;
    }

    private void copiar(Vertice original, Vertice nuevo) {
        nuevo.setElemento(original.getElemento());
        Adyacente aux, aux2;
        aux = original.getPrimerAdyacente();
        nuevo.setPrimerAdyacente(aux);
        aux2 = nuevo.getPrimerAdyacente();
        while (aux != null) {
            aux2.setProximoAdyacente(aux.getProximoAdyacente());
            aux2 = aux2.getProximoAdyacente();
            aux = aux.getProximoAdyacente();
        }
        if (original.getSiguienteVertice() != null) {
            Vertice nvoSiguiente = new Vertice();
            nuevo.setSiguienteVertice(nvoSiguiente);
            copiar(original.getSiguienteVertice(), nvoSiguiente);
        }
    }

    public boolean insertarVertice(String elem) {
        boolean res = false;
        Vertice nuevo, aux;
        aux = obtenerVertice(elem);
        if (aux == null) {
            nuevo = new Vertice();
            nuevo.setElemento(elem);
            if (inicio == null) {
                inicio = nuevo;
                res = true;
            } else {
                aux = inicio;
                while (aux.getSiguienteVertice() != null) {
                    aux = aux.getSiguienteVertice();
                }
                aux.setSiguienteVertice(nuevo);
                res = true;
            }
        }
        return res;
    }

    public boolean eliminarVertice(String elem) {
        boolean res = false;
        Vertice vertice = inicio, anterior = null, verif;
        Adyacente aux, prox;
        verif = obtenerVertice(elem);
        if (verif != null) {
            while (vertice != null) {
                if (vertice.getSiguienteVertice() != null) {
                    if (vertice.getSiguienteVertice().getElemento().equals(elem)) {
                        anterior = vertice;
                    }
                }
                if (vertice.getPrimerAdyacente() != null) {
                    if (vertice.getPrimerAdyacente().getVerticeAdyacente().getElemento().equals(elem)) {
                        vertice.setPrimerAdyacente(vertice.getPrimerAdyacente().getProximoAdyacente());
                    }
                }
                aux = vertice.getPrimerAdyacente();
                while (aux != null) {
                    prox = aux.getProximoAdyacente();
                    if (prox != null && prox.getVerticeAdyacente().getElemento().equals(elem)) {
                        aux.setProximoAdyacente(prox.getProximoAdyacente());
                    }
                    aux = prox;
                }
                vertice = vertice.getSiguienteVertice();
            }
            if (inicio.getElemento().equals(elem)) {
                inicio = inicio.getSiguienteVertice();
                res = true;
            } else {
                if (anterior != null) {
                    anterior.setSiguienteVertice(anterior.getSiguienteVertice().getSiguienteVertice());
                    res = true;
                }
            }
        }
        return res;
    }

    public boolean insertarArco(String origen, String destino, double rotulo) {
        boolean res;
        Vertice aux = inicio, aux2 = inicio, verif1, verif2;
        Adyacente arcoIda, arcoVuelta, adyAux;
        verif1 = obtenerVertice(origen);
        verif2 = obtenerVertice(destino);
        if (origen.equals(destino)) {
            res = false;
        } else if (verif1 == null || verif2 == null) {
            res = false;
        } else {
            //buscar origen y destino en los vertices
            while (aux != null && !aux.getElemento().equals(origen)) {
                aux = aux.getSiguienteVertice();
            }
            while (aux2 != null && !aux2.getElemento().equals(destino)) {
                aux2 = aux2.getSiguienteVertice();
            }
            if (aux != null && aux2 != null) {
                adyAux = aux.getPrimerAdyacente();
                while (adyAux != null && !adyAux.getVerticeAdyacente().getElemento().equals(destino)) {
                    adyAux = adyAux.getProximoAdyacente();
                }
                if (adyAux == null) {
                    arcoIda = new Adyacente();
                    arcoVuelta = new Adyacente();
                    if (aux.getPrimerAdyacente() == null) {
                        aux.setPrimerAdyacente(arcoIda);
                    } else {
                        adyAux = aux.getPrimerAdyacente();
                        while (adyAux.getProximoAdyacente() != null) {
                            adyAux = adyAux.getProximoAdyacente();
                        }
                        adyAux.setProximoAdyacente(arcoIda);
                    }
                    arcoIda.setVerticeAdyacente(aux2);
                    arcoIda.setRotulo(rotulo);
                    if (aux2.getPrimerAdyacente() == null) {
                        aux2.setPrimerAdyacente(arcoVuelta);
                    } else {
                        adyAux = aux2.getPrimerAdyacente();
                        while (adyAux.getProximoAdyacente() != null) {
                            adyAux = adyAux.getProximoAdyacente();
                        }
                        adyAux.setProximoAdyacente(arcoVuelta);
                    }
                    arcoVuelta.setVerticeAdyacente(aux);
                    arcoVuelta.setRotulo(rotulo);
                    res = true;
                } else {
                    res = false;
                }
            } else {
                //no se encontro vertice origen/destino
                res = false;
            }
        }
        return res;
    }

    public boolean eliminarArco(String origen, String destino) {
        boolean res;
        Vertice vOrigen = obtenerVertice(origen), vDestino = obtenerVertice(destino);
        Adyacente aux, prox;
        if (vOrigen != null && vDestino != null) {
            aux = vOrigen.getPrimerAdyacente();
            if (aux != null) {
                if (aux.getVerticeAdyacente().getElemento().equals(destino)) {
                    vOrigen.setPrimerAdyacente(aux.getProximoAdyacente());
                } else {
                    while (aux != null) {
                        prox = aux.getProximoAdyacente();
                        if (prox != null && prox.getVerticeAdyacente().getElemento().equals(destino)) {
                            aux.setProximoAdyacente(prox.getProximoAdyacente());
                        }
                        aux = aux.getProximoAdyacente();
                    }
                }
                aux = vDestino.getPrimerAdyacente();
                if (aux != null) {
                    if (aux.getVerticeAdyacente().getElemento().equals(origen)) {
                        vDestino.setPrimerAdyacente(aux.getProximoAdyacente());
                    } else {
                        while (aux != null) {
                            prox = aux.getProximoAdyacente();
                            if (prox != null && prox.getVerticeAdyacente().getElemento().equals(origen)) {
                                aux.setProximoAdyacente(prox.getProximoAdyacente());
                            }
                            aux = aux.getProximoAdyacente();
                        }
                    }
                    res = true;
                } else {
                    res = false;
                }
            } else {
                res = false;
            }
        } else {
            res = false;
        }
        return res;
    }

    public boolean verificarVertice(String nombre) {
        Vertice aux = obtenerVertice(nombre);
        if (aux == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean verificarArco(String origen, String destino) {
        boolean res = false;
        Vertice vOrigen = obtenerVertice(origen);
        Vertice vDestino = obtenerVertice(destino);
        Adyacente aux;
        if (vOrigen != null && vDestino != null) {
            aux = vOrigen.getPrimerAdyacente();
            while (!res && aux != null) {
                if (aux.getVerticeAdyacente().getElemento().equals(destino)) {
                    res = true;
                }
                aux = aux.getProximoAdyacente();
            }
        } else {
            res = false;
        }
        return res;
    }

    public double recuperarArco(String origen, String destino) {
        double valor = 0;
        boolean res = false;
        Vertice vOrigen = obtenerVertice(origen);
        Vertice vDestino = obtenerVertice(destino);
        Adyacente aux;
        if (vOrigen != null && vDestino != null) {
            aux = vOrigen.getPrimerAdyacente();
            while (!res && aux != null) {
                if (aux.getVerticeAdyacente().getElemento().equals(destino)) {
                    res = true;
                    valor = aux.getRotulo();
                }
                aux = aux.getProximoAdyacente();
            }
        }
        return valor;
    }

    public ListaString listarProfundidad() {    //al menos que sea un metodo estatico que recibe la referencia del objeto grafo!. 
        ListaString visitados = new ListaString();
        listarProfundidadAux(inicio, visitados);
        return visitados;
    }

    private void listarProfundidadAux(Vertice vertice, ListaString visitados) {
        Adyacente aAux;
        Vertice vertAux;
        if (vertice != null) {
            if (!visitados.pertenece(vertice.getElemento())) {
                visitados.insertar(vertice.getElemento(), visitados.longitud() + 1);
            }
            aAux = vertice.getPrimerAdyacente();
            while (aAux != null) {
                if (!visitados.pertenece(aAux.getVerticeAdyacente().getElemento())) {
                    listarProfundidadAux(aAux.getVerticeAdyacente(), visitados);
                }
                aAux = aAux.getProximoAdyacente();
            }
            vertAux = inicio;
            while (vertAux != null && visitados.pertenece(vertAux.getElemento())) {
                vertAux = vertAux.getSiguienteVertice();
            }
            if (vertAux != null) {
                listarProfundidadAux(vertAux, visitados);
            }
        }
    }

    public ListaString listarProfundidadV() {
        ListaString visitados = new ListaString();
        Vertice aux = this.inicio;
        while (aux != null) {
            if (!visitados.pertenece(aux.getElemento())) {
                listarProfundidadAuxV(aux, visitados);
            }
            aux = aux.getSiguienteVertice();
        }
        return visitados;
    }

    private void listarProfundidadAuxV(Vertice n, ListaString visitados) {
        if (n != null) {
            visitados.insertar(n.getElemento(), visitados.longitud() + 1);
            Adyacente ady = n.getPrimerAdyacente();
            while (ady != null) {
                if (!visitados.pertenece(ady.getVerticeAdyacente().getElemento())) {
                    listarProfundidadAuxV(ady.getVerticeAdyacente(), visitados);
                }
                ady = ady.getProximoAdyacente();
            }
        }
    }

    public ListaString listarAnchura() {
        ListaString visitados = new ListaString();
        ColaString porVisitar = new ColaString();
        if (inicio != null) {
            porVisitar.poner(inicio.getElemento());
            listarAnchuraAux(visitados, porVisitar, inicio);
        }
        return visitados;
    }

    private void listarAnchuraAux(ListaString visitados, ColaString porVisitar, Vertice vertice) {
        Adyacente aux;
        if (vertice != null) {
            if (!visitados.pertenece(vertice.getElemento())) {
                visitados.insertar(vertice.getElemento(), visitados.longitud() + 1);
                aux = vertice.getPrimerAdyacente();
                while (aux != null) {
                    if (!visitados.pertenece(aux.getVerticeAdyacente().getElemento())) {
                        porVisitar.poner(aux.getVerticeAdyacente().getElemento());
                    }
                    aux = aux.getProximoAdyacente();
                }
            }
            porVisitar.sacar();
            if (!porVisitar.esVacia()) {
                listarAnchuraAux(visitados, porVisitar, obtenerVertice(porVisitar.obtenerFrente()));
            }
        }
    }

    public boolean existeCamino(String partida, String llegada) {
        Vertice vertPartida = obtenerVertice(partida);
        Vertice vertLlegada = obtenerVertice(llegada);
        ListaString visitados = new ListaString();
        boolean res;
        if (vertPartida != null && vertLlegada != null) {
            res = existeCaminoAux(vertPartida, visitados, llegada, false);
        } else {
            res = false;
        }
        return res;
    }

    private boolean existeCaminoAux(Vertice partida, ListaString visitados, String llegada, boolean res) {
        Adyacente aux;
        if (!visitados.pertenece(partida.getElemento())) {
            visitados.insertar(partida.getElemento(), visitados.longitud() + 1);
            aux = partida.getPrimerAdyacente();
            while (!res && aux != null) {
                if (aux.getVerticeAdyacente().getElemento().equals(llegada)) {
                    res = true;
                    visitados.insertar(llegada, visitados.longitud() + 1);
                } else {
                    res = existeCaminoAux(aux.getVerticeAdyacente(), visitados, llegada, res);
                }
                aux = aux.getProximoAdyacente();
            }
        }
        return res;
    }

    public ListaString caminoMasCorto(String partida, String llegada) {
        ListaString visitados = new ListaString(), menor = new ListaString();
        Vertice vPartida = obtenerVertice(partida);
        Vertice vLlegada = obtenerVertice(llegada);
        if (vPartida != null && vLlegada != null) {
            menor = caminoMasCortoAux(vPartida, visitados, menor, llegada);
        }
        return menor;
    }

    private ListaString caminoMasCortoAux(Vertice partida, ListaString visitados,
            ListaString menor, String llegada) {
        Adyacente aux;
        ListaString menorAux;
        if (!visitados.pertenece(partida.getElemento())) {
            visitados.insertar(partida.getElemento(), visitados.longitud() + 1);
            if (partida.getElemento().equals(llegada)) {
                if (menor.esVacia()) {
                    menor = visitados.clonar();
                } else {
                    if (visitados.longitud() < menor.longitud()) {
                        menor = visitados.clonar();
                    }
                }
            } else {
                aux = partida.getPrimerAdyacente();
                while (aux != null) {
                    menorAux = caminoMasCortoAux(aux.getVerticeAdyacente(), visitados, menor, llegada);
                    if (!menorAux.esVacia()) {
                        if (!menor.esVacia()) {
                            if (menorAux.longitud() < menor.longitud()) {
                                menor = menorAux.clonar();
                            }
                        } else {
                            menor = menorAux.clonar();
                        }
                    }
                    aux = aux.getProximoAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
        }
        return menor;
    }

    public ListaString caminoMasLargo(String partida, String llegada) {
        ListaString visitados = new ListaString(), mayor = new ListaString();
        Vertice vPartida = obtenerVertice(partida);
        Vertice vLlegada = obtenerVertice(llegada);
        if (vPartida != null && vLlegada != null) {
            mayor = caminoMasLargoAux(vPartida, visitados, mayor, llegada);
        }
        return mayor;
    }

    private ListaString caminoMasLargoAux(Vertice partida, ListaString visitados,
            ListaString mayor, String llegada) {
        Adyacente aux;
        ListaString mayorAux;
        if (!visitados.pertenece(partida.getElemento())) {
            visitados.insertar(partida.getElemento(), visitados.longitud() + 1);
            if (partida.getElemento().equals(llegada)) {
                if (mayor.esVacia()) {
                    mayor = visitados.clonar();
                } else {
                    if (visitados.longitud() > mayor.longitud()) {
                        mayor = visitados.clonar();
                    }
                }
            } else {
                aux = partida.getPrimerAdyacente();
                while (aux != null) {
                    mayorAux = caminoMasCortoAux(aux.getVerticeAdyacente(), visitados, mayor, llegada);
                    if (!mayorAux.esVacia()) {
                        if (!mayor.esVacia()) {
                            if (mayorAux.longitud() > mayor.longitud()) {
                                mayor = mayorAux.clonar();
                            }
                        } else {
                            mayor = mayorAux.clonar();
                        }
                    }
                    aux = aux.getProximoAdyacente();
                }

            }
            visitados.eliminar(visitados.longitud());
        }
        return mayor;
    }

    private Vertice obtenerVertice(String elem) {
        Vertice res = null, aux;
        if (inicio != null) {
            aux = inicio;
            while (aux != null && !aux.getElemento().equals(elem)) {
                aux = aux.getSiguienteVertice();
            }
            res = aux;
        }
        return res;
    }

    @Override
    public String toString() {
        String res = "";
        Vertice aux;
        Adyacente auxA;
        if (inicio == null) {
            res = "Grafo vacío";
        } else {
            aux = inicio;
            while (aux != null) {
                System.out.println("Vertice: " + aux.getElemento());
                auxA = aux.getPrimerAdyacente();
                while (auxA != null) {
                    System.out.println("Adyacente a " + auxA.getVerticeAdyacente().getElemento()
                            + " - Rotulo: " + auxA.getRotulo());
                    auxA = auxA.getProximoAdyacente();
                }
                if (aux.getSiguienteVertice() != null) {
                    System.out.println("Sig. vertice: "
                            + aux.getSiguienteVertice().getElemento());
                }
                aux = aux.getSiguienteVertice();
                System.out.println("---------------------------------------------------------------");
            }
        }
        return res;
    }

    public double menorKilometraje(String partida, String llegada) {
        double salida = 0;
        ListaString visitados = new ListaString();
        Vertice vPartida = obtenerVertice(partida);
        Vertice vLlegada = obtenerVertice(llegada);
        if (vPartida != null && vLlegada != null) {
            salida = menorKilometrajeAux(vPartida, visitados, llegada, -1, -1);
        }
        return salida;
    }

    private double menorKilometrajeAux(Vertice partida, ListaString visitados, String llegada, double minimoHastaAhora, double kilometros) {
        //System.out.println("Entra con vertice " + partida.getElemento());
        //System.out.println("Visitados hasta ahora " + visitados.toString());
        Adyacente aux;
        if (!visitados.pertenece(partida.getElemento())) {
            visitados.insertar(partida.getElemento(), visitados.longitud() + 1);
            if (partida.getElemento().equals(llegada)) {
                if (minimoHastaAhora == -1) {
                    minimoHastaAhora = kilometros;
                } else {
                    if (kilometros < minimoHastaAhora) {
                        minimoHastaAhora = kilometros;
                    }
                }
            } else {
                aux = partida.getPrimerAdyacente();
                while (aux != null) {
                    System.out.println("etiqueta del rotulo " + aux.getRotulo());
                    minimoHastaAhora = menorKilometrajeAux(aux.getVerticeAdyacente(), visitados, llegada, minimoHastaAhora, kilometros + aux.getRotulo());
                    if (minimoHastaAhora == -1) {
                        minimoHastaAhora = kilometros;
                    }
                    aux = aux.getProximoAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
        }
        //System.out.println("Sale del vertice " + partida.getElemento());
        return minimoHastaAhora;
    }

    public ListaString existeCaminoAlojamiento(String partida, String llegada, Diccionario dicc) {
        Vertice vertPartida = obtenerVertice(partida);
        Vertice vertLlegada = obtenerVertice(llegada);
        ListaString visitados = new ListaString();
        boolean res;
        if (vertPartida != null && vertLlegada != null) {
            res = existeCaminoAlojamientoAux(vertPartida, visitados, partida, llegada, false, dicc);
        }
        if (visitados.longitud() == 1 && visitados.pertenece(partida)) {
            visitados.vaciar();
        }
        if (!visitados.pertenece(llegada)) {
            visitados.vaciar();
        }
        return visitados;
    }

    private boolean existeCaminoAlojamientoAux(Vertice partida, ListaString visitados, String origen, String llegada, boolean res, Diccionario dicc) {
        Adyacente aux;
        if (!visitados.pertenece(partida.getElemento())) {
            if (partida.getElemento().equalsIgnoreCase(origen) || partida.getElemento().equalsIgnoreCase(llegada)
                    || dicc.obtenerCiudad(partida.getElemento()).isAlojamiento()) {
                visitados.insertar(partida.getElemento(), visitados.longitud() + 1);
                aux = partida.getPrimerAdyacente();
                while (!res && aux != null) {
                    if (aux.getVerticeAdyacente().getElemento().equalsIgnoreCase(llegada)) {
                        res = true;
                        visitados.insertar(llegada, visitados.longitud() + 1);
                    } else {
                        res = existeCaminoAlojamientoAux(aux.getVerticeAdyacente(), visitados, origen, llegada, res, dicc);
                    }
                    aux = aux.getProximoAdyacente();
                }
            }
        }
        return res;
    }
}


 */
