/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import DAO.KhoHangDAO;
import DAO.NhanVienDAO;
import DAO.SanPhamDAO;
import helper.DialogHelper;
import helper.ShareHelper;
import helper.XDate;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.KhoHang;
import model.SanPham;
/**
 *
 * @author ADMIN
 */
public class KhoHangJFrame extends javax.swing.JDialog {
 int index = 0;
    KhoHangDAO dao = new KhoHangDAO();
    SanPhamDAO spdao = new SanPhamDAO();
    NhanVienDAO nvdao = new NhanVienDAO();

    /**
     * Creates new form KhoJFrame
     */
    public KhoHangJFrame(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    KhoHangJFrame() {
        initComponents();
        init();
    }
    void init() {
        setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);
        ShareHelper.setBoderForTable(jScrollPane2);
        this.setTitle("Hệ thống quản lý quán coffee");
        load();
        fillCombobox();
        load();
        setLocationRelativeTo(null);
        setIconImage(ShareHelper.APP_ICON);
    }

    void fillCombobox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboSanPham.getModel();
        model.removeAllElements();
        try {
            List<SanPham> list = spdao.select();
            for (SanPham sanPham : list) {
                model.addElement(sanPham.getTenSanPham());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblQuanLyKhoHang.getModel();
        model.setRowCount(0);
        try {
            List<KhoHang> list = dao.select();
            for (KhoHang kh : list) {
                Object[] row = {
                    kh.getMaKhoHang(),
                    spdao.findById(kh.getMaSanPham()).getTenSanPham(),
                    nvdao.findById(kh.getMaNhanVien()).getHoTen(),
                    kh.getNgayNhap(),
                    kh.getHanSuDung(),
                    kh.getSoLuong()
                };
                model.addRow(row);

            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");

        }
    }

    void insert() {
        KhoHang model = getModel();
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm mới thất bại!");
            System.out.println(e.toString());
        }
    }

    void update() {
        KhoHang model = getModel();
        try {
            dao.update(model);
            this.load();
            this.clear();
            DialogHelper.alert(this, "Cập nhập thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập nhập thất bại!");
        }
    }

    void delete() {
        if (DialogHelper.confirm(this, "Bạn thực sự muôn xóa Kho Hàng này!")) {
            Integer makh = Integer.valueOf(cboSanPham.getToolTipText());
            try {
                dao.delete(makh);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại!");
            }

        }
    }

    void clear() {
        KhoHang model = new KhoHang();
        String sanpham = (String) cboSanPham.getSelectedItem();
        SanPham sp = spdao.findByName(sanpham);
        model.setSoLuong(model.getSoLuong());
        model.setGhiChu(model.getGhiChu());
        this.cboSanPham.setSelectedItem(0);
        this.setModel(model);
        this.setStatus(true);
    }

    void edit() {
        try {
            int makh = (int) tblQuanLyKhoHang.getValueAt(this.index, 0);
            KhoHang model = dao.findById(makh);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void setModel(KhoHang model) {
        cboSanPham.setToolTipText(String.valueOf(model.getMaKhoHang()));
        SanPham sp = spdao.findById(model.getMaSanPham());
        try {
            cboSanPham.setSelectedItem(sp.getTenSanPham());
        } catch (Exception e) {
        }
        txtSoLuong.setText(String.valueOf(model.getSoLuong()));
        txtGhiChu.setText(model.getGhiChu());
    }

    KhoHang getModel() {
        KhoHang khoHang = new KhoHang();
        String tenSanPham = (String) cboSanPham.getSelectedItem();
        SanPham sp = spdao.findByName(tenSanPham);
        if (cboSanPham.getToolTipText() != null) {
            khoHang.setMaKhoHang(Integer.valueOf(cboSanPham.getToolTipText()));
        }
        khoHang.setMaSanPham(sp.getMaSanPham());
        khoHang.setMaNhanVien(ShareHelper.USER.getMaNhanVien());
        khoHang.setNgayNhap(XDate.now());
        khoHang.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        khoHang.setGhiChu(txtGhiChu.getText());
        khoHang.setHanSuDung(XDate.addDays(XDate.now(), 30));  // hạn sử dụng 1 tháng kể từ ngày thêm 
        return khoHang;
    }

    void setStatus(boolean insertable) {
        btnthem.setEnabled(insertable);
        btnsua.setEnabled(!insertable);
        btnxoa.setEnabled(!insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tblQuanLyKhoHang.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);
        btnPrev.setEnabled(!insertable && first);
        btnLast.setEnabled(!insertable && last);
        btnNext.setEnabled(!insertable && last);
    }

    public boolean check() {
        boolean check = true;
        if (txtSoLuong.getText().isEmpty()) {
            DialogHelper.alert(this, "Số lượng không được rỗng");
            check = false;
        } else {
            try {
                if (Integer.parseInt(txtSoLuong.getText()) < 50 || Integer.parseInt(txtSoLuong.getText()) > 100) {
                    DialogHelper.alert(this, "Số lượng phải từ 50 đến 100.");
                    check = false;
                }
            } catch (Exception e) {
                DialogHelper.alert(this, "Số lượng phải là số.");
                check = false;
            }
        }
        return check;
    }
 void selectComboBox() {
        SanPham sanpham = (SanPham) cboSanPham.getSelectedItem();
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tblQuanLy = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboSanPham = new javax.swing.JComboBox<>();
        txtSoLuong = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnthem = new javax.swing.JButton();
        btnnhap = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblQuanLyKhoHang = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblQuanLy.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        tblQuanLy.setText("Quản Lý Kho Hàng");

        jLabel2.setText("Sản phẩm:");

        jLabel3.setText("Số lượng:");

        jLabel4.setText("Ghi chú: ");

        cboSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSanPham.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cboSanPhamPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnnhap.setText("Nhập");
        btnnhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnhapActionPerformed(evt);
            }
        });

        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setText("<<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnLast.setText(">>");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnNext.setText(">|");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        tblQuanLyKhoHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Kho hàng", "Mã sản phẩm", "Mã nhân viên", "Ngày nhập", "Số lượng ", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQuanLyKhoHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuanLyKhoHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblQuanLyKhoHang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnthem)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnnhap)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnxoa)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnsua)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)
                                .addGap(28, 28, 28)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(btnFirst)
                                .addGap(18, 18, 18)
                                .addComponent(btnPrev)
                                .addGap(18, 18, 18)
                                .addComponent(btnLast)
                                .addGap(18, 18, 18)
                                .addComponent(btnNext)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tblQuanLy)
                        .addGap(395, 395, 395)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(tblQuanLy)
                .addGap(96, 96, 96)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFirst)
                    .addComponent(btnPrev)
                    .addComponent(btnLast)
                    .addComponent(btnNext))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnsua)
                            .addComponent(btnxoa)
                            .addComponent(btnnhap)
                            .addComponent(btnthem)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
         if (check()) {
            insert();
        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnnhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnhapActionPerformed
        // TODO add your handling code here:
        if (check()) {
            update();
        }
    }//GEN-LAST:event_btnnhapActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
         if (ShareHelper.getQuyenTruyCap()) {
            delete();
        } else {
            DialogHelper.alert(this, "Bạn không đủ quyền để thực hiện chức năng này.");
        }
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnsuaActionPerformed

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

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
         this.index = tblQuanLyKhoHang.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        this.index++;
        this.edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void tblQuanLyKhoHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuanLyKhoHangMouseClicked
        // TODO add your handling code here:
         if (evt.getClickCount() == 1) {
            this.index = tblQuanLyKhoHang.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
            }
        }
    }//GEN-LAST:event_tblQuanLyKhoHangMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.load();
        this.setStatus(true);
    }//GEN-LAST:event_formWindowOpened

    private void cboSanPhamPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cboSanPhamPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
         selectComboBox();
    }//GEN-LAST:event_cboSanPhamPopupMenuWillBecomeInvisible

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
            java.util.logging.Logger.getLogger(KhoHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhoHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhoHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhoHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KhoHangJFrame dialog = new KhoHangJFrame(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnnhap;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel tblQuanLy;
    private javax.swing.JTable tblQuanLyKhoHang;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
