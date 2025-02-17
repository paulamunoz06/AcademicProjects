package co.edu.unicauca.mycompany.projects.domain.services;
import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import co.edu.unicauca.mycompany.projects.infra.ValidationException;

/**
 * Clase que valida los datos de una empresa ({@link Company}) antes de ser almacenada.
 * 
 * Verifica que los campos obligatorios estén completos y que cumplan con los formatos 
 * establecidos para el correo electrónico y la contraseña.
 *
 * @author Paula Munoz
 */
public class DataValidationCompany implements IValidation{
    private Company company;

    /**
     * Constructor de la clase que recibe una empresa a validar.
     *
     * @param company La empresa cuyos datos serán validados.
     */
    public DataValidationCompany(Company company) {
        this.company = company;
    }

    /**
     * Valida los datos de la empresa asegurando que los campos requeridos no estén vacíos 
     * y que cumplan con los formatos establecidos.
     * 
     * @return {@code true} si la empresa es válida.
     * @throws ValidationException Si algún dato no cumple con las reglas establecidas.
     */
    @Override
    public boolean isValid()throws ValidationException{
        String validationEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        String validationPassword = "^(?=.*[A-Z])(?=.*[!@#$%^&*\\-_.]).{6,}$";
        
        if(company.getNit().isBlank() || company.getNit()==null) throw new ValidationException("El NIT es obligatorio", "nit");
        else if(company.getName().isBlank()  || company.getName()==null) throw new ValidationException("El nombre es obligatorio", "nombre");
        else if(company.getSector()==null || company.getSector().toString().isBlank()) throw new ValidationException("El sector industrial es obligatorio", "sector");
        else if(company.getEmail().isBlank() || company.getEmail()==null) throw new ValidationException("El email es obligatorio", "email");
        else if(company.getPassword().isBlank() || company.getPassword()==null) throw new ValidationException("La contraseña es obligatoria", "password");
        
        if(!company.getEmail().matches(validationEmail)) throw new ValidationException("El correo no es válido", "email");
        if(!company.getPassword().matches(validationPassword)) throw new ValidationException("La contraseña ndebe tener al menos 6 caracteres, una mayúscula y un carácter especial.", "password");
        return true;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    
}
