package aed;

public class Agenda {
    private Fecha _fechaActual;
    private Recordatorio[] _conjRecord;
    public Agenda(Fecha fechaActual) {
        _fechaActual=fechaActual;
        _conjRecord= new Recordatorio[0];
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        Recordatorio[] nuevoRecord= new Recordatorio[_conjRecord.length+1];
        System.arraycopy(_conjRecord,0,nuevoRecord,0,_conjRecord.length);
        nuevoRecord[_conjRecord.length] = recordatorio; 
        _conjRecord = nuevoRecord;
    }

    @Override
    public String toString() {
        StringBuffer modificar = new StringBuffer();
        modificar.append(fechaActual());
        modificar.append("\n");
        modificar.append("=====\n");
        for(int i=0; i<_conjRecord.length;i=i+1){
            if (_conjRecord[i].fecha().equals(fechaActual())) {
                modificar.append(_conjRecord[i]);
                modificar.append("\n");
            }
        }
        return modificar.toString();
    }

    public void incrementarDia() {
        fechaActual().incrementarDia();
    }

    //public Fecha fechaActual() { 
    //    Fecha fechanueva= new Fecha(_fechaActual);
    //    return fechanueva;
    //} este otro metodo era una opcion valida pero redundante e inecesario definir una variable temporal para eso y es mejor devolverlo directamente
    public Fecha fechaActual() {
        return _fechaActual;
    }

}
