package proyecto;
/**
 * Se importan las bibliotecas
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
    
    // Recibe una ruta y para utilizar el archivo 
    public Archivo(String ruta) throws IOException, FileNotFoundException, ArchivoNoValidoException {
        registros = new ArrayList();
        indice = new ArrayList();
        if (ruta.isEmpty())
            throw new IOException("No se puede crear un objeto Archivo sin un nombre de archivo"); //Excepción si el archivo no tiene nombre
        nombreArchivo = ruta;
        validarArchivo(); // validar archivo
    }
       
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
    
    private long moverAIndice() throws IOException {
        estructura.seek(3);
        byte []b = new byte[3];
        estructura.read(b);
        int posIndice = Conversion.de3BytesAInt(b);
        estructura.seek(posIndice);
        return posIndice;
    }
    // Cuenta la cantidad de registro
    public int contarRegistros() throws IOException{
        moverAIndice();
        byte []b = new byte[3];
        estructura.read(b);
        return Conversion.de3BytesAInt(b);
    }
    
    // Contador para obtener el numero de registros
    public void leerIndice() throws IOException{
        int contador = contarRegistros();
        indice.clear();
        if (contador > 0)
            for (int i = 0; i < contador; i++ ) {
                indice.add(ManejadorArchivo.leerReferenciaIndice(estructura));
            }
    }
    
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
    
    // Función para agregar registros
    public void agregarRegistro(Registro r){
        registros.add(r);
    }
    
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

    // Cierra el programa
    public void cerrar() throws IOException{
        estructura.close();
    }
    // Obtiene numero de registros
    public ArrayList<Registro> getRegistros() {
        return registros;
    }
    
    //Obtiene indice
    public ArrayList<Referencia> getIndice() {
        return indice;
    }
    
}
