package aed;

class Debugging {
    boolean xor(boolean a, boolean b) {
        return (a || b) && !(a && b);
    }

    boolean iguales(int[] xs, int[] ys) {
        boolean res = true;
        if (xs.length != ys.length) {
            res=false;
        }
        else {
            for (int i = 0; i < xs.length; i++) {
                if (xs[i] != ys[i]) {
                    res = false;
                }
            }
        }
        return res;
    }

    boolean ordenado(int[] xs) {
        boolean res = true;
        if (xs.length==1) {
            res=true;
        }
        else {
            for (int i = 1; i < xs.length; i++) {
                if (xs[i-1] > xs [i]) {
                    res = false;
                }
            }
        }
        return res;
    }

    int maximo(int[] xs) {
        int res = xs[0];
        for (int i = 1; i < xs.length; i++) {
            if (xs[i] > res) res = xs[i];
        }
        return res;
    }

    boolean todosPositivos(int[] xs) {
        boolean res = true;
        for (int x : xs) {
            if (x <= 0) {
                res = false;
            }
        }
        return res;
    }
}
