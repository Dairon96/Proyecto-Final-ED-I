package PaquetePrincipal;


public class Marca {
     private String nombreMarca;
     private Articulo articulos;
     private Marca siguiente;

      public Marca(String nombreMarca) {
          this.nombreMarca = nombreMarca;
          this.articulos = null;
          this.siguiente = null;
      }
      public String getNombreMarca() {
          return nombreMarca;
      }
      public void setNombreMarca(String nombreMarca) {
          this.nombreMarca = nombreMarca;
      }
      public Articulo getArticulos() {
          return articulos;
      }
      public void setArticulos(Articulo articulos) {
          this.articulos = articulos;
      }
      public Marca getSiguiente() {
          return siguiente;
      }
      public void setSiguiente(Marca siguiente) {
          this.siguiente = siguiente;
      }
 }