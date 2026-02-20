/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PaquetePrincipal;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
/**
 *
 * @author Dairon
 */
public class Visual extends javax.swing.JFrame {
    
    private TiendaMultilista tienda = new TiendaMultilista();
    private BaseDatosManager bdManager = new BaseDatosManager();
    
    DefaultTableModel dtm= new DefaultTableModel();
    DefaultTableModel dtm2= new DefaultTableModel();
    DefaultTableModel dtm3= new DefaultTableModel();
    
  
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Visual.class.getName());

    /**
     * Creates new form Visual
     */
    public Visual() {
         this.setUndecorated(true);
         
        initComponents();
         this.setLocationRelativeTo(null);
       
        String[] titulo=new String[]{"Nombre","Tipo"};
         dtm.setColumnIdentifiers(titulo);
        jTable1.setModel(dtm);      
         
        String[] titulo2=new String[]{"Nombre","Tipo"};
         dtm2.setColumnIdentifiers(titulo2);
         jTable2.setModel(dtm2); 
        
         String[] titulo3=new String[]{"Nombre","Tipo"};
         dtm3.setColumnIdentifiers(titulo3);
          jTable3.setModel(dtm3); 
          
      jTable1.getSelectionModel().addListSelectionListener(e -> {
    if (e.getValueIsAdjusting()) return;

    if (jTable1.getSelectedRow() != -1) {
        jTable2.clearSelection();
        jTable3.clearSelection();
    }
});

      jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
jTable3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      
jTable2.getSelectionModel().addListSelectionListener(e -> {
    if (e.getValueIsAdjusting()) return;

    if (jTable2.getSelectedRow() != -1) {
        jTable1.clearSelection();
        jTable3.clearSelection();
    }
});

jTable3.getSelectionModel().addListSelectionListener(e -> {
    if (e.getValueIsAdjusting()) return;

    if (jTable3.getSelectedRow() != -1) {
        jTable1.clearSelection();
        jTable2.clearSelection();
    }
});
          
          
          MarcaCombo.setModel(new javax.swing.DefaultComboBoxModel<>(
    new String[] { "GN125", "AX100", "ETZ"}
));
    tienda.agregarmarca("GN125");
    tienda.agregarmarca("AX100");
    tienda.agregarmarca("ETZ");
               
    
     boolean conectado = ConexionBD.probarConexion();
    if (conectado) {
        System.out.println("✅ Base de datos lista");
    } else {
        System.out.println("⚠️  No se pudo conectar a la BD");
        JOptionPane.showMessageDialog(this,
            "No se pudo conectar a la base de datos.\n" +
            "Verifica que PostgreSQL esté corriendo.",
            "Error de Conexión",
            JOptionPane.WARNING_MESSAGE);
    }
    }
     void agregar(){
         String nom = TextNom.getText();
         String tip = TextTipo.getText();
      if(nom.isBlank() || tip.isBlank() || !nom.matches("^[A-Za-z\\s]+$") || !tip.matches("^[A-Za-z\\s]+$") ){
          JOptionPane.showMessageDialog(null, "Rellene correctamente los campos");
          return;
      }
         String marcaSeleccionada = (String) MarcaCombo.getSelectedItem();
          tienda.agregararticulo(marcaSeleccionada, nom, tip);
         
         switch(marcaSeleccionada){
                 
             case "GN125":
                  dtm.addRow(new Object[]{
          TextNom.getText(), TextTipo.getText()
        });
                  break;
               case "AX100":
                  dtm2.addRow(new Object[]{
          TextNom.getText(), TextTipo.getText()
        });       
                  break;
                   
             case "ETZ":
                  dtm3.addRow(new Object[]{
          TextNom.getText(), TextTipo.getText()
        });
                  break; 
                 
         }   
          TextNom.setText("");
          TextTipo.setText("");
          TextNom.requestFocus();
    }
     void eliminar(){
    int fila = jTable1.getSelectedRow();
    int fila2= jTable2.getSelectedRow();
    int fila3= jTable3.getSelectedRow();
    
    if(fila!=-1 || fila2!=-1 || fila3!=-1){
     
        if(fila!=-1){
          String nombre = (String) jTable1.getValueAt(fila, 0); 
           String tipo = (String) jTable1.getValueAt(fila, 1);
            tienda.eliminararticulo("GN125", nombre, tipo);
             dtm.removeRow(fila);
                   
        }
        if(fila2!=-1){
           String nombre = (String) jTable2.getValueAt(fila2, 0); 
            String tipo = (String) jTable2.getValueAt(fila2, 1);
             tienda.eliminararticulo("AX100", nombre, tipo);
              dtm2.removeRow(fila2);
        }
        if(fila3!=-1){
           String nombre = (String) jTable3.getValueAt(fila3, 0); 
            String tipo = (String) jTable3.getValueAt(fila3, 1);
             tienda.eliminararticulo("ETZ", nombre, tipo);
              dtm3.removeRow(fila3);
        }
        return;
    } else { JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        return;}
   
         
}
     
     void actualizar() {
    String nuevoNombre = TextNom.getText().trim();
    String nuevoTipo = TextTipo.getText().trim();
    
   
    if(nuevoNombre.isEmpty()) {
        JOptionPane.showMessageDialog(null, "El campo Nombre es obligatorio");
        TextNom.requestFocus();
        return;
    }
    
    if(nuevoTipo.isEmpty()) {
        JOptionPane.showMessageDialog(null, "El campo Tipo es obligatorio");
        TextTipo.requestFocus();
        return;
    }
    

    DefaultTableModel modelo = null;
    int filaSeleccionada = -1;
    String nombreTabla = "";
    
    if(jTable1.getSelectedRow() != -1) {
        filaSeleccionada = jTable1.getSelectedRow();
        modelo = dtm;
        nombreTabla = "GN125";
    } else if(jTable2.getSelectedRow() != -1) {
        filaSeleccionada = jTable2.getSelectedRow();
        modelo = dtm2;
        nombreTabla = "AX100";
    } else if(jTable3.getSelectedRow() != -1) {
        filaSeleccionada = jTable3.getSelectedRow();
        modelo = dtm3;
        nombreTabla = "ETZ";
    }
    
   
    if(filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, 
            "Seleccione una fila para actualizar");
        return;
    }
    
    String nombreAntiguo = (String) modelo.getValueAt(filaSeleccionada, 0);
    String tipoAntiguo = (String) modelo.getValueAt(filaSeleccionada, 1);
    
 
    int confirmacion = JOptionPane.showConfirmDialog(null,
        "¿Actualizar '" + nombreAntiguo + "' (" + tipoAntiguo + ")" +
        "\npor '" + nuevoNombre + "' (" + nuevoTipo + ")" +
        "\nen tabla " + nombreTabla + "?",
        "Confirmar actualización",
        JOptionPane.YES_NO_OPTION);
    
    if(confirmacion != JOptionPane.YES_OPTION) {
        return;
    }
    

    modelo.setValueAt(nuevoNombre, filaSeleccionada, 0);
    modelo.setValueAt(nuevoTipo, filaSeleccionada, 1);
    tienda.actualizararticulo(nombreTabla, nombreAntiguo, tipoAntiguo, nuevoNombre, nuevoTipo);
    
         ordenarTablas(modelo);

    JOptionPane.showMessageDialog(null,
        "✓ Actualización exitosa\n" +
        "• Tabla: " + nombreTabla + "\n" +
        "• Anterior: " + nombreAntiguo + " (" + tipoAntiguo + ")\n" +
        "• Nuevo: " + nuevoNombre + " (" + nuevoTipo + ")");
    
    
    if(jTable1.getSelectedRow() != -1) jTable1.clearSelection();
    if(jTable2.getSelectedRow() != -1) jTable2.clearSelection();
    if(jTable3.getSelectedRow() != -1) jTable3.clearSelection();
}
     
     private void ordenarTablas(DefaultTableModel modelo) {
    int cantidadFilas = modelo.getRowCount();
    
    if (cantidadFilas < 2) return;
    
    int contador = 0;
    int i = 0;
    int j = 1;
    
    while(contador < cantidadFilas) {
        if (j >= cantidadFilas) {
            contador++;      
            i = 0;           
            j = 1;           
            continue;      
        }

        String filasuperiorN = (String) modelo.getValueAt(i, 0);
        String filasuperiorT = (String) modelo.getValueAt(i, 1);   
        String filainferiorN = (String) modelo.getValueAt(j, 0);
        String filainferiorT = (String) modelo.getValueAt(j, 1);
        
        if(filasuperiorT.compareTo(filainferiorT) > 0) {
            modelo.setValueAt(filainferiorN, i, 0);
            modelo.setValueAt(filainferiorT, i, 1);
            modelo.setValueAt(filasuperiorN, j, 0);
            modelo.setValueAt(filasuperiorT, j, 1);
            
            contador = -1;
            i = -1;
            j = 0;
        }
        
        i++;
        j++;
        contador++;
    }
 }
     
     private void guardarTodoEnBD() {
    
    int confirmacion = JOptionPane.showConfirmDialog(
        this, "¿Guardar TODOS los datos en la base de datos?\n" +
        "Esto sobreescribirá lo que haya en la BD.",
        "Confirmar Guardado",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
    );
    
    if (confirmacion != JOptionPane.YES_OPTION) {
        return;
    }
    
    JOptionPane.showMessageDialog(this,
        "Guardando datos en la base de datos...",
        "",
        JOptionPane.INFORMATION_MESSAGE);
   
    int totalGuardados = 0;
    
    List<String[]> articulosGN125 = obtenerDatosDeTabla(dtm);
    if (bdManager.guardarTodosArticulos("GN125", articulosGN125)) {
        totalGuardados += articulosGN125.size();
    }
    
    List<String[]> articulosAX100 = obtenerDatosDeTabla(dtm2);
    if (bdManager.guardarTodosArticulos("AX100", articulosAX100)) {
        totalGuardados += articulosAX100.size();
    }
    
    List<String[]> articulosETZ = obtenerDatosDeTabla(dtm3);
    if (bdManager.guardarTodosArticulos("ETZ", articulosETZ)) {
        totalGuardados += articulosETZ.size();
    }
    
 
    JOptionPane.showMessageDialog(this,
        "✅ Guardado exitoso\n" +
        "Total de artículos guardados: " + totalGuardados,
        "Guardado Completado",
        JOptionPane.INFORMATION_MESSAGE);
}


