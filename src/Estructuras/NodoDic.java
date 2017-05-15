package Estructuras;
import Utiles.Ciudad;

public class NodoDic {
    private String clave;
    private Ciudad elem;
    private int altura;
    private NodoDic hIzq;
    private NodoDic hDer;
    
    
    public NodoDic(String clave, Ciudad elem){
        this.clave = clave;
        this.elem = elem;
        this.hDer = null;
        this.hIzq = null;
    }

    public Ciudad getElem() {
        return elem;
    }

    public void setElem(Ciudad elem) {
        this.elem = elem;
    }

    public NodoDic getIzq() {
        return hIzq;
    }

    public void setIzq(NodoDic hIzq) {
        this.hIzq = hIzq;
    }

    public NodoDic getDer() {
        return hDer;
    }

    public void setDer(NodoDic hDer) {
        this.hDer = hDer;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    
}