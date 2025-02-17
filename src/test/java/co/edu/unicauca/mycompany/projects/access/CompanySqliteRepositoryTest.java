/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import co.edu.unicauca.mycompany.projects.domain.entities.Sector;
import co.edu.unicauca.mycompany.projects.infra.ValidationException;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Clase de pruebas unitarias para CompanySqliteRepository.
 * Verifica la creación de la base de datos, la conexión, 
 * la desconexión y la inserción de datos en la base de datos SQLite.
 * 
 * @author Paula Munoz
 */

public class CompanySqliteRepositoryTest {
    
    /**
     * Verifica que el archivo de la base de datos se cree correctamente.
     */
    @Test
    void testDatabase_FileExists() {
        System.out.println("Creacion de la BDD");
        
        CompanySqliteRepository instance = new CompanySqliteRepository();
        File dbFile = new File("./mydatabase.db");
        
        assertTrue(dbFile.exists(), "El archivo de la base de datos no fue creado.");
    }
    
    /**
     * Verifica que un archivo de base de datos inexistente no sea detectado por error.
     */
    @Test
    void testDatabase_FileNoExists() {
        System.out.println("Creacion de la BDD en una ruta incorrecta");

        File dbFile = new File("./mydatabases.db");
        
        assertFalse(dbFile.exists(), "El archivo de la base de datos no fue creado.");
    }
    
    /**
     * Prueba la conexión exitosa con la base de datos SQLite.
     */
    @Test
    public void testConnect_Success() {
        try {
            System.out.println("Conexion con la BDD");
            
            CompanySqliteRepository instance = new CompanySqliteRepository();
            instance.connect();
            
            assertNotNull(instance.getConn(), "La conexion debe estar inicializada.");
            assertTrue(instance.getConn().isValid(5), "La conexión no es válida.");
            
        } catch (SQLException ex) {
            Logger.getLogger(CompanySqliteRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba la desconexión exitosa de la base de datos.
     */
    @Test
    public void testDisconnect_Success() {
        try {
            System.out.println("Desconexion con la BDD");
            
            CompanySqliteRepository instance = new CompanySqliteRepository();
            instance.disconnect();
            Connection connTest = instance.getConn();
            
            assertTrue(connTest == null || connTest.isClosed(), "La conexión debería estar cerrada.");
            
        } catch (SQLException ex) {
            Logger.getLogger(CompanySqliteRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Verifica que al intentar guardar una empresa nula, se lance una excepción.
     */
    @Test
    public void testSave_CompanyNull() {
        System.out.println("Anadir compania nula");
        
        CompanySqliteRepository instance = new CompanySqliteRepository();
        Exception exception = assertThrows(Exception.class, () -> {
            instance.save(null);
        });
        
        assertEquals("Compañia vacia", exception.getMessage());
    }

    /**
     * Verifica que al intentar insertar una empresa con un NIT existente, 
     * se lance una excepción de restricción de unicidad.
     *
     * @throws Exception si ocurre un error en la ejecución del test.
     */
    @Test
    public void testSave_SQLiteConstraint() throws Exception {
        System.out.println("Anadir compania con NIT existente");
        
        CompanySqliteRepository instance = new CompanySqliteRepository();
        Company newCompany = new Company("1", "Test Company", "123-456-7890", "www.test.com", Sector.TECHNOLOGY, "test@company.com", "password123");

        // Act & Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            instance.save(newCompany);
        });
        
        assertEquals("Actualmente existe una empresa con el NIT ", exception.getMessage());
    }

}
