package sanghvph30000.fpoly.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import sanghvph30000.fpoly.duan1.DAO.DAOUser;
import sanghvph30000.fpoly.duan1.Database.DbHelper;
import sanghvph30000.fpoly.duan1.Model.User;

public class DangKyActi extends AppCompatActivity {

    private EditText edtFullName, edtUsername, edtPassword, edtPhoneNumber, edtBirthYear;
    private CheckBox chkSavePassword;
    private Button btnRegister;
    TextView textView;

    // Khai báo đối tượng lớp DatabaseHelper để tạo và quản lý cơ sở dữ liệu
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        // Ánh xạ các thành phần giao diện
        edtFullName = findViewById(R.id.edtTenUser);
        edtUsername = findViewById(R.id.edtTenDangNhap);
        edtPassword = findViewById(R.id.edtMatKhau);
        edtPhoneNumber = findViewById(R.id.edtSDT);
        edtBirthYear = findViewById(R.id.edtNamSinh);
        chkSavePassword = findViewById(R.id.chkNhoMK1);
        textView = findViewById(R.id.tvDaco);
        btnRegister = findViewById(R.id.btnDangki);

        // Khởi tạo đối tượng DAOUser và truyền context của Activity vào constructor
        DAOUser daoUser = new DAOUser(this);

        Button btnDangki = findViewById(R.id.btnDangki);
        btnDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin từ các trường EditText trong layout
                String fullName = edtFullName.getText().toString().trim();
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString();
                int maChucVu = 2;
                String phoneNumber = edtPhoneNumber.getText().toString().trim();
                int birthYear = Integer.parseInt(edtBirthYear.getText().toString().trim());

                // Kiểm tra các trường dữ liệu không được để trống (như đã làm trong constructor của DAOUser)

                // Tạo một đối tượng User từ thông tin đã lấy được
                User newUser = new User(fullName, username, password,maChucVu, phoneNumber, birthYear);

                // Thực hiện đăng ký user mới bằng cách gọi phương thức insertUser() trong DAOUser
                long result = daoUser.insertUser(newUser);

                if (result != -1) {
                    Toast.makeText(DangKyActi.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    finish(); // Kết thúc Activity sau khi đăng ký thành công
                } else {
                    Toast.makeText(DangKyActi.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DangNhapAct.class);
                startActivity(intent);
            }
        });
    }
}