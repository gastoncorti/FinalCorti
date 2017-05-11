package Estructuras.Nodo;

public class NodoColaPrioridad {

    private NodoVert elem;
    private int prioridad;
    private NodoColaPrioridad enlace;

    public NodoColaPrioridad(NodoVert elem, int prioridad) {
        this.elem = elem;
        this.prioridad = prioridad;
        this.enlace = null;
    }

    public NodoColaPrioridad(NodoVert elem, int prioridad,  NodoColaPrioridad enlace) {
        this.elem = elem;
        this.prioridad = prioridad;
        this.enlace = enlace;
    }

    public NodoVert getElem() {
        return elem;
    }

    public void setElem(NodoVert elem) {
        this.elem = elem;
    }

    public NodoColaPrioridad getEnlace() {
        return enlace;
    }

    public void setEnlace(NodoColaPrioridad enlace) {
        this.enlace = enlace;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    
}
