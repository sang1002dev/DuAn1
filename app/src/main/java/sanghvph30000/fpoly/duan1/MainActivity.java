package sanghvph30000.fpoly.duan1;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import sanghvph30000.fpoly.duan1.Fragment.Account_Fragment;
import sanghvph30000.fpoly.duan1.Fragment.HomeFrgm;
import sanghvph30000.fpoly.duan1.Fragment.ProductFrgm;
import sanghvph30000.fpoly.duan1.Fragment.StoreFrgm;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.pageTrangChu);
        loadFragment(new HomeFrgm());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.pageTrangChu:
                fragment = new HomeFrgm();
                loadFragment(fragment);
                return true;

            case R.id.pageSanPham:
                fragment = new ProductFrgm();
                loadFragment(fragment);
                return true;

            case R.id.pageBanHang:
                fragment = new StoreFrgm();
                loadFragment(fragment);
                return true;

            case R.id.pageTaiKhoan:
                loadFragment(new Account_Fragment());
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