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

    public boolean insertarArco(String origen, String destino) {
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
                    aux.setSigAdyacente(new NodoAdy(d, null));
                } else {
                    o.setPrimerAdy(new NodoAdy(d, null));
                }
                seInserto = true;
            }
        }

        return seInserto;
    }

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
public ListaString caminoMasCorto(String partida, String llegada) { 
        ListaString visitados = new ListaString(), menor = new ListaString(); 
        Vertice vPartida = obtenerVertice(partida); 
        Vertice vLlegada = obtenerVertice(llegada); 
        if (vPartida != null && vLlegada != null) { 
            menor = caminoMasCortoAux(vPartida, visitados, menor, llegada); 
        } 
        return menor; 
    } 


    private ListaString caminoMasCortoAux(Vertice partida, ListaString visitados, ListaString menor, String llegada) { 
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

*/
