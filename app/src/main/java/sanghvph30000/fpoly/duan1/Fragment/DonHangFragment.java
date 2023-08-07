package sanghvph30000.fpoly.duan1.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sanghvph30000.fpoly.duan1.Adapter.DonHangAdapter;
import sanghvph30000.fpoly.duan1.DAO.DAOLuuHD;
import sanghvph30000.fpoly.duan1.Model.GioHang;
import sanghvph30000.fpoly.duan1.Model.LuuHoaDon;
import sanghvph30000.fpoly.duan1.R;

public class DonHangFragment extends Fragment {
    private RecyclerView recyclerDonHang;
    private DonHangAdapter donHangAdapter;
    private List<LuuHoaDon> listLuuHoaDon = new ArrayList<>();
    private  List<String> temTrangThaiList = new ArrayList<>();
    public static final String TAG = "DonHangFragment";
    ImageView btnBackDonHang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donhang, container, false);

        recyclerDonHang = view.findViewById(R.id.recyclerDonHang);

        // Tạo mới đối tượng DonHangAdapter và gán nó cho biến donHangAdapter
        List<String> trangThaiList = new ArrayList<>();
        // Thêm danh sách trạng thái trống để tránh lỗi
        donHangAdapter = new DonHangAdapter(listLuuHoaDon, trangThaiList,temTrangThaiList);

        recyclerDonHang.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerDonHang.setAdapter(donHangAdapter);

        loadHoaDonData();

        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        // Gán lại trạng thái từ List tạm thời vào List trạng thái chính
//        trangThaiList.clear();
//        trangThaiList.addAll(tempTrangThaiList);
//        // Thông báo cho adapter rằng dữ liệu đã thay đổi để cập nhật RecyclerView
//        donHangAdapter.notifyDataSetChanged();
//    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadHoaDonData() {
        DAOLuuHD daoLuuHD = new DAOLuuHD(getContext());
        listLuuHoaDon.clear();
        listLuuHoaDon.addAll(daoLuuHD.getAllHoaDon());

        List<String> trangThaiList = new ArrayList<>();
        for (LuuHoaDon hoaDon : listLuuHoaDon) {
            trangThaiList.add(hoaDon.getTrangThai());
        }

        // Cập nhật danh sách trạng thái cho Adapter
        donHangAdapter.setTrangThaiList(trangThaiList);
        Log.d(TAG, "Size of listLuuHoaDon: " + listLuuHoaDon.size());

        // Thêm kiểm tra null và khởi tạo listGioHang nếu cần thiết
        for (LuuHoaDon luuHoaDon : listLuuHoaDon) {
            if (luuHoaDon.getListGioHang() == null) {
                luuHoaDon.setListGioHang(new ArrayList<>());
            }
        }
        for (LuuHoaDon luuHoaDon : listLuuHoaDon) {
            ArrayList<GioHang> gioHangList = luuHoaDon.getListGioHang();
            if (gioHangList == null) {
                gioHangList = new ArrayList<>();
                luuHoaDon.setListGioHang(gioHangList);
            }
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



}
