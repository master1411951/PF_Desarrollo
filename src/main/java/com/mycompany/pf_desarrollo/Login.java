package com.mycompany.pf_desarrollo;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class home extends javax.swing.JFrame {

    String usuario, contraseña, ip;
    int id;
    public home() {
        initComponents();
        Conecta();
    }  
    
   private void Conecta() {
      Connection c = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/a00243504",
            "a00243504", "p00243504");
      } catch (Exception e) {
         e.printStackTrace();
         JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos"+e.toString());
         System.exit(0);
     JOptionPane.showMessageDialog(null, "Conexión exitosa");
    } 
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Entrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Registro");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Inicio de Sesión");

        jLabel3.setText("Usuario");

        jLabel4.setText("Contraseña");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                    .addComponent(jPasswordField1))))))
                .addContainerGap(244, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(54, 54, 54))
        );

        pack();
    }// </editor-fold>                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        usuario= jTextField1.getText();
        contraseña=jPasswordField1.getText();
        registro(usuario, contraseña);
    }                                        
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        usuario= jTextField1.getText();
        contraseña=jPasswordField1.getText();
        login(usuario, contraseña);
    }                                        
    //Se registra el usuario
    private void registro(String usuario, String contraseña){
      int resultado = 0;
      Connection c = null;
      Statement stmt = null;
      if (usuario.equals("")|| contraseña.equals("")){
          JOptionPane.showMessageDialog(null, "Usuario o contraseña vacios");
      }else{
        try {
           Class.forName("org.postgresql.Driver");
           c = DriverManager
              .getConnection("jdbc:postgresql://localhost:5432/a00243504",
              "a00243504", "p00243504");
           stmt = c.createStatement();
           ResultSet rs = stmt.executeQuery( "SELECT id FROM usuarios_login where nombre_usuario = '"+usuario+"';" );
           while (rs.next()){
               resultado = rs.getInt("id");
           }
           if(resultado == 0){
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO usuarios_login (nombre_usuario, contraseña) " + "VALUES('"+usuario+"','"+contraseña+"');";
             JOptionPane.showMessageDialog(null, "Registro exitoso");
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
           }else{
              JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe");
           }
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error "+e.getClass().getName()+": "+ e.getMessage());
            System.exit(0);
        }
      }
      jButton2.disable();
    }
    private void login(String usuario, String contraseña){
      String resultado = "";
      Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
              .getConnection("jdbc:postgresql://localhost:5432/a00243504",
              "a00243504", "p00243504");
         c.setAutoCommit(false);

         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT contraseña FROM usuarios_login where nombre_usuario = '"+usuario+"';" );
         while ( rs.next() ) {
            resultado = rs.getString("contraseña");
         }
        if (usuario.equals("") || contraseña.equals("")){ 
            JOptionPane.showMessageDialog(null, "Usuario o contraseña vacios");
        }else{
            if (resultado.equals(contraseña)){
                InetAddress IP = InetAddress.getLocalHost();
                ip = IP.getHostAddress();
               stmt = c.createStatement();
               String sql = ("UPDATE usuarios_login SET ip = '"+ip+"' where nombre_usuario = '"+usuario+"';");
               stmt.executeUpdate(sql);
               c.commit();
               stmt = c.createStatement();
               ResultSet s = stmt.executeQuery( "SELECT id FROM usuarios_login where nombre_usuario = '"+usuario+"';" );
            
               while (s.next()){
                   id = s.getInt("id");
               }
         
               // si el usuario existe y la contraseña es correcta cambiamos la vista a conversaciones
               //aqui se tiene que juntar con otra pagina del proyecto de desarrollo :v
               //
               //
               //conversaciones con = new conversaciones(id, usuario);
                   this.dispose();
                   con.setVisible(true);

            }else{
                JOptionPane.showMessageDialog(null,"Usuario o contraseña invalidos");
            }
        }
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         JOptionPane.showMessageDialog(null, "Error de conexion"+e.toString());
      }
    }
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration                   
}
