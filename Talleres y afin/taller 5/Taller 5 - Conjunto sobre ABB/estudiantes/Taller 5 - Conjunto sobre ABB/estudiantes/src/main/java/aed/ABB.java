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
      
    private void insertarRec(T val, Nodo actual){
        int comparacion = actual.valor.compareTo(val);
        Nodo nuevo = new Nodo(val);
        if (comparacion>0) {
            if (actual.izq==null) {
                actual.izq=nuevo;
                nuevo.prev=actual;
                _cardinal+=1;     
            }   
            else{
                insertarRec(val, actual.izq);
            }                             
        } 
        else if (comparacion<0) {
            if (actual.der==null) {
                actual.der=nuevo;
                nuevo.prev=actual;
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
    
    private boolean perteneceRec(T val,Nodo actual){
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
        Nodo actual=_raiz;
        if (this.pertenece(elem)){
            if (_cardinal==1) {//esto porque necesito comtemplar el caso en que tengo un arbol con un solo elemento y para simplificar el tratamiento y evitar contemplar innumerables casos me conviene hacerlo asi ya que es un solo caso y asi no lo pongo en la recursion y asì en la recursion ya no tengo que separar el caso que la reaiz sea el unico elemento  
                _raiz=null;                
            }
            else{
            eliminarRec(elem,actual);
            }
        }
    }

    private void eliminarRec(T val, Nodo actual){
        int comparacion=actual.valor.compareTo(val);
        if (comparacion>0) {
            eliminarRec(val,actual.izq);                
            
        }
        else if (comparacion<0) {
            eliminarRec(val,actual.der);
        }
        else{
            if (actual.der==null && actual.izq==null) {
                if (actual.prev.izq==actual){
                    actual.prev.izq=null;
                    _cardinal-=1;
                }
                else if (actual.prev.der==actual){
                    actual.prev.der=null;
                    _cardinal-=1;                        
                }
                actual.prev=null;
                
            }
            else if (actual.der==null || actual.izq==null){ //en realidad este caso es que actual.der!=null || actual.izq!=null pero eso incluiria el caso en el que ninguno de los dos sea nulo y ese lo quiero dejar en el else por lo tanto lo escribo asi y por como evalua el compilador de arriba a abajo al entrar en este if clause ya se que al menos uno de los dos no es null pero que no pasa que los dos son nul porque en ese caso entraria en el ifclause de arriba 
                if (actual.der==null) { 
                    if (actual.prev.izq==actual){ //estoy asumiendo también que actual.prev no es null ya que para poder llegar a actual==val todos los anteriores no debian ser distintos de null
                        actual.prev.izq=actual.izq;
                        actual.izq.prev=actual.prev;    
                        _cardinal-=1;                           
                    }
                    else if (actual.prev.der==actual){
                        actual.prev.der=actual.izq;
                        actual.izq.prev=actual.prev;
                        _cardinal-=1;
                    }
                    actual.prev=null; //este
                    actual.izq=null; //y este creo que debido al garbage collector de Java no es necesario pero me parecio como que no estaba completamente desconectado si tenia referencias a los otros nodos aunque no tuvieran referencias a este y no se pueda llegar desde los otros a este pero si se podria llegar desde este a los otros
                }
                else if (actual.izq==null) { 
                    if (actual.prev.izq==actual){ //estoy asumiendo también que actual.prev no es null ya que para poder llegar a actual==val todos los anteriores no debian ser distintos de null
                        actual.prev.izq=actual.der;
                        actual.der.prev=actual.prev;      
                        _cardinal-=1;                         
                    }
                    else if (actual.prev.der==actual){
                        actual.prev.der=actual.der;
                        actual.der.prev=actual.prev;  
                        _cardinal-=1;
                    }
                    actual.prev=null; //este
                    actual.der=null; //y este creo que debido al garbage collector de Java no es necesario pero me parecio como que no estaba completamente desconectado si tenia referencias a los otros nodos aunque no tuvieran referencias a este y no se pueda llegar desde los otros a este pero si se podria llegar desde este a los otros 
                }
            }
            else{// hay un subarbol derecho e izquierdo luego del que quiero eliminar
                Nodo siguiente=sucesorInmediato(actual);
                actual.valor=siguiente.valor;//sobreescribo el valor del elemento a eliminar con el valor del sucesor inmediato
                eliminarRec(actual.valor,siguiente);
            }
            
        }
    }
    private Nodo sucesorInmediato(Nodo actual){
        actual=actual.der;       
        while (actual.izq!=null){
            actual=actual.izq;
        }
        return actual;
    }




    @Override // el override no es necesario pero es una ayuda para el programador para hacerle notar que esta reescribiendo la definicion de la funcion y java se asegura que es una reescritura de la funcion previa si lo pongo
    public String toString(){
        Nodo actual=this._raiz;
        while (actual.izq!=null){
        //while (actual.izq.valor!=this.minimo()){ //actual".izq".valor la parte entre comillas es para que salga del ciclo en el elemento anterior al minimo y así n ome quedo con la posicion del minimo sino con la del anterior ya que no necesito la posiciòn del minimo para saber su valor ya que tengo un metodo que lo calcula y se que dado que esta ordenado el arbol el elemento que este antes del minimo es el inmediatamente anterior a este asi que si paro un poco antes despues en el while siguiente me sirve
            actual=actual.izq;                // el minimo es el valor menor de todos asi que no tiene sentido separar en los casos en los que actual sea mayor que el minimo y actual sea menor que el minimo y que salga cuando sea igual ya que el caso de menor que el minimo no deberia existir si hice las cosas bien
        }
        StringBuffer modif=new StringBuffer();
        T max =this.maximo();
        T min =this.minimo();
        while (actual.valor!=max) {
            if (actual.valor==min) {
                modif.append("{");
                modif.append(actual.valor);                
            }
            else if (actual.valor==max) {
                modif.append(actual.valor);
                modif.append("}");
            }
            else{
                modif.append("; ");
                modif.append(actual.valor); 
                modif.append(", ");
            }
            sucesor(actual);                     
        }
        return modif.toString();
    }

    private Nodo sucesor(Nodo actual){
        if (_cardinal>=2) { // esto porque si tiene 1 elemento no puede tener sucesor a ese y si no tiene elementos en el arbol bueno no puedo comparar nada asi que tampoco  
            if (actual.der==null) { //esto porque los descendientes izquierdos no me interesan ya que como empiezo en el minimo y voy al siguiente en orden creciente y los descendientes izquierdos son menores asi que en principio ya los contemple y entonces esto lo que hace es generalizar para incluir tanto que actual.der==0 && actual.izq==0 como el caso en que actual.der==0 && actual.izq!=0 porque no me interesa distinguirlos ya que voy a hacer lo mismo que es ir al antesesor
                T valorComparar=actual.valor;//esto referencia a la posicion del valor no a actual asi que incluso si se modifica
                while (valorComparar.compareTo(actual.valor)>=0) { //aqui no me preocupo por el caso de que llegue a la raiz y quiera seguir subiendo siendo que la raiz no tiene nodo previo ya que ese caso no puede pasar ya que si llego hasta la raiz ese es el que sigue ya que es mayor que todos los de la izquierda y no hay repetidos por tanto si subio hasta la raiz y todo estaba bien significa que todos los que habia abajo eran menores que el que quiero comparar y entonces el sucesor inmediato es la raiz ya que los de la derecha de la raiz son mayores a esta
                    actual=actual.prev;      
                }      
            }       
            else if (actual.der!=null) {
                sucesorInmediato(actual);         
            }            
        }
        return actual;
    }
    private class ABB_Iterador implements Iterador<T> {
        private Nodo _porSalir; //hace referencia a que es el elemento que esta "por salir" a continuacion en el recorrido
        
        private ABB_Iterador(){
            _porSalir=_raiz;
            while (_porSalir.izq!=null) { 
                _porSalir=_porSalir.izq;
            }
        }

        public boolean haySiguiente() {  
            return _porSalir.valor.compareTo(maximo())<=0;
        }
        public T siguiente() {//asumo que ya hicieron el haysiguiente antes de ejecutar esta funcion
            T sig=_porSalir.valor;
            _porSalir=sucesor(_porSalir);
            return sig;
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
