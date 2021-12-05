package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

public class M_Persona {
    int id;
    String ci;
    String nombre;
    int idGenero;
    int telefono;
    int idTipo;

    public M_Persona() {
    }

    public M_Persona(int id, String ci, String nombre, int idGenero, int telefono, int idTipo) {
        this.id = id;
        this.ci = ci;
        this.nombre = nombre;
        this.idGenero = idGenero;
        this.telefono = telefono;
        this.idTipo = idTipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }
    
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<M_Persona>datos = new ArrayList<>();
        String sql = "select * from persona";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                M_Persona pm = new M_Persona();
                pm.setId(rs.getInt(1));
                pm.setCi(rs.getString(2));
                pm.setNombre(rs.getString(3));
                pm.setIdGenero(rs.getInt(4));
                pm.setTelefono(rs.getInt(5));
                pm.setIdTipo(rs.getInt(6));
                datos.add(pm);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    public int agregar(M_Persona m){
        String sql ="insert into persona(CI, Nombre, idGenero, Telefono, idTipo) values(?,?,?,?,?)";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, m.getCi());
            ps.setString(2, m.getNombre());
            ps.setInt(3, m.getIdGenero());
            ps.setInt(4, m.getTelefono());
            ps.setInt(5, m.getIdTipo());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return 1;
    }
    
    public int actualizar(M_Persona m){
        int r=0;
        String sql ="update persona set CI=?, Nombre=?, idGenero=?, Telefono=?, idTipo=? where idPersona=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, m.getCi());
            ps.setString(2, m.getNombre());
            ps.setInt(3, m.getIdGenero());
            ps.setInt(4, m.getTelefono());
            ps.setInt(5, m.getIdTipo());
            ps.setInt(6, m.getId());
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
        String sql="delete from persona where idPersona="+id;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void llenarTipo(JComboBox combo){
        combo.addItem("Ingrese el tipo");
        try{
        String sql = "select * from tipo";
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {  
                combo.addItem(rs.getInt("idTipo") +" :- "+ rs.getString("Nombre"));
            }
        } 
        catch(Exception err){err.printStackTrace();
        }
    } 
    
     public void llenarGenero(JComboBox combo){
        combo.addItem("Ingrese el genero");
        try{
        String sql = "select * from genero";
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {  
                combo.addItem(rs.getInt("idGenero") +" :- "+ rs.getString("Nombre"));
            }
        } 
        catch(Exception err){err.printStackTrace();
        }
    } 
     
    public void llenarPropietario(JComboBox combo){
        combo.addItem("Ingrese el propietario");
        try{
        String sql = "SELECT propietario.idPropietario, persona.Nombre FROM propietario, persona WHERE persona.idPersona=propietario.idPersona";
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {  
                combo.addItem(rs.getInt("idPropietario") +" :- "+ rs.getString("Nombre"));
            }
        } 
        catch(Exception err){err.printStackTrace();
        }
    } 
}
