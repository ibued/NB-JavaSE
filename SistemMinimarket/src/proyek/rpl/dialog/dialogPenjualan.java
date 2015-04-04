
package proyek.rpl.dialog;

import javax.swing.ImageIcon;

/**
 *
 * @author ibued
 */
public class dialogPenjualan extends javax.swing.JDialog {

    /**
     * Creates new form dialogPenjualan
     * @param parent
     * @param modal
     */
    public dialogPenjualan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        komponen();
        this.setIconImage(new ImageIcon(getClass().
                getResource("/proyek/rpl/gambar/icon.png")).getImage());
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txJumlahBarang1 = new javax.swing.JTextField();
        txIDbarangJual1 = new javax.swing.JTextField();
        btGetIDbarangJual1 = new javax.swing.JButton();
        txIDbarangJual2 = new javax.swing.JTextField();
        btGetIDbarangJual2 = new javax.swing.JButton();
        txJumlahBarang2 = new javax.swing.JTextField();
        txIDbarangJual3 = new javax.swing.JTextField();
        btGetIDbarangJual3 = new javax.swing.JButton();
        txJumlahBarang3 = new javax.swing.JTextField();
        btGetIDbarangJual4 = new javax.swing.JButton();
        txJumlahBarang4 = new javax.swing.JTextField();
        txIDbarangJual4 = new javax.swing.JTextField();
        btGetIDbarangJual5 = new javax.swing.JButton();
        txJumlahBarang5 = new javax.swing.JTextField();
        txIDbarangJual5 = new javax.swing.JTextField();
        txJumlahBarang6 = new javax.swing.JTextField();
        btGetIDbarangJual6 = new javax.swing.JButton();
        txIDbarangJual6 = new javax.swing.JTextField();
        btGetIDbarangJual7 = new javax.swing.JButton();
        txIDbarangJual7 = new javax.swing.JTextField();
        txJumlahBarang7 = new javax.swing.JTextField();
        txJumlahBarang8 = new javax.swing.JTextField();
        txIDbarangJual8 = new javax.swing.JTextField();
        btGetIDbarangJual8 = new javax.swing.JButton();
        txIDbarangJual9 = new javax.swing.JTextField();
        btGetIDbarangJual9 = new javax.swing.JButton();
        txJumlahBarang9 = new javax.swing.JTextField();
        txJumlahBarang10 = new javax.swing.JTextField();
        txIDbarangJual10 = new javax.swing.JTextField();
        btGetIDbarangJual10 = new javax.swing.JButton();
        txIDbarangJual11 = new javax.swing.JTextField();
        txJumlahBarang11 = new javax.swing.JTextField();
        btGetIDbarangJual11 = new javax.swing.JButton();
        txIDbarangJual12 = new javax.swing.JTextField();
        txJumlahBarang12 = new javax.swing.JTextField();
        btGetIDbarangJual12 = new javax.swing.JButton();
        txIDbarangJual13 = new javax.swing.JTextField();
        btGetIDbarangJual13 = new javax.swing.JButton();
        txJumlahBarang13 = new javax.swing.JTextField();
        txIDbarangJual14 = new javax.swing.JTextField();
        txJumlahBarang14 = new javax.swing.JTextField();
        btGetIDbarangJual14 = new javax.swing.JButton();
        txIDbarangJual15 = new javax.swing.JTextField();
        btGetIDbarangJual15 = new javax.swing.JButton();
        txJumlahBarang15 = new javax.swing.JTextField();
        txIDbarangJual16 = new javax.swing.JTextField();
        btGetIDbarangJual16 = new javax.swing.JButton();
        txJumlahBarang16 = new javax.swing.JTextField();
        btGetIDbarangJual17 = new javax.swing.JButton();
        txIDbarangJual17 = new javax.swing.JTextField();
        txJumlahBarang17 = new javax.swing.JTextField();
        txIDbarangJual18 = new javax.swing.JTextField();
        btGetIDbarangJual18 = new javax.swing.JButton();
        txJumlahBarang18 = new javax.swing.JTextField();
        btGetIDbarangJual19 = new javax.swing.JButton();
        txJumlahBarang19 = new javax.swing.JTextField();
        txIDbarangJual19 = new javax.swing.JTextField();
        txIDbarangJual20 = new javax.swing.JTextField();
        btGetIDbarangJual20 = new javax.swing.JButton();
        txJumlahBarang20 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PENJUALAN LEBIH BANYAK");
        setUndecorated(true);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(51, 102, 0));

        jPanel1.setBackground(new java.awt.Color(51, 102, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "ID BARANG", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri", 1, 12))); // NOI18N

        txJumlahBarang1.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang1.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang1.setCaretColor(new java.awt.Color(204, 204, 204));

        txIDbarangJual1.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual1.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual1.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual1.setText("...");

        txIDbarangJual2.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual2.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual2.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual2.setText("...");

        txJumlahBarang2.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang2.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang2.setCaretColor(new java.awt.Color(204, 204, 204));

        txIDbarangJual3.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual3.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual3.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual3.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual3.setText("...");

        txJumlahBarang3.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang3.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang3.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual4.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual4.setText("...");

        txJumlahBarang4.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang4.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang4.setCaretColor(new java.awt.Color(204, 204, 204));

        txIDbarangJual4.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual4.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual4.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual5.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual5.setText("...");

        txJumlahBarang5.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang5.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang5.setCaretColor(new java.awt.Color(204, 204, 204));

        txIDbarangJual5.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual5.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual5.setCaretColor(new java.awt.Color(204, 204, 204));

        txJumlahBarang6.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang6.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang6.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual6.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual6.setText("...");

        txIDbarangJual6.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual6.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual6.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual7.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual7.setText("...");

        txIDbarangJual7.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual7.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual7.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual7.setCaretColor(new java.awt.Color(204, 204, 204));

        txJumlahBarang7.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang7.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang7.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang7.setCaretColor(new java.awt.Color(204, 204, 204));

        txJumlahBarang8.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang8.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang8.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang8.setCaretColor(new java.awt.Color(204, 204, 204));

        txIDbarangJual8.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual8.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual8.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual8.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual8.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual8.setText("...");

        txIDbarangJual9.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual9.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual9.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual9.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual9.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual9.setText("...");

        txJumlahBarang9.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang9.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang9.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang9.setCaretColor(new java.awt.Color(204, 204, 204));

        txJumlahBarang10.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang10.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang10.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang10.setCaretColor(new java.awt.Color(204, 204, 204));

        txIDbarangJual10.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual10.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual10.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual10.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual10.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual10.setText("...");

        txIDbarangJual11.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual11.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual11.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual11.setCaretColor(new java.awt.Color(204, 204, 204));

        txJumlahBarang11.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang11.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang11.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang11.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual11.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual11.setText("...");

        txIDbarangJual12.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual12.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual12.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual12.setCaretColor(new java.awt.Color(204, 204, 204));

        txJumlahBarang12.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang12.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang12.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang12.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual12.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual12.setText("...");

        txIDbarangJual13.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual13.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual13.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual13.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual13.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual13.setText("...");

        txJumlahBarang13.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang13.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang13.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang13.setCaretColor(new java.awt.Color(204, 204, 204));

        txIDbarangJual14.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual14.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual14.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual14.setCaretColor(new java.awt.Color(204, 204, 204));

        txJumlahBarang14.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang14.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang14.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang14.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual14.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual14.setText("...");

        txIDbarangJual15.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual15.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual15.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual15.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual15.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual15.setText("...");

        txJumlahBarang15.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang15.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang15.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang15.setCaretColor(new java.awt.Color(204, 204, 204));

        txIDbarangJual16.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual16.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual16.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual16.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual16.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual16.setText("...");

        txJumlahBarang16.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang16.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang16.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang16.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual17.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual17.setText("...");

        txIDbarangJual17.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual17.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual17.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual17.setCaretColor(new java.awt.Color(204, 204, 204));

        txJumlahBarang17.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang17.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang17.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang17.setCaretColor(new java.awt.Color(204, 204, 204));

        txIDbarangJual18.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual18.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual18.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual18.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual18.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual18.setText("...");

        txJumlahBarang18.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang18.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang18.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang18.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual19.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual19.setText("...");

        txJumlahBarang19.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang19.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang19.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang19.setCaretColor(new java.awt.Color(204, 204, 204));

        txIDbarangJual19.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual19.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual19.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual19.setCaretColor(new java.awt.Color(204, 204, 204));

        txIDbarangJual20.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual20.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual20.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual20.setCaretColor(new java.awt.Color(204, 204, 204));

        btGetIDbarangJual20.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btGetIDbarangJual20.setText("...");

        txJumlahBarang20.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarang20.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarang20.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarang20.setCaretColor(new java.awt.Color(204, 204, 204));

        jComboBox1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- BANYAK BARANG --", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual20, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang20, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual19, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang19))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual18, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual17, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang17))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual16, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang16))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual15, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang15))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual14, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang14))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txIDbarangJual13, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGetIDbarangJual13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarang13))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txIDbarangJual11, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                    .addComponent(txIDbarangJual12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btGetIDbarangJual12)
                                    .addComponent(btGetIDbarangJual11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txJumlahBarang11, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                    .addComponent(txJumlahBarang12)))))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual11)
                            .addComponent(txJumlahBarang11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual12)
                            .addComponent(txJumlahBarang12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual13)
                            .addComponent(txJumlahBarang13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual14)
                            .addComponent(txJumlahBarang14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual15)
                            .addComponent(txJumlahBarang15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual16)
                            .addComponent(txJumlahBarang16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual17)
                            .addComponent(txJumlahBarang17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual18)
                            .addComponent(txJumlahBarang18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual19)
                            .addComponent(txJumlahBarang19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual20)
                            .addComponent(txJumlahBarang20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual1)
                            .addComponent(txJumlahBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual2)
                            .addComponent(txJumlahBarang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual3)
                            .addComponent(txJumlahBarang3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual4)
                            .addComponent(txJumlahBarang4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual5)
                            .addComponent(txJumlahBarang5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual6)
                            .addComponent(txJumlahBarang6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual7)
                            .addComponent(txJumlahBarang7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual8)
                            .addComponent(txJumlahBarang8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual9)
                            .addComponent(txJumlahBarang9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDbarangJual10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDbarangJual10)
                            .addComponent(txJumlahBarang10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(670, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        jMenuBar1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jMenu1.setText("File");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/keluar.png"))); // NOI18N
        jMenuItem1.setText("Keluar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1306, 509));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        int a = jComboBox1.getSelectedIndex();
        if (a == 1) {
            txIDbarangJual1.setVisible(true);
            btGetIDbarangJual1.setVisible(true);
            txJumlahBarang1.setVisible(true);
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dialogPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                dialogPenjualan dialog = new dialogPenjualan(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btGetIDbarangJual1;
    private javax.swing.JButton btGetIDbarangJual10;
    private javax.swing.JButton btGetIDbarangJual11;
    private javax.swing.JButton btGetIDbarangJual12;
    private javax.swing.JButton btGetIDbarangJual13;
    private javax.swing.JButton btGetIDbarangJual14;
    private javax.swing.JButton btGetIDbarangJual15;
    private javax.swing.JButton btGetIDbarangJual16;
    private javax.swing.JButton btGetIDbarangJual17;
    private javax.swing.JButton btGetIDbarangJual18;
    private javax.swing.JButton btGetIDbarangJual19;
    private javax.swing.JButton btGetIDbarangJual2;
    private javax.swing.JButton btGetIDbarangJual20;
    private javax.swing.JButton btGetIDbarangJual3;
    private javax.swing.JButton btGetIDbarangJual4;
    private javax.swing.JButton btGetIDbarangJual5;
    private javax.swing.JButton btGetIDbarangJual6;
    private javax.swing.JButton btGetIDbarangJual7;
    private javax.swing.JButton btGetIDbarangJual8;
    private javax.swing.JButton btGetIDbarangJual9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txIDbarangJual1;
    private javax.swing.JTextField txIDbarangJual10;
    private javax.swing.JTextField txIDbarangJual11;
    private javax.swing.JTextField txIDbarangJual12;
    private javax.swing.JTextField txIDbarangJual13;
    private javax.swing.JTextField txIDbarangJual14;
    private javax.swing.JTextField txIDbarangJual15;
    private javax.swing.JTextField txIDbarangJual16;
    private javax.swing.JTextField txIDbarangJual17;
    private javax.swing.JTextField txIDbarangJual18;
    private javax.swing.JTextField txIDbarangJual19;
    private javax.swing.JTextField txIDbarangJual2;
    private javax.swing.JTextField txIDbarangJual20;
    private javax.swing.JTextField txIDbarangJual3;
    private javax.swing.JTextField txIDbarangJual4;
    private javax.swing.JTextField txIDbarangJual5;
    private javax.swing.JTextField txIDbarangJual6;
    private javax.swing.JTextField txIDbarangJual7;
    private javax.swing.JTextField txIDbarangJual8;
    private javax.swing.JTextField txIDbarangJual9;
    private javax.swing.JTextField txJumlahBarang1;
    private javax.swing.JTextField txJumlahBarang10;
    private javax.swing.JTextField txJumlahBarang11;
    private javax.swing.JTextField txJumlahBarang12;
    private javax.swing.JTextField txJumlahBarang13;
    private javax.swing.JTextField txJumlahBarang14;
    private javax.swing.JTextField txJumlahBarang15;
    private javax.swing.JTextField txJumlahBarang16;
    private javax.swing.JTextField txJumlahBarang17;
    private javax.swing.JTextField txJumlahBarang18;
    private javax.swing.JTextField txJumlahBarang19;
    private javax.swing.JTextField txJumlahBarang2;
    private javax.swing.JTextField txJumlahBarang20;
    private javax.swing.JTextField txJumlahBarang3;
    private javax.swing.JTextField txJumlahBarang4;
    private javax.swing.JTextField txJumlahBarang5;
    private javax.swing.JTextField txJumlahBarang6;
    private javax.swing.JTextField txJumlahBarang7;
    private javax.swing.JTextField txJumlahBarang8;
    private javax.swing.JTextField txJumlahBarang9;
    // End of variables declaration//GEN-END:variables

    private void komponen() {
//        txIDbarangJual1.setVisible(false);
//        btGetIDbarangJual1.setVisible(false);
//        txJumlahBarang1.setVisible(false);
//        txIDbarangJual2.setVisible(false);
//        btGetIDbarangJual2.setVisible(false);
//        txJumlahBarang2.setVisible(false);
//        txIDbarangJual3.setVisible(false);
//        btGetIDbarangJual3.setVisible(false);
//        txJumlahBarang3.setVisible(false);
//        
//        txIDbarangJual4.setVisible(false);
//        btGetIDbarangJual4.setVisible(false);
//        txJumlahBarang4.setVisible(false);
//        
//        txIDbarangJual5.setVisible(false);
//        btGetIDbarangJual5.setVisible(false);
//        txJumlahBarang5.setVisible(false);
//        
//        txIDbarangJual6.setVisible(false);
//        btGetIDbarangJual6.setVisible(false);
//        txJumlahBarang6.setVisible(false);
//        
//        txIDbarangJual7.setVisible(false);
//        btGetIDbarangJual7.setVisible(false);
//        txJumlahBarang7.setVisible(false);
//        
//        txIDbarangJual8.setVisible(false);
//        btGetIDbarangJual8.setVisible(false);
//        txJumlahBarang8.setVisible(false);
//        
//        txIDbarangJual9.setVisible(false);
//        btGetIDbarangJual9.setVisible(false);
//        txJumlahBarang9.setVisible(false);
//        
//        txIDbarangJual10.setVisible(false);
//        btGetIDbarangJual10.setVisible(false);
//        txJumlahBarang10.setVisible(false);
//        
//        txIDbarangJual11.setVisible(false);
//        btGetIDbarangJual11.setVisible(false);
//        txJumlahBarang11.setVisible(false);
//        
//        txIDbarangJual12.setVisible(false);
//        btGetIDbarangJual12.setVisible(false);
//        txJumlahBarang12.setVisible(false);
//        
//        txIDbarangJual13.setVisible(false);
//        btGetIDbarangJual13.setVisible(false);
//        txJumlahBarang13.setVisible(false);
//        
//        txIDbarangJual14.setVisible(false);
//        btGetIDbarangJual14.setVisible(false);
//        txJumlahBarang14.setVisible(false);
//        
//        txIDbarangJual15.setVisible(false);
//        btGetIDbarangJual15.setVisible(false);
//        txJumlahBarang15.setVisible(false);
//        
//        txIDbarangJual16.setVisible(false);
//        btGetIDbarangJual16.setVisible(false);
//        txJumlahBarang16.setVisible(false);
//        
//        txIDbarangJual17.setVisible(false);
//        btGetIDbarangJual17.setVisible(false);
//        txJumlahBarang17.setVisible(false);
//        
//        txIDbarangJual18.setVisible(false);
//        btGetIDbarangJual18.setVisible(false);
//        txJumlahBarang18.setVisible(false);
//        
//        txIDbarangJual19.setVisible(false);
//        btGetIDbarangJual19.setVisible(false);
//        txJumlahBarang19.setVisible(false);
//        
//        txIDbarangJual20.setVisible(false);
//        btGetIDbarangJual20.setVisible(false);
//        txJumlahBarang20.setVisible(false);
    }
}
