/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class M_Plantilla {
    //planilla
    int idplanilla;
    String fecha;
    int idconductor;
    int idcamion;
    int iddireccionO;
    int iddireccionD;
    
    //detalle
    int cantidad;
    int idproducto;
    int idcliente;

    public M_Plantilla() {
    }

    public M_Plantilla(int idplanilla, String fecha, int idconductor, int idcamion, int iddireccionO, int iddireccionD, int cantidad, int idproducto, int idcliente) {
        this.idplanilla = idplanilla;
        this.fecha = fecha;
        this.idconductor = idconductor;
        this.idcamion = idcamion;
        this.iddireccionO = iddireccionO;
        this.iddireccionD = iddireccionD;
        this.cantidad = cantidad;
        this.idproducto = idproducto;
        this.idcliente = idcliente;
    }

    public int getIdplanilla() {
        return idplanilla;
    }

    public void setIdplanilla(int idplanilla) {
        this.idplanilla = idplanilla;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdconductor() {
        return idconductor;
    }

    public void setIdconductor(int idconductor) {
        this.idconductor = idconductor;
    }

    public int getIdcamion() {
        return idcamion;
    }

    public void setIdcamion(int idcamion) {
        this.idcamion = idcamion;
    }

    public int getIddireccionO() {
        return iddireccionO;
    }

    public void setIddireccionO(int iddireccionO) {
        this.iddireccionO = iddireccionO;
    }

    public int getIddireccionD() {
        return iddireccionD;
    }

    public void setIddireccionD(int iddireccionD) {
        this.iddireccionD = iddireccionD;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }
    
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<M_Plantilla>datos = new ArrayList<>();
        String sql = "select * from planilla";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                M_Plantilla pm = new M_Plantilla();
                pm.setIdplanilla(rs.getInt(1));
                pm.setFecha(rs.getString(2));
                pm.setIdconductor(rs.getInt(3));
                pm.setIdcamion(rs.getInt(4));
                pm.setIddireccionO(rs.getInt(5));
                pm.setIddireccionD(rs.getInt(6));
                pm.setCantidad(rs.getInt(7));
                pm.setIdproducto(rs.getInt(8));
                pm.setIdcliente(rs.getInt(9));
                datos.add(pm);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return datos;
    }
    
    public int agregar(M_Plantilla m){
        String sql = "INSERT INTO `planilla` (`idPlanilla`, `fecha`, `idConductor`, `idCamion`, `iddireccionO`, `iddireccionD`, `Cantidad`, `idProducto`, `idCliente`) VALUES (NULL, ?, ?,?, ?,?,?,?,?)";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, m.getFecha());
            ps.setInt(2, m.getIdconductor());
            ps.setInt(3, m.getIdcamion());
            ps.setInt(4, m.getIddireccionO());
            ps.setInt(5, m.getIddireccionD());
            ps.setInt(6, m.getCantidad());
            ps.setInt(7, m.getIdproducto());
            ps.setInt(8, m.getIdcliente());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return 1;
    }
    public void llenarB(JComboBox combo)
    {
        combo.addItem("Ingrese la conductor");
        try{
        String sql = "select conductor.idConductor, persona.Nombre from conductor, persona where conductor.idPersona = persona.idPersona";
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {  
                combo.addItem(rs.getInt("idConductor") +":"+ rs.getString("Nombre"));
            }
        } 
        catch(Exception err){
            err.printStackTrace();
        }
    }
    public void llenarCliente(JComboBox combo)
    {
        combo.addItem("Ingrese la cliente");
        try{
        String sql = "select cliente.idCliente, persona.Nombre from cliente, persona where cliente.idPersona = persona.idPersona";
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {  
                combo.addItem(rs.getInt("idCliente") +":"+ rs.getString("Nombre"));
            }
        } 
        catch(Exception err){
            JOptionPane.showMessageDialog(null, err.toString());
        }
    }
    public void llenarCamion(JComboBox combo)
    {
        combo.addItem("Ingrese la camion");
        try{
        String sql = "select * from camion";
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {  
                combo.addItem(rs.getInt("idCamion") +":"+ rs.getString("Placa"));
            }
        } 
        catch(Exception err){
           JOptionPane.showMessageDialog(null, err.toString());
        }
    }
    
    public void llenarProduto(JComboBox combo)
    {
        combo.addItem("Ingrese el producto");
        try{
        String sql = "select * from producto";
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {  
                combo.addItem(rs.getInt("idProducto") +":"+ rs.getString("Nombre"));
            }
        } 
        catch(Exception err){
           JOptionPane.showMessageDialog(null, err.toString());
        }
    }
    
    public void llenarDir(JComboBox combo)
    {
        combo.addItem("Ingrese la  ruta");
        try{
        String sql = "select * from ruta";
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {  
                combo.addItem(rs.getInt("idRuta") +":"+ rs.getString("Nombre"));
            }
        } 
        catch(Exception err){
           JOptionPane.showMessageDialog(null, err.toString());
        }
    }
    
    public void llenarDirD(JComboBox combo)
    {
        combo.addItem("Ingrese la  ruta");
        try{
        String sql = "select * from ruta";
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {  
                combo.addItem(rs.getInt("idRuta") +":"+ rs.getString("Nombre"));
            }
        } 
        catch(Exception err){
           JOptionPane.showMessageDialog(null, err.toString());
        }
    }
}
