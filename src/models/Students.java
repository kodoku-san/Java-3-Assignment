package models;


/**
 *
 * @author phuho
 */
public class Students {
    private String maSV, hoTen, email, soDT, diaChi, hinhAnh;
    private int gioiTinh;
    
    public Students() {
        
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) throws Exception{
        if (hoTen.matches("-?\\d+(\\.\\d+)?")) {
            throw new Exception("Họ và tên không đúng định dạng!");
        }
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception{
        String regex = "^[a-zA-Z0-9]{1}[a-zA-Z0-9\\.]*[^\\.]@{0}([a-zA-Z0-9]+\\.+[a-zA-Z0-9]+)+$";

        if (!email.matches(regex)) {
            throw new Exception("Lỗi định dạng email");
        }
        this.email = email;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) throws Exception{
        try {
            Double.parseDouble(soDT);
        } catch (Exception e) {
            throw new Exception("Số điện thoại không được nhập chữ!");
        }
        this.soDT = soDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    
}
