/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import helper.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duann
 */
public class DanhMucDAO {

    public int getMaHDCTByBanAndHD(int maBan, int maHD) {
        try {
            ResultSet rs = null;
            String sql = "select MaHoaDonCT from HoaDon join HoaDonChiTiet on "
                    + "HoaDon.MaHoaDon = HoaDonChiTiet.MaHoaDon where "
                    + "HoaDon.MaBan = ? and HoaDon.MaHoaDon = ?";
            try {
                rs = Jdbc.executeQuery(sql, maBan, maHD);
                while (rs.next()) {
                    return rs.getInt(1);
                }

            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 0;
    }

    public List<Object[]> getDanhSachSPTheoBan(int maBan) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            String sql = "select HoaDonChiTiet.MaHoaDonCT, SanPham.TenSanPham, LoaiSanPham.TenLoaiSP, SanPham.GiaBan, HoaDonChiTiet.SoLuongSP, \n"
                    + "(SanPham.GiaBan*HoaDonChiTiet.SoLuongSP) as 'ThanhTien' from Ban join HoaDon on Ban.MaBan = \n"
                    + "HoaDon.MaBan join HoaDonChiTiet on HoaDon.MaHoaDon = HoaDonChiTiet.MaHoaDon join SanPham on \n"
                    + "HoaDonChiTiet.MaSanPham = SanPham.MaSanPham join LoaiSanPham on SanPham.MaLoaiSP = \n"
                    + "LoaiSanPham.MaLoaiSP where Ban.MaBan = ? and Ban.TrangThai = 1 and HoaDon.TrangThai = 0";
            try {
                rs = Jdbc.executeQuery(sql, maBan);
                while (rs.next()) {
                    Object[] model = {
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getInt(5),
                        rs.getFloat(6),};
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
        }
        return list;
    }

    public float getThanhToanTheoBan(int maBan) {
        try {
            ResultSet rs = null;
            String sql = "SELECT Sum(HoaDon.ThanhTien) from HoaDon JOIN Ban ON "
                    + "HoaDon.MaBan = Ban.MaBan where ban.TrangThai = 1 and "
                    + "HoaDon.TrangThai = 0 and Ban.maBan = ?";
            try {
                rs = Jdbc.executeQuery(sql, maBan);
                while (rs.next()) {
                    return rs.getFloat(1);
                }

            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 0;
    }
}
