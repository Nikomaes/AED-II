package aed;

class ArregloRedimensionableDeRecordatorios implements SecuenciaDeRecordatorios {
    private Recordatorio[] _arregloDeRecordatorios;
    public ArregloRedimensionableDeRecordatorios() {
        _arregloDeRecordatorios=new Recordatorio[0] ;
    }

   public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        vector.copiar();
    }

    public int longitud() {
        return _arregloDeRecordatorios.length;
    }

    public void agregarAtras(Recordatorio i) {
        Recordatorio[] nuevoarreglo = new Recordatorio[longitud()+1];
        System.arraycopy(_arregloDeRecordatorios,0,nuevoarreglo,0, longitud()); 
        nuevoarreglo[nuevoarreglo.length-1] = i;
        _arregloDeRecordatorios = nuevoarreglo;
    }

    public Recordatorio obtener(int i) {
        return _arregloDeRecordatorios[i];
    }

    public void quitarAtras() {
        Recordatorio[] nuevoquitado = new Recordatorio[longitud()-1];
        System.arraycopy(_arregloDeRecordatorios,0,nuevoquitado,0,longitud()-1); 
        _arregloDeRecordatorios = nuevoquitado;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        _arregloDeRecordatorios[indice]=valor;

    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        ArregloRedimensionableDeRecordatorios copiado = new ArregloRedimensionableDeRecordatorios ;
        for (int i = 0; i < this._arregloDeRecordatorios.length; i++) {
            //copiado._arregloDeRecordatorios[i]=this._arregloDeRecordatorios[i]; ¿esto tambien se puede? se deberia poder porque obtener hace exactamente la misma operación pero lo que no se es si traeria problemas el usar la variable privada     
            copiado._arregloDeRecordatorios[i]=this.obtener(i);
        }
        return copiado;
    }

}
