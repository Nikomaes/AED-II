package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    Nodo _raiz;
    int _cardinal;

    private class Nodo {
        // Agregar atributos privados del Nodo
        T valor;
        Nodo izq;
        Nodo der;
        Nodo prev;
        // Crear Constructor del nodo
        private Nodo(T v){
            valor = v;
            izq= null;
            der= null;
            prev= null;
        }
    }

    public ABB() {
        _raiz=null;
        _cardinal=0;
    }

    public int cardinal() {
        return _cardinal;
    }

    public T minimo(){
        Nodo actual = _raiz;
        while (actual.izq !=null) {
            actual=actual.izq;         
        }
        return actual.valor;
    }

    public T maximo(){
        Nodo actual = _raiz;
        while (actual.der !=null) {
            actual=actual.der;         
        }
        return actual.valor;
    }

    public void insertar(T elem){
        Nodo nuevo = new Nodo(elem);
        Nodo actual = _raiz;
        if (actual==null) {
            _raiz=nuevo;
            _cardinal+=1;                      
        }
        else{
            insertarRec(elem,actual);
        }
        }
      
            
        
        
    
    public void insertarRec(T val, Nodo actual){
        int comparacion = actual.valor.compareTo(val);
        Nodo nuevo = new Nodo(val);
        if (comparacion>0) {
            if (actual.izq==null) {
                actual.izq=nuevo;
                
                _cardinal+=1;     
            }   
            else{
                insertarRec(val, actual.izq);
            }                             
        } 
        else if (comparacion<0) {
            if (actual.der==null) {
                actual.der=nuevo;
                _cardinal+=1; 
            }
            else{
                insertarRec(val, actual.der);
            }
        }            
        }

    public boolean pertenece(T elem){
        boolean res;
        Nodo actual= _raiz;
        if (actual==null) {
            res=false;
        }
        else{
            res = perteneceRec(elem,actual);
            } 
        return res;
    }  
    
    public boolean perteneceRec(T val,Nodo actual){
        boolean res;
        if (actual==null) {
            res=false;
        }
        else {
            int comparacion=actual.valor.compareTo(val);
            if (comparacion==0) {
                res = true; 
            }
            else if (comparacion>0) {
                res= perteneceRec(val,actual.izq);
            }
            else {
                res= perteneceRec(val,actual.der);
            }
        }
        return res;
    }

    public void eliminar(T elem){
        Nodo actual=this._raiz;
        if (this.pertenece(elem)){
            if (actual.der==null && actual.izq==null) {
                if (actual.prev.der==actual) {
                    
                }
                actual.p=                
            }
        }
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {            
            throw new UnsupportedOperationException("No implementada aun");
        }
    
        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
