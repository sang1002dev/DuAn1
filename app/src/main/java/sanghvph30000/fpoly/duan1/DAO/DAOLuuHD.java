package sanghvph30000.fpoly.duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.ArrayList;

import sanghvph30000.fpoly.duan1.Database.DbHelper;
import sanghvph30000.fpoly.duan1.Model.LuuHoaDon;

public class DAOLuuHD {

    private SQLiteDatabase database;
    DbHelper dbHelper;

    //    Khởi tạo Constructor
    public DAOLuuHD(Context context){
        dbHelper = new DbHelper(context, "DuAn1", null, 1);
        database = dbHelper.getWritableDatabase();
        database = dbHelper.getReadableDatabase();
    }

//    Thêm Lưu Hóa đơn
    public boolean addLuuHD(LuuHoaDon luuHoaDon){
        ContentValues values = new ContentValues();
        values.put("maHoaDon", luuHoaDon.getMaHoaDon());
        values.put("maUser", luuHoaDon.getMaUser());
        values.put("tenUser", luuHoaDon.getTenUser());
        values.put("tenKhachHang", luuHoaDon.getTenKhachHang());
        values.put("NgayLapHD", luuHoaDon.getNgayLapHD());
        values.put("SDT", luuHoaDon.getSDT());
        values.put("DiaChi", luuHoaDon.getDiaChi());
        values.put("maSP", luuHoaDon.getMaSP());
        values.put("tenSP", luuHoaDon.getTenSP());
        values.put("soLuong", luuHoaDon.getSoLuong());
        values.put("donGia", luuHoaDon.getDonGia());
        values.put("thanhTien", luuHoaDon.getThanhTien());
        long check = database.insert("LuuHoaDon", null, values);
        Log.d("TAG", "addLuuHD: data insert " + values.toString() );
        // b2 fix here
        if (check == -1){
            return false;
        }
        else {
            return true;
        }
    }

//    Thống kê nhân viên và doanh thu theo từng nhân viên
    public ArrayList<LuuHoaDon> tkNhanVien(){
        ArrayList<LuuHoaDon> listTKNV = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT User.MaUser, User.fullname, User.username, User.ChucVu, User.SDT, User.namsinh, SUM(LuuHoaDon.soluong * LuuHoaDon.dongia) as doanhThu FROM User left JOIN LuuHoaDon on User.MaUser = LuuHoaDon.mauser GROUP BY User.MaUser", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                int maUser = cursor.getInt(0);
                String fullName = cursor.getString(1);
                String userName = cursor.getString(2);
                int chucVu = cursor.getInt(3);
                String userSDT = cursor.getString(4);
                int userNamSinh = cursor.getInt(5);
                double userDoanhThu = cursor.getDouble(6);
                listTKNV.add(new LuuHoaDon(maUser, fullName, userName, chucVu, userSDT, userNamSinh, userDoanhThu));
            }   while (cursor.moveToNext());
        }
        return listTKNV;
    }

//    Lấy tổng doanh thu theo khoảng thời gian
    public double getTongDoanhThu(String dStart, String dEnd, int caseTK, int maUserInput){
        double tongDT = 0;
        Cursor cursor = null;
        if (caseTK == 2){
            cursor = database.rawQuery("SELECT sum(LuuHoaDon.soluong * LuuHoaDon.dongia) FROM LuuHoaDon WHERE NgayLapHD BETWEEN ? AND ?;", new String[]{dStart, dEnd});
        }
        else {
            cursor = database.rawQuery("SELECT sum(LuuHoaDon.soluong * LuuHoaDon.dongia) FROM LuuHoaDon WHERE NgayLapHD BETWEEN ? AND ? AND LuuHoaDon.maUser = ?;", new String[]{dStart, dEnd, String.valueOf(maUserInput)});
        }
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                tongDT += cursor.getDouble(0);
            }   while (cursor.moveToNext());
        }

        return tongDT;
    }

    //    Lấy tổng doanh thu theo khoảng thời gian
    public double getAllDoanhThu(int caseTK, int maUserInput){
        double tongDT = 0;
        Cursor cursor = null;
        if (caseTK == 2){
            cursor = database.rawQuery("SELECT sum(LuuHoaDon.soluong * LuuHoaDon.dongia) FROM LuuHoaDon;", null);
        }
        else {
            cursor = database.rawQuery("SELECT sum(LuuHoaDon.soluong * LuuHoaDon.dongia) FROM LuuHoaDon WHERE LuuHoaDon.maUser = ?;", new String[]{String.valueOf(maUserInput)});
        }
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                tongDT += cursor.getDouble(0);
            }   while (cursor.moveToNext());
        }

        return tongDT;
    }

