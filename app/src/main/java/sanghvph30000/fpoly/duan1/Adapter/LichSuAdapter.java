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

public class LichSuAdapter extends RecyclerView.Adapter<LichSuAdapter.HoaDonViewHolder> {
    private List<LuuHoaDon> listHoaDon;

    public LichSuAdapter(List<LuuHoaDon> listHoaDon) {
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
        LuuHoaDon luuHoaDon = listHoaDon.get(position);
        holder.txtTenKhachHang.setText(luuHoaDon.getTenKhachHang());
        holder.txtNgayBan.setText(luuHoaDon.getNgayLapHD());
        holder.txtTongTien.setText(String.format("%,.0fđ", luuHoaDon.getThanhTien()));

        // Kiểm tra giá trị trước khi hiển thị trạng thái
        String trangThai = luuHoaDon.getTrangThai() != null ? luuHoaDon.getTrangThai() : "Đang xử lý";
        holder.txtTrangThai.setText(trangThai);

        // Sử dụng AdapterHoaDon để hiển thị danh sách sản phẩm của mỗi hóa đơn
        //fix here b1.
        Log.d("TAG", "onBindViewHolder: " + luuHoaDon.getListGioHang().toString());
        AdapterHoaDon adapterHoaDon = new AdapterHoaDon(holder.itemView.getContext(), luuHoaDon.getListGioHang());
        holder.recycle_itemLS.setAdapter(adapterHoaDon);
    }

    @Override
    public int getItemCount() {
        return listHoaDon.size();
    }

    public static class HoaDonViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenKhachHang, txtNgayBan, txtTongTien, txtTrangThai;
        RecyclerView recycle_itemLS;

        public HoaDonViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenKhachHang = itemView.findViewById(R.id.txtLSTenKH);
            txtNgayBan = itemView.findViewById(R.id.txtLSNgayBan);
            txtTongTien = itemView.findViewById(R.id.txtLSTongTien);
            txtTrangThai = itemView.findViewById(R.id.txtLSTrangThai);
            recycle_itemLS = itemView.findViewById(R.id.recycle_itemLS);
            // Không cần sử dụng LinearLayoutManager vì nó đã được sử dụng trong XML của item_lichsu
        }
    }
}
