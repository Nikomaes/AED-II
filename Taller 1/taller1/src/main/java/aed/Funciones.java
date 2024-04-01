package aed;

class Funciones {
    int cuadrado(int x) {
        int res = x * x ;
        return res;
    }

    double distancia(double x, double y) {
        double res = Math.sqrt(x*x+y*y);
        return res;
    }

    boolean esPar(int n) {
        boolean res = n % 2 == 0;
        return res;
    }
    //funcion auxiliar para simplificar el codigo en lo que sigue
    boolean divideA (int d, int n) {
        boolean res = n % d == 0;
        return res;
    } 
    boolean esBisiesto(int n) {
        boolean res=true;
        if (divideA( 4, n) && !divideA(100, n)) {
            res=true;            
        }
        else if (divideA(400,n)) {
            res=true;
        }
        else{
            res = false;
        }

        return res;
    }

    int factorialIterativo(int n) {
        int factorial= 1;
        if (n==0) {
            factorial=1;           
        }
        else {
            for (int i=1;
                i<=n;
                i++) {
                    factorial *= i;                    
            }
        }
        return factorial;
    }

    int factorialRecursivo(int n) {
        int factorial = n;
        if (n==0) {
           factorial=1;             
        }
        else {
            factorial *= factorialRecursivo(n-1);
        }
        return factorial;
    }

    boolean esPrimo(int n) {
        int divisores = 0;
        for (int i=1; i<=n; i++) {
            if (divideA(i, n)) {
                divisores += 1;                
            }
            /*else{
              nada
            }*/
             }
        boolean res = divisores == 2;
        return res;
    }

    int sumatoria(int[] numeros) {
        int suma = 0;
        int i = 0;
        while (i<numeros.length) {
            suma +=numeros[i];
            i+=1;
        }
        return suma;
    }

    int busqueda(int[] numeros, int buscado) {
        int indice = 0;
        /*esto guarda en indice el indice del ultimo valor igual 
        al buscado porque los anteriores los sobrescribe hasta 
        el final de la lista numeros incluso 
        si lo hubiera encontrado antes*/
        for (int i=0; i<numeros.length;i++) {
            if (numeros[i]==buscado) {
                indice = i;
            }
        }
        return indice;
    }

    boolean tienePrimo(int[] numeros) {
        boolean res = false;
        for (int i=0; i<numeros.length;i++) {
            if (esPrimo(numeros[i])) {
                res= true;                
            }
        }      
        return res;
    }

    boolean todosPares(int[] numeros) {
        boolean res = true;
        for (int i=0;i<numeros.length;i++) {
            res= res&&divideA(2,numeros[i]);
        }
        return res;
    }

    boolean esPrefijo(String s1, String s2) {
        int i = 0;
        boolean res= true;
        if (s1.length()>s2.length()) {
            res=false;                    
        }
        else {
            while (i<s1.length()) {
                res= res&&(s1.charAt(i)==s2.charAt(i));  
                i++;              
            }
        }
        return res;
    }
    String subsecuencia (String s,int inicio, int fin) {
        String result = "";
        for (int i=inicio;i<fin;i+=1) {
            result = result + (s.charAt(i));
        }
        return result;
    }

    boolean esSufijo(String s1, String s2) {
        boolean res =true;
        if (s1.length()>s2.length()) {
            res=false;          
        }
        else {
           res= res&&esPrefijo(s1,subsecuencia(s2,((s2.length())-(s1.length())),(s2.length())));
        }
        return res;
    }
}
