package proyecto;

/**
 * 
 * @author Roger
 */
public class Registro {
    private String Artista;
    private String Grupo;
    private String NombreCancion;
    private String Album;
    private String AñoAlbum;
    private String Comentario;
    private String Genero;
  
    /**
     * Constructor sin parametros
     */
    public Registro() {
        this.Artista = new String();
        this.Grupo = new String();
        this.NombreCancion = new String();
        this.Album = new String();
        this.AñoAlbum = new String();
        this.Comentario = new String();
        this.Genero = new String();
    }

    /**
     * Constructor con parametros
     * @param Artista
     * @param Grupo
     * @param NombreCancion
     * @param Album
     * @param AñoAlbum
     * @param Comentario
     * @param Genero 
     */
    public Registro(String Artista, String Grupo, String NombreCancion, String Album, String AñoAlbum, String Comentario, String Genero) {
        this.Artista = Artista;
        this.Grupo = Grupo;
        this.NombreCancion = NombreCancion;
        this.Album = Album;
        this.AñoAlbum = AñoAlbum;
        this.Comentario = Comentario;
        this.Genero = Genero;
    }
    
    /**
     * recibe el genero
     * @return 
     */
    public String getGenero() {
        return Genero;
    }
    
    /**
     * Actualiza el genero
     * @param Genero 
     */
    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    /**
     * recibe el artista
     * @return 
     */
    public String getArtista() {
        return Artista;
    }

    /**
     * actualiza el artista
     * @param Artista 
     */
    public void setArtista(String Artista) {
        this.Artista = Artista;
    }

    /**
     * RECIBE EL GRUPO
     * @return 
     */
    public String getGrupo() {
        return Grupo;
    }

    /**
     * ACTUALIZA EL GRUPO
     * @param Grupo 
     */
    public void setGrupo(String Grupo) {
        this.Grupo = Grupo;
    }

    /**
     * RECIBE EL NOMBRE DE LA CANCION
     * @return 
     */
    public String getNombreCancion() {
        return NombreCancion;
    }

    /**
     * ACTUALIZA EL NOMBRE DE LA CANCION
     * @param NombreCancion 
     */
    public void setNombreCancion(String NombreCancion) {
        this.NombreCancion = NombreCancion;
    }

    /**
     * RECIBE EL NOMBRE DEL ALBUM
     * @return 
     */
    public String getAlbum() {
        return Album;
    }
    
    /**
     * ACTUALIZA EL NOMBRE DEL ALBUM
     * @param Album 
     */
    public void setAlbum(String Album) {
        this.Album = Album;
    }

    /**
     * RECIBE EL AÑO DEL ALBUM
     * @return 
     */
    public String getAñoAlbum() {
        return AñoAlbum;
    }

    /**
     * ACTUALIZA EL AÑO DEL ALBUM
     * @param AñoAlbum 
     */
    public void setAñoAlbum(String AñoAlbum) {
        this.AñoAlbum = AñoAlbum;
    }
    
    /**
     * RECIBE UN COMENTARIO
     * @return 
     */
    public String getComentario() {
        return Comentario;
    }

    /**
     * ACTUALIZA EL COMENTARIO
     * @param Comentario 
     */
    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }
    
    /**
     * DEVUELVE UNA CADENA CON LOS VALORES DE LOS GETTER Y SETTERS
     * @return 
     */
    @Override
    public String toString() {
        return "Artista: "+Artista+
                "\nGrupo: "+Grupo+
                "\nNombre Cancion: "+NombreCancion+
                "\nAlbum: "+Album+
                "\nAño Del Album: "+AñoAlbum+
                "\nComentario: "+Comentario+
                "\nGenero: "+Genero;
    }
}
