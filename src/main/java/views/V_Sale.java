package views;

import business.B_clients;
import business.B_sale;
import business.B_saleDetail;
import entities.E_clients;
import entities.E_product;
import business.B_products;
import entities.E_sale;
import entities.E_saleDetail;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author LilJade
 */
public class V_Sale extends javax.swing.JFrame {

    E_product product = new E_product();
    B_products businessProducts = new B_products();

    E_saleDetail saleDetail = new E_saleDetail();
    B_saleDetail businessSaleDetail = new B_saleDetail();

    E_sale sale = new E_sale();
    B_sale businessSale = new B_sale();

    E_clients client = new E_clients();
    B_clients businessClient = new B_clients();

    DefaultTableModel model = new DefaultTableModel();

    int cantidad;
    double precio;
    double totalPagar;
    int totalArticles;

    // Creates new form V_Sale
    public V_Sale() {
        initComponents();
        this.setLocationRelativeTo(null);

        blockButtons();
        cleanFields();
        blockFields();
        lblIdSale.setText("");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        lblDate.setText(dtf.format(LocalDateTime.now()));

    }

    //Recibe y carga la informacion del producto buscado
    public static void reciveProductData(E_product product) {
        if (product != null) {
            lblSelectedProd.setText(product.getProductName());
            lblPrice.setText(String.valueOf(product.getSalePrice()));
            lblStock.setText(String.valueOf(product.getStock()));
            lblIdProd.setText(String.valueOf(product.getIdProduct()));

            btnAddProduct.setEnabled(true);
            btnCancelAddProd.setEnabled(true);
        }
    }

    void cleanFields() {
        lblIdSale.setText("");
        lblSelectedProd.setText("");
        txtCodeProd.setText("");
        lblPrice.setText("");
        lblStock.setText("");
        lblIdProd.setText("");
        lblArtSale.setText("");
        txtTotalNeto.setText("");

        txtSearchClient.setText("");
        btnSelectClient.setText("Seleccionar");
    }

    void blockFields() {
        txtCodeProd.setEnabled(false);
        tbDetailSale.setEnabled(false);
        txtTotalNeto.setEnabled(false);

        txtSearchClient.setEnabled(false);
        tbClient.setEnabled(false);

        rbGenericClient.setEnabled(false);
        rbRegisterClient.setEnabled(false);
        rbGenericClient.setSelected(true);
    }

    void unblockFields() {
        txtCodeProd.setEnabled(true);
        tbDetailSale.setEnabled(true);
        txtTotalNeto.setEnabled(true);

        rbGenericClient.setEnabled(true);
        rbRegisterClient.setEnabled(true);
    }

    void blockButtons() {
        btnSearchProd.setEnabled(false);
        btnAddProduct.setEnabled(false);
        btnCancelAddProd.setEnabled(false);
        btnDeleteProd.setEnabled(false);

        btnSelectClient.setEnabled(false);
        btnRefreshClients.setEnabled(false);
        btnAddClient.setEnabled(false);

        btnCompleteSale.setEnabled(false);
        btnPrint.setEnabled(false);
        btnCancelSale.setEnabled(false);
    }

    void unblockButtons() {
        btnSearchProd.setEnabled(true);

        btnCompleteSale.setEnabled(true);
        btnPrint.setEnabled(true);
        btnCancelSale.setEnabled(true);
    }

    void lblSearchProductsByDefault() {
        lblMaxStock.setText("");
        lblSelectedProd.setText("");
        lblPrice.setText("");
        lblStock.setText("");
        lblIdProd.setText("");

        btnCancelAddProd.setEnabled(false);
        btnAddProduct.setEnabled(false);
    }

    void calcularTotales() {
        totalPagar = 0;
        totalArticles = 0;

        for (int i = 0; i < tbDetailSale.getRowCount(); i++) {
            cantidad = Integer.parseInt(tbDetailSale.getValueAt(i, 2).toString());
            precio = Double.parseDouble(tbDetailSale.getValueAt(i, 3).toString());
            double subTotal = (cantidad * precio);
            tbDetailSale.setValueAt(subTotal, i, 4);

            totalPagar = totalPagar + (cantidad * precio);

            int article = Integer.parseInt(tbDetailSale.getValueAt(i, 2).toString());
            totalArticles = totalArticles + article;
        }

        txtTotalNeto.setText("" + totalPagar);
        lblArtSale.setText("" + totalArticles);
    }