//    Lấy danh sách Hóa đơn + Doanh thu theo mã hóa đơn
//    Lấy doanh thu theo khoảng thời gian
    public ArrayList<LuuHoaDon> getDSHoaDon(String dStart, String dEnd, int caseTk, int maUserInput){
        ArrayList<LuuHoaDon> listHD = new ArrayList<>();
        Cursor cursor = null;
        if (caseTk == 2){
            cursor = database.rawQuery("SELECT LuuHoaDon.maLuu, LuuHoaDon.mahoadon, LuuHoaDon.tenkhachhang, sum(LuuHoaDon.soluong * LuuHoaDon.dongia) " +
                    "FROM LuuHoaDon WHERE NgayLapHD BETWEEN ? AND ? " +
                    "GROUP BY LuuHoaDon.maHoaDon;", new String[]{dStart, dEnd});
        }
        else {
            cursor = database.rawQuery("SELECT LuuHoaDon.maLuu, LuuHoaDon.mahoadon, LuuHoaDon.tenkhachhang, sum(LuuHoaDon.soluong * LuuHoaDon.dongia) " +
                    "FROM LuuHoaDon WHERE NgayLapHD BETWEEN ? AND ?  AND LuuHoaDon.maUser = ? " +
                    "GROUP BY LuuHoaDon.maHoaDon;", new String[]{dStart, dEnd, String.valueOf(maUserInput)});
        }
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                int maLuu = cursor.getInt(0);
                int maHoaDon = cursor.getInt(1);
                String tenKH = cursor.getString(2);
                double thanhTien = cursor.getDouble(3);
                listHD.add(new LuuHoaDon(maLuu, maHoaDon, tenKH, thanhTien));
            }   while (cursor.moveToNext());
        }
        return listHD;
    }

    //    Lấy danh sách tất cả Hóa đơn + Doanh thu theo mã hóa đơn
    public ArrayList<LuuHoaDon> getAllHoaDon(int caseTk, int maUserInput){
        ArrayList<LuuHoaDon> listHD = new ArrayList<>();
        Cursor cursor = null;
        if (caseTk == 2){
            cursor = database.rawQuery("SELECT LuuHoaDon.maLuu, LuuHoaDon.mahoadon, LuuHoaDon.tenkhachhang, sum(LuuHoaDon.soluong * LuuHoaDon.dongia) FROM LuuHoaDon GROUP BY LuuHoaDon.maHoaDon;", null);
        }
        else {
            cursor = database.rawQuery("SELECT LuuHoaDon.maLuu, LuuHoaDon.mahoadon, LuuHoaDon.tenkhachhang, sum(LuuHoaDon.soluong * LuuHoaDon.dongia)" +
                    "FROM LuuHoaDon WHERE LuuHoaDon.maUser = ? " +
                    "GROUP BY LuuHoaDon.maHoaDon;", new String[]{String.valueOf(maUserInput)});
        }
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                int maLuu = cursor.getInt(0);
                int maHoaDon = cursor.getInt(1);
                String tenKH = cursor.getString(2);
                double thanhTien = cursor.getDouble(3);
                listHD.add(new LuuHoaDon(maLuu, maHoaDon, tenKH, thanhTien));
            }   while (cursor.moveToNext());
        }
        return listHD;
    }
    @SuppressLint("Range")
    public ArrayList<LuuHoaDon> getAllHoaDon1(int maUserInput){
        ArrayList<LuuHoaDon> listHoaDon = new ArrayList<>();
        String sql = "SELECT * FROM LuuHoaDon where MaUser = ?";
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(maUserInput)});
        if (cursor.moveToFirst()) {
            do {
                int maLuu = cursor.getInt(cursor.getColumnIndex("maLuu"));
                int maHoaDon = cursor.getInt(cursor.getColumnIndex("MaHoaDon"));
                int maUser = cursor.getInt(cursor.getColumnIndex("MaUser"));
                String tenUser = cursor.getString(cursor.getColumnIndex("tenUser"));
                String tenKhachHang = cursor.getString(cursor.getColumnIndex("tenKhachHang"));
                String ngayLapHD = cursor.getString(cursor.getColumnIndex("NgayLapHD"));
                String sdt = cursor.getString(cursor.getColumnIndex("SDT"));
                String diaChi = cursor.getString(cursor.getColumnIndex("DiaChi"));
                int maSP = cursor.getInt(cursor.getColumnIndex("maSP"));
                String tenSP = cursor.getString(cursor.getColumnIndex("tenSP"));
                int soLuong = cursor.getInt(cursor.getColumnIndex("soLuong"));
                String size = cursor.getString(cursor.getColumnIndex("size"));
                double donGia = cursor.getDouble(cursor.getColumnIndex("donGia"));
                double thanhTien = cursor.getDouble(cursor.getColumnIndex("thanhTien"));
                String trangThai = cursor.getString(cursor.getColumnIndex("TrangThai"));

                listHoaDon.add(new LuuHoaDon(maLuu,tenKhachHang,ngayLapHD,sdt,diaChi,maSP,thanhTien));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return listHoaDon;
    }

//    Lấy hóa đơn theo mã hóa đơn
    public ArrayList<LuuHoaDon> getHDofMaHD(int maHD){
        ArrayList<LuuHoaDon> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT LuuHoaDon.maluu, LuuHoaDon.mahoadon, LuuHoaDon.tenuser, LuuHoaDon.tenkhachhang, LuuHoaDon.ngaylaphd, LuuHoaDon.tensp, LuuHoaDon.soluong, LuuHoaDon.size, LuuHoaDon.dongia from LuuHoaDon WHERE LuuHoaDon.mahoadon = ?", new String[]{String.valueOf(maHD)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                int maLuu = cursor.getInt(0);
                int maHoaDon = cursor.getInt(1);
                String tenNv = cursor.getString(2);
                String tenKH = cursor.getString(3);
                String ngayBan = cursor.getString(4);
                String tenSP = cursor.getString(5);
                int soLuong = cursor.getInt(6);
                String size = cursor.getString(7);
                double donGia = cursor.getDouble(8);
                list.add(new LuuHoaDon(maLuu, maHoaDon, tenNv, tenKH, ngayBan, tenSP, soLuong, size, donGia));
            }   while (cursor.moveToNext());
        }
        return list;
    }

