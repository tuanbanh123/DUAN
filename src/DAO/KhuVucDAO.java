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
import model.KhuVuc;

/**
 *
 * @author duann
 */
public class KhuVucDAO {

    public void insert(KhuVuc model) {
        String sql = "INSERT INTO KhuVuc(TenKhuVuc, ViTri, GhiChu) VALUES (?,?,?)";
        Jdbc.executeUpdate(sql,
                model.getTenKhuVuc(),
                model.getViTri(),
                model.getGhiChu());
    }

    public void update(KhuVuc model) {
        String sql = "UPDATE KhuVuc SET TenKhuVuc=?, ViTri=?, GhiChu=? WHERE MaKhuVuc=?";
        Jdbc.executeUpdate(sql,
                model.getTenKhuVuc(),
                model.getViTri(),
                model.getGhiChu(),
                model.getMaKhuVuc());
    }

    public void delete(Integer maKhuVuc) {
        String sql = "DELETE FROM KhuVuc WHERE MaKhuVuc=?";
        Jdbc.executeUpdate(sql, maKhuVuc);
    }

    private KhuVuc readFromResultSet(ResultSet rs) throws SQLException {
        KhuVuc model = new KhuVuc();
        model.setMaKhuVuc(rs.getInt(1));
        model.setTenKhuVuc(rs.getString(2));
        model.setViTri(rs.getString(3));
        model.setGhiChu(rs.getString(4));
        return model;
    }

    private List<KhuVuc> select(String sql, Object... args) {
        List<KhuVuc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    KhuVuc model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<KhuVuc> select() {
        String sql = "SELECT * FROM KhuVuc";
        return select(sql);
    }

    public KhuVuc findById(int maKhuVuc) {
        String sql = "SELECT * FROM KhuVuc WHERE MaKhuVuc=?";
        List<KhuVuc> list = select(sql, maKhuVuc);
        return list.size() > 0 ? list.get(0) : null;
    }

    public KhuVuc findByName(String tenBan) {
        String sql = "SELECT * FROM KhuVuc WHERE TenKhuVuc=?";
        List<KhuVuc> list = select(sql, tenBan);
        return list.size() > 0 ? list.get(0) : null;
    }
    public boolean check(String khuVuc) {
        String sql = "SELECT * FROM KhuVuc WHERE MaBan LIKE ?";
        List<KhuVuc> list = select(sql, khuVuc);
        return list.size() == 0;
    }
}
