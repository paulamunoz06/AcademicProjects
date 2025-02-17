package co.edu.unicauca.mycompany.projects.infra;

/**
 * Excepción para manejar errores de validación en la aplicación.
 * 
 * Se usa cuando un atributo no cumple con los criterios de validación, permitiendo 
 * identificar tanto el mensaje de error como el atributo afectado.
 *
 * @author Paula Munoz
 */
public class ValidationException extends Exception {
    private final String atributoError;
    
    /**
     * Crea una nueva instancia de {@code ValidationException} con un mensaje de error 
     * y el nombre del atributo que causó la excepción.
     *
     * @param msg Mensaje detallado del error.
     * @param atributoError Nombre del atributo que no pasó la validación.
     */
    public ValidationException(String msg, String atributoError) {
        super(msg);
        this.atributoError = atributoError;
    }
    
    /**
     * Obtiene el nombre del atributo que causó la excepción.
     *
     * @return Nombre del atributo con error.
     */
    public String getAtributoError() {
        return atributoError;
    }  
}
