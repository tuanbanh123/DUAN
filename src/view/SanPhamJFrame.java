/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import DAO.SanPhamDAO;
import helper.DialogHelper;
import helper.ShareHelper;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.SanPham;

/**
 *
 * @author Admin
 */
public class SanPhamJFrame extends javax.swing.JFrame {
    /**
     * Creates new form SanPhamJFrame
     */
      int index = 0;
    SanPhamDAO dao = new SanPhamDAO();
    public SanPhamJFrame() {
        initComponents();   
        init();
    }

    SanPhamJFrame(ManiJFrame aThis, boolean rootPaneCheckingEnabled) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 void init() {
       
        setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);
        this.load();
        this.setStatus(true);
        this.setTitle("Hệ thống quản lý quán coffee");
        ShareHelper.setBoderForTable(jScrollPane2);

    }

  

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        try {
            List<SanPham> list = dao.select();
            for (SanPham sp : list) {
                Object[] row = {
                    sp.getMaSanPham(),
                    sp.getTenSanPham(),                 
                    sp.getGiaBan(),
                    sp.getGhiChu()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setModel(SanPham model) {
        txtMaSanPham.setToolTipText(String.valueOf(model.getMaSanPham()));
        txtTenSanPham.setText(model.getTenSanPham());
        txtGiaBan.setText(String.valueOf(model.getGiaBan()));
        txtGhiChu.setText(model.getGhiChu());

    }

    SanPham getModel() {
        SanPham sanpham = new SanPham();
        sanpham.setTenSanPham(txtTenSanPham.getText());
        sanpham.setGiaBan(Float.valueOf(txtGiaBan.getText()));
        sanpham.setGhiChu(txtGhiChu.getText());

        if (txtTenSanPham.getToolTipText() != null) {
            sanpham.setMaSanPham(Integer.valueOf(txtTenSanPham.getToolTipText()));
        }
        return sanpham;
    }

    void setStatus(boolean insertTable) {

        btnThem.setEnabled(insertTable);
        btnSua.setEnabled(!insertTable);
        btnXoa.setEnabled(!insertTable);
        boolean first = this.index > 0;
        boolean last = this.index < tblSanPham.getRowCount() - 1;
        btnFirst.setEnabled(!insertTable && first);
        btnPrev.setEnabled(!insertTable && first);
        btnNext.setEnabled(!insertTable && last);
        btnLast.setEnabled(!insertTable && last);

    }

    void clear() {
        SanPham model = new SanPham();
        this.setModel(model);
        this.setStatus(true);
        txtGiaBan.setText("");
    }

    void insert() {
        SanPham model = getModel();
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm mới thất bại!");
            e.printStackTrace();
        }
    }

    void update() {
        SanPham model = getModel();
        try {
            dao.update(model);
            this.load();
            this.clear();
            DialogHelper.alert(this, "Cập nhập thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập nhập thất bại");
        }
    }

    void delete() {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa sản phẩm này")) {
            Integer masp = Integer.valueOf(txtTenSanPham.getToolTipText());
            try {
                dao.delete(masp);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại");
            }
        }
    }

    void edit() {
        try {
            int masp = (int) tblSanPham.getValueAt(this.index, 0);
            SanPham model = dao.findById(masp);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public boolean check() {
        boolean check = true;
        if (txtTenSanPham.getText().isEmpty()) {
            DialogHelper.alert(this, "Tên sản phẩm không được để rỗng");
            check = false;
        }

        if (txtGiaBan.getText().isEmpty()) {
            DialogHelper.alert(this, "Giá bán không được để rỗng");
            check = false;
        } else {
            try {
                if (Float.parseFloat(txtGiaBan.getText()) <= 0) {
                    DialogHelper.alert(this, "Giá bán phải lớn hơn 0");
                    check = false;
                }
            } catch (Exception e) {
                DialogHelper.alert(this, "Giá bán phải là số.");
                check = false;
            }
        }
        return check;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIcon = new javax.swing.JLabel();
        pnlWrapper = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        lblTenSanPham = new javax.swing.JLabel();
        lblLoaiSanPham = new javax.swing.JLabel();
        lblGhiChu = new javax.swing.JLabel();
        lblGiaBan = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        txtTenSanPham = new javax.swing.JTextField();

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Ie9liJ.jpg"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý sản phẩm");
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblTieuDe.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("Quản Lý Sản Phẩm");

        lblTenSanPham.setText("Mã Sản Phẩm:");

        lblLoaiSanPham.setText("Tên Sản Phẩm:");

        lblGhiChu.setText("Ghi Chú:");

        lblGiaBan.setText("Giá Bán:");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        tblSanPham.setAutoCreateRowSorter(true);
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản phẩm", "Giá Bán", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        btnFirst.setText("<<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setText("|<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setText(">|");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setText(">>");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlWrapperLayout = new javax.swing.GroupLayout(pnlWrapper);
        pnlWrapper.setLayout(pnlWrapperLayout);
        pnlWrapperLayout.setHorizontalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenSanPham)
                            .addComponent(lblLoaiSanPham)
                            .addComponent(lblGiaBan)
                            .addComponent(lblGhiChu))
                        .addGap(34, 34, 34)
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtGiaBan, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMaSanPham, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(14, 14, 14)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
                        .addComponent(btnFirst)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLast)
                        .addGap(210, 210, 210))
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlWrapperLayout.setVerticalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenSanPham)
                            .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLoaiSanPham)
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGiaBan)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGhiChu)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnSua)
                            .addComponent(btnXoa)
                            .addComponent(btnMoi)))
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnLast))
                            .addComponent(btnFirst))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pnlWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
       if (check()) {
            insert();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
       if (check()) {
            update();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
      if (ShareHelper.getQuyenTruyCap()) {
            delete();
        } else {
            DialogHelper.alert(this, "Bạn không đủ quyền để thực hiện chức năng này.");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
       clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
       if (evt.getClickCount() == 1) {
            this.index = tblSanPham.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
            }
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
 this.load();
        this.setStatus(true);
    }//GEN-LAST:event_formWindowOpened

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
     this.index--;
        this.edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        this.edit();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
      this.index = tblSanPham.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.index++;
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SanPhamJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SanPhamJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SanPhamJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SanPhamJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SanPhamJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblLoaiSanPham;
    private javax.swing.JLabel lblTenSanPham;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JPanel pnlWrapper;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables
}
