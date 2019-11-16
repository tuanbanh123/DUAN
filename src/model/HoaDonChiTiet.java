/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author duann
 */
public class HoaDonChiTiet {

    private int maHoaDonCT;
    private int maHoaDon;
    private int soLuongSP;
    private int maSanPham;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int maHoaDonCT, int maHoaDon, int soLuongSP, int maSanPham) {
        this.maHoaDonCT = maHoaDonCT;
        this.maHoaDon = maHoaDon;
        this.soLuongSP = soLuongSP;
        this.maSanPham = maSanPham;
    }

    public int getMaHoaDonCT() {
        return maHoaDonCT;
    }

    public void setMaHoaDonCT(int maHoaDonCT) {
        this.maHoaDonCT = maHoaDonCT;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

}
