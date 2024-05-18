package aed;

public class Horario {  
    private int _hora;
    private int _minutos;
    public Horario(int hora, int minutos) {
        _hora=hora;
        _minutos=minutos;        
    }

    public int hora() {
        return _hora;
    }

    public int minutos() {
        return _minutos;
    }

    @Override
    public String toString() {
        StringBuffer modificar = new StringBuffer();
        modificar.append(String.valueOf(_hora));
        modificar.append(":");
        modificar.append(minutos());
        return modificar.toString();
    }

    //@Override
    //public String toString() {
    //    StringBuffer modificar = new StringBuffer();
    //    modificar.append(hora());
    //    modificar.append(":");
    //    if (minutos()<10 && minutos()>=0) {
    //        modificar.append("0");
    //    }
    //    modificar.append(minutos());
    //    return modificar.toString();;
    //}

    @Override
    public boolean equals(Object otro) {
        // otra es null
        boolean oen = (otro == null);
        // clase es distinta
        boolean cd = otro.getClass() != this.getClass();
        if (oen || cd) {
            return false;
        }
        else{
            Horario otroHorario = (Horario) otro;
            return _hora== otroHorario._hora
            && _minutos==otroHorario._minutos;
        }
    }

}
