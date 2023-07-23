package sanghvph30000.fpoly.duan1.Fragment;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;



import java.util.ArrayList;

import sanghvph30000.fpoly.duan1.DAO.DAOGioHang;
import sanghvph30000.fpoly.duan1.DAO.DAOUser;
import sanghvph30000.fpoly.duan1.MainActivity;
import sanghvph30000.fpoly.duan1.Model.GioHang;
import sanghvph30000.fpoly.duan1.Model.SanPham;
import sanghvph30000.fpoly.duan1.Model.User;
import sanghvph30000.fpoly.duan1.R;

public class ChiTietSPFrgm extends Fragment {

    SanPham sanPham;
    TextView txtChiTietTenSp, txtChiTietGiaSP, txtChiTietMoTaSP, txtChiTietTongTien, txtChiTietSL, txtTongtien, txtSoluong;
    ImageView img_sp, img_sp1, btnSoLuongTang, btnSoLuongGiam,btnBackSanPham;
    double donGia = 0;
    int soLuong;
    double tongTien;
    DAOUser daoUser;
    DAOGioHang daoGioHang;

    public ChiTietSPFrgm(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi_tiet_s_p_frgm, container, false);
        txtChiTietTenSp = view.findViewById(R.id.txtChiTietTenSp);
        txtChiTietGiaSP = view.findViewById(R.id.txtChiTietGiaSP);
        txtChiTietMoTaSP = view.findViewById(R.id.txtChiTietMoTaSP);
        txtTongtien = view.findViewById(R.id.txtTongtien);
        txtSoluong = view.findViewById(R.id.txtSoluong);
        txtChiTietSL = view.findViewById(R.id.txtChiTietSL);
        img_sp = view.findViewById(R.id.imgCTSanPham);
        img_sp1 = view.findViewById(R.id.imgCTSanPham1);
        txtChiTietTongTien = view.findViewById(R.id.txtChiTietTongTien);
        btnSoLuongTang = view.findViewById(R.id.btnSoLuongTang);
        btnSoLuongGiam = view.findViewById(R.id.btnSoLuongGiam);
        btnBackSanPham = view.findViewById(R.id.btnBackSanPham);
        EditText btnChiTietAddToCart = view.findViewById(R.id.btnChiTietAddToCart);
        daoGioHang = new DAOGioHang(getContext());
        daoUser = new DAOUser(getContext());

        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", getActivity().MODE_PRIVATE);
        int maUser = pref.getInt("MA", 0);
        User user = daoUser.getUser(maUser);
        int quyenUser = user.getMaChucVu();

        if (quyenUser == 1) {
            btnSoLuongGiam.setVisibility(View.GONE);
            btnSoLuongTang.setVisibility(View.GONE);
            txtChiTietTongTien.setVisibility(View.GONE);
            txtChiTietSL.setVisibility(View.GONE);
            txtTongtien.setVisibility(View.GONE);
            txtSoluong.setVisibility(View.GONE);
            btnChiTietAddToCart.setVisibility(View.GONE);

        }

//        Set kích thước Size


//        Set số lượng
        soLuong = 1;
        txtChiTietSL.setText("0" + soLuong);
        btnSoLuongGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soLuong > 1){
                    soLuong --;
                    if (soLuong < 10){
                        txtChiTietSL.setText("0" + soLuong);
                    }
                    else {
                        txtChiTietSL.setText(soLuong + "");
                    }
                    tongTien = tinhTien(soLuong, donGia);
                    String mTinhTien = String.format("%,.0f", tongTien);
                    txtChiTietTongTien.setText(mTinhTien + " VNĐ");
                }
            }
        });

        btnSoLuongTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong++;
                if (soLuong < 10){
                    txtChiTietSL.setText("0" + soLuong);
                }
                else {
                    txtChiTietSL.setText(soLuong + "");
                }
                tongTien = tinhTien(soLuong, donGia);
                String mTinhTien = String.format("%,.0f", tongTien);
                txtChiTietTongTien.setText(mTinhTien + " VNĐ");
            }
        });

//        Set Data cho các View
        txtChiTietTenSp.setText(sanPham.getTenSanPham());
        donGia = sanPham.getPrice();
        String mGiaSP = String.format("%,.0f", donGia);
        txtChiTietGiaSP.setText(mGiaSP + " VNĐ");
        txtChiTietMoTaSP.setText(sanPham.getMota());
        byte[] productsImage = sanPham.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(productsImage, 0, productsImage.length);
        img_sp.setImageBitmap(bitmap);
        img_sp1.setImageBitmap(bitmap);

        tongTien = tinhTien(soLuong, donGia);
        String mTinhTien = String.format("%,.0f", tongTien);
        txtChiTietTongTien.setText(mTinhTien + " VNĐ");



//        Thêm sự kiện Button Add
        btnChiTietAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHang gioHang = new GioHang(1, sanPham.getId(), soLuong, donGia );
                ArrayList<GioHang> outList = daoGioHang.checkValidGioHang(gioHang);
                if (outList.size() != 0){
//                - Có: Update số lượng
                    GioHang gioHang1 = outList.get(0);
                    int newSL = gioHang1.getSoLuong() + gioHang.getSoLuong();
                    gioHang.setSoLuong(newSL);
                    boolean kiemtra = daoGioHang.updateGioHang(gioHang);
                    if (kiemtra){
                        Toast.makeText(getContext(), "Thêm sản phẩm thành công!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), "Update SL Fail!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
//                - Không: Thêm sản phẩm
                    boolean check = daoGioHang.addGiohang(gioHang);
                    if (check){
                        Toast.makeText(getContext(), "Thêm sản phẩm thành công!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), "Fail!", Toast.LENGTH_SHORT).show();
                    }
                }
                loadFragment(new StoreFrgm());
                MainActivity.bottomNavigationView.setSelectedItemId(R.id.pageBanHang);
            }
        });
        btnBackSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment currentFragment = getParentFragmentManager().findFragmentById(R.id.frame_container);

                // Kiểm tra nếu currentFragment là HomeFrgm thì không làm gì cả (để mặc định quay lại màn hình trước đó)
                // Nếu currentFragment là ProductFrgm, thì không làm gì cả (để giữ nguyên ở trong ProductFrgm)
                if (!(currentFragment instanceof HomeFrgm)) {
                    getParentFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }


////    Tính tổng tiền
    public double tinhTien(int soLuong , double donGia){
        double tongTien = 0;
        tongTien = soLuong * donGia ;
        return tongTien;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}