    boolean amountDetails(E_product product) {
        if (product == null) {
            for (int i = 0; i < tbDetailSale.getRowCount(); i++) {

                if (lblIdProd.getText().equals(String.valueOf(tbDetailSale.getValueAt(i, 0)))) {

                    int cantidad = Integer.parseInt(String.valueOf(tbDetailSale.getValueAt(i, 2)));
                    cantidad = cantidad + 1;

                    int ultimaCantidad = Integer.parseInt(String.valueOf(tbDetailSale.getValueAt(i, 2)));
                    System.out.println("Last amount valid: " + ultimaCantidad);
                    if (cantidad > Integer.parseInt(String.valueOf(tbDetailSale.getValueAt(i, 5)))) {
                        JOptionPane.showMessageDialog(null, "Ya no quedan mas productos para esta venta!");

                        tbDetailSale.setValueAt(ultimaCantidad, i, 2);
                        
                        return false;
                    } else {
                        tbDetailSale.setValueAt(cantidad, i, 2);

                        calcularTotales();
                        return true;
                    }
                }
            }

            return false;
        } else {
            for (int i = 0; i < tbDetailSale.getRowCount(); i++) {

                if (String.valueOf(product.getIdProduct()).equals(String.valueOf(tbDetailSale.getValueAt(i, 0)))) {
                    int cantidad = Integer.parseInt(String.valueOf(tbDetailSale.getValueAt(i, 2)));
                    cantidad = cantidad + 1;

                    int ultimaCantidad = Integer.parseInt(String.valueOf(tbDetailSale.getValueAt(i, 2)));
                    System.out.println("Last amount valid: " + ultimaCantidad);
                    if (cantidad > Integer.parseInt(String.valueOf(tbDetailSale.getValueAt(i, 5)))) {
                        JOptionPane.showMessageDialog(null, "Ya no quedan mas productos para esta venta!");

                        tbDetailSale.setValueAt(ultimaCantidad, i, 2);
                        
                        return true;
                    } else {
                        tbDetailSale.setValueAt(cantidad, i, 2);

                        calcularTotales();
                        return true;
                    }
                }
            }

            return false;
        }
    }

    void deleteRows(JTable table) {
        DefaultTableModel df = (DefaultTableModel) table.getModel();

        while (table.getRowCount() > 0) {
            df.removeRow(0);
        }
    }

    public void showListClients() {
        String titles[] = {"Id", "Nombres", "Apellidos", "Telefono"};

        DefaultTableModel df = new DefaultTableModel(null, titles);

        ArrayList<E_clients> list = businessClient.B_listClients();
        Iterator i = list.iterator();
        String rows[] = new String[4];

        while (i.hasNext()) {
            E_clients client;
            client = (E_clients) i.next();

            rows[0] = String.valueOf(client.getIdClient());
            rows[1] = client.getFirstName();
            rows[2] = client.getLastName();
            rows[3] = client.getNumberphone();

            df.addRow(rows);
        }

        tbClient.setModel(df);

        tbClient.getColumnModel().getColumn(0).setMaxWidth(0);
        tbClient.getColumnModel().getColumn(0).setMinWidth(0);
        tbClient.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tbClient.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        tbClient.getColumnModel().getColumn(3).setMaxWidth(0);
        tbClient.getColumnModel().getColumn(3).setMinWidth(0);
        tbClient.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
        tbClient.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
    }

