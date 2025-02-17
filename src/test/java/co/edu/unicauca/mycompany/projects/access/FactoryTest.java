/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la clase Factory.
 * Verifica que la obtención de repositorios funcione correctamente.
 * 
 * @author Paula Munoz
 */
public class FactoryTest {
    
    /**
     * Prueba unitaria para el método getRepository de la clase Factory.
     * Se verifica que el repositorio de tipo "SQLITE" se obtenga correctamente
     * y contenga los mismos datos esperados.
     */
    @Test
    public void testGetRepository_SLITE() {
        System.out.println("Obtener repositorio SQLITE");
        
        try {
            String repository = "SQLITE";
            
            ICompanyRepository expResult = new CompanySqliteRepository();
            List<Company> listaExpResult = expResult.listAll();
            ICompanyRepository result = Factory.getInstance().getRepository(repository);
            List<Company> listaResult = result.listAll();
            
            if(listaExpResult.size()==listaResult.size()){
                for (int i = 0; i < listaExpResult.size(); i++) {
                    Company ExpCompania = listaExpResult.get(i);
                    Company Compania = listaResult.get(i);
                    assertEquals(ExpCompania.getNit(), Compania.getNit());
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(FactoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba unitaria para el método getRepository de la clase Factory.
     * Se verifica que el repositorio de tipo "ARRAYS" se obtenga correctamente
     * y contenga los mismos datos esperados.
     */
    @Test
    public void testGetRepository_ARRAYS() {
        System.out.println("Obtener repositorio ARRAYS");
        
        try {
            String repository = "ARRAYS";
            
            ICompanyRepository expResult = new CompanyArraysRepository();
            List<Company> listaExpResult = expResult.listAll();
            ICompanyRepository result = Factory.getInstance().getRepository(repository);
            List<Company> listaResult = result.listAll();
            
            if(listaExpResult.size()==listaResult.size()){
                for (int i = 0; i < listaExpResult.size(); i++) {
                    Company ExpCompania = listaExpResult.get(i);
                    Company Compania = listaResult.get(i);
                    assertEquals(ExpCompania.getNit(), Compania.getNit());
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(FactoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
