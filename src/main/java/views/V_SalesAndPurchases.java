package views;

import business.B_saleDetail;
import entities.E_product;
import entities.E_sale;
import entities.E_saleDetail;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LilJade
 */
public class V_SalesAndPurchases extends javax.swing.JDialog {

    B_saleDetail business = new B_saleDetail();
    E_saleDetail saleDetail = new E_saleDetail();
    E_product product = new E_product();

    // Creates new form V_SalesAndPurchases
    public V_SalesAndPurchases(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);

        monthNow();
        loadYears();
    }
    
    public void loadYears() {
        cmbYear.removeAllItems();
        for (int i = 2000; i <= 2100; i++) {
            String year = String.valueOf(i);
            cmbYear.addItem(year);
        }
        
        int year = LocalDate.now().getYear();
        
        int index = year - 2000;
        
        cmbYear.setSelectedIndex(index);
    }

    public void monthNow() {
        cmbMonths.removeAllItems();
        cmbMonths.addItem("Enero");
        cmbMonths.addItem("Febrero");
        cmbMonths.addItem("Marzo");
        cmbMonths.addItem("Abril");
        cmbMonths.addItem("Mayo");
        cmbMonths.addItem("Junio");
        cmbMonths.addItem("Julio");
        cmbMonths.addItem("Agosto");
        cmbMonths.addItem("Septiembre");
        cmbMonths.addItem("Octubre");
        cmbMonths.addItem("Noviembre");
        cmbMonths.addItem("Diciembre");

        Month month = LocalDate.now().getMonth();
        String actualMonth = month.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

        switch (actualMonth) {
            case "enero":
                cmbMonths.setSelectedIndex(0);
                break;
            case "febrero":
                cmbMonths.setSelectedIndex(1);
                break;
            case "marzo":
                cmbMonths.setSelectedIndex(2);
                break;
            case "abril":
                cmbMonths.setSelectedIndex(3);
                break;
            case "mayo":
                cmbMonths.setSelectedIndex(4);
                break;
            case "junio":
                cmbMonths.setSelectedIndex(5);
                break;
            case "julio":
                cmbMonths.setSelectedIndex(6);
                break;
            case "agosto":
                cmbMonths.setSelectedIndex(7);
                break;
            case "septiembre":
                cmbMonths.setSelectedIndex(8);
                break;
            case "octubre":
                cmbMonths.setSelectedIndex(9);
                break;
            case "noviembre":
                cmbMonths.setSelectedIndex(10);
                break;
            case "diciembre":
                cmbMonths.setSelectedIndex(11);
                break;
        }
    }

    public void receiveIdProduct(int id) {
        int month = cmbMonths.getSelectedIndex() + 1;
        System.out.println("Month selected: " + cmbMonths.getSelectedIndex() + 1);
        int year = cmbYear.getSelectedIndex() + 2000;
        System.out.println("Year selected: " + cmbYear.getSelectedIndex());
        product.setIdProduct(id);
        System.out.println("New Id Product: " + product.getIdProduct());
        showListSales(month, year, product);
    }

    public void showListSales(int month, int year, E_product p) {
        String titulos[] = {"IdDetalle", "Vendido", "Total", "Fecha y Hora", "IdVenta", "IdProducto"};
        DefaultTableModel df = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ArrayList<E_saleDetail> list = business.listProductSalesMonth(month, year, p);

        Iterator i = list.iterator();
        String Filas[] = new String[6];

        while (i.hasNext()) {
            E_saleDetail sd;
            sd = (E_saleDetail) i.next();
            int id = sd.getIdSaleDetail();
            int soldUnits = sd.getSoldUnits();
            double subtotal = sd.getSubtotal();
            String date = sd.getDateSaleDetail().toString();

            E_sale sale = sd.getIdSale();
            int idSale = sale.getIdSale();

            E_product product = sd.getIdProduct();
            int idProduct = product.getIdProduct();

            Filas[0] = String.valueOf(id);
            Filas[1] = String.valueOf(soldUnits);
            Filas[2] = String.valueOf("$ " + subtotal);
            Filas[3] = String.valueOf(date);
            Filas[4] = String.valueOf(idSale);
            Filas[5] = String.valueOf(idProduct);

            df.addRow(Filas);
        }

        tbProductSales.setModel(df);

        tbProductSales.getColumnModel().getColumn(0).setMaxWidth(0);
        tbProductSales.getColumnModel().getColumn(0).setMinWidth(0);
        tbProductSales.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tbProductSales.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        tbProductSales.getColumnModel().getColumn(1).setMaxWidth(100);
        tbProductSales.getColumnModel().getColumn(1).setMinWidth(100);
        tbProductSales.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(100);
        tbProductSales.getTableHeader().getColumnModel().getColumn(1).setMinWidth(100);

        tbProductSales.getColumnModel().getColumn(2).setMaxWidth(110);
        tbProductSales.getColumnModel().getColumn(2).setMinWidth(110);
        tbProductSales.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(110);
        tbProductSales.getTableHeader().getColumnModel().getColumn(2).setMinWidth(110);

        tbProductSales.getColumnModel().getColumn(4).setMaxWidth(0);
        tbProductSales.getColumnModel().getColumn(4).setMinWidth(0);
        tbProductSales.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        tbProductSales.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);

        tbProductSales.getColumnModel().getColumn(5).setMaxWidth(0);
        tbProductSales.getColumnModel().getColumn(5).setMinWidth(0);
        tbProductSales.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        tbProductSales.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
    }
    
    void deleteRows() {
        DefaultTableModel df = (DefaultTableModel) tbProductSales.getModel();

        while (tbProductSales.getRowCount() > 0) {
            df.removeRow(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbProductSales = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cmbMonths = new javax.swing.JComboBox<>();
        cmbYear = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        jLabel1.setFont(new java.awt.Font("MADE TOMMY", 1, 36)); // NOI18N
        jLabel1.setText("Historial de Ventas y Compras de Producto");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("MADE TOMMY", 1, 18)); // NOI18N
        jLabel2.setText("Compras del Producto");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        tbProductSales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbProductSales);

        jLabel3.setFont(new java.awt.Font("MADE TOMMY", 1, 18)); // NOI18N
        jLabel3.setText("Ventas del Producto");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 204, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        cmbMonths.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbMonths.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMonthsActionPerformed(evt);
            }
        });

        cmbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbYearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbMonths, 0, 269, Short.MAX_VALUE)
                    .addComponent(cmbYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbMonths, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbMonthsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMonthsActionPerformed
        deleteRows();
        
        int month = cmbMonths.getSelectedIndex() + 1;
        int year = cmbYear.getSelectedIndex() + 2000;
        System.out.println("product id: " + product.getIdProduct());
        
        showListSales(month, year, product);
    }//GEN-LAST:event_cmbMonthsActionPerformed

    private void cmbYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbYearActionPerformed
        deleteRows();
        
        int month = cmbMonths.getSelectedIndex() + 1;
        int year = cmbYear.getSelectedIndex() + 2000;
        System.out.println("product id: " + product.getIdProduct());
        
        showListSales(month, year, product);
    }//GEN-LAST:event_cmbYearActionPerformed

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
            java.util.logging.Logger.getLogger(V_SalesAndPurchases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(V_SalesAndPurchases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(V_SalesAndPurchases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(V_SalesAndPurchases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                V_SalesAndPurchases dialog = new V_SalesAndPurchases(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cmbMonths;
    private javax.swing.JComboBox<String> cmbYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tbProductSales;
    // End of variables declaration//GEN-END:variables
}
