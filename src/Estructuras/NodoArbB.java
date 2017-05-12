package Estructuras.Nodo;
import Utiles.Ciudad;
public class NodoArbB {
    private String clave;
    private Ciudad elem;
    private int altura;
    private NodoArbB hIzq;
    private NodoArbB hDer;
    
    
    public NodoArbB(String clave, Ciudad elem){
        this.elem = elem;
        this.clave = clave;
        this.altura = 0;
        this.hDer = null;
        this.hIzq = null;
    }

    public Ciudad getElem() {
        return elem;
    }

    public void setElem(Ciudad elem) {
        this.elem = elem;
    }

    public NodoArbB getIzq() {
        return hIzq;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setIzq(NodoArbB hIzq) {
        this.hIzq = hIzq;
    }

    public NodoArbB getDer() {
        return hDer;
    }

    public void setDer(NodoArbB hDer) {
        this.hDer = hDer;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
}