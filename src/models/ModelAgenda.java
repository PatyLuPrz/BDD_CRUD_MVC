/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Salvador Hernandez Mendoza
 */
public class ModelAgenda {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;

    private String nombre;
    private String email;
    private String telefono;

    public String getTelefono() {
        System.out.println("getTelefono ModelAgenda");
        return telefono;
    }

    public void setTelefono(String telefono) {
        System.out.println("setTelefono ModelAgenda");
        this.telefono = telefono;
    }

    public String getNombre() {
        System.out.println("getNombre ModelAgenda");
        return nombre;
    }

    public void setNombre(String nombre) {
        System.out.println("setNombre ModelAgenda");
        this.nombre = nombre;
    }

    public String getEmail() {
        System.out.println("getEmail ModelAgenda");
        return email;
    }

    public void setEmail(String email) {
        System.out.println("setTelefono ModelAgenda");
        this.email = email;
    }

    /**
     * Método que realiza las siguietnes acciones: 1- Conecta con la base
     * agenda_mvc, 2- Consulta todo los registros de la tabla contactos, 3-
     * Obtiene el nombre y el email y los guarda en las variables globales
     * nombre y email.
     */
    public void conectarDB() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_mvc", "user_mvc", "pass_mvc.2018");
            st = conexion.createStatement();
            String sql = "SELECT * FROM contactos;";
            System.out.println(sql);
            rs = st.executeQuery(sql);
            rs.next();
            setValues();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 001: " + err.getMessage());
        }
    }

    /**
     * Lee los valores del registro seleccionado y los asigna a las variables
     * miembre nombre y email.
     */
    public void setValues() {
        System.out.println("setValues ModelAgenda");
        try {
            nombre = rs.getString("nombre");
            email = rs.getString("email");
            telefono = rs.getString("telefono");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 002: " + err.getMessage());

        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al primer registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverPrimerRegistro() {
        System.out.println("moverPrimerRegistro ModelAgenda");
        try{
            rs.first();
            setValues();
        } catch(SQLException err){
            JOptionPane.showMessageDialog(null,"Error ModelAgenda 003: "+ err.getMessage());
        }
        
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al siguiente
     * registro 2.- obtener el valor del nombre de rs y guardarlo en la variable
     * nombre 3.- obtener el valor del email de rs y guardarlo en la variable
     * email
     */
    public void moverSiguienteRegistro() {
        System.out.println("moverSiguienteRegistro ModelAgenda");
        try {
            if (!rs.isLast()) {
                rs.next();
                setValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 004: " + ex.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al anterior
     * registro 2.- obtener el valor del nombre de rs y guardarlo en la variable
     * nombre 3.- obtener el valor del email de rs y guardarlo en la variable
     * email
     */
    public void moverAnteriorRegistro() {
        System.out.println("moverAnteriorRegistro ModelAgenda");
        try {
            if (!rs.isFirst()) {
                rs.previous();
                setValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 005: " + ex.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al ultimo registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la ariable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverUltimoRegistro() {
        System.out.println("moverUltimoRegistro ModelAgenda");
        try {
            rs.last();
            setValues();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 006" + ex.getMessage());
        }
    }
    
    public void insertarRegistro(String nombre, String email, String telefono){
        System.out.println("Accion insertarRegistro - ModelAgenda");
        try{
            String sql = "INSERT INTO contactos(nombre,email,telefono) VALUES ('"+nombre+"','"+email+"','"+telefono+"');";
            System.out.println(sql);
            st.executeUpdate(sql);
            this.conectarDB();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 007: " + ex.getMessage());
        }
    }
    
    public void modificarRegistro(String nombre, String email, String telefono){
        System.out.println("Accion modificarRegistro - ModelAgenda");
        try{
            String actualEmail = this.getEmail();
            String sql = "UPDATE contactos SET nombre='"+nombre+"',email='"+email+"',telefono='"+telefono+"' WHERE email='"+actualEmail+"';";
            st.executeUpdate(sql);
            this.conectarDB();
           } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 008: " + ex.getMessage());
        }
    }
    
    public void eliminarRegistro(String email){
        System.out.println("Accion eliminarRegistro - ModelAgenda");
        try{
            String sql= "DELETE FROM contactos WHERE email='"+email+"'";
            st.executeUpdate(sql);
            this.conectarDB();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 009: " + ex.getMessage());
        }
    }
    
}
