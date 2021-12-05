package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class M_Ruta {
    int id;
    String nombre;
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public M_Ruta() {
    }

    public M_Ruta(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
    
    public List listar(){
        List<M_Ruta>datos = new ArrayList<>();
        String sql = "select * from ruta";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                M_Ruta rm = new M_Ruta();
                rm.setId(rs.getInt(1));
                rm.setNombre(rs.getString(2));
                datos.add(rm);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    public int agregar(M_Ruta m){
        String sql ="insert into ruta(Nombre) values(?)";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return 1;
    }
    
    public int actualizar(M_Ruta m){
        int r=0;
        String sql ="update ruta set Nombre=? where idRuta=?";
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
        String sql="delete from ruta where idRuta="+id;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
}
