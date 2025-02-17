package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.access.ICompanyRepository;
import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import java.util.List;

/**
 * Servicio para la gestión de empresas.
 * 
 * @author Libardo Pantoja, Julio Hurtado
 */
public class CompanyService {
    /**
     * Validador de los datos de las empresas.
     */
    private IValidation validator;
    
    /**
     * Repositorio donde se almacenan las empresas.
     */
    private final ICompanyRepository repository;

    /**
     * Constructor que inicializa el servicio con un repositorio específico.
     *
     * @param repository El repositorio donde se almacenarán las empresas.
     */
    public CompanyService(ICompanyRepository repository) {
        this.repository = repository;
    }
    
    /**
     * Obtiene la lista de todas las empresas almacenadas en el repositorio.
     *
     * @return Lista de empresas.
     * @throws Exception Si ocurre un error al obtener la lista.
     */
    public List<Company> getAllCompanies() throws Exception {
        return repository.listAll();
    }

    /**
     * Guarda una nueva empresa en el repositorio después de validarla.
     *
     * @param newCompany La empresa a guardar.
     * @return {@code true} si la empresa fue guardada correctamente.
     * @throws Exception Si la validación falla o hay un error en el guardado.
     */
    public boolean saveCompany(Company newCompany) throws Exception {
        validator = new DataValidationCompany(newCompany);
        if(validator.isValid()){
            repository.save(newCompany);
            return true;
        }
        return false;
    }

}
