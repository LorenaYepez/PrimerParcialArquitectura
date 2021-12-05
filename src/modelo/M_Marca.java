package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class M_Marca {
    int id;
    String nombre;
    Connection con;
    Conexion conectar = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public M_Marca(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public M_Marca() {
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
   
     public ArrayList<M_Marca> getMarca(){
        con=conectar.getConnection();
        Statement stmt;
        ArrayList<M_Marca>datos = new ArrayList<>();
         try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from marca");
            while (rs.next()) {                
                M_Marca marca = new M_Marca();
                marca.setId(rs.getInt("idMarca"));
                marca.setNombre(rs.getString("Nombre"));
                datos.add(marca);
            }
         } catch (Exception e) {
         }
        return datos;
    }
    public List listar(){
        List<M_Marca>datos = new ArrayList<>();
        String sql = "select * from marca";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                M_Marca m = new M_Marca();
                m.setId(rs.getInt(1));
                m.setNombre(rs.getString(2));
                datos.add(m);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return datos;
    }
    
    public void agregar(M_Marca m){
        String sql ="insert into marca(Nombre) values(?)";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.executeUpdate();
                   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    
    
    public int actualizar(M_Marca m){
        int r=0;
        String sql ="update marca set Nombre=? where idMarca=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getId());
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
        String sql="delete from marca where idMarca="+id;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
}
