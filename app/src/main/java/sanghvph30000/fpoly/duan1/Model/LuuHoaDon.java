package sanghvph30000.fpoly.duan1.Model;

import java.util.ArrayList;

public class LuuHoaDon {
    int maLuu;
    int maHoaDon;
    int maUser;
    String tenUser;
    String userName;
    int chucVu;
    int userNamSinh;
    String tenKhachHang;
    String  trangThai;
    String NgayLapHD;
    String SDT;
    String DiaChi;
    int maSP;
    String tenSP;
    int soLuong;
    String size;
    double donGia;
    double thanhTien;
    ArrayList<GioHang> listGioHang;


    public LuuHoaDon(int maLuu, int maHoaDon,int maUser, String tenUser, String tenKhachHang,String SDT, String DiaChi, String trangThai, int maSP, String tenSP, int soLuong, double donGia, double thanhTien) {
        this.maLuu = maLuu;
        this.maHoaDon = maHoaDon;
        this.maUser = maUser;
        this.tenUser = tenUser;
        this.tenKhachHang = tenKhachHang;
        this.trangThai = trangThai;
        this.NgayLapHD = NgayLapHD;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.size = size;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
        this.listGioHang = new ArrayList<>();
    }

    public LuuHoaDon() {
    }

    public LuuHoaDon(int maLuu, String tenKhachHang, String ngayLapHD, String SDT, String diaChi, int maSP, double thanhTien) {
        this.maLuu = maLuu;
        this.tenKhachHang = tenKhachHang;
        NgayLapHD = ngayLapHD;
        this.SDT = SDT;
        DiaChi = diaChi;
        this.maSP = maSP;
        this.thanhTien = thanhTien;
    }

    public LuuHoaDon(int maLuu, int maHoaDon, String tenKhachHang, double thanhTien) {
        this.maLuu = maLuu;
        this.maHoaDon = maHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.thanhTien = thanhTien;
    }

    public LuuHoaDon(int maHoaDon, int maUser, String tenUser, String tenKhachHang, String NgayLapHD, int maSP, String tenSP, int soLuong, String size, double donGia, double thanhTien) {
        this.maHoaDon = maHoaDon;
        this.maUser = maUser;
        this.tenUser = tenUser;
        this.tenKhachHang = tenKhachHang;
        this.NgayLapHD = NgayLapHD;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.size = size;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public LuuHoaDon(int maUser, String tenUser, String userName, int chucVu, int userNamSinh, double thanhTien) {
        this.maUser = maUser;
        this.tenUser = tenUser;
        this.userName = userName;
        this.chucVu = chucVu;
        this.userNamSinh = userNamSinh;
        this.thanhTien = thanhTien;
    }

    public LuuHoaDon(int maLuu, int maHoaDon, String tenUser, String tenKhachHang, String ngayLapHD, String tenSP, int soLuong, String size, double donGia) {
        this.maLuu = maLuu;
        this.maHoaDon = maHoaDon;
        this.tenUser = tenUser;
        this.tenKhachHang = tenKhachHang;
        NgayLapHD = ngayLapHD;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.size = size;
        this.donGia = donGia;
    }

    public LuuHoaDon(int maHoaDon, int maUser, String tenUser, String ngayLapHD, int maSP, String tenSP, int soLuong, String tenKhachHang, double donGia, double thanhTien) {
    }

    public LuuHoaDon(int maHoaDon, int maUser, String tenUser, String tenKhachHang, String ngayLapHD, int maSP, String tenSP, int soLuong, double donGia, double thanhTien) {
    }

    public LuuHoaDon(int maUser, String fullName, String userName, int chucVu, String userSDT, int userNamSinh, double userDoanhThu) {
    }

    public int getMaLuu() {
        return maLuu;
    }

    public void setMaLuu(int maLuu) {
        this.maLuu = maLuu;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaUser() {
        return maUser;
    }

    public void setMaUser(int maUser) {
        this.maUser = maUser;
    }

    public String getTenUser() {
        return tenUser;
    }

    public void setTenUser(String tenUser) {
        this.tenUser = tenUser;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getNgayLapHD() {
        return NgayLapHD;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public void setNgayLapHD(String ngayLapHD) {
        NgayLapHD = ngayLapHD;
    }

    public int getMaSP() {
        return maSP;
    }



    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getChucVu() {
        return chucVu;
    }

    public void setChucVu(int chucVu) {
        this.chucVu = chucVu;
    }


    public int getUserNamSinh() {
        return userNamSinh;
    }

    public void setUserNamSinh(int userNamSinh) {
        this.userNamSinh = userNamSinh;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public ArrayList<GioHang> getListGioHang() {
        if (listGioHang == null) {
            listGioHang = new ArrayList<>();
        }
        return listGioHang;
    }

    public void setListGioHang(ArrayList<GioHang> listGioHang) {
        this.listGioHang = listGioHang;
    }

}
