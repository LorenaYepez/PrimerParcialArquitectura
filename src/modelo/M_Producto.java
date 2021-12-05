package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

public class M_Producto {
    int id;
    String nombre;
    int idMedida;
    Connection con;
    Conexion conectar = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public M_Producto() {
    }

    public M_Producto(int id, String nombre, int idMedida) {
        this.id = id;
        this.nombre = nombre;
        this.idMedida = idMedida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(int idMedida) {
        this.idMedida = idMedida;
    }
    
    public List listar(){
        List<M_Producto>datos = new ArrayList<>();
        String sql = "select * from producto";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                M_Producto pm = new M_Producto();
                pm.setId(rs.getInt(1));
                pm.setNombre(rs.getString(2));
                pm.setIdMedida(rs.getInt(3));
                datos.add(pm);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    public int agregar(M_Producto m){
        String sql ="insert into producto(Nombre, IdMedida) values(?,?)";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getIdMedida());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return 1;
    }
     
    public int actualizar(M_Producto m){
        int r=0;
        String sql ="update producto set Nombre=?, IdMedida=? where idProducto=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getIdMedida());
            ps.setInt(3, m.getId());
            r = ps.executeUpdate();
            if (r==1) {
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e) {
        }
        return r;
    }
    
    public void delete(int id){
        String sql="delete from producto where idProducto="+id;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void llenarB(JComboBox combo)
    {
        Statement stmt;
        combo.addItem("Ingrese la medida");
        try{
        String sql = "select * from medida";
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {  
                combo.addItem(rs.getInt("idMedida") +":"+ rs.getString("Nombre"));
            }
        } 
        catch(Exception err){err.printStackTrace();
        }
    } 
}
