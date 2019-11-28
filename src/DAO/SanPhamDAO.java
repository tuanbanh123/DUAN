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
import model.SanPham;

/**
 *
 * @author duann
 */
public class SanPhamDAO {

    public void insert(SanPham model) {
        String sql = "INSERT INTO SanPham (TenSanPham, MaLoaiSP, GiaBan, TrangThai, GhiChu) VALUES (?,?,?,?,?)";
        Jdbc.executeUpdate(sql,
                model.getTenSanPham(),
                model.getMaLoaiSP(),
                model.getGiaBan(),
                model.getTrangThai(),
                model.getGhiChu());

    }

    public void update(SanPham model) {
        String sql = "UPDATE SanPham SET TenSanPham=?, MaLoaiSP=?, GiaBan=?, GhiChu=? WHERE MaSanPham=?";
        Jdbc.executeUpdate(sql,
                model.getTenSanPham(),
                model.getMaLoaiSP(),
                model.getGiaBan(),
                model.getGhiChu(),
                model.getMaSanPham());

    }

    public void delete(int maSanPham) {
        String sql = "DELETE FROM SanPham WHERE MaSanPham=?";
        Jdbc.executeUpdate(sql, maSanPham);
    }

    private SanPham readFormResultSet(ResultSet rs) throws SQLException {
        SanPham sanpham = new SanPham();
        sanpham.setMaSanPham(rs.getInt(1));
        sanpham.setTenSanPham(rs.getString(2));
        sanpham.setMaLoaiSP(rs.getInt(3));
        sanpham.setGiaBan(rs.getFloat(4));
        sanpham.setTrangThai(rs.getBoolean(5));
        sanpham.setGhiChu(rs.getString(6));
        return sanpham;
    }

    public List<SanPham> select(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    SanPham sanpham = readFormResultSet(rs);
                    list.add(sanpham);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<SanPham> select() {
        String sql = "SELECT * FROM SanPham";
        return select(sql);
    }

    public SanPham findById(int maSanPham) {
        String sql = "SELECT * FROM SanPham WHERE MaSanPham=?";
        List<SanPham> list = select(sql, maSanPham);
        return list.size() > 0 ? list.get(0) : null;
    }

    public SanPham findByName(String sanpham) {
        String sql = "SELECT * FROM SanPham WHERE TenSanPham like ?";
        List<SanPham> list = select(sql, sanpham);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<SanPham> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM SanPham WHERE TenSanPham LIKE ?";
        return select(sql, "%" + keyword + "%");
    }

}
