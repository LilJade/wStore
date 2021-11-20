package views;

import business.B_productPurchaseHistory;
import business.B_saleDetail;
import entities.E_product;
import entities.E_productPurchaseHistory;
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

    B_saleDetail businessSD = new B_saleDetail();
    E_saleDetail saleDetail = new E_saleDetail();

    B_productPurchaseHistory businessPPH = new B_productPurchaseHistory();
    E_productPurchaseHistory pph = new E_productPurchaseHistory();

    E_product product = new E_product();

    // Creates new form V_SalesAndPurchases
    public V_SalesAndPurchases(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);

        monthNow();
        loadYears();
        optionsByDefault();
    }

    public void optionsByDefault() {
        btnMonth.setEnabled(false);
        btnYear.setEnabled(true);

        cmbMonths.setEnabled(true);
        cmbYear.setEnabled(true);

        cmbYearOnly.setEnabled(false);
    }

    public void loadYears() {
        cmbYear.removeAllItems();
        cmbYearOnly.removeAllItems();
        for (int i = 2000; i <= 2100; i++) {
            String year = String.valueOf(i);
            cmbYear.addItem(year);
            cmbYearOnly.addItem(year);
        }

        int year = LocalDate.now().getYear();

        int index = year - 2000;

        cmbYear.setSelectedIndex(index);
        cmbYearOnly.setSelectedIndex(index);
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
        int year = cmbYear.getSelectedIndex() + 2000;
        product.setIdProduct(id);
        showListSalesMonth(month, year, product);
        showListPurchasesMonth(month, year, product);
    }

    public void showListSalesMonth(int month, int year, E_product p) {
        String titulos[] = {"IdDetalle", "Vendido", "Total", "Fecha y Hora", "IdVenta", "IdProducto"};
        DefaultTableModel df = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ArrayList<E_saleDetail> list = businessSD.listProductSalesMonth(month, year, p);

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
    
    public void showListSalesYear(int year, E_product p) {
        String titulos[] = {"IdDetalle", "Vendido", "Total", "Fecha y Hora", "IdVenta", "IdProducto"};
        DefaultTableModel df = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ArrayList<E_saleDetail> list = businessSD.listProductSalesYear(year, p);

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

    public void showListPurchasesMonth(int month, int year, E_product p) {
        String titulos[] = {"IdCompra", "Viejo Stock", "Nuevo Stock", "Total", "Fecha y Hora", "IdProducto"};
        DefaultTableModel df = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ArrayList<E_productPurchaseHistory> list = businessPPH.listProductPurchasesMonth(month, year, p);

        Iterator i = list.iterator();
        String Filas[] = new String[6];

        while (i.hasNext()) {
            E_productPurchaseHistory pph;
            pph = (E_productPurchaseHistory) i.next();
            int id = pph.getIdProductPH();
            int oldStock = pph.getOld_Stock();
            int newStock = pph.getNew_Stock();
            double price = pph.getPrice();
            String date = pph.getDatePurchase().toString();

            E_product product = pph.getIdProduct();
            int idProduct = product.getIdProduct();

            Filas[0] = String.valueOf(id);
            Filas[1] = String.valueOf(oldStock);
            Filas[2] = String.valueOf(newStock);
            Filas[3] = String.valueOf("$ " + price);
            Filas[4] = String.valueOf(date);
            Filas[5] = String.valueOf(idProduct);

            df.addRow(Filas);
        }

        tbPurchasesProd.setModel(df);

        tbPurchasesProd.getColumnModel().getColumn(0).setMaxWidth(0);
        tbPurchasesProd.getColumnModel().getColumn(0).setMinWidth(0);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        tbPurchasesProd.getColumnModel().getColumn(1).setMaxWidth(70);
        tbPurchasesProd.getColumnModel().getColumn(1).setMinWidth(70);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(70);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(1).setMinWidth(70);

        tbPurchasesProd.getColumnModel().getColumn(2).setMaxWidth(85);
        tbPurchasesProd.getColumnModel().getColumn(2).setMinWidth(85);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(85);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(2).setMinWidth(85);

        tbPurchasesProd.getColumnModel().getColumn(3).setMaxWidth(90);
        tbPurchasesProd.getColumnModel().getColumn(3).setMinWidth(90);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(90);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(3).setMinWidth(90);

        /*        tbProductSales.getColumnModel().getColumn(4).setMaxWidth(0);
        tbProductSales.getColumnModel().getColumn(4).setMinWidth(0);
        tbProductSales.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        tbProductSales.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);*/
        tbPurchasesProd.getColumnModel().getColumn(5).setMaxWidth(0);
        tbPurchasesProd.getColumnModel().getColumn(5).setMinWidth(0);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
    }
    
    public void showListPurchasesYear(int year, E_product p) {
        String titulos[] = {"IdCompra", "Viejo Stock", "Nuevo Stock", "Total", "Fecha y Hora", "IdProducto"};
        DefaultTableModel df = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ArrayList<E_productPurchaseHistory> list = businessPPH.listProductPurchasesYear(year, p);

        Iterator i = list.iterator();
        String Filas[] = new String[6];

        while (i.hasNext()) {
            E_productPurchaseHistory pph;
            pph = (E_productPurchaseHistory) i.next();
            int id = pph.getIdProductPH();
            int oldStock = pph.getOld_Stock();
            int newStock = pph.getNew_Stock();
            double price = pph.getPrice();
            String date = pph.getDatePurchase().toString();

            E_product product = pph.getIdProduct();
            int idProduct = product.getIdProduct();

            Filas[0] = String.valueOf(id);
            Filas[1] = String.valueOf(oldStock);
            Filas[2] = String.valueOf(newStock);
            Filas[3] = String.valueOf("$ " + price);
            Filas[4] = String.valueOf(date);
            Filas[5] = String.valueOf(idProduct);

            df.addRow(Filas);
        }

        tbPurchasesProd.setModel(df);

        tbPurchasesProd.getColumnModel().getColumn(0).setMaxWidth(0);
        tbPurchasesProd.getColumnModel().getColumn(0).setMinWidth(0);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        tbPurchasesProd.getColumnModel().getColumn(1).setMaxWidth(70);
        tbPurchasesProd.getColumnModel().getColumn(1).setMinWidth(70);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(70);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(1).setMinWidth(70);

        tbPurchasesProd.getColumnModel().getColumn(2).setMaxWidth(85);
        tbPurchasesProd.getColumnModel().getColumn(2).setMinWidth(85);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(85);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(2).setMinWidth(85);

        tbPurchasesProd.getColumnModel().getColumn(3).setMaxWidth(90);
        tbPurchasesProd.getColumnModel().getColumn(3).setMinWidth(90);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(90);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(3).setMinWidth(90);

        /*        tbProductSales.getColumnModel().getColumn(4).setMaxWidth(0);
        tbProductSales.getColumnModel().getColumn(4).setMinWidth(0);
        tbProductSales.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        tbProductSales.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);*/
        tbPurchasesProd.getColumnModel().getColumn(5).setMaxWidth(0);
        tbPurchasesProd.getColumnModel().getColumn(5).setMinWidth(0);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        tbPurchasesProd.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
    }

    void deleteRowsSales() {
        DefaultTableModel df = (DefaultTableModel) tbProductSales.getModel();

        while (tbProductSales.getRowCount() > 0) {
            df.removeRow(0);
        }
    }

    void deleteRowsPurchases() {
        DefaultTableModel df = (DefaultTableModel) tbPurchasesProd.getModel();

        while (tbPurchasesProd.getRowCount() > 0) {
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
        tbPurchasesProd = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbProductSales = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cmbMonths = new javax.swing.JComboBox<>();
        cmbYear = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbYearOnly = new javax.swing.JComboBox<>();
        btnMonth = new javax.swing.JButton();
        btnYear = new javax.swing.JButton();

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
            .addComponent(jLabel1)
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        tbPurchasesProd.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbPurchasesProd);

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

        jLabel4.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel4.setText("Historial Mensual");

        jLabel5.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel5.setText("Historial Anual");

        jLabel6.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel6.setText("Mes: ");

        jLabel7.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel7.setText("Año: ");

        jLabel8.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel8.setText("Año: ");

        cmbYearOnly.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbYearOnly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbYearOnlyActionPerformed(evt);
            }
        });

        btnMonth.setBackground(new java.awt.Color(0, 0, 204));
        btnMonth.setFont(new java.awt.Font("MADE TOMMY", 1, 18)); // NOI18N
        btnMonth.setForeground(new java.awt.Color(255, 255, 255));
        btnMonth.setText("Ver Historial Mensual");
        btnMonth.setBorder(null);
        btnMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonthActionPerformed(evt);
            }
        });

        btnYear.setBackground(new java.awt.Color(0, 0, 204));
        btnYear.setFont(new java.awt.Font("MADE TOMMY", 1, 18)); // NOI18N
        btnYear.setForeground(new java.awt.Color(255, 255, 255));
        btnYear.setText("Ver Historial Anual");
        btnYear.setBorder(null);
        btnYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbMonths, 0, 393, Short.MAX_VALUE)
                            .addComponent(cmbYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cmbYearOnly, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(326, 326, 326))
                            .addComponent(btnYear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMonth, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(btnYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(cmbYearOnly, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbMonths, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
        deleteRowsSales();
        deleteRowsPurchases();

        int month = cmbMonths.getSelectedIndex() + 1;
        int year = cmbYear.getSelectedIndex() + 2000;

        showListSalesMonth(month, year, product);
        showListPurchasesMonth(month, year, product);
    }//GEN-LAST:event_cmbMonthsActionPerformed

    private void cmbYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbYearActionPerformed
        deleteRowsSales();
        deleteRowsPurchases();

        int month = cmbMonths.getSelectedIndex() + 1;
        int year = cmbYear.getSelectedIndex() + 2000;

        showListSalesMonth(month, year, product);
        showListPurchasesMonth(month, year, product);
    }//GEN-LAST:event_cmbYearActionPerformed

    private void cmbYearOnlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbYearOnlyActionPerformed
        deleteRowsSales();
        deleteRowsPurchases();
        
        int year = cmbYearOnly.getSelectedIndex() + 2000;
        showListSalesYear(year, product);
        showListPurchasesYear(year, product);
    }//GEN-LAST:event_cmbYearOnlyActionPerformed

    private void btnYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYearActionPerformed
        btnYear.setEnabled(false);
        cmbYearOnly.setEnabled(true);
        
        btnMonth.setEnabled(true);
        cmbMonths.setEnabled(false);
        cmbYear.setEnabled(false);
        
        deleteRowsSales();
        deleteRowsPurchases();
        
        int year = cmbYearOnly.getSelectedIndex() + 2000;
        showListSalesYear(year, product);
        showListPurchasesYear(year, product);
    }//GEN-LAST:event_btnYearActionPerformed

    private void btnMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonthActionPerformed
        optionsByDefault();
        System.out.println("IdProd: " + product.getIdProduct());
        receiveIdProduct(product.getIdProduct());
    }//GEN-LAST:event_btnMonthActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(V_SalesAndPurchases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(V_SalesAndPurchases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(V_SalesAndPurchases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(V_SalesAndPurchases.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                V_SalesAndPurchases dialog = new V_SalesAndPurchases(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMonth;
    private javax.swing.JButton btnYear;
    private javax.swing.JComboBox<String> cmbMonths;
    private javax.swing.JComboBox<String> cmbYear;
    private javax.swing.JComboBox<String> cmbYearOnly;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbProductSales;
    private javax.swing.JTable tbPurchasesProd;
    // End of variables declaration//GEN-END:variables
}
