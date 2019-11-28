/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.CaLamViecDAO;
import DAO.NhanVienDAO;
import helper.DialogHelper;
import helper.ShareHelper;
import helper.XDate;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.CaLamViec;
import model.NhanVien;

/**
 *
 * @author duann
 */
public class NhanVienJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NhanVienJFrame
     */
    NhanVienDAO dao = new NhanVienDAO();
    ButtonGroup grGioiTinh;
    ButtonGroup grVaiTro;
    CaLamViecDAO clvdao = new CaLamViecDAO();
    int index = 0;

    public NhanVienJFrame() {
        initComponents();
        init();
    }

    void init() {
        this.setLocationRelativeTo(null);
        setIconImage(ShareHelper.APP_ICON);
        grGioiTinh = new ButtonGroup();
        grGioiTinh.add(rdoNam);
        grGioiTinh.add(rdoNu);

        grVaiTro = new ButtonGroup();
        grVaiTro.add(rdoQuanLy);
        grVaiTro.add(rdoNhanVien);
        fillComboboxCaLV();
        this.load();
        this.clear();
    }

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        try {
            List<NhanVien> list = dao.select();
            for (NhanVien nhanVien : list) {
                Object[] row = {
                    nhanVien.getMaNhanVien(),
                    nhanVien.getHoTen(),
                    XDate.toString(nhanVien.getNgaySinh()),
                    nhanVien.getDienThoai(),
                    nhanVien.isGioiTinh() ? "Nữ" : "Nam",
                    nhanVien.getEmail(),
                    nhanVien.getMatKhau(),
                    nhanVien.isVaiTro() ? "Nhân viên" : "Quản lý",
                    nhanVien.getCaLamViec().getTenCaLamViec()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
        }
    }

    void fillComboboxCaLV() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboCaLamViec.getModel();
        model.removeAllElements();
        try {
            List<CaLamViec> list = clvdao.select();
            for (CaLamViec caLamViec : list) {
                model.addElement(caLamViec.getTenCaLamViec());
            }
        } catch (Exception e) {
        }
    }

    NhanVien getModel() {
        NhanVien nhanVien = new NhanVien();
        try {
            nhanVien.setMaNhanVien(Integer.parseInt(txtHoTen.getToolTipText()));
        } catch (Exception e) {
        }
        nhanVien.setHoTen(txtHoTen.getText());
        nhanVien.setMatKhau(txtMatKhau.getText());
        nhanVien.setGioiTinh(rdoNu.isSelected());
        nhanVien.setVaiTro(rdoNhanVien.isSelected());
        nhanVien.setNgaySinh(XDate.toDate(txtNgaySinh.getText()));
        nhanVien.setEmail(txtEmail.getText());
        nhanVien.setDienThoai(txtDienThoai.getText());
        nhanVien.setGhiChu(txtGhiChu.getText());
        nhanVien.setMaCaLamViec(clvdao.findByName((String) cboCaLamViec.getSelectedItem()).getMaCaLamViec());
        nhanVien.setSoNgayLamViec(0);
        return nhanVien;
    }

    void setModel(NhanVien model) {
        try {
            cboCaLamViec.setSelectedItem(model.getCaLamViec().getTenCaLamViec());
        } catch (Exception e) {
            cboCaLamViec.setSelectedIndex(0);
        }
        txtHoTen.setToolTipText(String.valueOf(model.getMaNhanVien()));
        txtHoTen.setText(model.getHoTen());
        txtNgaySinh.setText(XDate.toString(model.getNgaySinh()));
        txtDienThoai.setText(model.getDienThoai());

        rdoNam.setSelected(!model.isGioiTinh());
        rdoNu.setSelected(model.isGioiTinh());

        txtEmail.setText(model.getEmail());
        txtMatKhau.setText(model.getMatKhau());
        txtMatKhauXacNhan.setText(model.getMatKhau());

        rdoNhanVien.setSelected(model.isVaiTro());
        rdoQuanLy.setSelected(!model.isVaiTro());

        txtGhiChu.setText(model.getGhiChu());
    }

    void setStatus(boolean insertTable) {
        try {
            btnThem.setEnabled(insertTable);
            btnSua.setEnabled(!insertTable);
            btnXoa.setEnabled(!insertTable);

            boolean first = this.index > 0;
            boolean last = this.index < tblNhanVien.getRowCount() - 1;
            btnFirst.setEnabled(!insertTable && first);
            btnPrev.setEnabled(!insertTable && first);
            btnNext.setEnabled(!insertTable && last);
            btnLast.setEnabled(!insertTable && last);
        } catch (Exception e) {
        }
    }

    void fillToForm() {
        try {
            Integer maNhanVien = (Integer) tblNhanVien.getValueAt(this.index, 0);
            NhanVien model = dao.findById(maNhanVien);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    void clear() {
        NhanVien model = new NhanVien();
        this.setModel(model);
        setStatus(true);
        rdoNam.setSelected(true);
        rdoNhanVien.setSelected(true);
        cboCaLamViec.setSelectedIndex(0);
        txtNgaySinh.setText("");
    }

    private void insert() {
        NhanVien model = getModel();
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHelper.setInfinity(lblMsg, "Thêm mới thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm mới thất bại!");
        }
    }

    void update() {
        NhanVien model = getModel();
        try {
            dao.update(model);
            this.load();
            DialogHelper.setInfinity(lblMsg, "Cập nhật thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập nhật thất bại!");
            System.out.println("error: " + e.toString());
        }
    }

    void delete() {
        if (DialogHelper.confirm(this, "Bạn có thực sự muốn xóa nhân viên này?")) {
            Integer maNhanVien = Integer.valueOf(txtHoTen.getToolTipText());
            try {
                dao.delete(maNhanVien);
                this.load();
                this.clear();
                DialogHelper.setInfinity(lblMsg, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại!");
                System.out.println(e.toString());
            }
        }
    }

    private boolean check() {
        boolean check = true;

        if (txtHoTen.getText().isEmpty()) {
            DialogHelper.alert(this, "Tên nhân viên không được để trống!");
            check = false;
        }

        if (txtNgaySinh.getText().isEmpty()) {
            DialogHelper.alert(this, "Ngày sinh không được để trống!");
            check = false;
        } else {
            Date ngaysinh = null;
            try {
                ngaysinh = XDate.toDate(txtNgaySinh.getText());
            } catch (RuntimeException ex) {
                DialogHelper.alert(this, "Nhập sai định dạng ngày!");
                check = false;
            }
        }

        if (txtDienThoai.getText().length() == 10 || txtDienThoai.getText().length() == 11) {
            try {
                Integer.parseInt(txtDienThoai.getText());
            } catch (Exception e) {
                DialogHelper.alert(this, "Số điện thoại phải là số.");
                check = false;
            }
        } else {
            DialogHelper.alert(this, "Số điện thoại 10 hoặc 11 số.");
            check = false;
        }

        if (txtEmail.getText().isEmpty()) {
            DialogHelper.alert(this, "Email không được để trống!");
            check = false;
        } else if (!txtEmail.getText().contains("@")) {
            DialogHelper.alert(this, "Email không đúng định dạng email!");
            check = false;
        }

        if (txtMatKhau.getPassword().length < 3) {
            DialogHelper.alert(this, "Mật khẩu ít nhất 3 ký tự!");
            check = false;
        }

        if (!txtMatKhau.getText().equals(txtMatKhauXacNhan.getText())) {
            DialogHelper.alert(this, "Xác nhận mật khẩu không đúng");
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

        pnlWrapper = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        pnlThongTinNhanVien = new javax.swing.JPanel();
        pnlThongTin = new javax.swing.JPanel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtEmail = new javax.swing.JTextField();
        rdoNhanVien = new javax.swing.JRadioButton();
        rdoQuanLy = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        lblMatKhau = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        lblVaiTro = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        lblGhiChu = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        cboCaLamViec = new javax.swing.JComboBox<>();
        btnMoi = new javax.swing.JButton();
        txtHoTen = new javax.swing.JTextField();
        btnLast = new javax.swing.JButton();
        txtNgaySinh = new javax.swing.JTextField();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lblCaLamViec = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblDienThoai = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtDienThoai = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JPasswordField();
        lblMatKhauXacNhan = new javax.swing.JLabel();
        txtMatKhauXacNhan = new javax.swing.JPasswordField();
        lblMsg = new javax.swing.JLabel();
        pnlBangNhanVien = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý nhân viên");

        lblTieuDe.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("QUẢN LÝ NHÂN VIÊN");

        rdoNam.setText("Nam");

        rdoNu.setText("Nữ");

        rdoNhanVien.setText("Nhân viên");

        rdoQuanLy.setText("Quản lý");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        lblMatKhau.setText("Mật khẩu");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        lblVaiTro.setText("Vai trò");

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        lblGhiChu.setText("Ghi chú");

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        cboCaLamViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCaLamViecActionPerformed(evt);
            }
        });

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnLast.setText(">>");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

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

        lblCaLamViec.setText("Ca làm việc");

        lblHoTen.setText("Tên nhân viên");

        lblNgaySinh.setText("Ngày sinh");

        lblDienThoai.setText("Điện thoại");

        lblGioiTinh.setText("Giới tính");

        lblEmail.setText("Email");

        lblMatKhauXacNhan.setText("Xác nhận mật khẩu");

        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlThongTinLayout = new javax.swing.GroupLayout(pnlThongTin);
        pnlThongTin.setLayout(pnlThongTinLayout);
        pnlThongTinLayout.setHorizontalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(lblCaLamViec)
                        .addGap(71, 71, 71)
                        .addComponent(cboCaLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(lblHoTen)
                        .addGap(57, 57, 57)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(lblGioiTinh)
                        .addGap(87, 87, 87)
                        .addComponent(rdoNam)
                        .addGap(86, 86, 86)
                        .addComponent(rdoNu))
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(lblVaiTro)
                        .addGap(91, 91, 91)
                        .addComponent(rdoNhanVien)
                        .addGap(60, 60, 60)
                        .addComponent(rdoQuanLy))
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btnThem)
                        .addGap(11, 11, 11)
                        .addComponent(btnSua)
                        .addGap(9, 9, 9)
                        .addComponent(btnXoa)
                        .addGap(9, 9, 9)
                        .addComponent(btnMoi)
                        .addGap(171, 171, 171)
                        .addComponent(btnFirst)
                        .addGap(11, 11, 11)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(btnLast))
                    .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlThongTinLayout.createSequentialGroup()
                            .addGap(145, 145, 145)
                            .addComponent(lblGhiChu)
                            .addGap(87, 87, 87)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlThongTinLayout.createSequentialGroup()
                            .addGap(144, 144, 144)
                            .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblEmail)
                                .addComponent(lblMatKhau)
                                .addComponent(lblMatKhauXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(25, 25, 25)
                            .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEmail)
                                .addComponent(txtMatKhau)
                                .addComponent(txtMatKhauXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))))
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addComponent(lblNgaySinh)
                                .addGap(78, 78, 78)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addComponent(lblDienThoai)
                                .addGap(76, 76, 76)
                                .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlThongTinLayout.setVerticalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblCaLamViec))
                    .addComponent(cboCaLamViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblHoTen))
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblNgaySinh))
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblDienThoai))
                    .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblGioiTinh))
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addGap(7, 7, 7)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblEmail))
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblMatKhau))
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMatKhauXacNhan)
                    .addComponent(txtMatKhauXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblVaiTro))
                    .addComponent(rdoNhanVien)
                    .addComponent(rdoQuanLy))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGhiChu)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnMoi)
                    .addComponent(btnFirst)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlThongTinNhanVienLayout = new javax.swing.GroupLayout(pnlThongTinNhanVien);
        pnlThongTinNhanVien.setLayout(pnlThongTinNhanVienLayout);
        pnlThongTinNhanVienLayout.setHorizontalGroup(
            pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlThongTinNhanVienLayout.setVerticalGroup(
            pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                .addComponent(pnlThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabs.addTab("Thông tin nhân viên", pnlThongTinNhanVien);

        tblNhanVien.setAutoCreateRowSorter(true);
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại", "Giới tính", "Email", "Mật khẩu", "Vai trò", "Ca làm việc"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.setGridColor(new java.awt.Color(255, 255, 255));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhanVien);

        javax.swing.GroupLayout pnlBangNhanVienLayout = new javax.swing.GroupLayout(pnlBangNhanVien);
        pnlBangNhanVien.setLayout(pnlBangNhanVienLayout);
        pnlBangNhanVienLayout.setHorizontalGroup(
            pnlBangNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBangNhanVienLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        pnlBangNhanVienLayout.setVerticalGroup(
            pnlBangNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBangNhanVienLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        tabs.addTab("Nhân viên", pnlBangNhanVien);

        javax.swing.GroupLayout pnlWrapperLayout = new javax.swing.GroupLayout(pnlWrapper);
        pnlWrapper.setLayout(pnlWrapperLayout);
        pnlWrapperLayout.setHorizontalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlWrapperLayout.setVerticalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblTieuDe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
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
            .addComponent(pnlWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.index = tblNhanVien.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                tabs.setSelectedIndex(0);
                this.fillToForm();
            }
        }

    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void cboCaLamViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCaLamViecActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboCaLamViecActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (check()) {
            insert();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (check()) {
            update();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        this.fillToForm();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        this.index--;
        this.fillToForm();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        this.index++;
        this.fillToForm();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.index = tblNhanVien.getRowCount() - 1;
        this.fillToForm();
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
            java.util.logging.Logger.getLogger(NhanVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVienJFrame().setVisible(true);
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
    private javax.swing.JComboBox<String> cboCaLamViec;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCaLamViec;
    private javax.swing.JLabel lblDienThoai;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblMatKhauXacNhan;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JPanel pnlBangNhanVien;
    private javax.swing.JPanel pnlThongTin;
    private javax.swing.JPanel pnlThongTinNhanVien;
    private javax.swing.JPanel pnlWrapper;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoQuanLy;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtDienThoai;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JPasswordField txtMatKhauXacNhan;
    private javax.swing.JTextField txtNgaySinh;
    // End of variables declaration//GEN-END:variables

}
