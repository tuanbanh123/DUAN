/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *

 * @author Admin
 */
public class SanPham {

    private int maSanPham;
    private String tenSanPham;
    private int maLoaiSP;
    private Float giaBan;
    private Boolean trangThai;
    private String ghiChu;

    public SanPham() {
    }

    public SanPham(int maSanPham, String tenSanPham, int maLoaiSP, Float giaBan, Boolean trangThai, String ghiChu) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.maLoaiSP = maLoaiSP;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(int maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public Float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Float giaBan) {
        this.giaBan = giaBan;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
