package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.M_Camion;
import modelo.M_Persona;
import modelo.M_Registro;
import vista.v_Registro;

public class C_Registro implements ActionListener{
    M_Registro modelo;
    v_Registro vista;
    M_Camion modelocamion;
    M_Persona modelopersona;

    public C_Registro() {
        modelo = new M_Registro();
        vista = new v_Registro();  
        modelocamion=new M_Camion();
        modelopersona=new M_Persona();
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnListar.addActionListener(this);
        modelopersona.llenarPropietario(vista.cboPropietario);
        modelocamion.llenarCamion(vista.cboCamion);
        listar(vista.tabla);
    }
    
    public void iniciar(){
        vista.setTitle("REGISTRAR REGISTRO");
        vista.setLocationRelativeTo(null);
        vista.show();    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==vista.btnListar) {
            listar(vista.tabla);
        }
        if (e.getSource()==vista.btnRegistrar) {
            agregar();
        }
        listar(vista.tabla);
    }

    DefaultTableModel modelo1 = new DefaultTableModel();
    private void listar(JTable tabla){
       limpiarTabla();
        modelo1 = (DefaultTableModel)tabla.getModel();
        List<M_Registro>lista=this.modelo.listar();
        Object[]object= new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0]=lista.get(i).getIdregistro();
            object[1]=lista.get(i).getFecha();
            object[2]=lista.get(i).getIdpropietario();
            object[3]=lista.get(i).getIdcamion();
            modelo1.addRow(object);
        }
        vista.tabla.setModel(modelo1);  
    }
    
    public void agregar() {
        if(vista.cboPropietario.getSelectedIndex()>0){
            StringTokenizer st=new StringTokenizer(vista.cboPropietario.getSelectedItem().toString()," :- ");
            int idPropietario = Integer.parseInt(st.nextToken());
            StringTokenizer st1=new StringTokenizer(vista.cboCamion.getSelectedItem().toString(), " :- ");
            int idCamion = Integer.parseInt(st1.nextToken());
            String fecha=vista.txtFecha.getText();
            modelo.setIdpropietario(idPropietario);
            modelo.setIdcamion(idCamion);
            modelo.setFecha(fecha);
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
}
