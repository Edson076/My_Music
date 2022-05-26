package proyecto;
/*
Excepción de archivo
@author Roger Solano
*/
public class ArchivoNoValidoException extends Exception{
    private String mensaje = "El archivo no tiene la estructura de una Agenda";
    
    
    public ArchivoNoValidoException() {
        super(); 
    }   

    //Recibe el mensaje y lo lee
    public ArchivoNoValidoException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }
 
    public ArchivoNoValidoException(Throwable causa) {
        super(causa);
    }
    
    // Recibe mensaje y causa 
    public ArchivoNoValidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
    
    
}
