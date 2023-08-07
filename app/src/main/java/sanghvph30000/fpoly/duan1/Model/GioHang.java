package sanghvph30000.fpoly.duan1.Model;

public class GioHang {
    int MaGioHang;
    int maSanPham;
    byte [] imgSP;
    String tenSP;
    int soLuong;
    double donGia;

    public GioHang(int maGioHang, int maSanPham, int soLuong, double donGia) {
        MaGioHang = maGioHang;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public GioHang() {
    }

    public GioHang(int maGioHang, int maSanPham, byte[] imgSP, String tenSP, int soLuong, double donGia) {
        MaGioHang = maGioHang;
        this.maSanPham = maSanPham;
        this.imgSP = imgSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public byte[] getImgSP() {
        return imgSP;
    }

    public void setImgSP(byte[] imgSP) {
        this.imgSP = imgSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getMaGioHang() {
        return MaGioHang;
    }

    public void setMaGioHang(int maGioHang) {
        MaGioHang = maGioHang;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }


    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}
