package PaquetePrincipal;

import java.sql.*;

public class ConexionBD {
  
    private static final String URL = "jdbc:postgresql://localhost:5432/motomania_db";
    private static final String USUARIO = "postgres";
    private static final String CONTRASENA = "Dai*2413";  
    
    private static Connection conexion = null;
    
    public static Connection conectar() {
        try {
            if (conexion == null || conexion.isClosed()) {
           
                Class.forName("org.postgresql.Driver");
                
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                System.out.println("✅ CONECTADO A POSTGRESQL");
            }
        } catch (Exception e) {
            System.err.println("❌ ERROR DE CONEXIÓN:");
            e.printStackTrace();
        }
        return conexion;
    }
    
    public static void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("🔌 Desconectado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean probarConexion() {
        try {
            Connection conn = conectar();
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Conexión activa");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Falló la prueba de conexión");
        }
        return false;
    }
}