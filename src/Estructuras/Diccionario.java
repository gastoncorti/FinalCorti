package Estructuras;

import Estructuras.Nodo.NodoDic;
import Utiles.Ciudad;
import java.util.LinkedList;
import java.util.Queue;

public class Diccionario {

    private NodoDic raiz;

    public Diccionario() {
        this.raiz = null;
    }

    public boolean insertar(String clave, Ciudad nueva) {
        boolean esCorrecto;
        if (raiz != null) {
            esCorrecto = insertarAuxAux(clave, nueva, raiz, null);
        } else {
            raiz = new NodoDic(clave, nueva);
            esCorrecto = true;
        }
        return esCorrecto;
    }

    //BALANCE = altHijoIZQ - alturaHijoDER
    private boolean insertarAuxAux(String clave, Ciudad nueva, NodoDic raizActual, NodoDic padre) {
        boolean seInserto = false;
        if (!raizActual.getClave().equals(clave)) {
            if (raizActual.getClave().compareTo(clave) > 0) {
                if (raizActual.getIzq() != null) {
                    seInserto = insertarAuxAux(clave, nueva, raizActual.getIzq(), raizActual);
                    //raizActual.setAltura(alturaNodo(raizActual));
                } else {
                    raizActual.setIzq(new NodoDic(clave, nueva));
                    //raizActual.setAltura(alturaNodo(raizActual));
                    seInserto = true;
                }
            } else {
                if (raizActual.getDer() != null) {
                    seInserto = insertarAuxAux(clave, nueva, raizActual.getDer(), raizActual);
                    //raizActual.setAltura(alturaNodo(raizActual));
                } else {
                    raizActual.setDer(new NodoDic(clave, nueva));
                    //raizActual.setAltura(alturaNodo(raizActual));
                    seInserto = true;
                }
            }
            if (seInserto) {
                raizActual.setAltura(alturaNodo(raizActual));
                balancear(raizActual, padre);
            }

        }
        return seInserto;
    }

    public boolean eliminar(String clave) {
        boolean seElimino = false;
        if (raiz != null) {
            seElimino = eliminarAux(clave, raiz, null);
        }
        return seElimino;
    }

    private boolean eliminarAux(String clave, NodoDic nodoElim, NodoDic padre) {
        boolean seElimino = false;
        String cantHijos;
        String valorAux;
        Ciudad ciudadAux;
        NodoDic aux;
        if (nodoElim != null) {
            //Lo busco por izq
            if (clave.compareTo(nodoElim.getClave()) < 0) {
                seElimino = eliminarAux(clave, nodoElim.getIzq(), nodoElim);
                nodoElim.setAltura(alturaNodo(nodoElim));
                //Lo busco por der
            } else if (clave.compareTo(nodoElim.getClave()) > 0) {
                seElimino = eliminarAux(clave, nodoElim.getDer(), nodoElim);
                nodoElim.setAltura(alturaNodo(nodoElim));
                //Lo encontre ahora ver cantidad de hijos
            } else {
                //Switch de caso eliminacion
                cantHijos = casoEliminacion(nodoElim);
                switch (cantHijos) {
                    case "AMBOS":
                        if (padre == null) {
                            raiz = null;
                        } else {
                            if (nodoElim.getClave().compareTo(padre.getClave()) < 0) {
                                padre.setIzq(null);
                            } else {
                                padre.setDer(null);
                            }
                            padre.setAltura(alturaNodo(padre));
                        }
                        break;
                    case "IZQ":
                        if (padre == null) {
                            raiz = raiz.getIzq();
                            raiz.setAltura(alturaNodo(raiz));
                        } else {
                            if (nodoElim.getClave().compareTo(padre.getClave()) < 0) {
                                padre.setIzq(nodoElim.getIzq());
                            } else {
                                padre.setDer(nodoElim.getIzq());
                            }
                            padre.setAltura(alturaNodo(padre));
                        }
                        break;
                    case "DER":
                        if (padre == null) {
                            raiz = raiz.getDer();
                            raiz.setAltura(alturaNodo(raiz));
                        } else {
                            if (nodoElim.getClave().compareTo(padre.getClave()) < 0) {
                                padre.setIzq(nodoElim.getDer());
                            } else {
                                padre.setDer(nodoElim.getDer());
                            }
                            padre.setAltura(alturaNodo(padre));
                        }
                        break;
                    default:
                        aux = nodoElim.getIzq();
                        while (aux.getDer() != null) {
                            aux = aux.getDer();
                        }
                        valorAux = aux.getClave();
                        ciudadAux = aux.getCiudad();
                        eliminarAux(valorAux, raiz, null);
                        nodoElim.setClave(valorAux);
                        nodoElim.setCiudad(ciudadAux);
                        nodoElim.setAltura(alturaNodo(nodoElim));
                        if (padre != null) {
                            padre.setAltura(alturaNodo(padre));
                        }
                        break;
                }
                seElimino = true;
            }
            balancear(nodoElim, padre);
        }
        return seElimino;
    }

