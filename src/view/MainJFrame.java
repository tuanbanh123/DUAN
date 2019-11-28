/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.*;
import helper.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Ban;

/**
 *
 * @author duann
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form DanhMucJFrame
     */
    public static BanDAO banDAO = new BanDAO();
    public static KhoHangDAO khDAO = new KhoHangDAO();
    public static SanPhamDAO spDAO = new SanPhamDAO();
    public static LoaiSanPhamDAO lspDAO = new LoaiSanPhamDAO();
    public static HoaDonDAO hdDAO = new HoaDonDAO();
    public static KhuVucDAO kvDAO = new KhuVucDAO();
    DanhMucDAO dmdao = new DanhMucDAO();
    public static JLabel ban;
    public static int index = 0;
    public static ImageIcon CafeGreen;
    public static ImageIcon CafeBlack;

    static {
        CafeGreen = new ImageIcon(MainJFrame.class.getResource("/images/icons8-cafe-50.png"));
        CafeBlack = new ImageIcon(MainJFrame.class.getResource("/images/icons8-cafe-filled-50.png"));
    }

    public MainJFrame() {
        initComponents();
        init();
          setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);

        new Timer(1000, new ActionListener() {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");

            @Override
            public void actionPerformed(ActionEvent e) {
                lbldongHo.setText(format.format(new Date()));
            }
        }).start();
       
    }
    

    void init() {
        setIconImage(ShareHelper.APP_ICON);
        this.setLocationRelativeTo(null);
        this.setTitle("Hệ thống quản lý quán coffee");
        this.openWelcome();
        this.openLogin();
        loadTabs();
        loadDonHangTheoBan();
        setBoderForTable(jScrollPane1);
    }

    void openWelcome() {
        new ChaoJDialog(this, true).setVisible(true);
    }

    void openLogin() {
        new DangNhapJDialog(this, true).setVisible(true);
    }

    public static void loadTabs() {
        loadBanChung();
    }

    void setBoderForTable(JScrollPane scp) {
        scp.setViewportBorder(null);
        scp.setBorder(null);
    }

    public static void loadBanChung() {
        List<Ban> list = banDAO.select();
        pnlBan.removeAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isTrangThai()) {
                int id = i;
                ban = new JLabel(CafeGreen);
                if (list.get(i).getMaBan() < 10) {
                    ban.setText("Bàn 0" + list.get(i).getMaBan());
                } else {
                    ban.setText("Bàn " + list.get(i).getMaBan());
                }
                ban.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            thongTinTheoBan(list.get(id).getMaBan());
                        }
                    }
                });
                pnlBan.add(ban);
                pnlBan.revalidate();
                pnlBan.setVisible(true);
            } else {
                ban = new JLabel(CafeBlack);
                if (list.get(i).getMaBan() < 10) {
                    ban.setText("Bàn 0" + list.get(i).getMaBan());
                } else {
                    ban.setText("Bàn " + list.get(i).getMaBan());
                }
                int id = i;
                ban.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            thongTinTheoBan(list.get(id).getMaBan());
                        }
                    }
                });
                pnlBan.add(ban);
                pnlBan.revalidate();
                pnlBan.setVisible(true);
            }
        }
    }

    public static void thongTinTheoBan(int id) {
        new ThongTinDonHangJFrame(id).setVisible(true);
    }

    private void logOff() {
        ShareHelper.logOff();
        this.dispose();
        this.openLogin();
        if (ShareHelper.USER != null) {
            this.setVisible(true);
        }
    }

    void xemThongTinBan() {
        DialogHelper.alert(this, "Xem thông tin bàn");
    }

    void xemThongTinChiTiet() {
        new HoaDonChiTietJFrame().setVisible(true);
    }

    public static void loadDonHangTheoBan() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        try {
            List<Object[]> list = hdDAO.getHoaDonTheoBan();
            for (Object[] objects : list) {
                model.addRow(objects);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlWrapper = new javax.swing.JPanel();
        lblIcon = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        lblBanHD = new javax.swing.JLabel();
        pnlBan = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbldongHo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        mnu = new javax.swing.JMenuBar();
        mnuHeThong = new javax.swing.JMenu();
        mniDangXuat = new javax.swing.JMenuItem();
        mniThongTinUngDung = new javax.swing.JMenu();
        mniBan = new javax.swing.JMenu();
        mniKhuVuc = new javax.swing.JMenu();
        mniSanPham = new javax.swing.JMenu();
        mniLoaiSanPham = new javax.swing.JMenu();
        mniNhanVien = new javax.swing.JMenu();
        mniCaLamViec = new javax.swing.JMenu();
        mniKhoHang = new javax.swing.JMenu();
        mniThongKe = new javax.swing.JMenu();
        mniChiTiet = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Ie9liJ.jpg"))); // NOI18N

        tblHoaDon.setAutoCreateRowSorter(true);
        tblHoaDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bàn", "Khu vực"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setGridColor(new java.awt.Color(255, 255, 255));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        lblBanHD.setForeground(new java.awt.Color(255, 255, 255));
        lblBanHD.setText("Thông tin các bàn đang hoạt động");

        pnlBan.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlBan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel1.setForeground(new java.awt.Color(204, 255, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 47));

        lbldongHo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Alarm.png"))); // NOI18N
        lbldongHo.setText("10:55 PM");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Info.png"))); // NOI18N
        jLabel1.setText("Hệ quản lý đào tạo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbldongHo)
                .addGap(48, 48, 48))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(lbldongHo, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlWrapperLayout = new javax.swing.GroupLayout(pnlWrapper);
        pnlWrapper.setLayout(pnlWrapperLayout);
        pnlWrapperLayout.setHorizontalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(pnlBan, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBanHD)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1186, Short.MAX_VALUE)
            .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlWrapperLayout.createSequentialGroup()
                    .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 1186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlWrapperLayout.setVerticalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addComponent(lblBanHD)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlWrapperLayout.createSequentialGroup()
                    .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 499, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        mnuHeThong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Hierarchy.png"))); // NOI18N
        mnuHeThong.setText("Hệ thống");

        mniDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Exit.png"))); // NOI18N
        mniDangXuat.setText("Đăng xuất");
        mniDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangXuatActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniDangXuat);

        mniThongTinUngDung.setText("Thông tin ứng dụng");
        mnuHeThong.add(mniThongTinUngDung);

        mnu.add(mnuHeThong);

        mniBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Conference.png"))); // NOI18N
        mniBan.setText("Bàn");
        mniBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniBanMouseClicked(evt);
            }
        });
        mnu.add(mniBan);

        mniKhuVuc.setText("Khu vực");
        mniKhuVuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniKhuVucMouseClicked(evt);
            }
        });
        mnu.add(mniKhuVuc);

        mniSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gift.png"))); // NOI18N
        mniSanPham.setText("Sản phẩm");
        mniSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniSanPhamMouseClicked(evt);
            }
        });
        mnu.add(mniSanPham);

        mniLoaiSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Unordered list.png"))); // NOI18N
        mniLoaiSanPham.setText("Loại sản phẩm");
        mniLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniLoaiSanPhamMouseClicked(evt);
            }
        });
        mnu.add(mniLoaiSanPham);

        mniNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/User group.png"))); // NOI18N
        mniNhanVien.setText("Nhân viên");
        mniNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniNhanVienMouseClicked(evt);
            }
        });
        mnu.add(mniNhanVien);

        mniCaLamViec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Date.png"))); // NOI18N
        mniCaLamViec.setText("Ca làm việc");
        mniCaLamViec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniCaLamViecMouseClicked(evt);
            }
        });
        mnu.add(mniCaLamViec);

        mniKhoHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Box.png"))); // NOI18N
        mniKhoHang.setText("Kho hàng");
        mniKhoHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniKhoHangMouseClicked(evt);
            }
        });
        mnu.add(mniKhoHang);

        mniThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Card file.png"))); // NOI18N
        mniThongKe.setText("Thống kê");
        mniThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniThongKeMouseClicked(evt);
            }
        });
        mnu.add(mniThongKe);

        mniChiTiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Globe.png"))); // NOI18N
        mniChiTiet.setText("Chi tiết");
        mniChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniChiTietMouseClicked(evt);
            }
        });
        mnu.add(mniChiTiet);

        setJMenuBar(mnu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, 1184, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, 508, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.index = tblHoaDon.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                thongTinTheoBan((int) tblHoaDon.getValueAt(index, 0));
            }
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void mniBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniBanMouseClicked
        // TODO add your handling code here:
        new BanJFrame().setVisible(true);
    }//GEN-LAST:event_mniBanMouseClicked

    private void mniKhuVucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniKhuVucMouseClicked
        // TODO add your handling code here:
        new KhuVucJFrame().setVisible(true);
    }//GEN-LAST:event_mniKhuVucMouseClicked

    private void mniSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniSanPhamMouseClicked
        // TODO add your handling code here:
        new SanPhamJFrame().setVisible(true);
    }//GEN-LAST:event_mniSanPhamMouseClicked

    private void mniLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniLoaiSanPhamMouseClicked
        // TODO add your handling code here:
        new LoaiSanPhamJFrame().setVisible(true);
    }//GEN-LAST:event_mniLoaiSanPhamMouseClicked

    private void mniNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniNhanVienMouseClicked
        // TODO add your handling code here:
        if (ShareHelper.getQuyenTruyCap()) {
            new NhanVienJFrame().setVisible(true);
        } else {
            DialogHelper.alert(this, "Bạn không đủ quyền để vào chức năng này.");
        }
    }//GEN-LAST:event_mniNhanVienMouseClicked

    private void mniCaLamViecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniCaLamViecMouseClicked
        // TODO add your handling code here:
        new CaLamViecJFrame().setVisible(true);
    }//GEN-LAST:event_mniCaLamViecMouseClicked

    private void mniKhoHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniKhoHangMouseClicked
        // TODO add your handling code here:
        if (ShareHelper.getQuyenTruyCap()) {
            new KhoHangJFrame().setVisible(true);
        } else {
            DialogHelper.alert(this, "Bạn không đủ quyền để vào chức năng này.");
        }
    }//GEN-LAST:event_mniKhoHangMouseClicked

    private void mniThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniThongKeMouseClicked
        // TODO add your handling code here:
        if (ShareHelper.getQuyenTruyCap()) {
            new ThongKeJFrame().setVisible(true);
        } else {
            DialogHelper.alert(this, "Bạn không đủ quyền để vào chức năng này.");
        }
    }//GEN-LAST:event_mniThongKeMouseClicked

    private void mniChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniChiTietMouseClicked
        // TODO add your handling code here:
        this.xemThongTinChiTiet();
    }//GEN-LAST:event_mniChiTietMouseClicked

    private void mniDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangXuatActionPerformed
        // TODO add your handling code here:
        this.logOff();
    }//GEN-LAST:event_mniDangXuatActionPerformed

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
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBanHD;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lbldongHo;
    private javax.swing.JMenu mniBan;
    private javax.swing.JMenu mniCaLamViec;
    private javax.swing.JMenu mniChiTiet;
    private javax.swing.JMenuItem mniDangXuat;
    private javax.swing.JMenu mniKhoHang;
    private javax.swing.JMenu mniKhuVuc;
    private javax.swing.JMenu mniLoaiSanPham;
    private javax.swing.JMenu mniNhanVien;
    private javax.swing.JMenu mniSanPham;
    private javax.swing.JMenu mniThongKe;
    private javax.swing.JMenu mniThongTinUngDung;
    private javax.swing.JMenuBar mnu;
    private javax.swing.JMenu mnuHeThong;
    public static javax.swing.JPanel pnlBan;
    public static javax.swing.JPanel pnlWrapper;
    public static javax.swing.JTable tblHoaDon;
    // End of variables declaration//GEN-END:variables
}
