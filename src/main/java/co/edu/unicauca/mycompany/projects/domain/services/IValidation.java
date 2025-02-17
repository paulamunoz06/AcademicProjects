/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.infra.ValidationException;

/**
 * Interfaz para la validación de entidades.
 *
 * @author Paula Munoz
 */
public interface IValidation {

    /**
     * Valida una entidad según reglas especificas.
     *
     * @return {@code true} si la validación es exitosa.
     * @throws ValidationException Si la entidad no cumple con los criterios de validación.
     */
    boolean isValid() throws ValidationException;
}