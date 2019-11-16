/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.XDate;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class KhoHang {

    private int maKhoHang;
    private int maSanPham;
    private Date ngayNhap;
    private int maNhanVien;
    private int soLuong;
    private String ghiChu;
    private Date hanSuDung = XDate.now();
    public KhoHang() {
    }

    public KhoHang(int maKhoHang, int maSanPham, Date ngayNhap, int maNhanVien, int soLuong, String ghiChu) {
        this.maKhoHang = maKhoHang;
        this.maSanPham = maSanPham;
        this.ngayNhap = ngayNhap;
        this.maNhanVien = maNhanVien;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
    }

    public int getMaKhoHang() {
        return maKhoHang;
    }

    public void setMaKhoHang(int maKhoHang) {
        this.maKhoHang = maKhoHang;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Date getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(Date hanSuDung) {
        this.hanSuDung = hanSuDung;
    }

}
