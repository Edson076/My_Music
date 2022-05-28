package proyecto;

/**
 * En caso de que un archivo no tenga la estructura adecuada envia un error
 * @author elias
 */
public class ArchivoNoValidoException extends Exception{
    private String mensaje = "El archivo no tiene la estructura de una Agenda";
    
    
    /**
     * Constructor sin paramatros
     */
    public ArchivoNoValidoException() {
        super();
    }   
    
    /**
     * Constructor con parametro
     * @param mensaje 
     */
    public ArchivoNoValidoException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }
    
    /**
     * Recibe la causa del error
     * @param causa 
     */
    public ArchivoNoValidoException(Throwable causa) {
        super(causa);
    }

    /**
     * almacena los valores del mensaje y la causa
     * @param mensaje
     * @param causa 
     */
    public ArchivoNoValidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.mensaje = mensaje;
    }

    /**
     * devuelve el mensaje
     * @return 
     */
    @Override
    public String toString() {
        return mensaje;
    }

    /**
     * recibe el mensaje
     * @return 
     */
    @Override
    public String getMessage() {
        return mensaje;
    }
    
    
}
