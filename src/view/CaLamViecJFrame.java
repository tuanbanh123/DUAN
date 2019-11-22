/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.CaLamViecDAO;
import helper.DialogHelper;
import helper.ShareHelper;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.CaLamViec;

/**
 *
 * @author duann
 */
public class CaLamViecJFrame extends javax.swing.JFrame {

    int index = 0;
    CaLamViecDAO dao = new CaLamViecDAO();

    /**
     * Creates new form CaLamViecJFrame
     */
    public CaLamViecJFrame() {
        initComponents();
        init();
    }

    CaLamViecJFrame(ManiJFrame aThis, boolean rootPaneCheckingEnabled) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void init() {
        setIconImage(ShareHelper.APP_ICON);
        this.setLocationRelativeTo(null);
        this.load();
        this.setStatus(true);
        this.setTitle("Hệ thống quản lý quán coffee");
        ShareHelper.setBoderForTable(jScrollPane2);
    }

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblCaLamViec.getModel();
        model.setRowCount(0);
        try {
            List<CaLamViec> list = dao.select();
            for (CaLamViec clv : list) {
                Object[] row = {
                    clv.getMaCaLamViec(),
                    clv.getTenCaLamViec(),
                    clv.getBatDau(),
                    clv.getKetThuc(),
                    clv.getGhiChu()};
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setModel(CaLamViec model) throws ParseException {
        txtTenCaLV.setToolTipText(String.valueOf(model.getMaCaLamViec()));
        txtTenCaLV.setText(model.getTenCaLamViec());
        txtBatDau.setText(model.getBatDau());
        txtKetThuc.setText(model.getKetThuc());
        txtGhiChu.setText(model.getGhiChu());
    }

    CaLamViec getModel() {
        CaLamViec model = new CaLamViec();
        model.setTenCaLamViec(txtTenCaLV.getText());
        model.setBatDau((String) txtBatDau.getText());
        model.setKetThuc((String) txtKetThuc.getText());
        model.setGhiChu((String) txtGhiChu.getText());
        if (txtTenCaLV.getToolTipText() != null) {
            model.setMaCaLamViec(Integer.parseInt(txtTenCaLV.getToolTipText()));
        }
        return model;
    }

    void setStatus(boolean insertable) {
        btnThem.setEnabled(insertable);
        btnSua.setEnabled(!insertable);
        btnXoa.setEnabled(!insertable);

        boolean first = this.index > 0;
        boolean last = this.index < tblCaLamViec.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);
        btnPrev.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
    }

    void clear() throws ParseException {
        CaLamViec model = new CaLamViec();
        this.setModel(model);
        this.setStatus(true);
    }

    void insert() {
        CaLamViec model = getModel();
        try {
            dao.insert(model);
            this.load();
            DialogHelper.setInfinity(lblThongBao, "Thêm mới thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm mới thất bại!");
        }
    }

    void update() {
        CaLamViec model = getModel();
        try {
            dao.update(model);
            this.load();
            DialogHelper.setInfinity(lblThongBao, "Cập nhật thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập nhật thất bại!");
        }
    }

    void delete(Integer maCaLamViec) {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa khu vực này?")) {
            try {
                dao.delete(maCaLamViec);
                this.load();
                this.clear();
                DialogHelper.setInfinity(lblThongBao, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Không thể xóa được vì dữ liệu đã được liên kết!");
            }
        }
    }

    void edit() {
        try {
            int maCa = (int) tblCaLamViec.getValueAt(this.index, 0);
            CaLamViec model = dao.findById(maCa);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public boolean check() {
        boolean check = true;
        if (txtTenCaLV.getText().isEmpty()) {
            DialogHelper.alert(this, "Tên ca làm việc không được để trống!");
            return false;
        }
        if (txtBatDau.getText().isEmpty()) {
            DialogHelper.alert(this, "Thời gian bắt đầu không được để trống!");
            check = false;
        }
        if (txtKetThuc.getText().isEmpty()) {
            DialogHelper.alert(this, "Thời gian kết thúc không được để trống!");
            check = false;
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
        lblBatDau = new javax.swing.JLabel();
        lblKetThuc = new javax.swing.JLabel();
        lblTenCa = new javax.swing.JLabel();
        txtTenCaLV = new javax.swing.JTextField();
        lblGhiChu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCaLamViec = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lblThongBao = new javax.swing.JLabel();
        txtBatDau = new javax.swing.JTextField();
        txtKetThuc = new javax.swing.JTextField();

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/red-grey-wallpapers-25292-6318392.jpg"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý ca làm việc");
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblTieuDe.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("Quản Lý Ca Làm việc");

        lblBatDau.setText("Thời gian bắt đầu:");

        lblKetThuc.setText("Thời gian kết thúc:");

        lblTenCa.setText("Tên ca làm việc:");

        lblGhiChu.setText("Ghi Chú:");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        tblCaLamViec.setAutoCreateRowSorter(true);
        tblCaLamViec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ca làm việc", "Tên ca làm việc", "Thời gian bắt đầu", "Thời gian Kết thúc"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCaLamViec.setGridColor(new java.awt.Color(255, 255, 255));
        tblCaLamViec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCaLamViecMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCaLamViec);

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

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnFirst.setText("<<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnLast.setText(">>");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
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

        javax.swing.GroupLayout pnlWrapperLayout = new javax.swing.GroupLayout(pnlWrapper);
        pnlWrapper.setLayout(pnlWrapperLayout);
        pnlWrapperLayout.setHorizontalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlWrapperLayout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlWrapperLayout.createSequentialGroup()
                                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblBatDau)
                                            .addComponent(lblTenCa))
                                        .addGap(28, 28, 28)
                                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTenCaLV, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                            .addComponent(txtBatDau)))
                                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblKetThuc)
                                            .addComponent(lblGhiChu))
                                        .addGap(26, 26, 26)
                                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane1)
                                            .addComponent(txtKetThuc))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)))
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlWrapperLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlWrapperLayout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(btnFirst)
                                .addGap(20, 20, 20)
                                .addComponent(btnPrev)
                                .addGap(18, 18, 18)
                                .addComponent(btnNext)
                                .addGap(18, 18, 18)
                                .addComponent(btnLast)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(48, 48, 48))
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addComponent(lblThongBao)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlWrapperLayout.setVerticalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirst)
                    .addComponent(btnLast)
                    .addComponent(btnPrev)
                    .addComponent(btnNext))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(45, 45, 45))
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblTieuDe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThongBao)
                .addGap(60, 60, 60)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenCa)
                            .addComponent(txtTenCaLV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBatDau)
                            .addComponent(txtBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblKetThuc))
                    .addComponent(txtKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGhiChu)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnMoi))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (check()) {
            insert();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblCaLamViecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCaLamViecMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            this.index = tblCaLamViec.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
            }
        }
    }//GEN-LAST:event_tblCaLamViecMouseClicked

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        this.edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        this.index--;
        this.edit();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        this.index++;
        this.edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.index = tblCaLamViec.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:

        this.load();
        this.setStatus(true);
    }//GEN-LAST:event_formWindowOpened

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (check()) {
            update();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (ShareHelper.getQuyenTruyCap()) {
            delete(Integer.parseInt(txtTenCaLV.getToolTipText()));
            this.load();
        } else {
            DialogHelper.alert(this, "Bạn không đủ quyền để thực hiện chức năng này.");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        try {
            clear();
        } catch (ParseException ex) {
            Logger.getLogger(CaLamViecJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnMoiActionPerformed

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
            java.util.logging.Logger.getLogger(CaLamViecJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CaLamViecJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CaLamViecJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CaLamViecJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CaLamViecJFrame().setVisible(true);
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
    private javax.swing.JLabel lblBatDau;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblKetThuc;
    private javax.swing.JLabel lblTenCa;
    private javax.swing.JLabel lblThongBao;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JPanel pnlWrapper;
    private javax.swing.JTable tblCaLamViec;
    private javax.swing.JTextField txtBatDau;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtKetThuc;
    private javax.swing.JTextField txtTenCaLV;
    // End of variables declaration//GEN-END:variables
}
