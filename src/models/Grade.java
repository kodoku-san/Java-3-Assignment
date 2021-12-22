/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author phuho
 */
public class Grade {
    private String hoTen, maSV;
    private double tiengAnh, tinHoc, GDTC, TB;
    
    public Grade() {
        
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public double getTiengAnh() {
        return Math.floor(tiengAnh * 10) / 10;
    }

    public void setTiengAnh(double tiengAnh) throws Exception{
        if(tiengAnh > 10 || tiengAnh < 0) {
            throw new Exception("Vui lòng nhập điểm từ 0-10");
        }
        
        this.tiengAnh = tiengAnh;
    }

    public double getTinHoc() {
        return Math.floor(tinHoc * 10) / 10;
    }

    public void setTinHoc(double tinHoc) throws Exception{
        if(tinHoc > 10 || tinHoc < 0) {
            throw new Exception("Vui lòng nhập điểm từ 0-10");
        }
        this.tinHoc = tinHoc;
    }

    public double getGDTC() {
        return Math.floor(GDTC * 10) / 10;
    }

    public void setGDTC(double GDTC) throws Exception{
        if(GDTC > 10 || GDTC < 0) {
            throw new Exception("Vui lòng nhập điểm từ 0-10");
        }
        this.GDTC = GDTC;
    }

    public double getTB() {
        return Math.floor(TB * 10) / 10;
    }

    public void setTB(double TB) {
        this.TB = TB;
    }
    
    
}
