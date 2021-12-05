package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

public class M_Camion {
    int id;
    String placa;
    int numero;
    int modelo;
    String color;
    int  capacidad;
    String descripcion;
    int idmarca;

    public M_Camion() {
    }

    public M_Camion(int id, String placa, int numero, int modelo, String color, int capacidad, String descripcion, int idmarca) {
        this.id = id;
        this.placa = placa;
        this.numero = numero;
        this.modelo = modelo;
        this.color = color;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.idmarca = idmarca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(int idmarca) {
        this.idmarca = idmarca;
    }
    
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<M_Camion>datos = new ArrayList<>();
        String sql = "select * from camion";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                M_Camion m = new M_Camion();
                m.setId(rs.getInt(1));
                m.setPlaca(rs.getString(2));
                m.setNumero(rs.getInt(3));
                m.setModelo(rs.getInt(4));
                m.setCapacidad(rs.getInt(5));
                m.setColor(rs.getString(6));
                m.setDescripcion(rs.getString(7));
                m.setIdmarca(rs.getInt(8));                
                datos.add(m);
            }
        } catch (Exception e) {
        }
        return datos;
    }    
   
    public int agregar(M_Camion m){
        String sql ="insert into camion(Placa, Numero, Modelo, Capacidad, Color, Descripcion, idMarca) values(?,?,?,?,?,?,?)";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, m.getPlaca());
            ps.setInt(2, m.getNumero());
            ps.setInt(3, m.getModelo());
            ps.setInt(4, m.getCapacidad());
            ps.setString(5, m.getColor());
            ps.setString(6, m.getDescripcion());
            ps.setInt(7, m.getIdmarca());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return 1;
    }
    
     public void llenarB(JComboBox combo)
    {
        combo.addItem("Ingrese la marca");
        try{
        String sql = "select * from marca";
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {  
                combo.addItem(rs.getInt("idMarca") +":"+ rs.getString("Nombre"));
            }
        } 
        catch(Exception err){
            err.printStackTrace();
        }
    }
    
     public int actualizar(M_Camion m){
        int r=0;
        String sql ="update camion set Placa=?, Numero=?, Modelo=?, Capacidad=?, Color=?, Descripcion=?, idMarca=? where idCamion=?";
        //   String sql ="update producto set Nombre=?, IdMedida=? where idProducto=?";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, m.getPlaca());
            ps.setInt(2, m.getNumero());
            ps.setInt(3, m.getModelo());
            ps.setInt(4, m.getCapacidad());
            ps.setString(5, m.getColor());
            ps.setString(6, m.getDescripcion());
            ps.setInt(7, m.getIdmarca());
            ps.setInt(8, m.getId());
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
        String sql="delete from camion where idCamion="+id;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
     public void llenarCamion(JComboBox combo){
        combo.addItem("Selecciona el Camion");
        try{
        String sql = "select idCamion, Placa from camion";
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {  
                combo.addItem(rs.getInt("idCamion")  +" :- "+ rs.getString("Placa"));
            }
        } 
        catch(Exception err){
            err.printStackTrace();
        }
    }
}
