package sanghvph30000.fpoly.duan1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sanghvph30000.fpoly.duan1.Model.HoaDon;
import sanghvph30000.fpoly.duan1.R;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HoaDonViewHolder> {
    private List<HoaDon> listHoaDon;

    public HoaDonAdapter(List<HoaDon> listHoaDon) {
        this.listHoaDon = listHoaDon;
    }

    @NonNull
    @Override
    public HoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lichsu, parent, false);
        return new HoaDonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonViewHolder holder, int position) {
        HoaDon hoaDon = listHoaDon.get(position);
        holder.txtTenKhachHang.setText(hoaDon.getTenKhachHang());
        holder.txtNgayBan.setText(hoaDon.getNgayLapHD());
        holder.txtTongTien.setText(String.format("%,.0fđ", hoaDon.getThanhTien()));
        holder.txtTrangThai.setText(hoaDon.getTrangThai());
    }

    @Override
    public int getItemCount() {
        return listHoaDon.size();
    }

    public static class HoaDonViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenKhachHang, txtNgayBan, txtTongTien, txtTrangThai;

        public HoaDonViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenKhachHang = itemView.findViewById(R.id.txtLSTenKH);
            txtNgayBan = itemView.findViewById(R.id.txtLSNgayBan);
            txtTongTien = itemView.findViewById(R.id.txtLSTongTien);
            txtTrangThai = itemView.findViewById(R.id.txtLSTrangThai);
        }
    }

    public void addHoaDon(HoaDon hoaDon) {
        hoaDon.setTrangThai("Đang xử lý");
        listHoaDon.add(hoaDon);
        notifyDataSetChanged();
    }
}

