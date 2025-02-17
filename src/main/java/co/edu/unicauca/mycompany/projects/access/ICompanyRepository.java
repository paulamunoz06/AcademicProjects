package co.edu.unicauca.mycompany.projects.access;


import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import java.util.List;

/**
 * Interfaz que define la gestión de empresas en un repositorio de datos.
 *
 * @author Libardo, Julio
 */
public interface ICompanyRepository {
    /**
     * Guarda una nueva empresa en el repositorio.
     *
     * @param newCompany La empresa que se desea almacenar.
     * @return {@code true} si la empresa se guardó correctamente, {@code false} en caso contrario.
     * @throws Exception Si ocurre un error durante el proceso de almacenamiento.
     */
    boolean save(Company newCompany) throws Exception;
    
    /**
     * Recupera la lista de todas las empresas almacenadas en el repositorio.
     *
     * @return Una lista de objetos {@link Company}.
     * @throws Exception Si ocurre un error durante la consulta de datos.
     */
    List<Company> listAll() throws Exception;
}
