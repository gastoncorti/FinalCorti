
package Estructuras;
import Utiles.Ciudad;
public class NodoArb {

    private NodoArb izq, der;
    private int altura;
    private Ciudad elem;

    public NodoArb(Ciudad elem) {
        this.elem = elem;
    }

    public NodoArb(Ciudad elem, int alt) {
        this.elem = elem;
        this.altura = alt;
    }

    public NodoArb getIzq() {
        return izq;
    }

    public void setIzq(NodoArb izq) {
        this.izq = izq;
    }

    public NodoArb getDer() {
        return der;
    }

    public void setDer(NodoArb der) {
        this.der = der;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Ciudad getElem() {
        return elem;
    }

    public void setElem(Ciudad elem) {
        this.elem = elem;
    }

}

/*
package Estructuras;
public class NodoArb {

    private NodoArb izq, der;
    private int altura;
    private int elem;

    public NodoArb(int elem) {
        this.elem = elem;
    }

    public NodoArb(int elem, int alt) {
        this.elem = elem;
        this.altura = alt;
    }

    public NodoArb getIzq() {
        return izq;
    }

    public void setIzq(NodoArb izq) {
        this.izq = izq;
    }

    public NodoArb getDer() {
        return der;
    }

    public void setDer(NodoArb der) {
        this.der = der;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getElem() {
        return elem;
    }

    public void setElem(int elem) {
        this.elem = elem;
    }

}
*/