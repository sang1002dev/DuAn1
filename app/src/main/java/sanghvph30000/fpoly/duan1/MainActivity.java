package sanghvph30000.fpoly.duan1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import sanghvph30000.fpoly.duan1.DAO.DAOUser;
import sanghvph30000.fpoly.duan1.Fragment.Account_Fragment;
import sanghvph30000.fpoly.duan1.Fragment.DonHangFragment;
import sanghvph30000.fpoly.duan1.Fragment.HomeFrgm;
import sanghvph30000.fpoly.duan1.Fragment.ProductFrgm;
import sanghvph30000.fpoly.duan1.Fragment.StoreFrgm;
import sanghvph30000.fpoly.duan1.Model.User;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
private DAOUser daoUser;
    public static BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        // Khởi tạo đối tượng DAOUser
        daoUser = new DAOUser(this);

        // Lấy mã người dùng từ SharedPreferences
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        int maUser = pref.getInt("MA", 0);

        // Lấy thông tin user dựa vào mã người dùng
        User user = daoUser.getUser(maUser);
        int quyenUser = user.getMaChucVu();

        // Ẩn hoặc hiển thị mục "pageBanHang" dựa vào vai trò người dùng
        if (quyenUser == 2) {
            bottomNavigationView.getMenu().findItem(R.id.pageBanHang).setVisible(true);
            bottomNavigationView.getMenu().findItem(R.id.pageDonHang).setVisible(false);
        } else {
            bottomNavigationView.getMenu().findItem(R.id.pageBanHang).setVisible(false);
            bottomNavigationView.getMenu().findItem(R.id.pageDonHang).setVisible(true);
        }

        bottomNavigationView.setSelectedItemId(R.id.pageTrangChu);
        loadFragment(new HomeFrgm());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.pageTrangChu:
                fragment = new HomeFrgm();
                break;

            case R.id.pageSanPham:
                fragment = new ProductFrgm();
                break;

            case R.id.pageBanHang:
                fragment = new StoreFrgm();
                break;

            case R.id.pageDonHang:
                fragment = new DonHangFragment();
                break;

            case R.id.pageTaiKhoan:
                fragment = new Account_Fragment();
                break;
        }

        // Load fragment nếu fragment khác null
        if (fragment != null) {
            loadFragment(fragment);
            return true;
        }

        return false;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}