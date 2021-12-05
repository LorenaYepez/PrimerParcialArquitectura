package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.M_Plantilla;
import vista.v_Plantilla;

public class C_Plantilla implements ActionListener{
    M_Plantilla modelo;
    v_Plantilla vista;

    public C_Plantilla() {
        vista=new v_Plantilla();
        modelo=new M_Plantilla();
        vista.jButton1.addActionListener(this);
        vista.jTextField1.addActionListener(this);
        vista.jComboBox2.addActionListener(this);
        vista.jTextField3.addActionListener(this);
        modelo.llenarB(vista.jComboBox1);
        modelo.llenarCliente(vista.jComboBox6);
        modelo.llenarCamion(vista.jComboBox2);
        modelo.llenarProduto(vista.jComboBox5);
        modelo.llenarDir(vista.jComboBox3);
        modelo.llenarDir(vista.jComboBox4);
        listar(vista.tabla);
    }
    public void iniciar()
    {
        vista.setTitle("Registrar Planilla");
        vista.setLocationRelativeTo(null);
        vista.show();    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==vista.jButton2) {
            listar(vista.tabla);
        }
        if (e.getSource()==vista.jButton1) {
            agregar();
        }
        listar(vista.tabla);
    }

    DefaultTableModel modelo1 = new DefaultTableModel();
    private void listar(JTable tabla){
        limpiarTabla();
        modelo1 = (DefaultTableModel)tabla.getModel();
        List<M_Plantilla>lista=this.modelo.listar();
        Object[]object= new Object[9];
        for (int i = 0; i < lista.size(); i++) {
            object[0]=lista.get(i).getIdplanilla();
            object[1]=lista.get(i).getFecha();
            object[2]=lista.get(i).getIdconductor();
            object[3]=lista.get(i).getIdcamion();
            object[4]=lista.get(i).getIddireccionO();
            object[5]=lista.get(i).getIddireccionD();
            object[6]=lista.get(i).getCantidad();
            object[7]=lista.get(i).getIdproducto();
            object[8]=lista.get(i).getIdcliente();
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
         if(vista.jComboBox1.getSelectedIndex()>0){
//            int id=Integer.parseInt(vista.jTextField1.getText());
            String fecha=vista.jTextField2.getText();
            int cantidad =Integer.parseInt(vista.jTextField3.getText());
            
            StringTokenizer st1=new StringTokenizer(vista.jComboBox1.getSelectedItem().toString(), " :- ");
            int id1 = Integer.parseInt(st1.nextToken());
            
            StringTokenizer st2=new StringTokenizer(vista.jComboBox2.getSelectedItem().toString(), " :- ");
            int id2 = Integer.parseInt(st2.nextToken());
            
            StringTokenizer st3=new StringTokenizer(vista.jComboBox3.getSelectedItem().toString(), " :- ");
            int id3 = Integer.parseInt(st3.nextToken());
            
            StringTokenizer st4=new StringTokenizer(vista.jComboBox4.getSelectedItem().toString(), " :- ");
            int id4 = Integer.parseInt(st4.nextToken());
   
            StringTokenizer st5=new StringTokenizer(vista.jComboBox5.getSelectedItem().toString(), " :- ");
            int id5 = Integer.parseInt(st5.nextToken());
            
            StringTokenizer st6=new StringTokenizer(vista.jComboBox6.getSelectedItem().toString(), " :- ");
            int id6 = Integer.parseInt(st6.nextToken());
   
   
//            modelo.setIdplanilla(id);
            modelo.setFecha(fecha);
            modelo.setIdconductor(id1);
            modelo.setIdcamion(id2);
            modelo.setIddireccionO(id3);
            modelo.setIddireccionD(id4);
            modelo.setCantidad(cantidad);
            modelo.setIdproducto(id5);
            modelo.setIdcliente(id6);
            int r = modelo.agregar(modelo);
            
            if(r==1) {
                JOptionPane.showMessageDialog(vista, "Dato Agregado con Exito");
            }else{    
                JOptionPane.showMessageDialog(vista, "Error");
            }    
        }
    }
}
