package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class M_Registro {
    int idregistro;
    int idcamion;
    int idpropietario;
    String fecha;
    
    Connection con;
    Conexion conectar = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public M_Registro() {
    }

    public M_Registro(int idregistro, int idcamion, int idpropietario, String fecha) {
        this.idregistro = idregistro;
        this.idcamion = idcamion;
        this.idpropietario = idpropietario;
        this.fecha = fecha;
    }

    public int getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(int idregistro) {
        this.idregistro = idregistro;
    }

    public int getIdcamion() {
        return idcamion;
    }

    public void setIdcamion(int idcamion) {
        this.idcamion = idcamion;
    }

    public int getIdpropietario() {
        return idpropietario;
    }

    public void setIdpropietario(int idpropietario) {
        this.idpropietario = idpropietario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
        
    public List listar(){
        List<M_Registro>datos = new ArrayList<>();
        String sql = "select * from registro";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                M_Registro m = new M_Registro();
                m.setIdregistro(rs.getInt(1));
                m.setIdcamion(rs.getInt(2));
                m.setIdpropietario(rs.getInt(3));
                m.setFecha(rs.getString(4));              
                datos.add(m);
            }
        } catch (Exception e) {
        }
        return datos;
    }    
    
      public int agregar(M_Registro m){
        String sql ="insert into registro(idCamion, idPropietario, Fecha) values(?,?,?)";
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, m.getIdcamion());
            ps.setInt(2, m.getIdpropietario());
            ps.setString(3, m.getFecha());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return 1;
    }
    
}
