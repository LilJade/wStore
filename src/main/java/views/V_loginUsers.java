/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import entities.E_users;
import business.B_users;

/**
 *
 * @author LilJade
 */
public class V_loginUsers extends javax.swing.JFrame {

    /**
     * Creates new form V_loginUsers
     */
    E_users user = new E_users();
    B_users business = new B_users();
    int x, y;
    
    public V_loginUsers() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblExit = new javax.swing.JLabel();
        lblMoveFrm = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        lblErrName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUserPass = new javax.swing.JPasswordField();
        lblErrPass = new javax.swing.JLabel();
        lblErrResult = new javax.swing.JLabel();
        jpContBtnLogin = new javax.swing.JPanel();
        btnLogin = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblExit.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        lblExit.setForeground(new java.awt.Color(255, 0, 0));
        lblExit.setText("X");
        lblExit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblExitMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblExitMousePressed(evt);
            }
        });
        getContentPane().add(lblExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 20, 30));

        lblMoveFrm.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lblMoveFrmMouseDragged(evt);
            }
        });
        lblMoveFrm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblMoveFrmMousePressed(evt);
            }
        });
        getContentPane().add(lblMoveFrm, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 430, 100));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 120, 140));

        jLabel1.setFont(new java.awt.Font("MADE TOMMY", 0, 18)); // NOI18N
        jLabel1.setText("Ingrese su nombre de usuario: ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        txtUserName.setFont(new java.awt.Font("MADE TOMMY", 1, 18)); // NOI18N
        txtUserName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });
        getContentPane().add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 290, 30));

        lblErrName.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        lblErrName.setForeground(new java.awt.Color(255, 0, 0));
        lblErrName.setText(" ");
        getContentPane().add(lblErrName, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 250, -1));

        jLabel2.setFont(new java.awt.Font("MADE TOMMY", 0, 18)); // NOI18N
        jLabel2.setText("Ingrese su contraseña: ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        txtUserPass.setFont(new java.awt.Font("MADE TOMMY", 1, 18)); // NOI18N
        getContentPane().add(txtUserPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 290, 30));

        lblErrPass.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        lblErrPass.setForeground(new java.awt.Color(255, 0, 0));
        lblErrPass.setText(" ");
        getContentPane().add(lblErrPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, 250, -1));

        lblErrResult.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        lblErrResult.setForeground(new java.awt.Color(255, 0, 0));
        lblErrResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblErrResult.setText(" ");
        getContentPane().add(lblErrResult, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, 290, -1));

        jpContBtnLogin.setBackground(new java.awt.Color(51, 204, 0));

        btnLogin.setFont(new java.awt.Font("MADE TOMMY", 1, 24)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLogin.setText("INICIAR SESIÓN");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLoginMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLoginMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLoginMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jpContBtnLoginLayout = new javax.swing.GroupLayout(jpContBtnLogin);
        jpContBtnLogin.setLayout(jpContBtnLoginLayout);
        jpContBtnLoginLayout.setHorizontalGroup(
            jpContBtnLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        jpContBtnLoginLayout.setVerticalGroup(
            jpContBtnLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jpContBtnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, 250, 50));

        lblBackground.setIcon(new javax.swing.ImageIcon("F:\\NetBeansProjects\\wStore\\src\\main\\java\\imgs\\background-03.jpg")); // NOI18N
        getContentPane().add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //use to move the form
    private void lblMoveFrmMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMoveFrmMouseDragged
        Point p = MouseInfo.getPointerInfo().getLocation();
        setLocation(p.x - x, p.y - y);
    }//GEN-LAST:event_lblMoveFrmMouseDragged

    //use to move the form
    private void lblMoveFrmMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMoveFrmMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_lblMoveFrmMousePressed

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblExitMouseClicked

    private void lblExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseEntered
        lblExit.setForeground(new Color(255, 102, 102));
    }//GEN-LAST:event_lblExitMouseEntered

    private void lblExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseExited
        lblExit.setForeground(new Color(255, 0, 0));
    }//GEN-LAST:event_lblExitMouseExited

    private void lblExitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMousePressed
        lblExit.setForeground(new Color(204, 0, 0));
    }//GEN-LAST:event_lblExitMousePressed

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameActionPerformed

    //login of the user
    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
        lblErrName.setText("");
        lblErrPass.setText("");
        lblErrResult.setText("¡Error!");

        if(txtUserName.getText().equals("")) {
            lblErrName.setText("Este campo es obligatorio*");
        }

        if (txtUserPass.getText().equals("")) {
            lblErrPass.setText("Este campo es obligatorio*");
        }
        
        user.setEmail(txtUserName.getText());
        user.setPass(txtUserPass.getText());
        
       /* lblErrResult.setText(business.B_loginUsers(user));
        System.out.println("User logged: " + user.toString());*/
       
        if (business.B_loginUsers2(user) == null) {
            lblErrResult.setForeground(Color.red);
            lblErrResult.setText("Login Fail!");
        } else {
            lblErrResult.setForeground(new Color(0, 255, 0));
            lblErrResult.setText("Login Success! Welcome " + user.getFirstName() + " " + user.getLastName());
            System.out.println("User logged: " + user.toString());
            
            V_Sale win = new V_Sale();
            win.setVisible(true);
                        
            this.dispose();
        }
    }//GEN-LAST:event_btnLoginMouseClicked

    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseEntered
        jpContBtnLogin.setBackground(new Color(0, 153, 0));
    }//GEN-LAST:event_btnLoginMouseEntered

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseExited
        jpContBtnLogin.setBackground(new Color(51, 204, 0));
    }//GEN-LAST:event_btnLoginMouseExited

    private void btnLoginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMousePressed
        jpContBtnLogin.setBackground(new Color(102, 255, 51));
    }//GEN-LAST:event_btnLoginMousePressed

    private void btnLoginMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseReleased
        jpContBtnLogin.setBackground(new Color(51, 204, 0));
    }//GEN-LAST:event_btnLoginMouseReleased

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
            java.util.logging.Logger.getLogger(V_loginUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(V_loginUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(V_loginUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(V_loginUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new V_loginUsers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jpContBtnLogin;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblErrName;
    private javax.swing.JLabel lblErrPass;
    private javax.swing.JLabel lblErrResult;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblMoveFrm;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JPasswordField txtUserPass;
    // End of variables declaration//GEN-END:variables
}
