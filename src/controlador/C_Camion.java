package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.M_Camion;
import vista.v_Camion;

public class C_Camion implements ActionListener{
    M_Camion modelo;
    v_Camion vista;

    public C_Camion() {
        vista=new v_Camion();
        modelo=new M_Camion();
        modelo.llenarB(vista.jComboBox1);
        this.vista.btnMostrar.addActionListener(this);
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        listar(vista.tabla);
    }
    
    public void iniciar(){
        vista.setTitle("CAMION");
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
        if (e.getSource()==vista.btnEditar) {
            int fila = vista.tabla.getSelectedRow();
            if (fila==-1) {
                JOptionPane.showMessageDialog(vista, "Debe Seleccionar una Fila");
            } else {
                int id=Integer.parseInt((String)vista.tabla.getValueAt(fila, 1).toString());
                String placa=(String)vista.tabla.getValueAt(fila, 2);
                int numero=Integer.parseInt((String)vista.tabla.getValueAt(fila, 3).toString());
                int mode=Integer.parseInt((String)vista.tabla.getValueAt(fila, 4).toString());
                int capacidad=Integer.parseInt((String)vista.tabla.getValueAt(fila, 5).toString());
                String color=(String)vista.tabla.getValueAt(fila, 6);
                String descripcion=(String)vista.tabla.getValueAt(fila, 7);
                StringTokenizer st=new StringTokenizer(vista.jComboBox1.getSelectedItem().toString(), ":");
                vista.txtId.setText(""+id);
                vista.txtPlaca.setText(placa);
                vista.txtNumero.setText(""+numero);
                vista.txtModelo.setText(""+mode);
                vista.txtCapacidad.setText(""+capacidad);
                vista.txtColor.setText(color);
                vista.txtDescripcion.setText(descripcion);
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
        List<M_Camion>lista=this.modelo.listar();
        Object[]object= new Object[9];
        for (int i = 0; i < lista.size(); i++) {
            object[0]=i+1;
            object[1]=lista.get(i).getId();
            object[2]=lista.get(i).getPlaca();
            object[3]=lista.get(i).getNumero();
            object[4]=lista.get(i).getModelo();
            object[5]=lista.get(i).getCapacidad();
            object[6]=lista.get(i).getColor();
            object[7]=lista.get(i).getDescripcion();
            object[8]=lista.get(i).getIdmarca();
            modelo1.addRow(object);
        }
        vista.tabla.setModel(modelo1);  
    }
    
     public void agregar() {
        if(vista.jComboBox1.getSelectedIndex()>0){
            String placa=vista.txtPlaca.getText();
            int numero=Integer.parseInt(vista.txtNumero.getText());
            int model=Integer.parseInt(vista.txtModelo.getText());
            int capacidad=Integer.parseInt(vista.txtCapacidad.getText());
            String color=vista.txtColor.getText();
            String descripcion=vista.txtDescripcion.getText();
            StringTokenizer st=new StringTokenizer(vista.jComboBox1.getSelectedItem().toString(), ":");
            int id = Integer.parseInt(st.nextToken());
            modelo.setPlaca(placa);
            modelo.setNumero(numero);
            modelo.setModelo(model);
            modelo.setCapacidad(capacidad);
            modelo.setColor(color);
            modelo.setDescripcion(descripcion);
            modelo.setIdmarca(id);
            int r = modelo.agregar(modelo);
             if(r==1) {
                JOptionPane.showMessageDialog(vista, "Dato Agregado con Exito");
            }else{    
                JOptionPane.showMessageDialog(vista, "Error");
            }   
        }
    }
     
    private void actualizar() {
       if(vista.jComboBox1.getSelectedIndex()>0){
            int id=Integer.parseInt(vista.txtId.getText());
            String placa=vista.txtPlaca.getText();
            int numero=Integer.parseInt(vista.txtNumero.getText());
            int model=Integer.parseInt(vista.txtModelo.getText());
            int capacidad=Integer.parseInt(vista.txtCapacidad.getText());
            String color=vista.txtColor.getText();
            String descripcion=vista.txtDescripcion.getText();
            StringTokenizer st=new StringTokenizer(vista.jComboBox1.getSelectedItem().toString(), ":");
            int idMarca = Integer.parseInt(st.nextToken());
            modelo.setId(id);
            modelo.setPlaca(placa);
            modelo.setNumero(numero);
            modelo.setModelo(model);
            modelo.setCapacidad(capacidad);
            modelo.setColor(color);
            modelo.setDescripcion(descripcion);
            modelo.setIdmarca(idMarca);
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
            JOptionPane.showMessageDialog(vista, "Debe Seleccionar un Camion");
        }else{
            int id=Integer.parseInt((String)vista.tabla.getValueAt(fila, 1).toString());
            modelo.delete(id);
            JOptionPane.showMessageDialog(vista, "Producto eliminado");
        }
    }  
    
    private void limpiarTabla() {
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo1.removeRow(i);
            i=i-1;
        }
    }    
   
    
}
