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
import model.HoaDonChiTiet;

/**
 *
 * @author duann
 */
public class HoaDonChiTietDAO {

    public int getMaHDByMaHDCT(int id) {
        try {
            ResultSet rs = null;
            String sql = "select MaHoaDon from HoaDonChiTiet where MaHoaDonCT = ?";
            try {
                rs = Jdbc.executeQuery(sql, id);
                while (rs.next()) {
                    return rs.getInt(1);
                }

            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return -1;
    }

    public void insert(HoaDonChiTiet model, int maHoaDon) {
        String sql = "INSERT INTO HoaDonChiTiet (MaHoaDon, SoLuongSP, MaSanPham) VALUES (?,?,?)";
        Jdbc.executeUpdate(sql,
                maHoaDon,
                model.getSoLuongSP(),
                model.getMaSanPham()
        );
    }

    public void update(HoaDonChiTiet model, int id) {
        String sql = "UPDATE HoaDonChiTiet SET SoLuongSP=?, MaSanPham= ? WHERE MaHoaDonCT=?";
        Jdbc.executeUpdate(sql,
                model.getSoLuongSP(),
                model.getMaSanPham(),
                id
        );
    }

    public void delete(int maHoaDonCT) {
        String sql = "DELETE FROM HoaDonChiTiet WHERE MaHoaDonCT=?";
        Jdbc.executeUpdate(sql, maHoaDonCT);
    }

    private HoaDonChiTiet readFormResultSet(ResultSet rs) throws SQLException {
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setMaHoaDonCT(rs.getInt(1));
        hoaDonChiTiet.setMaHoaDon(rs.getInt(2));
        hoaDonChiTiet.setSoLuongSP(rs.getInt(3));
        hoaDonChiTiet.setMaSanPham(rs.getInt(4));
        return hoaDonChiTiet;
    }

    private List<HoaDonChiTiet> select(String sql, Object... args) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    HoaDonChiTiet hoaDonChiTiet = readFormResultSet(rs);
                    list.add(hoaDonChiTiet);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<HoaDonChiTiet> select() {
        String sql = "SELECT * FROM HoaDonChiTiet";
        return select(sql);
    }

    public HoaDonChiTiet findById(int id) {
        String sql = "SELECT * FROM HoaDonChiTiet WHERE MaHoaDonCT=?";
        List<HoaDonChiTiet> list = select(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<HoaDonChiTiet> selectByKeyword(int id) {
        String sql = "SELECT * FROM HoaDonChiTiet WHERE MaHoaDonCT=?";
        List<HoaDonChiTiet> list = select(sql, "%" + id + "%");
        return list;
    }
}