private List<String[]> obtenerDatosDeTabla(DefaultTableModel modelo) {
    List<String[]> datos = new ArrayList<>();
    
    for (int i = 0; i < modelo.getRowCount(); i++) {
        String nombre = (String) modelo.getValueAt(i, 0);
        String tipo = (String) modelo.getValueAt(i, 1);
        datos.add(new String[]{nombre, tipo});
    }
    
    return datos;
}

private void cargarDesdeBD() {
    // Limpiar tablas
    dtm.setRowCount(0);
    dtm2.setRowCount(0);
    dtm3.setRowCount(0);
    
    tienda = new TiendaMultilista();
tienda.agregarmarca("GN125");
tienda.agregarmarca("AX100");
tienda.agregarmarca("ETZ");

    List<String[]> gn125 = bdManager.cargarArticulos("GN125");
    for (String[] articulo : gn125) {
        dtm.addRow(articulo);
           tienda.agregararticulo("GN125", articulo[0], articulo[1]);
    }
    
    List<String[]> ax100 = bdManager.cargarArticulos("AX100");
    for (String[] articulo : ax100) {
        dtm2.addRow(articulo);
           tienda.agregararticulo("AX100", articulo[0], articulo[1]);
    }
    
    List<String[]> etz = bdManager.cargarArticulos("ETZ");
    for (String[] articulo : etz) {
        dtm3.addRow(articulo);
           tienda.agregararticulo("ETZ", articulo[0], articulo[1]);
    }
    JOptionPane.showMessageDialog(null, "Datos cargados exitosamente desde la base de datos");
 
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        MarcaCombo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        TextNom = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TextTipo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, new java.awt.Color(255, 255, 255)));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel7.setFont(new java.awt.Font("Papyrus", 0, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 0, 0));
        jLabel7.setText("Motomania");

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton4.setText("X");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(335, 335, 335)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton1.setText("AÑADIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton2.setText("MODIFICAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton3.setText("ELIMINAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(51, 51, 51));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton5.setText("CANTIDAD X MARCA");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(51, 51, 51));
        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton6.setText("GUARDAR EN DB");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(51, 51, 51));
        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton7.setText("CARGAR DB");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane2.setViewportView(jTable2);

        jTable3.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane3.setViewportView(jTable3);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("GN125");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("AX100");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ETZ");

        jLabel4.setFont(new java.awt.Font("Mongolian Baiti", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Marca:");

        MarcaCombo.setBackground(new java.awt.Color(51, 51, 51));
        MarcaCombo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        MarcaCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AX100", "GN125", "ETZ", " " }));

        jLabel5.setFont(new java.awt.Font("Mongolian Baiti", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre:");

        TextNom.setBackground(new java.awt.Color(51, 51, 51));
        TextNom.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Mongolian Baiti", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tipo:");

        TextTipo.setBackground(new java.awt.Color(51, 51, 51));
        TextTipo.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(178, 178, 178)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MarcaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextNom, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(TextTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)))))
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 12, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextNom, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MarcaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      agregar();
       ordenarTablas(dtm);
       ordenarTablas(dtm2);
       ordenarTablas(dtm3);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.exit(0);    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        eliminar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     actualizar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      String marcaSeleccionada = (String) MarcaCombo.getSelectedItem();
       int contar= tienda.contarartpormarca(marcaSeleccionada);
       JOptionPane.showMessageDialog(null, "La marca "+marcaSeleccionada+" cuenta con: "+contar+" articulos");
       
     
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         guardarTodoEnBD();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        cargarDesdeBD();
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Visual().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> MarcaCombo;
    private javax.swing.JTextField TextNom;
    private javax.swing.JTextField TextTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
