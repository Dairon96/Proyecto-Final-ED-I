package PaquetePrincipal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class BaseDatosManager {
    
    public List<String[]> cargarArticulos(String marca) {
        List<String[]> articulos = new ArrayList<>();
        
        String sql = "SELECT a.nombre, a.tipo " +
                     "FROM articulos a " +
                     "JOIN marcas m ON a.marca_id = m.id " +
                     "WHERE m.nombre = ? " +
                     "ORDER BY a.tipo, a.nombre";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, marca);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");
                articulos.add(new String[]{nombre, tipo});
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return articulos;
    }
    
    public boolean guardarTodosArticulos(String marca, List<String[]> articulos) {
        try (Connection conn = ConexionBD.conectar()) {
            
            String eliminarSQL = "DELETE FROM articulos " +
                                "WHERE marca_id = (SELECT id FROM marcas WHERE nombre = ?)";
            
            try (PreparedStatement pstmtEliminar = conn.prepareStatement(eliminarSQL)) {
                pstmtEliminar.setString(1, marca);
                pstmtEliminar.executeUpdate();
            }
            
          
            String insertarSQL = "INSERT INTO articulos (marca_id, nombre, tipo) " +
                                "VALUES ((SELECT id FROM marcas WHERE nombre = ?), ?, ?)";
            
            try (PreparedStatement pstmtInsertar = conn.prepareStatement(insertarSQL)) {
                for (String[] articulo : articulos) {
                    String nombre = articulo[0];
                    String tipo = articulo[1];
                    
                    pstmtInsertar.setString(1, marca);
                    pstmtInsertar.setString(2, nombre);
                    pstmtInsertar.setString(3, tipo);
                    pstmtInsertar.addBatch();  // Agregar a lote
                }
                
            
                pstmtInsertar.executeBatch();
                return true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}