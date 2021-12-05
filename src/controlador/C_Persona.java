
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.M_Persona;
import vista.v_Persona;

public class C_Persona implements ActionListener{
    M_Persona modelo;
    v_Persona vista;

    public C_Persona() {
        modelo = new M_Persona();
        vista = new v_Persona();  
        vista.btnListar.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.btnEditar.addActionListener(this);
        vista.btnActualizar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        listar(vista.tabla);
        modelo.llenarGenero(vista.cboGenero);
        modelo.llenarTipo(vista.cboTipo);
    }
    
    public void iniciar(){
        vista.setTitle("PERSONAS");
        vista.setLocationRelativeTo(null);
        vista.show();    
    }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==vista.btnListar) {
            listar(vista.tabla);
        }
        if(e.getSource()==vista.btnGuardar){
           agregar();
        }
        if (e.getSource()==vista.btnActualizar) {
            actualizar();
        }
        if (e.getSource()==vista.btnEliminar) {
            delete();
        }
        if (e.getSource()==vista.btnEditar) {
            int fila = vista.tabla.getSelectedRow();
            if (fila==-1) {
                JOptionPane.showMessageDialog(vista, "Debe Seleccionar una Fila");
            } else {
                int id=Integer.parseInt((String)vista.tabla.getValueAt(fila, 0).toString());
                String ci=(String)vista.tabla.getValueAt(fila, 1);
                String nombre=(String)vista.tabla.getValueAt(fila, 2);
                int telefono=Integer.parseInt((String)vista.tabla.getValueAt(fila, 4).toString());
                StringTokenizer st=new StringTokenizer(vista.cboGenero.getSelectedItem().toString(), " :- ");
                StringTokenizer stTipo=new StringTokenizer(vista.cboTipo.getSelectedItem().toString(), " :- ");
                vista.txtId.setText(""+id);
                vista.txtCI.setText(ci);
                vista.txtNombre.setText(nombre);
                vista.txtTelefono.setText(""+telefono);
            }
        }
        listar(vista.tabla);     
    }

    DefaultTableModel modelo1 = new DefaultTableModel();
    private void listar(JTable tabla){
        limpiarTabla();
        modelo1 = (DefaultTableModel)tabla.getModel();
        List< M_Persona>lista=this.modelo.listar();
        Object[]object= new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            object[0]=lista.get(i).getId();
            object[1]=lista.get(i).getCi();
            object[2]=lista.get(i).getNombre();
            object[3]=lista.get(i).getIdGenero();
            object[4]=lista.get(i).getTelefono();
            object[5]=lista.get(i).getIdTipo();
            modelo1.addRow(object);
        }
        vista.tabla.setModel(modelo1);  
    }

    private void limpiarTabla() {
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo1.removeRow(i);
            i=i-1;
        }
    }

    private void agregar() {
        if(vista.cboGenero.getSelectedIndex()>0){
            String ci=vista.txtCI.getText();
            String nombre=vista.txtNombre.getText();
            int telefono=Integer.parseInt(vista.txtTelefono.getText());
            StringTokenizer st=new StringTokenizer(vista.cboGenero.getSelectedItem().toString(), " :- ");
            int idGenero = Integer.parseInt(st.nextToken());
            StringTokenizer stTipo=new StringTokenizer(vista.cboTipo.getSelectedItem().toString(), " :- ");
            int idTipo = Integer.parseInt(stTipo.nextToken());
            modelo.setCi(ci);
            modelo.setNombre(nombre);
            modelo.setIdGenero(idGenero);
            modelo.setTelefono(telefono);
            modelo.setIdTipo(idTipo);
            int r = modelo.agregar(modelo);
            if(r==1) {
                JOptionPane.showMessageDialog(vista, "Dato Agregado con Exito");
            }else{    
                JOptionPane.showMessageDialog(vista, "Error");
            }    
        }
    }
    
    private void actualizar() {
        if(vista.cboGenero.getSelectedIndex()>0){
            int id=Integer.parseInt(vista.txtId.getText());
            String ci=vista.txtCI.getText();
            String nombre=vista.txtNombre.getText();
            int telefono=Integer.parseInt(vista.txtTelefono.getText());
            StringTokenizer st=new StringTokenizer(vista.cboGenero.getSelectedItem().toString(), " :- ");
            int idGenero = Integer.parseInt(st.nextToken());
            StringTokenizer stTipo=new StringTokenizer(vista.cboTipo.getSelectedItem().toString(), " :- ");
            int idTipo = Integer.parseInt(stTipo.nextToken());
            modelo.setId(id);
            modelo.setCi(ci);
            modelo.setNombre(nombre);
            modelo.setIdGenero(idGenero);
            modelo.setTelefono(telefono);
            modelo.setIdTipo(idTipo);
            int r = modelo.actualizar(modelo);
            if(r==1) {
                JOptionPane.showMessageDialog(vista, "Dato Actualizado con Exito");
            }else{
                JOptionPane.showMessageDialog(vista, "Error");
            }
        }
    }
    
    private void delete() {
         int fila=vista.tabla.getSelectedRow();
        if (fila==-1) {
            JOptionPane.showMessageDialog(vista, "Debe Seleccionar un Persona");
        }else{
            int id=Integer.parseInt((String)vista.tabla.getValueAt(fila, 0).toString());
            modelo.delete(id);
            JOptionPane.showMessageDialog(vista, "Persona eliminado");
        }
    }
    
    /*
    private void delete() {
        int fila=personavista.tabla.getSelectedRow();
        if (fila==-1) {
            JOptionPane.showMessageDialog(personavista, "Debe Seleccionar una Persona");
        }else{
            int id=Integer.parseInt((String)personavista.tabla.getValueAt(fila, 0).toString());
            persona.delete(id);
            JOptionPane.showMessageDialog(personavista, "Persona eliminada");
        }
    */
}
