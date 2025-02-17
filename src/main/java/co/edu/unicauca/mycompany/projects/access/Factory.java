package co.edu.unicauca.mycompany.projects.access;

import java.util.HashMap;
import java.util.Map;

/**
 * Fabrica que se encarga de instanciar ProductRepository o cualquier otro que
 * se cree en el futuro.
 *
 * @author Libardo, Julio
 */
public class Factory {

    /**
     * Instancia única de la fábrica (Singleton).
     */
    private static Factory instance;

    /**
     * Diccionario que almacena las implementaciones de {@link ICompanyRepository} disponibles.
     */
    private final Map<String, ICompanyRepository> dictionary;

    /**
     * Constructor privado que inicializa el diccionario con las implementaciones disponibles.
     */
    private Factory() {
        dictionary = new HashMap<>();
        dictionary.put("ARRAYS", new CompanyArraysRepository());
        dictionary.put("SQLITE", new CompanySqliteRepository());
    }

    /**
     * Obtiene la única instancia de la fábrica (Singleton).
     *
     * @return La instancia única de {@code Factory}.
     */
    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    /**
     * Método que crea una instancia concreta de la jerarquia ICompanyRepository
     *
     * @param repository cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IProductRepository
     */
    public ICompanyRepository getRepository(String repository) {
        ICompanyRepository result = null;
        if (dictionary.containsKey(repository)) {
            result = dictionary.get(repository);
        }
        return result;
    }
}
