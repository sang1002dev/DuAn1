package sanghvph30000.fpoly.duan1.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sanghvph30000.fpoly.duan1.Model.LuuHoaDon;
import sanghvph30000.fpoly.duan1.R;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.HoaDonViewHolder>{
    private List<LuuHoaDon> listHoaDon;

    public DonHangAdapter(List<LuuHoaDon> listHoaDon) {
        this.listHoaDon = listHoaDon;
    }

    @NonNull
    @Override
    public DonHangAdapter.HoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang, parent, false);
        return new DonHangAdapter.HoaDonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangAdapter.HoaDonViewHolder holder, int position) {
        LuuHoaDon luuHoaDon = listHoaDon.get(position);
        holder.txtTenKhachHang.setText(luuHoaDon.getTenKhachHang());
        holder.txtNgayBan.setText(luuHoaDon.getNgayLapHD());
        holder.txtSDT.setText(luuHoaDon.getSDT());
        holder.txtDiaChi.setText(luuHoaDon.getDiaChi());
        holder.txtTongTien.setText(String.format("%,.0fđ", luuHoaDon.getThanhTien()));

        // Kiểm tra giá trị trước khi hiển thị trạng thái
        String trangThai = luuHoaDon.getTrangThai() != null ? luuHoaDon.getTrangThai() : "Đang chờ xử lý";
        holder.txtTrangThai.setText(trangThai);

        // Sử dụng AdapterHoaDon để hiển thị danh sách sản phẩm của mỗi hóa đơn
        //fix here b1.
        Log.d("TAG", "onBindViewHolder: " + luuHoaDon.getListGioHang().toString());
        AdapterHoaDon adapterHoaDon = new AdapterHoaDon(holder.itemView.getContext(), luuHoaDon.getListGioHang());
        holder.recycle_donhang.setAdapter(adapterHoaDon);
    }

    @Override
    public int getItemCount() {
        return listHoaDon.size();
    }

    public static class HoaDonViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenKhachHang, txtNgayBan,txtSDT, txtDiaChi, txtTongTien, txtTrangThai;
        RecyclerView recycle_donhang;

        public HoaDonViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenKhachHang = itemView.findViewById(R.id.txtHDTenKH1);
            txtNgayBan = itemView.findViewById(R.id.txtHDNgayBan1);
            txtSDT = itemView.findViewById(R.id.txtHDSDT1);
            txtDiaChi = itemView.findViewById(R.id.txtHDDiaChi1);
            txtTongTien = itemView.findViewById(R.id.txtHDTongTien1);
            txtTrangThai = itemView.findViewById(R.id.txtHDTrangThai1);
            recycle_donhang = itemView.findViewById(R.id.recycle_donHang);
            // Không cần sử dụng LinearLayoutManager vì nó đã được sử dụng trong XML của item_lichsu
        }
    }

}