package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.M_Producto;
import vista.v_Producto;

public class C_Producto implements ActionListener{
    M_Producto modelo;
    v_Producto vista;

    public C_Producto() {
        modelo = new M_Producto();
        vista = new v_Producto();  
        vista.btnMostrar.addActionListener(this);
        vista.btnRegistrar.addActionListener(this);
        vista.btnEditar.addActionListener(this);
        vista.btnActualizar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.cboMedida.addActionListener(this);
        listar(vista.tabla);
        modelo.llenarB(vista.cboMedida);
    }
    
     public void iniciar(){
        vista.setTitle("Productos");
        vista.setLocationRelativeTo(null);
        vista.show();    
    }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==vista.btnMostrar) {
            listar(vista.tabla);
        }
        if(e.getSource()==vista.btnRegistrar){
           agregar();
        }
        if (e.getSource()==vista.cboMedida) {
              StringTokenizer st=new StringTokenizer(vista.cboMedida.getSelectedItem().toString(), ":");
//            JOptionPane.showMessageDialog(null, "ID: "+st.nextToken());
        }
        if (e.getSource()==vista.btnEditar) {
            int fila = vista.tabla.getSelectedRow();
            if (fila==-1) {
                JOptionPane.showMessageDialog(vista, "Debe Seleccionar una Fila");
            } else {
                int id=Integer.parseInt((String)vista.tabla.getValueAt(fila, 1).toString());
                String nombre=(String)vista.tabla.getValueAt(fila, 2);
                StringTokenizer st=new StringTokenizer(vista.cboMedida.getSelectedItem().toString(), ":");
                vista.txtId.setText(""+id);
                vista.txtNombre.setText(nombre);
            }
        }
        if (e.getSource()==vista.btnActualizar) {
            actualizar();
        }
        if (e.getSource()==vista.btnEliminar) {
            delete();
        }
         listar(vista.tabla);         
    }
    
    DefaultTableModel modelo1 = new DefaultTableModel();
    private void listar(JTable tabla){
        limpiarTabla();
        modelo1 = (DefaultTableModel)tabla.getModel();
        List<M_Producto>lista=this.modelo.listar();
        Object[]object= new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0]=i+1;
            object[1]=lista.get(i).getId();
            object[2]=lista.get(i).getNombre();
            object[3]=lista.get(i).getIdMedida();
            modelo1.addRow(object);
        }
        vista.tabla.setModel(modelo1);  
    }
    
     public void agregar() {
        if(vista.cboMedida.getSelectedIndex()>0){
            String nombre=vista.txtNombre.getText();
            StringTokenizer st=new StringTokenizer(vista.cboMedida.getSelectedItem().toString(), ":");
            int id = Integer.parseInt(st.nextToken());
            modelo.setNombre(nombre);
            modelo.setIdMedida(id);
            int r = modelo.agregar(modelo);
            if(r==1) {
                JOptionPane.showMessageDialog(vista, "Dato Agregado con Exito");
            }else{    
                JOptionPane.showMessageDialog(vista, "Error");
            }    
        }
    }

    private void limpiarTabla() {
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo1.removeRow(i);
            i=i-1;
        }
    }

    private void actualizar() {
        if(vista.cboMedida.getSelectedIndex()>0){
            int id=Integer.parseInt(vista.txtId.getText());
            String nombre=vista.txtNombre.getText();
            StringTokenizer st=new StringTokenizer(vista.cboMedida.getSelectedItem().toString(), ":");
            int idMedida= Integer.parseInt(st.nextToken());
            modelo.setId(id);
            modelo.setNombre(nombre);
            modelo.setIdMedida(idMedida);
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
            JOptionPane.showMessageDialog(vista, "Debe Seleccionar un Producto");
        }else{
            int id=Integer.parseInt((String)vista.tabla.getValueAt(fila, 1).toString());
            modelo.delete(id);
            JOptionPane.showMessageDialog(vista, "Producto eliminado");
        }
    }
}
