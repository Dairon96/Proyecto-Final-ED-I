package PaquetePrincipal;
public class TiendaMultilista implements ITienda{
    private Marca cabeza;

    public TiendaMultilista(){
        this.cabeza = null;
    }

    @Override
    public void agregararticulo( String marca, String nombreArt, String tipoArt) {

            Marca actualMarca = cabeza;

            while(actualMarca != null){
                if(actualMarca.getNombreMarca().equals(marca)){
                  Articulo nuevoArticulo = new Articulo(nombreArt, tipoArt);

                  if(actualMarca.getArticulos()==null){
                     actualMarca.setArticulos(nuevoArticulo);
                  } else {
                   Articulo actualArticulo = actualMarca.getArticulos();
                   while(actualArticulo.getSiguiente() != null){
                       actualArticulo = actualArticulo.getSiguiente();
                   }
                   actualArticulo.setSiguiente(nuevoArticulo);
                  }
    System.out.println("Articulo agregado "+nuevoArticulo.getNombreArt());
                  return ;
                }
                actualMarca=actualMarca.getSiguiente();
             }
         System.out.println("Marca no encontrada "+ marca);
    }

    @Override
    public void agregarmarca(String Marca) {

        Marca actualMarca = cabeza;
        while(actualMarca != null){
            if(actualMarca.getNombreMarca().equals(Marca)){
                System.out.println("Esta marca ya existe "+Marca);
                return;
            }
            actualMarca=actualMarca.getSiguiente();
        }

        Marca nuevaMarca = new Marca(Marca);

            if(cabeza == null){
                cabeza = nuevaMarca;
            } else{
                Marca actual = cabeza;
                while(actual.getSiguiente() != null){
                    actual = actual.getSiguiente();
                }
                actual.setSiguiente(nuevaMarca);
            }
            System.out.println("Marca agregada "+Marca);

    }

    @Override
    public void buscararticulospormarca(String Marca) {
       Marca actualMarca = cabeza;
       while(actualMarca != null){
           if(actualMarca.getNombreMarca().equals(Marca)){
               Articulo articulos = actualMarca.getArticulos();

               while(articulos != null){
                   System.out.println(articulos.getNombreArt());
                   articulos = articulos.getSiguiente();
               }
               return;
           }

           actualMarca=actualMarca.getSiguiente();
       }
       System.out.println("Marca no encontrada "+ Marca);
    }

    @Override
    public void eliminararticulo(String Marca, String nombreArt, String tipoArt) {
     Marca actualMarca = cabeza;

     while(actualMarca != null){
         if(actualMarca.getNombreMarca().equals(Marca)){
           Articulo articulos =actualMarca.getArticulos();
          
           if(articulos==null){
               System.out.println("No hay articulos ");
               return;
           }
           if (articulos.getNombreArt().equals(nombreArt) && 
                articulos.getTipoArt().equals(tipoArt)) {
                
                actualMarca.setArticulos(articulos.getSiguiente());
                System.out.println("Artículo eliminado: " + nombreArt + " (" + tipoArt + ")");
                return; 
             }
           Articulo actualArticulo = actualMarca.getArticulos();

               while (actualArticulo.getSiguiente() != null) {
                Articulo siguienteArticulo = actualArticulo.getSiguiente();
                
                if (siguienteArticulo.getNombreArt().equals(nombreArt) && 
                    siguienteArticulo.getTipoArt().equals(tipoArt)) {
                    
                    // Saltar el nodo a eliminar
                    actualArticulo.setSiguiente(siguienteArticulo.getSiguiente());
                    System.out.println("Artículo eliminado: " + nombreArt + " (" + tipoArt + ")");
                    return;  
              }
              actualArticulo=actualArticulo.getSiguiente();
            }
System.out.println("Articulo no encontrado  "+nombreArt);
           }
         actualMarca=actualMarca.getSiguiente();
         }
     System.out.println("No hay marcas");
       }
    
    @Override
public void actualizararticulo(String Marca, String nombreAntiguo, String tipoAntiguo, String nombreNuevo, String tipoNuevo) {
    
    Marca actualMarca = cabeza;
    
    while (actualMarca != null) {
        if (actualMarca.getNombreMarca().equals(Marca)) {
            
            Articulo actualArticulo = actualMarca.getArticulos();
            
           
            while (actualArticulo != null) {
                if (actualArticulo.getNombreArt().equals(nombreAntiguo) && 
                    actualArticulo.getTipoArt().equals(tipoAntiguo)) {
                    
                    actualArticulo.setNombreArt(nombreNuevo);
                    actualArticulo.setTipoArt(tipoNuevo);
                    
                    System.out.println("Artículo actualizado:");
                    System.out.println("  De: " + nombreAntiguo + " (" + tipoAntiguo + ")");
                    System.out.println("  A:  " + nombreNuevo + " (" + tipoNuevo + ")");
                    System.out.println("  Marca: " + Marca);
                    
                    return;
                }
                actualArticulo = actualArticulo.getSiguiente();
            }
            
            System.out.println("Artículo no encontrado: " + nombreAntiguo + " (" + tipoAntiguo + ")");
            return;
        }
        actualMarca = actualMarca.getSiguiente();
    }
    
    
    System.out.println("Marca no encontrada: " + Marca);
}

    @Override
    public int contarartpormarca(String Marca) {
         Marca actualMarca = cabeza;
         while(actualMarca != null){
             if(actualMarca.getNombreMarca().equals(Marca)){
             int contador=0;
                 Articulo articulos = actualMarca.getArticulos();
                 while(articulos != null){
                     contador++;
                     articulos = articulos.getSiguiente();
                 }
                 return contador;
             }
             actualMarca=actualMarca.getSiguiente();
         }
        return 0;
    }

    @Override
    public int contartotal() {
        Marca actualMarca = cabeza;
        int contador=0;
        while(actualMarca != null){
            Articulo articulos = actualMarca.getArticulos();
            while(articulos != null){
                contador++;
                articulos = articulos.getSiguiente();
            }
           actualMarca=actualMarca.getSiguiente();
        }
        return contador;
    }

    @Override
    public void mostrartodos() {
    Marca actualMarca = cabeza;
    while(actualMarca != null){
        System.out.println("---"+actualMarca.getNombreMarca()+"---");
        Articulo articulos = actualMarca.getArticulos();
        int contador=0;
        while(articulos != null){
            contador++;
            System.out.println(contador+"-"+articulos.getNombreArt());

            articulos=articulos.getSiguiente();
        }
        actualMarca=actualMarca.getSiguiente();
     }
    if(cabeza==null) {
        System.out.println("No hay articulos ");
      }
    }
}
