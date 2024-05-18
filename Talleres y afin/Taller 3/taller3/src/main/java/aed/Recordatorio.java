package aed;

public class Recordatorio {
    private String _mensaje;
    private Fecha _fecha;
    private Horario _horario;
    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        _mensaje = mensaje;
        _fecha = new Fecha(fecha);
        _horario = new Horario(horario.hora(),horario.minutos()); //  seria lo mismo si voy a horario y defino un constructor que le paso un horario y hace esta asignacion de hora y minutos
    }

    public Horario horario() {
        return _horario;
    }

    public Fecha fecha() {
        return new Fecha(_fecha); //para evitar el aliasing creo un nuevo objeto fecha y le paso la variable privada en vez de pasarsela directamente
    }

    public String mensaje() {
        return _mensaje;
    }

    @Override
    public String toString() {
        StringBuffer modificar = new StringBuffer();
        modificar.append(_mensaje);
        modificar.append(" @ ");
        modificar.append(_fecha.toString());
        modificar.append(" ");
        modificar.append(_horario.toString());
        return modificar.toString();
    }

    @Override
    public boolean equals(Object otro) {
        // otro es null
        boolean oen = (otro == null);
        // clase es distinta
        boolean cd = otro.getClass() != this.getClass();
        if (oen || cd) {
            return false;
        }
        else {
            Recordatorio otroRecordatorio = (Recordatorio) otro;
            return this._mensaje == otroRecordatorio._mensaje 
            && this._fecha.equals(otroRecordatorio._fecha)
            && this._horario.equals(otroRecordatorio._horario);
        }
    }

}
