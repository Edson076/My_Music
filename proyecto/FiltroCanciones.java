package proyecto;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * 
 * @author Roger
 */
public class FiltroCanciones extends FileFilter{
    private final String extension;
    private final String description;
    
    /**
     * Constructor
     * @param extension
     * @param description 
     */
    public FiltroCanciones(String extension, String description){
        this.extension = extension;
        this.description = description;
    }
    
    /**
     * recibe un archivo del directorio
     * @param file
     * @return 
     */
    @Override
    public boolean accept(File file) {
        if(file.isDirectory()){
            return true;
        }
        return file.getName().endsWith(extension);
    }

    /**
     * recibe la descripcion del archivo 
     * @return 
     */
    @Override
    public String getDescription() {
        return description + String.format(" (*%s)", extension);
    }
}