    void filtro(String consulta, JTable jtableBuscar) {
        model = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        jtableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngTypeClient = new javax.swing.ButtonGroup();
        pnlConfigSale = new javax.swing.JPanel();
        lblIdSale = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        pnlCliente = new javax.swing.JPanel();
        txtSearchClient = new javax.swing.JTextField();
        btnAddClient = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbClient = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        btnSelectClient = new javax.swing.JButton();
        btnRefreshClients = new javax.swing.JButton();
        btnNewSale = new javax.swing.JButton();
        btnCompleteSale = new javax.swing.JButton();
        btnCancelSale = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        rbGenericClient = new javax.swing.JRadioButton();
        rbRegisterClient = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        pnlProdsAdd = new javax.swing.JPanel();
        lblLogoWStore = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSearchProd = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCodeProd = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lblIdProd = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblSelectedProd = new javax.swing.JLabel();
        btnAddProduct = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnCancelAddProd = new javax.swing.JButton();
        lblStock = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        pnlOptions = new javax.swing.JPanel();
        btnUsers1 = new javax.swing.JButton();
        btnUsers = new javax.swing.JButton();
        btnClients = new javax.swing.JButton();
        btnProduct = new javax.swing.JButton();
        btnCategory = new javax.swing.JButton();
        btnUsers2 = new javax.swing.JButton();
        pnlTableSale = new javax.swing.JPanel();
        txtTotalNeto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetailSale = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        btnDeleteProd = new javax.swing.JButton();
        lblArtSale = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblMaxStock = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlConfigSale.setBackground(new java.awt.Color(204, 204, 255));
        pnlConfigSale.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblIdSale.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        lblIdSale.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIdSale.setText("11111001111");
        lblIdSale.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel12.setText("ID:");

        jLabel14.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel14.setText("Fecha:");

        lblDate.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDate.setText("31 / 12 / 9999");
        lblDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pnlCliente.setBackground(new java.awt.Color(255, 255, 255));
        pnlCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtSearchClient.setFont(new java.awt.Font("MADE TOMMY", 0, 14)); // NOI18N
        txtSearchClient.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchClientKeyReleased(evt);
            }
        });

        btnAddClient.setFont(new java.awt.Font("MADE TOMMY", 0, 18)); // NOI18N
        btnAddClient.setText("Registrar nuevo cliente");
        btnAddClient.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAddClient.setContentAreaFilled(false);
        btnAddClient.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAddClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddClientActionPerformed(evt);
            }
        });

        tbClient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nombre"
            }
        ));
        tbClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbClientMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbClient);
        if (tbClient.getColumnModel().getColumnCount() > 0) {
            tbClient.getColumnModel().getColumn(0).setPreferredWidth(25);
            tbClient.getColumnModel().getColumn(1).setPreferredWidth(195);
        }

        jLabel16.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel16.setText("Buscar Cliente:");

        btnSelectClient.setFont(new java.awt.Font("MADE TOMMY", 0, 18)); // NOI18N
        btnSelectClient.setText("Seleccionar");
        btnSelectClient.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSelectClient.setContentAreaFilled(false);
        btnSelectClient.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSelectClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectClientActionPerformed(evt);
            }
        });

        btnRefreshClients.setFont(new java.awt.Font("MADE TOMMY", 0, 18)); // NOI18N
        btnRefreshClients.setText("Recargar Clientes");
        btnRefreshClients.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnRefreshClients.setContentAreaFilled(false);
        btnRefreshClients.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRefreshClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshClientsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlClienteLayout = new javax.swing.GroupLayout(pnlCliente);
        pnlCliente.setLayout(pnlClienteLayout);
        pnlClienteLayout.setHorizontalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSelectClient, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearchClient)
                    .addComponent(btnAddClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlClienteLayout.createSequentialGroup()
                        .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnRefreshClients, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlClienteLayout.setVerticalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSelectClient)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefreshClients)
                .addGap(20, 20, 20)
                .addComponent(btnAddClient)
                .addContainerGap())
        );

        btnNewSale.setBackground(new java.awt.Color(0, 153, 51));
        btnNewSale.setFont(new java.awt.Font("MADE TOMMY", 1, 18)); // NOI18N
        btnNewSale.setForeground(new java.awt.Color(255, 255, 255));
        btnNewSale.setText("Nueva Venta");
        btnNewSale.setBorder(null);
        btnNewSale.setBorderPainted(false);
        btnNewSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewSaleActionPerformed(evt);
            }
        });

        btnCompleteSale.setBackground(new java.awt.Color(0, 153, 51));
        btnCompleteSale.setFont(new java.awt.Font("MADE TOMMY", 1, 18)); // NOI18N
        btnCompleteSale.setForeground(new java.awt.Color(255, 255, 255));
        btnCompleteSale.setText("Realizar Venta");
        btnCompleteSale.setBorder(null);
        btnCompleteSale.setBorderPainted(false);
        btnCompleteSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompleteSaleActionPerformed(evt);
            }
        });

        btnCancelSale.setBackground(new java.awt.Color(204, 0, 51));
        btnCancelSale.setFont(new java.awt.Font("MADE TOMMY", 1, 18)); // NOI18N
        btnCancelSale.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelSale.setText("Cancelar Venta");
        btnCancelSale.setBorder(null);
        btnCancelSale.setBorderPainted(false);
        btnCancelSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelSaleActionPerformed(evt);
            }
        });

        btnPrint.setBackground(new java.awt.Color(51, 0, 255));
        btnPrint.setFont(new java.awt.Font("MADE TOMMY", 1, 18)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("Imprimir Factura");
        btnPrint.setBorder(null);
        btnPrint.setBorderPainted(false);
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btngTypeClient.add(rbGenericClient);
        rbGenericClient.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        rbGenericClient.setText("Genérico");
        rbGenericClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbGenericClientMouseClicked(evt);
            }
        });

        btngTypeClient.add(rbRegisterClient);
        rbRegisterClient.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        rbRegisterClient.setText("Específico");
        rbRegisterClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbRegisterClientMouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel17.setText("Cliente:");

        javax.swing.GroupLayout pnlConfigSaleLayout = new javax.swing.GroupLayout(pnlConfigSale);
        pnlConfigSale.setLayout(pnlConfigSaleLayout);
        pnlConfigSaleLayout.setHorizontalGroup(
            pnlConfigSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConfigSaleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlConfigSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlConfigSaleLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(lblIdSale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlConfigSaleLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCompleteSale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelSale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNewSale, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlConfigSaleLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlConfigSaleLayout.createSequentialGroup()
                        .addComponent(rbGenericClient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbRegisterClient)))
                .addContainerGap())
        );
        pnlConfigSaleLayout.setVerticalGroup(
            pnlConfigSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConfigSaleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlConfigSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdSale, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlConfigSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlConfigSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbGenericClient)
                    .addComponent(rbRegisterClient))
                .addGap(61, 61, 61)
                .addComponent(btnCompleteSale, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelSale, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNewSale, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlProdsAdd.setBackground(new java.awt.Color(255, 204, 102));
        pnlProdsAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLogoWStore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogoWStore.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel2.setText("Buscar producto: ");

        btnSearchProd.setBackground(new java.awt.Color(51, 51, 255));
        btnSearchProd.setFont(new java.awt.Font("MADE TOMMY", 1, 18)); // NOI18N
        btnSearchProd.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchProd.setText("Buscar Producto");
        btnSearchProd.setBorder(null);
        btnSearchProd.setBorderPainted(false);
        btnSearchProd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSearchProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchProdActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel3.setText("Producto Seleccionado:");

        txtCodeProd.setFont(new java.awt.Font("MADE TOMMY", 1, 18)); // NOI18N
        txtCodeProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodeProdActionPerformed(evt);
            }
        });
        txtCodeProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodeProdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodeProdKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel4.setText("Código del producto");

        lblIdProd.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        lblIdProd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIdProd.setText("11111001111");
        lblIdProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel6.setText("Id del producto:");

        lblSelectedProd.setFont(new java.awt.Font("MADE TOMMY", 1, 18)); // NOI18N
        lblSelectedProd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSelectedProd.setText(" ");
        lblSelectedProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnAddProduct.setBackground(new java.awt.Color(0, 153, 51));
        btnAddProduct.setFont(new java.awt.Font("MADE TOMMY", 1, 18)); // NOI18N
        btnAddProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnAddProduct.setText("Agregar Producto");
        btnAddProduct.setBorder(null);
        btnAddProduct.setBorderPainted(false);
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel10.setText("Stock:");

        btnCancelAddProd.setBackground(new java.awt.Color(204, 0, 51));
        btnCancelAddProd.setFont(new java.awt.Font("MADE TOMMY", 1, 18)); // NOI18N
        btnCancelAddProd.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelAddProd.setText("Cancelar");
        btnCancelAddProd.setBorder(null);
        btnCancelAddProd.setBorderPainted(false);
        btnCancelAddProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelAddProdActionPerformed(evt);
            }
        });

        lblStock.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        lblStock.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblStock.setText("11111001111");
        lblStock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblPrice.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        lblPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPrice.setText("11111001111");
        lblPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel11.setText("Precio: $");

        javax.swing.GroupLayout pnlProdsAddLayout = new javax.swing.GroupLayout(pnlProdsAdd);
        pnlProdsAdd.setLayout(pnlProdsAddLayout);
        pnlProdsAddLayout.setHorizontalGroup(
            pnlProdsAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProdsAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogoWStore, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(pnlProdsAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlProdsAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearchProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProdsAddLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblSelectedProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtCodeProd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProdsAddLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlProdsAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlProdsAddLayout.createSequentialGroup()
                                .addComponent(btnCancelAddProd, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(89, 89, 89)
                                .addComponent(btnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlProdsAddLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblStock, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );
        pnlProdsAddLayout.setVerticalGroup(
            pnlProdsAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProdsAddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProdsAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProdsAddLayout.createSequentialGroup()
                        .addGroup(pnlProdsAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodeProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(pnlProdsAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearchProd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlProdsAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSelectedProd, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(pnlProdsAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10)
                            .addComponent(lblStock, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlProdsAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAddProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(btnCancelAddProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lblLogoWStore, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pnlOptions.setBackground(new java.awt.Color(255, 255, 255));
        pnlOptions.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlOptions.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnUsers1.setBackground(new java.awt.Color(204, 0, 0));
        btnUsers1.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        btnUsers1.setForeground(new java.awt.Color(255, 255, 255));
        btnUsers1.setText("Cerrar Sesión");
        btnUsers1.setBorder(null);
        btnUsers1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnUsers1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsers1ActionPerformed(evt);
            }
        });
        pnlOptions.add(btnUsers1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 160, 30));

        btnUsers.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        btnUsers.setText("Administrar Usuarios");
        btnUsers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnUsers.setContentAreaFilled(false);
        btnUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsersActionPerformed(evt);
            }
        });
        pnlOptions.add(btnUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 160, 20));

        btnClients.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        btnClients.setText("Administrar Clientes");
        btnClients.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnClients.setContentAreaFilled(false);
        btnClients.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientsActionPerformed(evt);
            }
        });
        pnlOptions.add(btnClients, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 160, 20));

        btnProduct.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        btnProduct.setText("Inventario");
        btnProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnProduct.setContentAreaFilled(false);
        btnProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductActionPerformed(evt);
            }
        });
        pnlOptions.add(btnProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 110, 20));

        btnCategory.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        btnCategory.setText("Config Categorías");
        btnCategory.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCategory.setContentAreaFilled(false);
        btnCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoryActionPerformed(evt);
            }
        });
        pnlOptions.add(btnCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 20));

        btnUsers2.setBackground(new java.awt.Color(204, 0, 0));
        btnUsers2.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        btnUsers2.setForeground(new java.awt.Color(255, 255, 255));
        btnUsers2.setText("Cerrar Sesión y Salir");
        btnUsers2.setBorder(null);
        btnUsers2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnUsers2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsers2ActionPerformed(evt);
            }
        });
        pnlOptions.add(btnUsers2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, 160, 30));

        pnlTableSale.setBackground(new java.awt.Color(153, 204, 255));
        pnlTableSale.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTotalNeto.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        txtTotalNeto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalNeto.setText("11111001111");

        jLabel1.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel1.setText("Total: $");

        tbDetailSale.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdProducto", "NombreProducto", "Cantidad", "Precio", "SubTotal", "Stock"
            }
        ));
        tbDetailSale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDetailSaleMouseClicked(evt);
            }
        });
        tbDetailSale.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbDetailSaleKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbDetailSale);

        jLabel8.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel8.setText("Artículos Vendidos:");

        btnDeleteProd.setBackground(new java.awt.Color(204, 0, 51));
        btnDeleteProd.setFont(new java.awt.Font("MADE TOMMY", 1, 18)); // NOI18N
        btnDeleteProd.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteProd.setText("Eliminar Producto");
        btnDeleteProd.setBorder(null);
        btnDeleteProd.setBorderPainted(false);
        btnDeleteProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProdActionPerformed(evt);
            }
        });

        lblArtSale.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        lblArtSale.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblArtSale.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        jLabel9.setText("Stock: ");

        lblMaxStock.setFont(new java.awt.Font("MADE TOMMY", 1, 14)); // NOI18N
        lblMaxStock.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaxStock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlTableSaleLayout = new javax.swing.GroupLayout(pnlTableSale);
        pnlTableSale.setLayout(pnlTableSaleLayout);
        pnlTableSaleLayout.setHorizontalGroup(
            pnlTableSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(pnlTableSaleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDeleteProd, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMaxStock, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblArtSale, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalNeto)
                .addContainerGap())
        );
        pnlTableSaleLayout.setVerticalGroup(
            pnlTableSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableSaleLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTableSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTableSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(lblArtSale, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(lblMaxStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addComponent(btnDeleteProd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTableSale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlProdsAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlConfigSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(pnlOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlProdsAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlTableSale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlConfigSale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
int selectedRow;
    private void btnDeleteProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProdActionPerformed

        ((DefaultTableModel) tbDetailSale.getModel()).removeRow(selectedRow);
    }//GEN-LAST:event_btnDeleteProdActionPerformed

    private void btnNewSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewSaleActionPerformed
        if (businessSale.B_confirmPreviousSale() != null) {
            unblockFields();
            unblockButtons();
            btnNewSale.setEnabled(false);
            txtCodeProd.requestFocus();

            sale = businessSale.B_lastIdSale();
            System.out.println("Sale Id: " + sale.getIdSale());
            lblIdSale.setText(String.valueOf(sale.getIdSale()));
        } else {
            unblockFields();
            unblockButtons();
            btnNewSale.setEnabled(false);
            txtCodeProd.requestFocus();

            businessSale.B_insertDirectSale();
            sale = businessSale.B_lastIdSale();

            System.out.println("Sale Id: " + sale.getIdSale());
            lblIdSale.setText(String.valueOf(sale.getIdSale()));
        }
    }//GEN-LAST:event_btnNewSaleActionPerformed

    private void btnCompleteSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompleteSaleActionPerformed
        if (tbDetailSale.getRowCount() > 0) {
            calcularTotales();
            if (completeSale() == true) {
                saveSaleDetail();

                deleteRows(tbDetailSale);
                lblSearchProductsByDefault();
                cleanFields();
                blockFields();
                blockButtons();
                btnNewSale.setEnabled(true);

                deleteRows(tbClient);
                txtSearchClient.setText("");
                txtSearchClient.setEnabled(false);
                btnSelectClient.setEnabled(false);
                btnAddClient.setEnabled(false);
                tbClient.setEnabled(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Agregue productos a la venta!");
        }
    }//GEN-LAST:event_btnCompleteSaleActionPerformed

    boolean completeSale() {
        if (rbGenericClient.isSelected()) {
            businessClient.B_insertGenericClient();

            client = businessClient.B_lastIdClient();
            System.out.println("Id client: " + client.getIdClient());
            sale.setIdClient(client);
        }

        if (rbRegisterClient.isSelected()) {
            if (btnSelectClient.getText().equals("Seleccionado")) {
                sale.setIdClient(clientSelected);
            } else {
                JOptionPane.showMessageDialog(null, "Debe especificar el cliente!");
                return false;
            }
        }

        sale.setIdSale(Integer.parseInt(lblIdSale.getText()));
        sale.setTotalNeto(totalPagar);

        businessSale.B_completeSale(sale);

        return true;
    }

    void saveSaleDetail() {
        int idSale = Integer.parseInt(lblIdSale.getText());
        sale.setIdSale(idSale);
        for (int i = 0; i < tbDetailSale.getRowCount(); i++) {
            product.setIdProduct(Integer.parseInt(tbDetailSale.getValueAt(i, 0).toString()));
            int canti = Integer.parseInt(tbDetailSale.getValueAt(i, 2).toString());
            double subtotal = Double.parseDouble(tbDetailSale.getValueAt(i, 4).toString());
            saleDetail = new E_saleDetail();

            saleDetail.setSoldUnits(canti);
            saleDetail.setSubtotal(subtotal);
            saleDetail.setIdSale(sale);
            saleDetail.setIdProduct(product);

            businessSaleDetail.B_insertSaleDetail(saleDetail);
        }

    }

    private void btnCancelSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelSaleActionPerformed
        deleteRows(tbDetailSale);
        deleteRows(tbClient);
        cleanFields();
        blockFields();
        blockButtons();
        btnNewSale.setEnabled(true);
    }//GEN-LAST:event_btnCancelSaleActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductActionPerformed
        V_productsCrud win = new V_productsCrud();

        win.setVisible(true);
    }//GEN-LAST:event_btnProductActionPerformed

    private void btnCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoryActionPerformed
        V_CategoryCrud win = new V_CategoryCrud(this, true);

        win.setVisible(true);
    }//GEN-LAST:event_btnCategoryActionPerformed

    private void btnAddClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddClientActionPerformed
        V_addClient win = new V_addClient(new javax.swing.JDialog(), true);
        win.crudOrSale = 0;
        win.setVisible(true);
    }//GEN-LAST:event_btnAddClientActionPerformed

    private void btnSearchProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchProdActionPerformed
        V_SearchProduct win = new V_SearchProduct(this, true);
        win.setVisible(true);
    }//GEN-LAST:event_btnSearchProdActionPerformed

    private void btnCancelAddProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelAddProdActionPerformed
        btnAddProduct.setEnabled(false);
        btnCancelAddProd.setEnabled(false);
        lblSearchProductsByDefault();
    }//GEN-LAST:event_btnCancelAddProdActionPerformed

    private void txtCodeProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodeProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodeProdActionPerformed

    private void rbGenericClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbGenericClientMouseClicked
        deleteRows(tbClient);
        txtSearchClient.setText("");
        txtSearchClient.setEnabled(false);
        btnSelectClient.setEnabled(false);
        btnAddClient.setEnabled(false);
        tbClient.setEnabled(false);
    }//GEN-LAST:event_rbGenericClientMouseClicked

    private void rbRegisterClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbRegisterClientMouseClicked
        showListClients();
        pnlCliente.setEnabled(true);
        tbClient.setEnabled(true);
        txtSearchClient.setEnabled(true);
        btnSelectClient.setText("Seleccionar");
        btnRefreshClients.setEnabled(true);
        btnAddClient.setEnabled(true);

    }//GEN-LAST:event_rbRegisterClientMouseClicked

    private void btnClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientsActionPerformed
        V_clientCrud win = new V_clientCrud(this, true);
        win.setVisible(true);
    }//GEN-LAST:event_btnClientsActionPerformed

    private void btnUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsersActionPerformed
        V_userCrud win = new V_userCrud(this, true);
        win.setVisible(true);
    }//GEN-LAST:event_btnUsersActionPerformed

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        if (amountDetails(null) == true) {

        } else {
            double total;
            model = (DefaultTableModel) tbDetailSale.getModel();
            String titulos[] = {"IdProducto", "NombreProducto", "Cantidad", "Precio", "SubTotal", "Stock"};
            DefaultTableModel df = new DefaultTableModel(null, titulos) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    if (column == 2) {
                        return true;
                    }

                    return false;
                }
            };

            model = df;
            precio = Double.parseDouble(lblPrice.getText());
            int stock = Integer.parseInt(lblStock.getText());
            total = 1 * precio;

            ArrayList lista = new ArrayList();

            if (stock > 0) {
                lista.add(Integer.parseInt(lblIdProd.getText()));
                lista.add(lblSelectedProd.getText());
                lista.add(1);
                lista.add(Double.parseDouble(lblPrice.getText()));
                lista.add(total);
                lista.add(stock);

                Object[] ob = new Object[6];

                ob[0] = lista.get(0);
                ob[1] = lista.get(1);
                ob[2] = lista.get(2);
                ob[3] = lista.get(3);
                ob[4] = lista.get(4);
                ob[5] = lista.get(5);

                model.addRow(ob);
                tbDetailSale.setModel(model);

                tbDetailSale.getColumnModel().getColumn(5).setMaxWidth(0);
                tbDetailSale.getColumnModel().getColumn(5).setMinWidth(0);
                tbDetailSale.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
                tbDetailSale.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
            } else {
                JOptionPane.showMessageDialog(this, "Stock Producto no disponible");
            }

            lblSearchProductsByDefault();
            btnAddProduct.setEnabled(false);
            btnCancelAddProd.setEnabled(false);
            calcularTotales();
        }
    }//GEN-LAST:event_btnAddProductActionPerformed
    int originalAmount;
    private void tbDetailSaleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetailSaleMouseClicked
        selectedRow = tbDetailSale.rowAtPoint(evt.getPoint());
        lblMaxStock.setText(String.valueOf(tbDetailSale.getValueAt(selectedRow, 5)));
        originalAmount = Integer.parseInt(tbDetailSale.getValueAt(selectedRow, 2).toString());
        System.out.println("Amount: " + originalAmount);
        btnDeleteProd.setEnabled(true);
    }//GEN-LAST:event_tbDetailSaleMouseClicked

    private void btnUsers1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsers1ActionPerformed
        V_loginUsers win = new V_loginUsers();
        win.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_btnUsers1ActionPerformed

    private void btnUsers2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsers2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnUsers2ActionPerformed

    private void txtCodeProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodeProdKeyReleased
        if (evt.getExtendedKeyCode() == evt.VK_ENTER) {
            if (txtCodeProd.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese un ID válido!");
            } else {
                product = new E_product();
                int id = Integer.parseInt(txtCodeProd.getText());
                product.setIdProduct(id);
                product = businessProducts.B_productSearchById(product);

                if (product == null) {
                    JOptionPane.showMessageDialog(null, "ID no válido!\nAsegúrese de escribir el ID correctamente!");
                } else {
                    if (amountDetails(product) == true) {

                    } else {
                        double total;
                        model = (DefaultTableModel) tbDetailSale.getModel();
                        String titulos[] = {"IdProducto", "NombreProducto", "Cantidad", "Precio", "SubTotal", "Stock"};
                        DefaultTableModel df = new DefaultTableModel(null, titulos) {
                            @Override
                            public boolean isCellEditable(int row, int column) {
                                if (column == 2) {
                                    return true;
                                }

                                return false;
                            }
                        };

                        model = df;
                        precio = product.getSalePrice();
                        int stock = product.getStock();
                        total = 1 * precio;

                        ArrayList lista = new ArrayList();

                        if (stock > 0) {
                            lista.add(product.getIdProduct());
                            lista.add(product.getProductName());
                            lista.add(1);
                            lista.add(product.getSalePrice());
                            lista.add(total);
                            lista.add(stock);

                            Object[] ob = new Object[6];

                            ob[0] = lista.get(0);
                            ob[1] = lista.get(1);
                            ob[2] = lista.get(2);
                            ob[3] = lista.get(3);
                            ob[4] = lista.get(4);
                            ob[5] = lista.get(5);

                            model.addRow(ob);
                            tbDetailSale.setModel(model);

                            tbDetailSale.getColumnModel().getColumn(5).setMaxWidth(0);
                            tbDetailSale.getColumnModel().getColumn(5).setMinWidth(0);
                            tbDetailSale.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
                            tbDetailSale.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
                        } else {
                            JOptionPane.showMessageDialog(this, "Stock Producto no disponible");
                        }

                        lblSearchProductsByDefault();
                        calcularTotales();
                    }
                }

                txtCodeProd.setText("");
            }

        }
    }//GEN-LAST:event_txtCodeProdKeyReleased

    private void txtCodeProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodeProdKeyTyped
        char caracter = evt.getKeyChar();

        if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodeProdKeyTyped

    int selectedRowClient;
    E_clients clientSelected;
    private void tbClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClientMouseClicked
        btnSelectClient.setEnabled(true);
        clientSelected = new E_clients();
        selectedRowClient = tbClient.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_tbClientMouseClicked

    private void btnSelectClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectClientActionPerformed
        btnSelectClient.setText("Seleccionado");
        clientSelected.setIdClient(Integer.parseInt(tbClient.getValueAt(selectedRowClient, 0).toString()));
    }//GEN-LAST:event_btnSelectClientActionPerformed

    private void btnRefreshClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshClientsActionPerformed
        showListClients();
    }//GEN-LAST:event_btnRefreshClientsActionPerformed

    private void txtSearchClientKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchClientKeyReleased
        filtro(txtSearchClient.getText(), tbClient);
    }//GEN-LAST:event_txtSearchClientKeyReleased

    private void tbDetailSaleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDetailSaleKeyReleased
        int amount = Integer.parseInt(tbDetailSale.getValueAt(selectedRow, 2).toString());
        int limitAmount = Integer.parseInt(lblMaxStock.getText());

        if (amount > limitAmount) {
            JOptionPane.showMessageDialog(null, "La cantidad sobrepasa el inventario!");
            System.out.println("Amount before: " + amount);
            tbDetailSale.setValueAt(originalAmount, selectedRow, 2);
        } else {
            calcularTotales();
        }
    }//GEN-LAST:event_tbDetailSaleKeyReleased

    /*  MAIN METHOD  */

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
//            java.util.logging.Logger.getLogger(V_Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(V_Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(V_Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(V_Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new V_Sale().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddClient;
    public static javax.swing.JButton btnAddProduct;
    public static javax.swing.JButton btnCancelAddProd;
    private javax.swing.JButton btnCancelSale;
    private javax.swing.JButton btnCategory;
    private javax.swing.JButton btnClients;
    private javax.swing.JButton btnCompleteSale;
    public static javax.swing.JButton btnDeleteProd;
    private javax.swing.JButton btnNewSale;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnProduct;
    public static javax.swing.JButton btnRefreshClients;
    private javax.swing.JButton btnSearchProd;
    private javax.swing.JButton btnSelectClient;
    private javax.swing.JButton btnUsers;
    private javax.swing.JButton btnUsers1;
    private javax.swing.JButton btnUsers2;
    private javax.swing.ButtonGroup btngTypeClient;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblArtSale;
    private javax.swing.JLabel lblDate;
    private static javax.swing.JLabel lblIdProd;
    private javax.swing.JLabel lblIdSale;
    private javax.swing.JLabel lblLogoWStore;
    private javax.swing.JLabel lblMaxStock;
    public static javax.swing.JLabel lblPrice;
    public static javax.swing.JLabel lblSelectedProd;
    public static javax.swing.JLabel lblStock;
    private javax.swing.JPanel pnlCliente;
    private javax.swing.JPanel pnlConfigSale;
    private javax.swing.JPanel pnlOptions;
    private javax.swing.JPanel pnlProdsAdd;
    private javax.swing.JPanel pnlTableSale;
    private javax.swing.JRadioButton rbGenericClient;
    private javax.swing.JRadioButton rbRegisterClient;
    private javax.swing.JTable tbClient;
    private javax.swing.JTable tbDetailSale;
    private javax.swing.JTextField txtCodeProd;
    private javax.swing.JTextField txtSearchClient;
    private javax.swing.JTextField txtTotalNeto;
    // End of variables declaration//GEN-END:variables
}
