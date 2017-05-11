package Estructuras;

import Estructuras.Nodo.NodoColaPrioridad;
import Estructuras.Nodo.NodoVert;

public class ColaPrioridad {

    private NodoColaPrioridad frente;

    public ColaPrioridad() {
        frente = null;
    }

    public boolean poner(NodoVert elem, int prioridad) {
        boolean sePuso = true;
        if (frente != null) {
            if (frente.getPrioridad() < prioridad) {
                NodoColaPrioridad aux = frente;
                while (aux.getEnlace() != null) {
                    if (prioridad >= aux.getEnlace().getPrioridad()) {
                        aux = aux.getEnlace();
                    } else {
                        aux.setEnlace(new NodoColaPrioridad(elem, prioridad, aux.getEnlace()));
                    }
                }
            } else {
                frente = new NodoColaPrioridad(elem, prioridad, frente);
            }

        } else {
            frente = new NodoColaPrioridad(elem, prioridad);
        }
        return sePuso;
    }

    public NodoVert sacar() {
        NodoVert elem = null;
        if (frente != null) {
            elem = frente.getElem();
            frente = frente.getEnlace();
        }
        return elem;
    }

    public NodoVert getFrente() {
        return (frente == null) ? null : frente.getElem();
    }

    public boolean esVacia() {
        return frente == null;
    }

    public void vaciar() {
        frente = null;
    }

    public ColaStr clonar() {
        return null;
    }

    @Override
    public String toString() {
        String cad = "";
        if (!esVacia()) {
            NodoColaPrioridad aux = frente;
            while (aux != null) {
                cad = cad + aux.getElem().getElem() + ",";
                aux = aux.getEnlace();
            }
        } else {
            cad = "Vacia!";
        }
        return cad;
    }
}
