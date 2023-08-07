package sanghvph30000.fpoly.duan1.Model;

public class SanPham {
    int id;
    byte [] image;
    String TenSanPham;
    Double DonGia;
    int MaLoai;
    String Mota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SanPham(int id) {
        this.id = id;
    }

    public SanPham(int id, byte[] image, String tenSanPham, Double donGia, int maLoai, String mota) {
        this.id = id;
        this.image = image;
        TenSanPham = tenSanPham;
        DonGia = donGia;
        MaLoai = maLoai;
        Mota = mota;
    }

    public SanPham() {

    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public Double getDonGia() {
        return DonGia;
    }

    public void setDonGia(Double donGia) {
        DonGia = donGia;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }
}
