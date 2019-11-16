/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class CaLamViec {

    private int maCaLamViec;
    private String batDau;
    private String ketThuc;
    private String ghiChu;
    private String tenCaLamViec;

    public CaLamViec() {
    }

    public CaLamViec(int maCaLamViec, String batDau, String ketThuc, String ghiChu, String tenCaLamViec) {
        this.maCaLamViec = maCaLamViec;
        this.batDau = batDau;
        this.ketThuc = ketThuc;
        this.ghiChu = ghiChu;
        this.tenCaLamViec = tenCaLamViec;
    }

    public int getMaCaLamViec() {
        return maCaLamViec;
    }

    public void setMaCaLamViec(int maCaLamViec) {
        this.maCaLamViec = maCaLamViec;
    }

    public String getBatDau() {
        return batDau;
    }

    public void setBatDau(String batDau) {
        this.batDau = batDau;
    }

    public String getKetThuc() {
        return ketThuc;
    }

    public void setKetThuc(String ketThuc) {
        this.ketThuc = ketThuc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTenCaLamViec() {
        return tenCaLamViec;
    }

    public void setTenCaLamViec(String tenCaLamViec) {
        this.tenCaLamViec = tenCaLamViec;
    }

}
