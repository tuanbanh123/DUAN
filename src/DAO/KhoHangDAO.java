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
import model.KhoHang;

/**
 *
 * @author duann
 */
public class KhoHangDAO {

    public void insert(KhoHang model) {
        String sql = "INSERT INTO KhoHang( MaSanPham, NgayNhap, MaNhanVien, SoLuong, GhiChu, HanSuDung) VALUES (?,?,?,?,?,?)";
        Jdbc.executeUpdate(sql,
                model.getMaSanPham(),
                model.getNgayNhap(),
                model.getMaNhanVien(),
                model.getSoLuong(),
                model.getGhiChu(),
                model.getHanSuDung());
    }

    public void update(KhoHang model) {
        String sql = "UPDATE KhoHang SET MaSanPham=?, SoLuong=?, GhiChu=? WHERE MaKhoHang=?";
        Jdbc.executeUpdate(sql,
                model.getMaSanPham(),
                model.getSoLuong(),
                model.getGhiChu(),
                model.getMaKhoHang());
    }

    public void delete(int MaKhoHang) {
        String sql = "DELETE FROM KhoHang WHERE MaKhoHang=?";
        Jdbc.executeUpdate(sql, MaKhoHang);
    }

    private KhoHang readFormResultSet(ResultSet rs) throws SQLException {
        KhoHang khohang = new KhoHang();
        khohang.setMaKhoHang(rs.getInt(1));
        khohang.setMaSanPham(rs.getInt(2));
        khohang.setNgayNhap(rs.getDate(3));
        khohang.setMaNhanVien(rs.getInt(4));
        khohang.setSoLuong(rs.getInt(5));
        khohang.setGhiChu(rs.getString(6));
        khohang.setHanSuDung(rs.getDate(7));
        return khohang;
    }

    public List<KhoHang> select(String sql, Object... args) {
        List<KhoHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    KhoHang khohang = readFormResultSet(rs);
                    list.add(khohang);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<KhoHang> select() {
        String sql = "SELECT * FROM KhoHang ";
        return select(sql);
    }

    public KhoHang findById(int maKhoHang) {
        String sql = "SELECT * FROM KhoHang WHERE MaKhoHang=?";
        List<KhoHang> list = select(sql, maKhoHang);
        return list.size() > 0 ? list.get(0) : null;
    }

    public KhoHang findByName(String khohang) {
        String sql = "SELECT * FROM KhoHang WHERE MaKhoHang like ?";
        List<KhoHang> list = select(sql, khohang);
        return list.size() > 0 ? list.get(0) : null;
    }

//    dưới này duần làm
    public int getSLByMaSP(int maSP) {
        try {
            ResultSet rs = null;
            String sql = "SELECT SUM(SoLuong) FROM KhoHang WHERE MaSanPham = ? group by MaSanPham";
            try {
                rs = Jdbc.executeQuery(sql, maSP);
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
        }
        return 0;
    }

//    lấy mã kho hàng treo ngày nhập và lấy mã kho cũ nhất để dùng hết hàng cũ
    public int getMaKhoHangByMaSP(int maSanPham) {
        try {
            ResultSet rs = null;
            String sql = "SELECT MaKhoHang FROM KhoHang WHERE MaSanPham = ? and SoLuong > 0 ORDER BY NgayNhap ASC";
            try {
                rs = Jdbc.executeQuery(sql, maSanPham);
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    public int getMaSLHang(int maKhoHang) {
        try {
            ResultSet rs = null;
            String sql = "SELECT SoLuong FROM KhoHang WHERE MaKhoHang = ?";
            try {
                rs = Jdbc.executeQuery(sql, maKhoHang);
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    public void updateSLByMaSP(int soLuong, int maSP, int maKhoHang) {
        String sql = "update KhoHang set SoLuong = SoLuong - ? where MaSanPham = ? and MaKhoHang = ?";
        Jdbc.executeUpdate(sql, soLuong, maSP, maKhoHang);
    }

    public void themSLByMaSP(int soLuong, int maSP, int maKhoHang) {
        String sql = "update KhoHang set SoLuong = SoLuong + ? where MaSanPham = ? and MaKhoHang = ?";
        Jdbc.executeUpdate(sql, soLuong, maSP, maKhoHang);
    }

}
