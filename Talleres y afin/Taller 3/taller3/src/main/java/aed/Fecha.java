package aed;

public class Fecha {
    private int _dia ;
    private int _mes ;
    public Fecha(int dia, int mes) {
        _dia=dia;
        _mes=mes;
    }

    public Fecha(Fecha fecha) {
        _dia=fecha.dia();
        _mes=fecha.mes();

    }

    public Integer dia() {
        return _dia;
    }

    public Integer mes() {
        return _mes;
    }
    @Override
    public String toString() {
        StringBuffer modificar = new StringBuffer();
        modificar.append(dia());
        modificar.append("/");
        modificar.append(mes());
        return modificar.toString();
    }

    @Override
    public boolean equals(Object otra) {
        // otra es null
        boolean oen = (otra == null);
        // clase es distinta
        boolean cd = otra.getClass() != this.getClass();
        if (oen || cd) {
            return false;
        }
        else{
            Fecha otraFecha = (Fecha) otra;
            return _dia== otraFecha._dia
            && _mes==otraFecha._mes;
        }
    }

    public void incrementarDia() {
        if (dia()<diasEnMes(mes()) && (mes()<=12 && mes()>0)) {
            _dia+=1;            
        }
        else if (dia()==diasEnMes(mes())) {
            if (mes()<12 && mes()>0) {
                _mes+=1;
                _dia=1;              
            }
            else if (this.mes()==12) {
                _mes=1;
                _dia=1;
            }                      
        } 
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
