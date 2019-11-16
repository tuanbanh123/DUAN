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

/**
 *
 * @author duann
 */
public class ThongKeDAO {
    
   
    public List<Object[]> getKhoHang() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeKhoHang}";
                rs = Jdbc.executeQuery(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getInt(1),
                        rs.getInt(2),
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
   
    public List<Object[]> getDoanhThuNam() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeDoanhThu}";
                rs = Jdbc.executeQuery(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getInt(1),
                        rs.getInt(2)
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Object[]> getDoanhThuThang() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeDoanhThuTheoThang}";
                rs = Jdbc.executeQuery(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getDate(1),
                        rs.getInt(2)
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
