/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.ModelAgenda;
import views.ViewAgenda;

/**
 *
 * @author Salvador Hernandez Mendoza
 */
public class ControllerAgenda {

    public ModelAgenda modelAgenda;
    public ViewAgenda viewAgenda;

    /**
     * Objeto de tipo ActionListener para atrapar los eventos actionPerformed y
     * llamar a los metodos para ver los registros almacenados en la bd.
     */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewAgenda.jbtn_primero) {
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_anterior) {
                jbtn_anterior_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_siguiente) {
                jbtn_siguiente_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_ultimo) {
                jbtn_ultimo_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_nuevo){
                jbtn_nuevo_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_insertar){
                jbtn_insertar_actionPerformed();
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_modificar){
                jbtn_modificar_actionPerformed();
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_eliminar){
                jbtn_eliminar_actionPerformed();
                jbtn_primero_actionPerformed();
            }
        }
    };

    /**
     * Constructor de Controlador para unir el ModelAgenda y ViewAgenda
     *
     * @param modelAgenda objeto de tipo ModelAgenda
     * @param viewAgenda objeto de tipo ViewAgenda
     */
    public ControllerAgenda(ModelAgenda modelAgenda, ViewAgenda viewAgenda) {
        this.modelAgenda = modelAgenda;
        this.viewAgenda = viewAgenda;
        setActionListener();
        initDB();
    }

    /**
     * Método que llama al método conectarBD del modelo y muestra el nombre y
     * email del primer registro en las cajas de texto de ViewAgenda.
     */
    private void initDB() {
        modelAgenda.conectarDB();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_telefono.setText(modelAgenda.getTelefono());
    }

    /**
     * Metodo para inicializar la ViewAgenda
     */
    public void initComponents() {
        //viewAgenda.setLocationRelativeTo(null);
        //viewAgenda.setTitle("Agenda MVC");
        viewAgenda.setVisible(true);
    }

    /**
     * Método para agregar el actionListener a cada boton de la vista
     */
    private void setActionListener() {
        viewAgenda.jbtn_primero.addActionListener(actionListener);
        viewAgenda.jbtn_anterior.addActionListener(actionListener);
        viewAgenda.jbtn_siguiente.addActionListener(actionListener);
        viewAgenda.jbtn_ultimo.addActionListener(actionListener);
        viewAgenda.jbtn_nuevo.addActionListener(actionListener);
        viewAgenda.jbtn_insertar.addActionListener(actionListener);
        viewAgenda.jbtn_modificar.addActionListener(actionListener);
        viewAgenda.jbtn_eliminar.addActionListener(actionListener);
        viewAgenda.jbtn_cancelar.addActionListener(actionListener);
    }

    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jbtn_primero_actionPerformed() {
        System.out.println("Action del boton jbtn_primero - Controller Agenda");
        modelAgenda.moverPrimerRegistro();
        setValues();
    }

    /**
     * Método para ver el registro anterior de la tabla contactos.
     */
    private void jbtn_anterior_actionPerformed() {
        System.out.println("Action del boton jbtn_anterior - Controller Agenda");
        modelAgenda.moverAnteriorRegistro();
        setValues();
    }

    /**
     * Método para ver el último registro de la tabla contactos.
     */
    private void jbtn_ultimo_actionPerformed() {
        System.out.println("Action del boton jbtn_ultimo - Controller Agenda");
        modelAgenda.moverUltimoRegistro();
        setValues();
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos.
     */
    private void jbtn_siguiente_actionPerformed() {
        System.out.println("Action del boton jbtn_siguiente - Controller Agenda");
        modelAgenda.moverSiguienteRegistro();
        setValues();
    }

    /**
     * Muestra el nombre y email almacenados en el modelAgenda en el viewAgenda.
     */
    private void setValues() {
        System.out.println("setValues - Controller Agenda");
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_telefono.setText(modelAgenda.getTelefono());
    }
    
    /**
     * Método para limpiar las cajas de texto
     */
    private void jbtn_nuevo_actionPerformed() {
        System.out.println("Action del boton jbtn_nuevo - Controller Agenda");
        viewAgenda.jtf_nombre.setEditable(true);
        viewAgenda.jtf_email.setEditable(true);
        viewAgenda.jtf_telefono.setEditable(true);
        viewAgenda.jbtn_anterior.setEnabled(false);
        viewAgenda.jbtn_siguiente.setEnabled(false);
        viewAgenda.jbtn_primero.setEnabled(false);
        viewAgenda.jbtn_ultimo.setEnabled(false);
        viewAgenda.jbtn_modificar.setEnabled(false);
        viewAgenda.jbtn_eliminar.setEnabled(false);
        viewAgenda.jbtn_nuevo.setEnabled(false);
        viewAgenda.jtf_nombre.setText(null);
        viewAgenda.jtf_email.setText(null);
        viewAgenda.jtf_telefono.setText(null);
    }
    /**
     * Método para insertar un nuevo registro a la base de datos
     */
    private void jbtn_insertar_actionPerformed() {
        System.out.println("Action del boton jbtn_insertar - Controller Agenda");
        modelAgenda.setEmail(viewAgenda.jtf_email.getText());
        modelAgenda.setNombre(viewAgenda.jtf_nombre.getText());
        modelAgenda.setTelefono(viewAgenda.jtf_telefono.getText());
        modelAgenda.insertarRegistro(
                modelAgenda.getNombre(), 
                modelAgenda.getEmail(), 
                modelAgenda.getTelefono());
        JOptionPane.showMessageDialog(null,"Registro añadido con exito");
        viewAgenda.jbtn_anterior.setEnabled(true);
        viewAgenda.jbtn_siguiente.setEnabled(true);
        viewAgenda.jbtn_primero.setEnabled(true);
        viewAgenda.jbtn_ultimo.setEnabled(true);
        viewAgenda.jbtn_modificar.setEnabled(true);
        viewAgenda.jbtn_eliminar.setEnabled(true);
        viewAgenda.jbtn_nuevo.setEnabled(true);
        viewAgenda.jtf_nombre.setEditable(false);
        viewAgenda.jtf_email.setEditable(false);
        viewAgenda.jtf_telefono.setEditable(false);
    }
    /**
     * Método para modificar los datos de un registro
     */
    private void jbtn_modificar_actionPerformed() {
        System.out.println("Action del boton jbtn_modificar - Controller Agenda");
        modelAgenda.modificarRegistro(
                viewAgenda.jtf_nombre.getText(), 
                viewAgenda.jtf_email.getText(),
                viewAgenda.jtf_telefono.getText());
        JOptionPane.showMessageDialog(null,"Registro modificado con exito");
    }
    /**
     * Método para eliminar un registro
     */
    private void jbtn_eliminar_actionPerformed() {
        System.out.println("Action del boton jbtn_eliminar - Controller Agenda");
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de "
                    + "eliminar el registro?", "Alerta!", JOptionPane.YES_NO_OPTION);
        if(resp==0){
            modelAgenda.setEmail(viewAgenda.jtf_email.getText());
            modelAgenda.setNombre(viewAgenda.jtf_nombre.getText());
            modelAgenda.setTelefono(viewAgenda.jtf_telefono.getText());
            modelAgenda.eliminarRegistro(modelAgenda.getEmail());
            JOptionPane.showMessageDialog(null,"Registro borrado con exito");
        }
    }
}
