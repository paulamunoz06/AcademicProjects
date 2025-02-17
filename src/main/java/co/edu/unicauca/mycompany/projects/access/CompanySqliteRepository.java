package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import co.edu.unicauca.mycompany.projects.domain.entities.Sector;
import co.edu.unicauca.mycompany.projects.infra.ValidationException;
import java.security.Provider.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementación del repositorio utilizando SQLite para gestionar 
 * objetos de tipo Company.
 *
 * @author Paula Munoz
 */
public class CompanySqliteRepository implements ICompanyRepository {
    /**
     * Conexión a la base de datos SQLite.
     */
    private Connection conn;

    /**
     * Constructor que inicializa la base de datos.
     */
    public CompanySqliteRepository() {
        initDatabase();
    }
    
    /**
     * Inicializa la base de datos y crea la tabla Company si no existe.
     */
    private void initDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS Company (\n"
                + "	Nit text PRIMARY KEY,\n"
                + "	Name text NOT NULL,\n"
                + "	Phone text NULL,\n"
                + "	PageWeb text NULL,\n"
                + "	Sector text NOT NULL,\n"
                + "	Email text NOT NULL,\n"
                + "	Password text NOT NULL\n"
                + ");";
        try {
            this.connect();
            
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            
            this.disconnect();
        } catch (SQLException ex) {
            this.disconnect();
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Establece la conexión con la base de datos SQLite.
     */
    public void connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:./mydatabase.db";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cierra la conexión con la base de datos SQLite si está abierta.
     */
    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Guarda una nueva empresa en el repositorio.
     *
     * @param newCompany La empresa que se desea almacenar.
     * @return {@code true} si la empresa se guardó correctamente, {@code false} en caso contrario.
     * @throws Exception Si ocurre un error durante el proceso de almacenamiento.
     */
    @Override
    public boolean save(Company newCompany) throws Exception {
        try{
            //Validate product
            if (newCompany == null) {
                throw new Exception("Compañia vacia");
            }
            
            this.connect();
            String sql = "INSERT INTO Company ( Nit, Name, Phone, PageWeb, Sector, Email, Password ) "
                    + "VALUES ( ?,?,?,?,?,?,? )";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newCompany.getNit());
            pstmt.setString(2, newCompany.getName());
            pstmt.setString(3, newCompany.getPhone());
            pstmt.setString(4, newCompany.getPageWeb());
            pstmt.setString(5, newCompany.getSector().name());
            pstmt.setString(6, newCompany.getEmail());
            pstmt.setString(7, newCompany.getPassword());
            pstmt.executeUpdate();

            this.disconnect();
            return true;
            
        }catch (SQLException e) {
            if (e.getMessage().contains("SQLITE_CONSTRAINT")) {
                throw new ValidationException("Actualmente existe una empresa con el NIT ","nit");
            }
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    /**
     * Recupera la lista de todas las empresas almacenadas en el repositorio.
     *
     * @return Una lista de objetos {@link Company}.
     * @throws Exception Si ocurre un error durante la consulta de datos.
     */
    @Override
    public List<Company> listAll() throws Exception{
        
        List<Company> companies = new ArrayList<>();
        try {

            String sql = "SELECT Nit, Name, Phone, PageWeb, Sector, Email, Password FROM Company";
            this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Company newCompany = new Company(rs.getString("Nit"),
                        rs.getString("Name"),
                        rs.getString("Phone"),
                        rs.getString("PageWeb"),
                        Sector.valueOf(rs.getString("Sector")),
                        rs.getString("Email"),
                        rs.getString("Password"));
                companies.add(newCompany);
            }
            this.disconnect();
        } catch (SQLException ex) {
            this.disconnect();
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return companies;
       
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
}

