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

public class CaLamViecDAO {

    public void insert(CaLamViec model) {
        String sql = "INSERT INTO CaLamViec (BatDau, KetThuc, GhiChu,TenCaLV) VALUES (?,?,?,?)";
        Jdbc.executeUpdate(sql,
                model.getBatDau(),
                model.getKetThuc(),
                model.getGhiChu(),
                model.getTenCaLamViec());
    }

    public void update(CaLamViec model) {
        String sql = "UPDATE CaLamViec SET BatDau=?, KetThuc=?, GhiChu=?, TenCaLV=? WHERE MaCaLV=?";
        Jdbc.executeUpdate(sql,
                model.getBatDau(),
                model.getKetThuc(),
                model.getGhiChu(),
                model.getTenCaLamViec(),
                model.getMaCaLamViec());
    }

    public void delete(Integer maCaLamViec) {
        String sql = "DELETE FROM CaLamViec WHERE MaCaLV=?";
        Jdbc.executeUpdate(sql, maCaLamViec);
    }
    
    private CaLamViec readFromResultSet(ResultSet rs) throws SQLException {
        CaLamViec model = new CaLamViec();
        model.setMaCaLamViec(rs.getInt(1));
        model.setBatDau(rs.getString(2));
        model.setKetThuc(rs.getString(3));
        model.setGhiChu(rs.getString(4));
        model.setTenCaLamViec(rs.getString(5));
        return model;
    }

    private List<CaLamViec> select(String sql, Object... args) {
        List<CaLamViec> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    CaLamViec model = readFromResultSet(rs);
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

    public List<CaLamViec> select() {
        String sql = "SELECT * FROM CaLamviec";
        return select(sql);
    }

    public CaLamViec findById(int maCaLV) {
        String sql = "SELECT * FROM CaLamviec WHERE MaCaLV=?";
        List<CaLamViec> list = select(sql, maCaLV);
        return list.size() > 0 ? list.get(0) : null;
    }

    public CaLamViec findByName(String tenCaLV) {
        String sql = "SELECT * FROM CaLamviec WHERE TenCaLV like ?";
        List<CaLamViec> list = select(sql, tenCaLV);
        return list.size() > 0 ? list.get(0) : null;
    }
}
