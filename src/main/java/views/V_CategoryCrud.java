package views;

import entities.E_category;
import business.B_categories;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LilJade
 */
public class V_CategoryCrud extends javax.swing.JDialog {

    //var to move JDialog
    int x, y;

    //objects to consult db
    E_category categoria = new E_category();
    B_categories business = new B_categories();

    // Creates new form V_CategoryCrud
    public V_CategoryCrud(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);

        cargar();
        cleanFields();
        blockFields();
        buttonsByDefault();
    }

    //Clean all fields of the form
    void cleanFields() {
        lblIdCat.setText("");
        txtNameCat.setText("");
        txtDescriptionCat.setText("");
    }

    void blockFields() {
        txtNameCat.setEnabled(false);
        txtDescriptionCat.setEnabled(false);
    }

    void unblockFields() {
        txtNameCat.requestFocus();
        txtNameCat.setEnabled(true);
        txtDescriptionCat.setEnabled(true);
    }

    void buttonsByDefault() {
        btnNewCategory.setEnabled(true);
        btnNewCategory.setText("Nueva Categoria");
        btnEdit.setEnabled(false);
        btnEdit.setText("Editar");
        btnDelete.setEnabled(false);
        btnCancel.setEnabled(false);
    }

    void cargar() {
        String titulos[] = {"ID", "Nombre Categoria", "Descripcion"};
        DefaultTableModel df = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        B_categories dc = new B_categories();
        ArrayList<E_category> ps = dc.B_listCategories();
        Iterator i = ps.iterator();
        String filas[] = new String[3];

        while (i.hasNext()) {
            E_category categoria;
            categoria = (E_category) i.next();
            int id = categoria.getIdCategory();
            filas[0] = String.valueOf(id);
            filas[1] = categoria.getNameC();
            filas[2] = categoria.getDescriptionC();

            df.addRow(filas);
        }
        tbCategory.setModel(df);

        tbCategory.getColumnModel().getColumn(0).setMaxWidth(50);
        tbCategory.getColumnModel().getColumn(0).setMinWidth(50);
        tbCategory.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(50);
        tbCategory.getTableHeader().getColumnModel().getColumn(0).setMinWidth(50);

        tbCategory.getColumnModel().getColumn(1).setMaxWidth(130);
        tbCategory.getColumnModel().getColumn(1).setMinWidth(130);
        tbCategory.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(130);
        tbCategory.getTableHeader().getColumnModel().getColumn(1).setMinWidth(130);
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
        pnTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCategory = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        pnConfig = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNameCat = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnNewCategory = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescriptionCat = new javax.swing.JTextArea();
        lblIdCat = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        pnTitle = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        lblTittle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 6));

        pnTable.setBackground(new java.awt.Color(204, 204, 255));
        pnTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tbCategory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nombre Categoría", "Decripción"
            }
        ));
        tbCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCategoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbCategory);

        btnRefresh.setBackground(new java.awt.Color(51, 0, 204));
        btnRefresh.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("Refrescar Datos");
        btnRefresh.setBorder(null);
        btnRefresh.setBorderPainted(false);
        btnRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefreshMouseClicked(evt);
            }
        });
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnTableLayout = new javax.swing.GroupLayout(pnTable);
        pnTable.setLayout(pnTableLayout);
        pnTableLayout.setHorizontalGroup(
            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
            .addGroup(pnTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnTableLayout.setVerticalGroup(
            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        pnConfig.setBackground(new java.awt.Color(153, 204, 255));
        pnConfig.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel1.setText("ID:");

        txtNameCat.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel2.setText("Nombre de la categoría:");

        jLabel3.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel3.setText("Descripción:");

        btnCancel.setBackground(new java.awt.Color(204, 0, 0));
        btnCancel.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancelar");
        btnCancel.setBorder(null);
        btnCancel.setBorderPainted(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnNewCategory.setBackground(new java.awt.Color(0, 153, 0));
        btnNewCategory.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        btnNewCategory.setForeground(new java.awt.Color(255, 255, 255));
        btnNewCategory.setText("Nueva Categoria");
        btnNewCategory.setBorder(null);
        btnNewCategory.setBorderPainted(false);
        btnNewCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCategoryActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(204, 0, 0));
        btnDelete.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Eliminar");
        btnDelete.setBorder(null);
        btnDelete.setBorderPainted(false);
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        txtDescriptionCat.setColumns(20);
        txtDescriptionCat.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        txtDescriptionCat.setLineWrap(true);
        txtDescriptionCat.setRows(5);
        txtDescriptionCat.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtDescriptionCat);

        lblIdCat.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        lblIdCat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIdCat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnEdit.setBackground(new java.awt.Color(255, 153, 0));
        btnEdit.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Editar");
        btnEdit.setBorder(null);
        btnEdit.setBorderPainted(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnConfigLayout = new javax.swing.GroupLayout(pnConfig);
        pnConfig.setLayout(pnConfigLayout);
        pnConfigLayout.setHorizontalGroup(
            pnConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(txtNameCat)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNewCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnConfigLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblIdCat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnConfigLayout.setVerticalGroup(
            pnConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblIdCat, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNameCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNewCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnTitle.setBackground(new java.awt.Color(255, 255, 255));
        pnTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnExit.setBackground(new java.awt.Color(204, 0, 0));
        btnExit.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("Cerrar");
        btnExit.setBorder(null);
        btnExit.setBorderPainted(false);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblTittle.setFont(new java.awt.Font("MADE TOMMY", 1, 36)); // NOI18N
        lblTittle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTittle.setText("Configurar Categorías");
        lblTittle.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lblTittleMouseDragged(evt);
            }
        });
        lblTittle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblTittleMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnTitleLayout = new javax.swing.GroupLayout(pnTitle);
        pnTitle.setLayout(pnTitleLayout);
        pnTitleLayout.setHorizontalGroup(
            pnTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTittle, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnTitleLayout.setVerticalGroup(
            pnTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTitleLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTittle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCategoryMouseClicked
        int seleccionar = tbCategory.rowAtPoint(evt.getPoint());

        lblIdCat.setText(String.valueOf(tbCategory.getValueAt(seleccionar, 0)));
        txtNameCat.setText(String.valueOf(tbCategory.getValueAt(seleccionar, 1)));
        txtDescriptionCat.setText(String.valueOf(tbCategory.getValueAt(seleccionar, 2)));

        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
        btnCancel.setEnabled(true);
        btnNewCategory.setEnabled(false);
    }//GEN-LAST:event_tbCategoryMouseClicked

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        cargar();
        blockFields();
        cleanFields();
        buttonsByDefault();
    }//GEN-LAST:event_btnCancelActionPerformed

    boolean NoRepeatData() {
        for (int i = 0; i < tbCategory.getRowCount(); i++) {
            if (txtNameCat.getText().equals(tbCategory.getValueAt(i, 1)) && txtDescriptionCat.getText().equals(tbCategory.getValueAt(i, 2))) {
                JOptionPane.showMessageDialog(null, "Ya existe un registro idéntico a éste.\nRevise la Categoría #" + tbCategory.getValueAt(i, 0));
                return false;
            }
        }

        return true;
    }

    private void btnNewCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCategoryActionPerformed
        if (btnNewCategory.getText().equals("Nueva Categoria")) {
            btnNewCategory.setText("Guardar");
            btnCancel.setEnabled(true);
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);

            unblockFields();

        } else if (btnNewCategory.getText().equals("Guardar")) {
            if (txtNameCat.getText().equals("") || txtDescriptionCat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe asegurarse de rellenar todos los campos!");
            } else {

                if (NoRepeatData() == true) {
                    categoria.setNameC(txtNameCat.getText());
                    categoria.setDescriptionC(txtDescriptionCat.getText());
                    business.B_insertCategory(categoria);

                    btnNewCategory.setText("Nueva Categoria");
                    btnCancel.setEnabled(false);
                    btnEdit.setEnabled(false);
                    btnDelete.setEnabled(false);
                    cleanFields();
                    blockFields();
                    cargar();
                }
            }
        }
    }//GEN-LAST:event_btnNewCategoryActionPerformed

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked

    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        categoria.setIdCategory(Integer.parseInt(lblIdCat.getText()));
        business.B_deleteCategory(categoria);

        cargar();
        cleanFields();
        buttonsByDefault();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMouseClicked
        cargar();
    }//GEN-LAST:event_btnRefreshMouseClicked

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        cleanFields();
        cargar();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void lblTittleMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTittleMouseDragged
        Point p = MouseInfo.getPointerInfo().getLocation();
        setLocation(p.x - x, p.y - y);
    }//GEN-LAST:event_lblTittleMouseDragged

    private void lblTittleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTittleMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_lblTittleMousePressed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (btnEdit.getText().equals("Editar")) {
            btnEdit.setText("Guardar Cambios");
            btnDelete.setEnabled(false);
            unblockFields();

        } else if (btnEdit.getText().equals("Guardar Cambios")) {
            if (txtNameCat.getText().equals("") || txtDescriptionCat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe asegurarse de rellenar todos los campos!");
            } else {
                btnEdit.setText("Editar");

                categoria.setNameC(txtNameCat.getText());
                categoria.setDescriptionC(txtDescriptionCat.getText());
                categoria.setIdCategory(Integer.parseInt(lblIdCat.getText()));

                business.B_updateCategory(categoria);

                cleanFields();
                blockFields();
                cargar();
                buttonsByDefault();
            }
        }
    }//GEN-LAST:event_btnEditActionPerformed

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
            java.util.logging.Logger.getLogger(V_CategoryCrud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(V_CategoryCrud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(V_CategoryCrud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(V_CategoryCrud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                V_CategoryCrud dialog = new V_CategoryCrud(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNewCategory;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblIdCat;
    private javax.swing.JLabel lblTittle;
    private javax.swing.JPanel pnConfig;
    private javax.swing.JPanel pnTable;
    private javax.swing.JPanel pnTitle;
    private javax.swing.JTable tbCategory;
    private javax.swing.JTextArea txtDescriptionCat;
    private javax.swing.JTextField txtNameCat;
    // End of variables declaration//GEN-END:variables
}