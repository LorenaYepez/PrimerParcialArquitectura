package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.M_Marca;
import vista.v_Marca;

public class C_Marca implements ActionListener{
    M_Marca modelo;
    v_Marca vista;

    public C_Marca() {
        vista=new v_Marca();
        modelo=new M_Marca();
        vista.btnRegistrar.addActionListener( this);
        vista.btnMostrar.addActionListener(this);
        vista.btnEditar.addActionListener(this);
        vista.btnActualizar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        listar(vista.tabla);
    }
    
    public void iniciar()
    {
        vista.setTitle("Marcas");
        vista.setLocationRelativeTo(null);
        vista.show();    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==vista.btnMostrar) {
            listar(vista.tabla);
        }
        if(e.getSource()==vista.btnRegistrar){
            modelo.agregar(new M_Marca(0, vista.txtNombre.getText()));
        }
        if (e.getSource()==vista.btnEditar) {
            int fila = vista.tabla.getSelectedRow();
            if (fila==-1) {
                JOptionPane.showMessageDialog(vista, "Debe Seleccionar una Fila");
            } else {
                int id=Integer.parseInt((String)vista.tabla.getValueAt(fila, 1).toString());
                String nombre=(String)vista.tabla.getValueAt(fila, 2);
                vista.txtID.setText(""+id);
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
        List<M_Marca>lista=this.modelo.listar();
        Object[]object= new Object[3];
        for (int i = 0; i < lista.size(); i++) {
            object[0]=i+1;
            object[1]=lista.get(i).getId();
            object[2]=lista.get(i).getNombre();
            modelo1.addRow(object);
        }
        vista.tabla.setModel(modelo1);  
    }
    
    private void actualizar() {
        int id=Integer.parseInt(vista.txtID.getText());
        String nombre=vista.txtNombre.getText();
        modelo.setId(id);
        modelo.setNombre(nombre);
        int r = modelo.actualizar(modelo);
        if(r==1) {
            JOptionPane.showMessageDialog(vista, "Dato Actualizado con Exito");
        }else{
            JOptionPane.showMessageDialog(vista, "Error");
        }
    }

    private void delete() {
        int fila=vista.tabla.getSelectedRow();
        if (fila==-1) {
            JOptionPane.showMessageDialog(vista, "Debe Seleccionar un Dato");
        }else{
            int id=Integer.parseInt((String)vista.tabla.getValueAt(fila, 1).toString());
            modelo.delete(id);
            JOptionPane.showMessageDialog(vista, "Dato eliminado");
        }
    }    
    
    private void limpiarTabla() {
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo1.removeRow(i);
            i=i-1;
        }
    }    
}
