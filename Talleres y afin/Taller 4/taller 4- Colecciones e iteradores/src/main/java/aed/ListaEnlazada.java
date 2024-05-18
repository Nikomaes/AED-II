package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo _primero;
    private Nodo _ultimo;
    private int _tamaño;
    

    private class Nodo {
        T valor;
        Nodo siguient;
        Nodo anter; 

        Nodo(T val) {
            valor =val;
            siguient=null;
            anter=null;
        }
    }

    public ListaEnlazada() {
        _primero=null;
        _ultimo=null;
        _tamaño=0;
    }

    public int longitud() {
        return _tamaño;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (_tamaño==0){
        _primero=nuevo;
        _ultimo=nuevo;
        _tamaño += 1;  
        } else if (_tamaño>0) {
            //Nodo primero =new Nodo(_primero);  // esto entiendo que no es estrictamente necesario hacerlo en una variable u objeto aparte y se lo podria crear directamente en nuevo.siguient aunque como no estoy modificando la misma posición de memoria no es necesario usar el constructor para crear una copia y puedo pasarle directamente la referencia de _primero
            _primero.anter=nuevo;
            nuevo.siguient= _primero ;
            _primero=nuevo;
            _tamaño += 1;
        }     
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (_tamaño==0){
            _primero=nuevo;
            _ultimo=nuevo;
            _tamaño += 1;  
        } else if (_tamaño>0) {;
            _ultimo.siguient=nuevo;
            nuevo.anter= _ultimo;
            _ultimo = nuevo;
            _tamaño ++;
        }
    }

    public T obtener(int i) {
        Nodo actual = _primero;
        for (int j = 0; j < i; j++) {
            actual = actual.siguient;
        } 
        return actual.valor;       
    }

    public void eliminar(int i) {
        Nodo actual = _primero;
        Nodo previo=_primero;
        for (int j = 0; j < i; j++) {
            previo=actual;
            actual=actual.siguient;                    
        }
        if (i==0) {
            if (longitud()==1) {
                _primero=null;
                _ultimo=null;
                _tamaño=0;
            }
            else if (longitud()>1){
                _primero=actual.siguient;
                actual.siguient.anter=null;
                _tamaño= longitud()-1;
            }            
        }
        else if (i==_tamaño-1) {
            _ultimo=actual.anter;
            actual.anter.siguient=null; 
            _tamaño= longitud()-1;                       
        }
        else if (i>0&&i<longitud()){
            previo.siguient=actual.siguient;
            actual.siguient.anter=previo;
            _tamaño= longitud()-1;
        }
    }
    public void modificarPosicion(int indice, T elem) {
        Nodo modificar = _primero;
        for (int j = 0; j < indice; j++) {
             modificar=modificar.siguient;           
        } 
        modificar.valor=elem;
    }

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> listaNueva = new ListaEnlazada<T>(); 
        Nodo actual = _primero;
        while (actual != null) {
            listaNueva.agregarAtras(actual.valor);
            actual= actual.siguient;           
        }
        return listaNueva;        
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        for (int i = 0; i < lista.longitud(); i++) {
           T actual = lista.obtener(i);
           this.agregarAtras(actual);               
        }
        // esto queda de complejidad cuadratica porque tengo un for y adentro obtener tiene un for entonces para reducir la complejidad tendria que usar un nodo actual en lista._primero o lista._ultimo y recorrer con el for con agregatr atras

    }
    
    @Override
    public String toString() {
        StringBuffer modificable = new StringBuffer();
        //T primerValor=_primero.valor;
        T ultimoValor=_ultimo.valor;
        Nodo actual= _primero;
        modificable.append("[");        
        while (actual!=_ultimo) {
            T valor=actual.valor;
            modificable.append(valor);
            modificable.append(", ");
            actual=actual.siguient;            
        }
        modificable.append(ultimoValor);
        modificable.append("]");
        return modificable.toString();
    }

    private class ListaIterador implements Iterador<T> { // en la idea teorica no existe un iterador actual sino que esta el siguiente y el anterior y entonces el iterador es un dedito que apunta al medio de entre ambos y yo lo represento con un nodo apuntando a el noso siguiente en si
    	Nodo siguiente;
        ListaIterador() {
            siguiente=_primero;
        }

        public boolean haySiguiente() {
	        boolean res;
            if (longitud()==0){
                res = false;
            }
            else if (siguiente!=_ultimo) {
                res= siguiente!=null&&siguiente.siguient!=null;  
            }
            else{
                res = true; //esto porque como que el iterador se para en el medio del anteultimo y el ultimo y yo lo represente como un puntero al ultimo entonces en realidad el siguiente no es null sino que es el ultimo en este caso y por eso como luego biene null debo contemplar aparte el caso de estar en el ultimo elemento porque no puedo tomar siguiente de el ultimo y plantear que sea distinto de null ya que en ese caso es igual a null y esta bien porque en realidad el siguiente es el nodo que uso para representarlo y por eso le puse ese nombre
            }
            return res;
        }
        
        public boolean hayAnterior() {
	        boolean res;
            if (siguiente!=_primero.siguient) {
                res = siguiente!=null&&siguiente.anter!=null;
            }
            else if (siguiente==_primero.siguient) {
                res =true;
            }
            else {
                res = false;
            }
            return res;
        }

        public T siguiente() {
	        //if (haySiguiente()) { es un requiere que ya haya siguiente para calcularlo
             T res =siguiente.valor;
            siguiente= siguiente.siguient;
            return res;
            
        }
        

        public T anterior() {
            T res =siguiente.anter.valor;
            siguiente= siguiente.anter;
            return res;
        }
    }

    public Iterador<T> iterador() {
	    ListaIterador nuevo = new ListaIterador() ;
        return nuevo ; 
    }

}
