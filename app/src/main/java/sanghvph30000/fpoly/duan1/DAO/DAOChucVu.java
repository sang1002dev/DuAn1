package sanghvph30000.fpoly.duan1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import sanghvph30000.fpoly.duan1.Database.DbHelper;
import sanghvph30000.fpoly.duan1.Model.ChucVu;

public class DAOChucVu {
    private SQLiteDatabase database;
    DbHelper dbHelper;

    public DAOChucVu(Context context) {
        dbHelper = new DbHelper(context, "DuAn1", null, 1);
        database = dbHelper.getWritableDatabase();
        database = dbHelper.getReadableDatabase();
    }

    public List<ChucVu> getAllChucVu() {
        String sql = "SELECT * FROM ChucVu";
        return getData(sql);
    }

    public List<ChucVu> getData(String sql, String... selectionAGrs) {
        ArrayList<ChucVu> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionAGrs);
        while (cursor.moveToNext()) {
            int id_chucVu = cursor.getInt(0);
            String tenChucVu = cursor.getString(1);
            list.add(new ChucVu(id_chucVu, tenChucVu));
        }
        return list;
    }
}
