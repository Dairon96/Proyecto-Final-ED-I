package PaquetePrincipal;

 public interface ITienda {
    void agregararticulo ( String Marca,String nombreArt, String tipoArt);
    void agregarmarca(String Marca);
    void eliminararticulo (String Marca, String nombreArt, String tipoArt);
    void actualizararticulo(String Marca, String nombreAntiguo, String tipoAntiguo, String nombreNuevo, String tipoNuevo);
    void mostrartodos();
    void buscararticulospormarca(String Marca);
    int contarartpormarca(String Marca);
    int contartotal();

 }