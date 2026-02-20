package PaquetePrincipal;

public class Articulo {
     private String nombreArt;
     private String tipoArt;
     private Articulo siguiente;

     public Articulo(String nombreArt, String tipoArt) {
         this.nombreArt = nombreArt;
         this.tipoArt = tipoArt;
         this.siguiente = null;
     }

     public String getNombreArt() {
         return nombreArt;
     }
     public void setNombreArt(String nombreArt) {
         this.nombreArt = nombreArt;
     }
     public String getTipoArt() {
         return tipoArt;
     }
     public void setTipoArt(String tipoArt) {
         this.tipoArt = tipoArt;
     }
     public Articulo getSiguiente() {
         return siguiente;
     }
     public void setSiguiente(Articulo siguiente) {
         this.siguiente = siguiente;
     }
 }

