/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import co.edu.unicauca.mycompany.projects.domain.entities.Sector;
import co.edu.unicauca.mycompany.projects.infra.ValidationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para la validación de datos de la empresa mediante la clase
 * DataValidationCompany. Se prueban múltiples escenarios de validación.
 * 
 * @author paula
 */
public class DataValidationCompanyTest {

    /**
     * Prueba para validar datos correctos.
     */
    @Test
    public void testIsValid_Success(){
        try {
            System.out.println("Datos validos");
            
            Company newcompany = new Company("123459", "Empresa D","3434345", "www.mipagina4.com", Sector.SERVICES, "gerente4@gmail.com", "quiseserfelizX4-");
            DataValidationCompany instance = new DataValidationCompany(newcompany);
            boolean result = instance.isValid();
            assertEquals(true, result);
        } catch (ValidationException ex) {
            Logger.getLogger(DataValidationCompanyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba para verificar el fallo cuando el NIT está vacío.
     */
    @Test
    public void testIsValid_Failure_NoNIT(){
        System.out.println("Sin NIT");

        DataValidationCompany instance = new DataValidationCompany(new Company(" ", "Empresa D","3434345", "www.mipagina4.com", Sector.SERVICES, "gerente4@gmail.com", "123"));
        ValidationException exception = assertThrows(ValidationException.class, () -> {instance.isValid();});
        assertEquals("El NIT es obligatorio", exception.getMessage());
    }
    
    /**
     * Prueba para verificar el fallo cuando el nombre está vacío.
     */
    @Test
    public void testIsValid_Failure_NoName(){
        System.out.println("Sin nombre");

        DataValidationCompany instance = new DataValidationCompany(new Company("11", "","3434345", "www.mipagina4.com", Sector.SERVICES, "gerente4@gmail.com", "123"));
        ValidationException exception = assertThrows(ValidationException.class, () -> {instance.isValid();});
        assertEquals("El nombre es obligatorio", exception.getMessage());
    }
    
    /**
     * Prueba para verificar el fallo cuando el telefono está vacío.
     */
    @Test
    public void testIsValid_Failure_NoPhone(){
        try {
            System.out.println("Sin telefono");
            
            DataValidationCompany instance = new DataValidationCompany(new Company("11", "Empresa D","", "www.mipagina4.com", Sector.SERVICES, "gerente4@gmail.com", "123"));
            boolean result = instance.isValid();
            assertEquals(true, result);
        } catch (ValidationException ex) {
            Logger.getLogger(DataValidationCompanyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba para verificar el fallo cuando la pagina web está vacía.
     */
    @Test
    public void testIsValid_Failure_NoPag(){
        try {
            System.out.println("Sin pagina web");
            
            DataValidationCompany instance = new DataValidationCompany(new Company("11", "Empresa D","3434345", "", Sector.SERVICES, "gerente4@gmail.com", "123"));
            boolean result = instance.isValid();
            assertEquals(true, result);
        } catch (ValidationException ex) {
            Logger.getLogger(DataValidationCompanyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba para verificar el fallo cuando el sector es nulo.
     */
    @Test
    public void testIsValid_Failure_NoSector(){
        System.out.println("Sin sector");

        DataValidationCompany instance = new DataValidationCompany(new Company("11", "Empresa D","3434345", "www.mipagina4.com", null, "gerente4@gmail.com", "123"));
        ValidationException exception = assertThrows(ValidationException.class, () -> {instance.isValid();});
        assertEquals("El sector industrial es obligatorio", exception.getMessage());
    }
    
    /**
     * Prueba para verificar el fallo cuando el email está vacío.
     */
    @Test
    public void testIsValid_Failure_NoEmail(){
        System.out.println("Sin email");

        DataValidationCompany instance = new DataValidationCompany(new Company("11", "Empresa D","3434345", "www.mipagina4.com", Sector.SERVICES, "", "123"));
        ValidationException exception = assertThrows(ValidationException.class, () -> {instance.isValid();});
        assertEquals("El email es obligatorio", exception.getMessage());
    }
    
    /**
     * Prueba para verificar el fallo cuando la contrasenia está vacia.
     */
    @Test
    public void testIsValid_Failure_NoPassword(){
        System.out.println("Sin contrasenia");

        DataValidationCompany instance = new DataValidationCompany(new Company("11", "Empresa D","3434345", "www.mipagina4.com", Sector.SERVICES, "gerente4@gmail.com", ""));
        ValidationException exception = assertThrows(ValidationException.class, () -> {instance.isValid();});
        assertEquals("La contraseña es obligatoria", exception.getMessage());
    }
    
    /**
     * Prueba para verificar el fallo cuando el email es inválido.
     */
    @Test
    public void testIsValid_Failure_Email1(){
        System.out.println("Email sin formato");

        DataValidationCompany instance = new DataValidationCompany(new Company("11", "Empresa D","3434345", "www.mipagina4.com", Sector.SERVICES, "g", "p"));
        ValidationException exception = assertThrows(ValidationException.class, () -> {instance.isValid();});
        assertEquals("El correo no es válido", exception.getMessage());
    }
    
    /**
     * Prueba para verificar el fallo cuando el email es inválido.
     */
    @Test
    public void testIsValid_Failure_Email2(){
        System.out.println("Email sin formato");

        DataValidationCompany instance = new DataValidationCompany(new Company("11", "Empresa D","3434345", "www.mipagina4.com", Sector.SERVICES, "g.com", "p"));
        ValidationException exception = assertThrows(ValidationException.class, () -> {instance.isValid();});
        assertEquals("El correo no es válido", exception.getMessage());
    }
    
    /**
     * Prueba para verificar el fallo cuando el email es inválido.
     */
    @Test
    public void testIsValid_Failure_Email3(){
        System.out.println("Email sin formato");

        DataValidationCompany instance = new DataValidationCompany(new Company("11", "Empresa D","3434345", "www.mipagina4.com", Sector.SERVICES, "g@m", "p"));
        ValidationException exception = assertThrows(ValidationException.class, () -> {instance.isValid();});
        assertEquals("El correo no es válido", exception.getMessage());
    }
    
    /**
     * Prueba para verificar el fallo cuando el email es inválido.
     */
    @Test
    public void testIsValid_Failure_Email4(){
        System.out.println("Email sin formato");

        DataValidationCompany instance = new DataValidationCompany(new Company("11", "Empresa D","3434345", "www.mipagina4.com", Sector.SERVICES, "@gmail.com", "p"));
        ValidationException exception = assertThrows(ValidationException.class, () -> {instance.isValid();});
        assertEquals("El correo no es válido", exception.getMessage());
    }
    
    /**
     * Prueba para verificar el fallo cuando la contraseña no cumple con los requisitos.
     */
    @Test
    public void testIsValid_Failure_Password1(){
        System.out.println("Contrasenia sin formato");

        DataValidationCompany instance = new DataValidationCompany(new Company("11", "Empresa D","3434345", "www.mipagina4.com", Sector.SERVICES, "gerente4@gmail.com", "p"));
        ValidationException exception = assertThrows(ValidationException.class, () -> {instance.isValid();});
        assertEquals("La contraseña ndebe tener al menos 6 caracteres, una mayúscula y un carácter especial.", exception.getMessage());
    }
    
    /**
     * Prueba para verificar el fallo cuando la contraseña no cumple con los requisitos.
     */
    @Test
    public void testIsValid_Failure_Password2(){
        System.out.println("Contrasenia sin formato");

        DataValidationCompany instance = new DataValidationCompany(new Company("11", "Empresa D","3434345", "www.mipagina4.com", Sector.SERVICES, "gerente4@gmail.com", "123456"));
        ValidationException exception = assertThrows(ValidationException.class, () -> {instance.isValid();});
        assertEquals("La contraseña ndebe tener al menos 6 caracteres, una mayúscula y un carácter especial.", exception.getMessage());
    }
    
    /**
     * Prueba para verificar el fallo cuando la contraseña no cumple con los requisitos.
     */
    @Test
    public void testIsValid_Failure_Password3(){
        System.out.println("Contrasenia sin formato");

        DataValidationCompany instance = new DataValidationCompany(new Company("11", "Empresa D","3434345", "www.mipagina4.com", Sector.SERVICES, "gerente4@gmail.com", "1234567!"));
        ValidationException exception = assertThrows(ValidationException.class, () -> {instance.isValid();});
        assertEquals("La contraseña ndebe tener al menos 6 caracteres, una mayúscula y un carácter especial.", exception.getMessage());
    }
    
    /**
     * Prueba para verificar el fallo cuando la contraseña no cumple con los requisitos.
     */
    @Test
    public void testIsValid_Failure_Password4(){
        System.out.println("Contrasenia sin formato");

        DataValidationCompany instance = new DataValidationCompany(new Company("11", "Empresa D","3434345", "www.mipagina4.com", Sector.SERVICES, "gerente4@gmail.com", "1234567X"));
        ValidationException exception = assertThrows(ValidationException.class, () -> {instance.isValid();});
        assertEquals("La contraseña ndebe tener al menos 6 caracteres, una mayúscula y un carácter especial.", exception.getMessage());
    }
    
    /**
     * Prueba para verificar el fallo cuando la contraseña no cumple con los requisitos.
     */
    @Test
    public void testIsValid_Failure_Password5(){
        System.out.println("Contrasenia sin formato");

        DataValidationCompany instance = new DataValidationCompany(new Company("11", "Empresa D","3434345", "www.mipagina4.com", Sector.SERVICES, "gerente4@gmail.com", "1.X"));
        ValidationException exception = assertThrows(ValidationException.class, () -> {instance.isValid();});
        assertEquals("La contraseña ndebe tener al menos 6 caracteres, una mayúscula y un carácter especial.", exception.getMessage());
    }
}