    /*
    private boolean eliminarAuxAUX(NodoArb raizActual, NodoArb padre, int elem) {
        boolean seElimino = false;
        if (raizActual != null) {
            if (raizActual.getElem() != elem) {
                if (raizActual.getElem() > elem) { //por izq
                    seElimino = eliminarAux(raizActual.getIzq(), raizActual, elem);
                } else {// por der
                    seElimino = eliminarAux(raizActual.getDer(), raizActual, elem);
                }
            } else { //encontre el elemento a eliminar
                switch (casoEliminacion(raizActual)) {
                    case 1:
                        NodoArb sustituto = new NodoArb(buscarSustituto(raizActual));
                        sustituto.setIzq(raizActual.getIzq());
                        sustituto.setDer(raizActual.getDer());
                        if (eliminar(sustituto.getElem())) {
                            if (padre == null) { // quiere borrar raiz
                                raiz = sustituto;
                            } else {
                                if (padre.getElem() > elem) {
                                    padre.setIzq(sustituto);
                                } else {
                                    padre.setDer(sustituto);
                                }
                            }
                        }
                        break;
                    case 2:
                        if (padre.getElem() > elem) {
                            padre.setIzq(raizActual.getIzq());
                        } else {
                            padre.setDer(raizActual.getIzq());
                        }
                        break;
                    case 3:
                        //tiene hder
                        if (padre.getElem() > elem) {
                            padre.setIzq(raizActual.getDer());
                        } else {
                            padre.setIzq(raizActual.getDer());
                        }
                        break;
                    case 4:
                        if (padre.getElem() > elem) {
                            padre.setIzq(null);
                        } else {
                            padre.setDer(null);
                        }
                        break;
                }
                seElimino = true;
            }
        }

        return seElimino;
    }
     */
    private String casoEliminacion(NodoDic nodoEliminar) {
        String caso = "";
        if(nodoEliminar.getIzq() != null) {
            if (nodoEliminar.getDer() != null) {
                caso = "AMBOS";
            } else {
                caso = "IZQ";
            }
        } else {
            if(nodoEliminar.getDer() != null) {
                caso = "DER";
            }
        }
        return caso;
    }

    /*
    private Ciudad buscarSustituto(NodoArb nodoEliminar) {
        Ciudad sustituto = nodoEliminar.getDer().getCiudad();
        NodoArb nodoAux = nodoEliminar.getDer();
        while (nodoAux.getIzq() != null) {
            sustituto = nodoAux.getIzq().getCiudad();
            nodoAux = nodoAux.getIzq();
        }
        return sustituto;
    }*/

 /*public int padre(String clave) {
        int padre = 0;

        if (raiz != null) {
            if (raiz.getElem() != elem) {
                NodoArb nodo = getPadre(raiz, elem);
                padre = (nodo != null) ? nodo.getElem() : 0;
            } else {
                padre = raiz.getElem();
            }
        }
        return padre;
    }*/

 /* private NodoArb getPadre(NodoArb raizActual, int elem) {
        NodoArb padre = null;
        if (raizActual != null) {
            if (raizActual.getElem() > elem) {//busco por izq
                if (raizActual.getIzq().getElem() != elem) {
                    padre = getPadre(raizActual.getIzq(), elem);
                } else {
                    padre = raizActual;
                }
            } else { //busco por derecha
                if (raizActual.getDer().getElem() != elem) {
                    padre = getPadre(raizActual.getDer(), elem);
                } else {
                    padre = raizActual;
                }
            }
        }
        return padre;
    }
     */
 /*  public boolean pertenece(int elem) {
        return perteneceAux(elem, raiz);
    }

    public boolean perteneceAux(int elem, NodoArb raizActual) {
        boolean pertenece = false;
        if (raizActual != null) {
            if (raizActual.getElem() != elem) {
                if (raizActual.getElem() > elem) {
                    pertenece = perteneceAux(elem, raizActual.getIzq());
                } else {
                    pertenece = perteneceAux(elem, raizActual.getDer());
                }
            } else {
                pertenece = true;
            }
        }
        return pertenece;
    }
     */
    public boolean esVacio() {
        return (raiz == null);
    }

    public int alturaNodo(NodoDic raizActual) {
        return alturaAux(raizActual);
    }

    public int alturaArbol() {
        return alturaAux(raiz);
    }

    private int alturaAux(NodoDic raizActual) {
        int altD = 0, altI = 0, alt;
        if (raizActual != null) {
            if (raizActual.getIzq() != null) {
                altI = 1 + alturaAux(raizActual.getIzq());
            }
            if (raizActual.getDer() != null) {
                altD = 1 + alturaAux(raizActual.getDer());
            }
            alt = (altI >= altD) ? altI : altD;
        } else {
            alt = 0;
        }
        return alt;
    }

