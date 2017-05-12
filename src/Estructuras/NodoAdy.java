package Estructuras.Nodo;

public class NodoAdy {
    private NodoVert vertice;
    private int etiqueta;
    private NodoAdy sigAdyacente;

    public NodoAdy(NodoVert vertice, int etiqueta , NodoAdy sigAdyacente) {
        this.vertice = vertice;
        this.etiqueta = etiqueta;
        this.sigAdyacente = sigAdyacente;
    }

    public NodoVert getVertice() {
        return vertice;
    }

    public void setVertice(NodoVert vertice) {
        this.vertice = vertice;
    }

    public NodoAdy getSigAdyacente() {
        return sigAdyacente;
    }

    public void setSigAdyacente(NodoAdy sigAdyacente) {
        this.sigAdyacente = sigAdyacente;
    }

    public int getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(int etiqueta) {
        this.etiqueta = etiqueta;
    }
    
}


  