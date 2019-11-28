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
import model.LoaiSanPham;

/**
 *
 * @author duann
 */
public class LoaiSanPhamDAO {

    public void insert(LoaiSanPham model) {
        String sql = "INSERT INTO LoaiSanPham (TenLoaiSp) VALUES(?)";
        Jdbc.executeUpdate(sql,
                model.getTenLoaiSP());

    }

    public void update(LoaiSanPham model) {
        String sql = "UPDATE LoaiSanPham SET TenLoaiSP=? WHERE MaLoaiSP=?";
        Jdbc.executeUpdate(sql,
                model.getTenLoaiSP(),
                model.getMaLoaiSP());
    }

    public void delete(int maLoaiSP) {
        String sql = "DELETE FROM LoaiSanPham WHERE MaLoaiSP=?";
        Jdbc.executeUpdate(sql,maLoaiSP);
    }

    private LoaiSanPham readFormResultSet(ResultSet rs) throws SQLException {
        LoaiSanPham loaisanpham = new LoaiSanPham();
        loaisanpham.setMaLoaiSP(rs.getInt(1));
        loaisanpham.setTenLoaiSP(rs.getString(2));
        return loaisanpham;
    }

    public List<LoaiSanPham> select(String sql, Object... args) {
        List<LoaiSanPham> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    LoaiSanPham loaiSanPham = readFormResultSet(rs);
                    list.add(loaiSanPham);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<LoaiSanPham> select() {
        String sql = "SELECT * FROM LoaiSanPham";
        return select(sql);
    }

    public LoaiSanPham findById(int maLoaiSP) {
        String sql = "SELECT * FROM LoaiSanPham WHERE MaLoaiSP=?";
        List<LoaiSanPham> list = select(sql, maLoaiSP);
        return list.size() > 0 ? list.get(0) : null;
    }

    public LoaiSanPham findByName(String tenLoaiSP) {
        String sql = "SELECT * FROM LoaiSanPham WHERE TenLoaiSP=?";
        List<LoaiSanPham> list = select(sql, tenLoaiSP);
        return list.size() > 0 ? list.get(0) : null;
    }

}
