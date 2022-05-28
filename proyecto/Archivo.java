package proyecto;
/**
 * importacion de librerias
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/*
Archivo representa a los archivos mp3 que va a leer el propgrama
@author Roger Solano
*/
public class Archivo {
    private final String nombreArchivo;
    private final String marcaDeInicio = "jj5";
    private RandomAccessFile estructura;
    private final ArrayList<Registro> registros;
    private final ArrayList<Referencia> indice;
    private int numeroDeRegistros;
    
    /**
     * Constructor de la clase
     * @param ruta
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ArchivoNoValidoException 
     */
    public Archivo(String ruta) throws IOException, FileNotFoundException, ArchivoNoValidoException {
        registros = new ArrayList();
        indice = new ArrayList();
        if (ruta.isEmpty())
            throw new IOException("No se puede crear un objeto Archivo sin un nombre de archivo"); //Excepción si el archivo no tiene nombre
        nombreArchivo = ruta;
        validarArchivo(); // validar archivo
    }
    
    /**
     * Valida el archivo que se va a cargar
     * @return boolean
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ArchivoNoValidoException 
     */
    public final boolean validarArchivo() throws FileNotFoundException, IOException, ArchivoNoValidoException{
        File archivo;
        archivo = new File(nombreArchivo);
        if (! archivo.exists()) {
            estructura = new RandomAccessFile(nombreArchivo, "rw");
            estructura.writeBytes(marcaDeInicio);
            estructura.write(Conversion.deLongA3Bytes(estructura.getFilePointer()+3));
            byte []cantidadDatos = {0,0,0};
            estructura.write(cantidadDatos);
            estructura.getFD().sync();
        } 
        else {
            estructura = new RandomAccessFile(nombreArchivo, "rw");
            byte []marca = new byte[marcaDeInicio.length()];
            estructura.read(marca);
            if (! marcaDeInicio.equals(new String(marca))){
                throw new ArchivoNoValidoException();
            }
        }
        return true;
    }
    
    /**
     * mueve la posicion del indice
     * @return posIndice
     * @throws IOException 
     */
    private long moverAIndice() throws IOException {
        estructura.seek(3);
        byte []b = new byte[3];
        estructura.read(b);
        int posIndice = Conversion.de3BytesAInt(b);
        estructura.seek(posIndice);
        return posIndice;
    }
    
    /**
     * Cuena la cantidad de registros que existen
     * @return
     * @throws IOException 
     */
    public int contarRegistros() throws IOException{
        moverAIndice();
        byte []b = new byte[3];
        estructura.read(b);
        return Conversion.de3BytesAInt(b);
    }
    
    /**
     * Lee la posicion actual del indice
     * @throws IOException 
     */
    public void leerIndice() throws IOException{
        int contador = contarRegistros();
        indice.clear();
        if (contador > 0)
            for (int i = 0; i < contador; i++ ) {
                indice.add(ManejadorArchivo.leerReferenciaIndice(estructura));
            }
    }
    
    /**
     * Permite buscar una cancion 
     * @param cancion
     * @return
     * @throws IOException
     * @throws ArchivoNoValidoException 
     */
    public Registro buscarPersona(String cancion) throws IOException, ArchivoNoValidoException {
        Registro r = null;
        leerIndice();
        int indiceReferencia = indice.indexOf(new Referencia(cancion));
        if (indiceReferencia >= 0) {
            Referencia ref = indice.get(indiceReferencia);
            estructura.seek(ref.getPosicion());
            r = ManejadorArchivo.leerRegistro(estructura);
        }
        else{
            System.out.println("no encontrado");
        }    
        return r;
    }
    
    /**
     * agrega un nuevo registro
     * @param r 
     */
    public void agregarRegistro(Registro r){
        registros.add(r);
    }
    
    /**
     * escribe los datos de un registro
     * @throws IOException 
     */
    public void escribirDatos() throws IOException{
        estructura.seek(6);
        indice.clear(); // limpia el indice
        
        for (Registro r: registros) {
            indice.add(new Referencia(r.getNombreCancion(), estructura.getFilePointer()));
            ManejadorArchivo.escribirRegistro(r, estructura);
        }
        indice.sort(null);
        long posicionIndice = estructura.getFilePointer();
        estructura.write(Conversion.deLongA3Bytes(registros.size()));
        for (Referencia f: indice) {
            ManejadorArchivo.escribirReferenciaIndice(f, estructura);
        }
        estructura.setLength(estructura.getFilePointer());
        estructura.seek(3);
        estructura.write(Conversion.deLongA3Bytes(posicionIndice));  
    }

    /**
     * Cierra el programa
     * @throws IOException 
     */
    public void cerrar() throws IOException{
        estructura.close();
    }
    /**
     * Obtiene los registros actuales
     * @return 
     */
    public ArrayList<Registro> getRegistros() {
        return registros;
    }
    
    /**
     * Retorna el indice
     * @return 
     */
    public ArrayList<Referencia> getIndice() {
        return indice;
    }
    
}