    public int nivel(int elem) {
        return 0;
    }

    public void vaciar() {
        raiz = null;
    }

    /* public ArbolBB clonar() {
        ArbolBB clon = new ArbolBB();
        clonarAux(raiz, clon);
        return clon;
    }

     private void clonarAux(NodoArb raizActual, AVL clon) {
        if (raizActual != null) {
            clon.insertar(raizActual.getCiudad());
            if (raizActual.getIzq() != null) {
                clonarAux(raizActual.getIzq(), clon);
            }
            if (raizActual.getDer() != null) {
                clonarAux(raizActual.getDer(), clon);
            }
        }
    }
     */
    public void listar() {
        if (raiz != null) {
            listarAux(raiz);
        } else {
            System.out.println("Sin Elem");
        }
    }

    private void listarAux(NodoDic nActual) {
        if (nActual != null) {
            listarAux(nActual.getIzq());
            System.out.print(nActual.getCiudad().getNombre() + ",");
            listarAux(nActual.getDer());
        }
    }

    public void listarAltura() {
        if (raiz != null) {
            listarAlturaAux(raiz);
            System.out.print("\n");
        } else {
            System.out.println("Sin Elem");
        }
    }

    private void listarAlturaAux(NodoDic nActual) {
        if (nActual != null) {
            listarAlturaAux(nActual.getIzq());
            System.out.print(nActual.getCiudad().getNombre() + ",Alt: " + nActual.getAltura() + "\n");
            listarAlturaAux(nActual.getDer());
        }
    }

    public void listarNivel() {
        if (raiz != null) {
            listarNivelAux(raiz);
        } else {
            System.out.println("Sin Elem");
        }
    }

    private void listarNivelAux(NodoDic raizActual) {
        Queue cola = new LinkedList();
        cola.add(raizActual);
        while (!cola.isEmpty()) {
            NodoDic nodoAux = (NodoDic) cola.poll();
            System.out.print(nodoAux.getCiudad().getNombre());

            if (nodoAux.getIzq() != null) {
                cola.add(nodoAux.getIzq());
            }
            if (nodoAux.getDer() != null) {
                cola.add(nodoAux.getDer());
            }

        }
    }

    private void balancear(NodoDic nodo, NodoDic padre) {
        int balance;
        NodoDic aux;
        balance = balance(nodo);
        if (balance < -1) {
            aux = nodo.getDer();
            balance = balance(aux);
            if (balance == 1) {
                nodo.setDer(rotacionDerecha(nodo.getDer()));
            }
            if (padre == null) {
                raiz = rotacionIzquierda(nodo);
            } else {
                if (nodo.getClave().compareTo(padre.getClave()) < 0) {
                    padre.setIzq(rotacionIzquierda(nodo));
                } else {
                    padre.setDer(rotacionIzquierda(nodo));
                }
                padre.setAltura(alturaNodo(padre));
            }
        }
        if (balance > 1) {
            aux = nodo.getIzq();
            balance = balance(aux);
            if (balance == -1) {
                nodo.setIzq(rotacionIzquierda(nodo.getIzq()));
            }
            if (padre == null) {
                raiz = rotacionDerecha(nodo);
            } else {
                if (nodo.getClave().compareTo(padre.getClave()) < 0) {
                    padre.setIzq(rotacionDerecha(nodo));
                } else {
                    padre.setDer(rotacionDerecha(nodo));
                }
                padre.setAltura(alturaNodo(padre));
            }
        }
    }

    private NodoDic rotacionDerecha(NodoDic nodo) {
        NodoDic aux = nodo.getIzq();
        NodoDic aux2 = aux.getDer();
        aux.setDer(nodo);
        nodo.setIzq(aux2);
        nodo.setAltura(alturaNodo(nodo));
        aux.setAltura(alturaNodo(aux));
        return aux;
    }

    private NodoDic rotacionIzquierda(NodoDic nodo) {
        NodoDic aux = nodo.getDer();
        NodoDic aux2 = aux.getIzq();
        aux.setIzq(nodo);
        nodo.setDer(aux2);
        nodo.setAltura(alturaNodo(nodo));
        aux.setAltura(alturaNodo(aux));
        return aux;
    }

    private int balance(NodoDic raizActual) {
        int res;
        if (raizActual.getIzq() != null) {
            if (raizActual.getDer() != null) {
                res = raizActual.getIzq().getAltura() - raizActual.getDer().getAltura();
            } else {
                res = raizActual.getIzq().getAltura() + 1;
            }
        } else {
            if (raizActual.getDer() != null) {
                res = -1 - raizActual.getDer().getAltura();
            } else {
                res = 0;
            }
        }
        return res;
    }
}
