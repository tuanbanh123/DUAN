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
import java.util.Date;
import java.util.List;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.SanPham;

/**
 *
 * @author duann
 */
public class HoaDonDAO {

    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            String sql = "SELECT * from HoaDon JOIN HoaDonChiTiet "
                    + "ON HoaDon.MaHoaDon = HoaDonChiTiet.MaHoaDon "
                    + "JOIN SanPham on HoaDonChiTiet.MaSanPham = SanPham.MaSanPham";
            try {
                rs = Jdbc.executeQuery(sql);
                while (rs.next()) {
                    HoaDon hoaDon = new HoaDon();
                    hoaDon.setMaHoaDon(rs.getInt(1));
                    hoaDon.setMaNhanVien(rs.getInt(2));
                    hoaDon.setMaBan(rs.getInt(3));
                    hoaDon.setGhiChu(rs.getString(4));
                    hoaDon.setTrangThai(rs.getBoolean(5));
                    hoaDon.setNgayThanhToan(rs.getDate(6));
                    hoaDon.setThanhTien(rs.getFloat(7));
//                    
                    HoaDonChiTiet hdct = new HoaDonChiTiet(rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
                    hoaDon.setHoaDonChiTiet(hdct);

                    SanPham sanPham = new SanPham(rs.getInt(12), rs.getString(13), rs.getInt(14), rs.getFloat(15), rs.getBoolean(16), rs.getString(17));
                    hoaDon.setSanPham(sanPham);
                    list.add(hoaDon);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
        }
        return list;
    }

    public HoaDon getAllByMaHD(int maHD) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            String sql = "SELECT * from HoaDon JOIN HoaDonChiTiet "
                    + "ON HoaDon.MaHoaDon = HoaDonChiTiet.MaHoaDon "
                    + "JOIN SanPham on HoaDonChiTiet.MaSanPham = SanPham.MaSanPham where HoaDon.MaHoaDon = ?";
            try {
                rs = Jdbc.executeQuery(sql, maHD);
                while (rs.next()) {
                    HoaDon hoaDon = new HoaDon();
                    hoaDon.setMaHoaDon(rs.getInt(1));
                    hoaDon.setMaNhanVien(rs.getInt(2));
                    hoaDon.setMaBan(rs.getInt(3));
                    hoaDon.setGhiChu(rs.getString(4));
                    hoaDon.setTrangThai(rs.getBoolean(5));
                    hoaDon.setNgayThanhToan(rs.getDate(6));
                    hoaDon.setThanhTien(rs.getFloat(7));
//                    
                    HoaDonChiTiet hdct = new HoaDonChiTiet(rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
                    hoaDon.setHoaDonChiTiet(hdct);
                    SanPham sanPham = new SanPham(rs.getInt(12), rs.getString(13), rs.getFloat(14), rs.getBoolean(15), rs.getString(16));
                    hoaDon.setSanPham(sanPham);
                    list.add(hoaDon);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
        }
        return list.get(0);
    }

    public int getIDIdentity() {
        try {
            ResultSet rs = null;
            String sql = "SELECT IDENT_CURRENT('HoaDon')";
            try {
                rs = Jdbc.executeQuery(sql);
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

    public static List<Object[]> getHoaDonTheoBan() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            String sql = "SELECT ban.MaBan, KhuVuc.TenKhuVuc from HoaDon JOIN Ban ON HoaDon.MaBan = Ban.MaBan JOIN KhuVuc ON Ban.MaKhuVuc = KhuVuc.MaKhuVuc where ban.TrangThai = 1 and HoaDon.TrangThai = 0";
            try {
                rs = Jdbc.executeQuery(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getInt(1),
                        rs.getString(2),};
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
        }
        return list;
    }

//    phương thức trả về để lấy điều kiện thêm tiếp cho bảng hóa đơn chi tiết, tránh trường hợp bảng này lỗi bảng kia thì thêm được
    public boolean insert(HoaDon model) {
        try {
            String sql = "INSERT INTO HoaDon (MaNhanVien, MaBan, GhiChu, TrangThai, NgayThanhToan, ThanhTien) VALUES(?,?,?,?,?,?)";
            Jdbc.executeUpdate(sql,
                    model.getMaNhanVien(),
                    model.getMaBan(),
                    model.getGhiChu(),
                    model.isTrangThai(),
                    model.getNgayThanhToan(),
                    model.getThanhTien()
            );
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public boolean update(HoaDon model) {
        try {
            String sql = "UPDATE HoaDon SET MaNhanVien= ?, MaBan= ?, GhiChu= ?, TrangThai= ?, NgayThanhToan= ?, ThanhTien = ? WHERE MaHoaDon= ?";
            Jdbc.executeUpdate(sql,
                    model.getMaNhanVien(),
                    model.getMaBan(),
                    model.getGhiChu(),
                    model.isTrangThai(),
                    model.getNgayThanhToan(),
                    model.getThanhTien(),
                    model.getMaHoaDon()
            );
            System.out.println(model.getMaHoaDon());
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public void updateTrangThaiHD(int trangThai, Date ngayThanhToan, int maHD, float ThanhTien) {
        String sql = "UPDATE HoaDon SET TrangThai= ?, NgayThanhToan= ?, ThanhTien = ? WHERE MaHoaDon= ?";
        Jdbc.executeUpdate(sql, trangThai, ngayThanhToan, ThanhTien, maHD);
    }

    public void delect(int maHoaDon) {
        String sql = "DELETE FROM HoaDon WHERE MaHoaDon= ?";
        Jdbc.executeUpdate(sql, maHoaDon);
    }

    private HoaDon readFormResultSet(ResultSet rs) throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon(rs.getInt(1));
        hoaDon.setMaNhanVien(rs.getInt(2));
        hoaDon.setMaBan(rs.getInt(3));
        hoaDon.setGhiChu(rs.getString(4));
        hoaDon.setTrangThai(rs.getBoolean(5));
        hoaDon.setNgayThanhToan(rs.getDate(6));
        return hoaDon;
    }

    private List<HoaDon> select(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    HoaDon hoaDon = readFormResultSet(rs);
                    list.add(hoaDon);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<HoaDon> select() {
        String sql = "SELECT * FROM HoaDon";
        return select(sql);
    }

    public HoaDon findById(int maHoaDon) {
        String sql = "SELECT * FROM HoaDon  WHERE MaHoaDon= ?";
        List<HoaDon> list = select(sql, maHoaDon);
        return list.size() > 0 ? list.get(0) : null;
    }

//    public HoaDon findByMaBan(int maBan) {
//        String sql = "SELECT * FROM HoaDon  WHERE MaBan= ?";
//        List<HoaDon> list = select(sql, maBan);
//        return list.size() > 0 ? list.get(0) : null;
//    }
}
