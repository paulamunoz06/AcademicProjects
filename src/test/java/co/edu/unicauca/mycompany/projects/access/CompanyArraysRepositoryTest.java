/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import co.edu.unicauca.mycompany.projects.domain.entities.Sector;
import co.edu.unicauca.mycompany.projects.domain.services.CompanyServiceTest;
import co.edu.unicauca.mycompany.projects.infra.ValidationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para CompanyArraysRepository.
 * Verifica las funcionalidades de listar y guardar empresas en un repositorio basado en arrays.
 * 
 * @author Paula Munoz
 */
public class CompanyArraysRepositoryTest {
    
    /**
     * Verifica que el método listAll devuelva correctamente los datos cargados por defecto.
     */
    @Test
    public void testListAll_PorDefecto() {
        System.out.println("Listar el repositorio arrays por defecto");
        try {
            CompanyArraysRepository instance = new CompanyArraysRepository();
            
            // Llamado al método
            List<Company> result = instance.listAll();
            
            // Verificación con los datos cargados por defecto
            assertNotNull(result);
            assertEquals(4, result.size());
            assertEquals("012-12-22", result.get(0).getNit());
            assertEquals("Lacteos Popayan", result.get(0).getName());
            assertEquals("314334334", result.get(1).getPhone());
            assertEquals("www.electromillonaria.com", result.get(1).getPageWeb());
            assertEquals(Sector.TECHNOLOGY, result.get(2).getSector());
            assertEquals("syp@gmail.com", result.get(2).getEmail());
            assertEquals("123", result.get(3).getPassword());

        } catch (Exception ex) {
            Logger.getLogger(CompanyServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Verifica que se pueda guardar una nueva empresa y que el método save devuelva true.
     */
    @Test
    void testSaveCompany_SuccessReturn() {
        System.out.println("Guardar una compania y validar por el retorno");
        try {
            CompanyArraysRepository instance = new CompanyArraysRepository();
            Company newCompany = new Company("123459", "Empresa D","3434345", "www.mipagina4.com", Sector.SERVICES, "gerente4@gmail.com", "123");
            
            // Verificar el retorno
            boolean result = instance.save(newCompany);
            assertTrue(result);

        } catch (ValidationException ex) {
            Logger.getLogger(CompanyServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Verifica que una empresa guardada efectivamente se almacene en la lista del repositorio.
     */
    @Test
    void testSaveCompany_SuccessList() {
        System.out.println("Guardar una compania y validar por repositorio");
        try {
            CompanyArraysRepository instance = new CompanyArraysRepository();
            Company newCompany = new Company("12345", "Empresa E","343434", "www.mipagina5.com", Sector.SERVICES, "gerente5@gmail.com", "127");

            instance.save(newCompany);

            // Verificar que se guarda en la lista
            List<Company> resultVerificar = instance.listAll();
            assertEquals("12345", resultVerificar.get(resultVerificar.size()-1).getNit());

        } catch (ValidationException ex) {
            Logger.getLogger(CompanyServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Verifica que no se pueda guardar una empresa con un NIT ya existente, generando una excepción.
     */
    @Test
    void testSaveCompany_Failure_ExistNIT() {
        System.out.println("Guardar una compania con un NIT existente");
        
        CompanyArraysRepository instance = new CompanyArraysRepository();
        Company newCompany = new Company("012-12-22", "Empresa E","343434", "www.mipagina5.com", Sector.SERVICES, "gerente5@gmail.com", "127");

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            instance.save(newCompany); // Se espera que lance la excepción
        });

        assertEquals("NIT existente", exception.getMessage()); // Verifica el mensaje
    }

}
