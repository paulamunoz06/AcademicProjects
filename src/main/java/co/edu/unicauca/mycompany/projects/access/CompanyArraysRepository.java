package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import co.edu.unicauca.mycompany.projects.domain.entities.Sector;
import co.edu.unicauca.mycompany.projects.domain.services.DataValidationCompany;
import co.edu.unicauca.mycompany.projects.infra.ValidationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del repositorio utilizando una lista de arreglos para gestionar 
 * objetos de tipo Company.
 *
 * @author Libardo, Julio, Paula
 */
public class CompanyArraysRepository implements ICompanyRepository {

    /**
     * Lista estática que almacena las empresas registradas.
     */
    public static List<Company> myArray;
    
    /**
     * Constructor que inicializa la lista con algunas empresas predeterminadas.
     */
    public CompanyArraysRepository() {
        myArray = new ArrayList<>();
        myArray.add(new Company("012-12-22", "Lacteos Popayan", "313234123", "www.lacteospopayan.com", Sector.SERVICES, "gerentelacteos@gmail.com", "123"));
        myArray.add(new Company("323-12-56", "Electromillonaria", "314334334", "www.electromillonaria.com", Sector.TECHNOLOGY, "electromillonaria@gmail.com", "123"));
        myArray.add(new Company("867-223-444", "S&P", "3142342344", "www.SP.com", Sector.TECHNOLOGY, "syp@gmail.com", "123"));
        myArray.add(new Company("456-1222-2233", "Solutions", "311454789", "www.solutions.com", Sector.HEALTH, "solutions@gmail.com", "123"));
    }

    /**
     * Guarda una nueva empresa en el repositorio.
     *
     * @param newCompany La empresa que se desea almacenar.
     * @return {@code true} si la empresa se guardó correctamente, {@code false} en caso contrario.
     * @throws ValidationException Si ocurre un error durante el proceso de almacenamiento.
     */
    @Override
    public boolean save(Company newCompany) throws ValidationException{
        if (!existsNit(newCompany.getNit())){
            myArray.add(newCompany);
        }
        else{
            throw new ValidationException("NIT existente","nit");
        }
        return true;
    }

    /**
     * Recupera la lista de todas las empresas almacenadas en el repositorio.
     *
     * @return Una lista de objetos {@link Company}.
     */
    @Override
    public List<Company> listAll() {
        return myArray;
    }
    
    /**
     * Verifica si una empresa con el NIT dado ya está registrada en la lista.
     *
     * @param nit NIT de la empresa a verificar.
     * @return {@code true} si el NIT ya existe, {@code false} en caso contrario.
     */
    private boolean existsNit(String nit){
        for (Company company: myArray){
            if (company.getNit().equals(nit)){
                return true;
            }
        }
        return false;
    }
}
