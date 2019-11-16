/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import helper.Jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CaLamViec;
import model.NhanVien;

/**
 *
 * @author duann
 */
public class NhanVienDAO {

    public void insert(NhanVien model) {
        String sql = "INSERT INTO NhanVien(HoTen, MatKhau, VaiTro, NgaySinh, GioiTinh, Email, DienThoai, GhiChu, MaCaLV, SoNgayLV) VALUES (?,?,?,?,?,?,?,?,?,?)";
        Jdbc.executeUpdate(sql,
                model.getHoTen(),
                model.getMatKhau(),
                model.isVaiTro(),
                model.getNgaySinh(),
                model.isGioiTinh(),
                model.getEmail(),
                model.getDienThoai(),
                model.getGhiChu(),
                model.getMaCaLamViec(),
                model.getSoNgayLamViec()
        );
    }

    public void update(NhanVien model) {
        String sql = "UPDATE NhanVien SET HoTen=?, MatKhau=?, VaiTro=?, NgaySinh=?, GioiTinh=?, Email=?, DienThoai=?, GhiChu=?, MaCaLV=?, SoNgayLV=? WHERE MaNhanVien=?";
        Jdbc.executeUpdate(sql,
                model.getHoTen(),
                model.getMatKhau(),
                model.isVaiTro(),
                model.getNgaySinh(),
                model.isGioiTinh(),
                model.getEmail(),
                model.getDienThoai(),
                model.getGhiChu(),
                model.getMaCaLamViec(),
                model.getSoNgayLamViec(),
                model.getMaNhanVien()
        );
    }

    public void diemDanh(NhanVien model) {
        String sql = "UPDATE NhanVien SET SoNgayLV = ? WHERE MaNhanVien = ?";
        Jdbc.executeUpdate(sql,
                model.getSoNgayLamViec(),
                model.getMaNhanVien()
        );
    }

    public void delete(int maNhanVien) {
        String sql = "DELETE FROM NhanVien WHERE MaNhanVien=?";
        Jdbc.executeUpdate(sql, maNhanVien);
    }

    private NhanVien readFormResultSet(ResultSet rs) throws SQLException {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNhanVien(rs.getInt(1));
        nhanVien.setHoTen(rs.getString(2));
        nhanVien.setMatKhau(rs.getString(3));
        nhanVien.setVaiTro(rs.getBoolean(4));
        nhanVien.setNgaySinh(rs.getDate(5));
        nhanVien.setGioiTinh(rs.getBoolean(6));
        nhanVien.setEmail(rs.getString(7));
        nhanVien.setDienThoai(rs.getString(8));
        nhanVien.setGhiChu(rs.getString(9));
        nhanVien.setMaCaLamViec(rs.getInt(10));
        nhanVien.setSoNgayLamViec(rs.getInt(11));
//      Thêm ca làm việc để bên bảng xuất hiện tên ca làm việc thay vì mã ca làm việc, giúp cho người sử dụng phần mềm tiện theo dõi.
        CaLamViec caLamViec = new CaLamViec();
        caLamViec.setMaCaLamViec(rs.getInt(12));
        caLamViec.setBatDau(rs.getString(13));
        caLamViec.setKetThuc(rs.getString(14));
        caLamViec.setGhiChu(rs.getString(15));
        caLamViec.setTenCaLamViec(rs.getString(16));
        nhanVien.setCaLamViec(caLamViec);
        return nhanVien;
    }

    public List<NhanVien> select(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    NhanVien nhanVien = readFormResultSet(rs);
                    list.add(nhanVien);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<NhanVien> select() {
        String sql = "SELECT * from NhanVien join CaLamViec on NhanVien.MaCaLV = CaLamViec.MaCaLV";
        return select(sql);
    }

    public NhanVien findById(int maNhanVien) {
        String sql = "SELECT * from NhanVien join CaLamViec on NhanVien.MaCaLV = CaLamViec.MaCaLV WHERE NhanVien.MaNhanVien = ?";
        List<NhanVien> list = select(sql, maNhanVien);
        return list.size() > 0 ? list.get(0) : null;
    }

    public NhanVien findByName(String hoTen) {
        String sql = "SELECT * from NhanVien join CaLamViec on NhanVien.MaCaLV = CaLamViec.MaCaLV WHERE NhanVien.HoTen like ?";
        List<NhanVien> list = select(sql, "%" + hoTen + "%");
        return list.size() > 0 ? list.get(0) : null;
    }

    public NhanVien findByEmail(String email) {
        String sql = "SELECT * FROM NhanVien Where Email like ?";
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, email);
                while (rs.next()) {
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setMaNhanVien(rs.getInt(1));
                    nhanVien.setHoTen(rs.getString(2));
                    nhanVien.setMatKhau(rs.getString(3));
                    nhanVien.setVaiTro(rs.getBoolean(4));
                    nhanVien.setNgaySinh(rs.getDate(5));
                    nhanVien.setGioiTinh(rs.getBoolean(6));
                    nhanVien.setEmail(rs.getString(7));
                    nhanVien.setDienThoai(rs.getString(8));
                    nhanVien.setGhiChu(rs.getString(9));
                    nhanVien.setMaCaLamViec(rs.getInt(10));
                    nhanVien.setSoNgayLamViec(rs.getInt(11));
                    return nhanVien;
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
