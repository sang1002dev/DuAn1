package sanghvph30000.fpoly.duan1.Fragment;

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

import sanghvph30000.fpoly.duan1.Adapter.LuuHoaDonAdapter;
import sanghvph30000.fpoly.duan1.DAO.DAOLuuHD;
import sanghvph30000.fpoly.duan1.Model.GioHang;
import sanghvph30000.fpoly.duan1.Model.LuuHoaDon;
import sanghvph30000.fpoly.duan1.R;

public class HistoryFragment extends Fragment {
    private RecyclerView recyclerLichSu;
    private LuuHoaDonAdapter hoaDonAdapter;
    private List<LuuHoaDon> listLuuHoaDon = new ArrayList<>(); // Khởi tạo listLuuHoaDon

    ImageView btnBackLichSu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        hoaDonAdapter = new LuuHoaDonAdapter(listLuuHoaDon);

        recyclerLichSu = view.findViewById(R.id.recyclerLichSu);
        recyclerLichSu.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerLichSu.setAdapter(hoaDonAdapter);

        btnBackLichSu = view.findViewById(R.id.btnBackLichSu);
        btnBackLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Account_Fragment());
            }
        });

        loadHoaDonData();

        return view;
    }

    private void loadHoaDonData() {
        DAOLuuHD daoLuuHD = new DAOLuuHD(getContext());
        listLuuHoaDon.clear();
        listLuuHoaDon.addAll(daoLuuHD.getAllHoaDon(2, 0));
        for (LuuHoaDon luuHoaDon : listLuuHoaDon) {
            if (luuHoaDon.getListGioHang() == null) {
                luuHoaDon.setListGioHang(new ArrayList<>());
            }
        }
        hoaDonAdapter.notifyDataSetChanged();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