//    Lấy tổng thu hóa đơn theo mã hóa đơn
    public double tongThuHD(int maHD){
        double tongThu = 0;
        Cursor cursor = database.rawQuery("SELECT sum(LuuHoaDon.soluong* LuuHoaDon.dongia) from LuuHoaDon WHERE LuuHoaDon.mahoadon = ?", new String[]{String.valueOf(maHD)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            tongThu += cursor.getDouble(0);
        }
        return tongThu;
    }

//    Get SP bán chạy
    public ArrayList<Integer> getTopSP (){
        ArrayList<Integer> listMaSP = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT LuuHoaDon.maSP, SUM(LuuHoaDon.soluong) as tongSL FROM LuuHoaDon GROUP by LuuHoaDon.maSP ORDER BY tongSL DESC LIMIT 4", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                int maSp = cursor.getInt(0);
                listMaSP.add(maSp);
            }   while (cursor.moveToNext());
        }
        return listMaSP;
    }
    @SuppressLint("Range")
    public ArrayList<LuuHoaDon> getAllHoaDon() {
        ArrayList<LuuHoaDon> listHoaDon = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM LuuHoaDon;", null);
        if (cursor.moveToFirst()) {
            do {
                int maLuu = cursor.getInt(cursor.getColumnIndex("maLuu"));
                int maHoaDon = cursor.getInt(cursor.getColumnIndex("MaHoaDon"));
                int maUser = cursor.getInt(cursor.getColumnIndex("MaUser"));
                String tenUser = cursor.getString(cursor.getColumnIndex("tenUser"));
                String tenKhachHang = cursor.getString(cursor.getColumnIndex("tenKhachHang"));
                String ngayLapHD = cursor.getString(cursor.getColumnIndex("NgayLapHD"));
                String sdt = cursor.getString(cursor.getColumnIndex("SDT"));
                String diaChi = cursor.getString(cursor.getColumnIndex("DiaChi"));
                int maSP = cursor.getInt(cursor.getColumnIndex("maSP"));
                String tenSP = cursor.getString(cursor.getColumnIndex("tenSP"));
                int soLuong = cursor.getInt(cursor.getColumnIndex("soLuong"));
                String size = cursor.getString(cursor.getColumnIndex("size"));
                double donGia = cursor.getDouble(cursor.getColumnIndex("donGia"));
                double thanhTien = cursor.getDouble(cursor.getColumnIndex("thanhTien"));
                String trangThai = cursor.getString(cursor.getColumnIndex("TrangThai"));

                listHoaDon.add(new LuuHoaDon(maLuu,tenKhachHang,ngayLapHD,sdt,diaChi,maSP,thanhTien));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return listHoaDon;
    }

}
