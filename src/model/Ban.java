/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class Ban {

    private int maBan;
    private int maKhuvuc;
    private boolean trangThai = false;

    public Ban() {
    }

    public Ban(int maBan, int maKhuvuc) {
        this.maBan = maBan;
        this.maKhuvuc = maKhuvuc;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public int getMaKhuvuc() {
        return maKhuvuc;
    }

    public void setMaKhuvuc(int maKhuvuc) {
        this.maKhuvuc = maKhuvuc;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

}
