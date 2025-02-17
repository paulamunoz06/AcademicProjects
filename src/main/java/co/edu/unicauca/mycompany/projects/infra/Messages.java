package co.edu.unicauca.mycompany.projects.infra;

import javax.swing.JOptionPane;

/**
 * Clase utilitaria para mostrar mensajes en cuadros de diálogo.
 * 
 * @author Libardo Pantoja, Julio A. Hurtado
 */
public class Messages {

    /**
     * Muestra un cuadro de diálogo con un mensaje informativo.
     *
     * @param message Mensaje a mostrar.
     * @param title   Título de la ventana del cuadro de diálogo.
     */
    public static void showMessageDialog(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
        
    /**
     * Muestra un cuadro de diálogo con un mensaje de error.
     *
     * @param message Mensaje de error a mostrar.
     * @param title   Título de la ventana del cuadro de diálogo.
     */
    public static void showErrorDialog(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Muestra un cuadro de diálogo de confirmación con opciones "Sí" y "No".
     *
     * @param message Mensaje de la confirmación.
     * @param title   Título de la ventana del cuadro de diálogo.
     * @return Un entero que indica la selección del usuario:
     *         {@code JOptionPane.YES_OPTION} (0) si selecciona "Sí",
     *         {@code JOptionPane.NO_OPTION} (1) si selecciona "No",
     *         {@code JOptionPane.CLOSED_OPTION} (-1) si se cierra el cuadro de diálogo.
     */
    public static int showConfirmDialog(String message, String title) {
        return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
