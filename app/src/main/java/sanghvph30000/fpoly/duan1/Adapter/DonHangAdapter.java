package sanghvph30000.fpoly.duan1.Adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sanghvph30000.fpoly.duan1.Model.LuuHoaDon;
import sanghvph30000.fpoly.duan1.R;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.HoaDonViewHolder> {
    private List<LuuHoaDon> listHoaDon;
    private List<String> trangThaiList;
    private List<String> tempTrangThaiList = new ArrayList<>();
    //private List<Boolean> daGiaoHangList = new ArrayList<>(); // Danh sách trạng thái đã giao hàng


    public DonHangAdapter(List<LuuHoaDon> listHoaDon, List<String> trangThaiList,List<String>tempTrangThaiList) {
        this.listHoaDon = listHoaDon;
        this.trangThaiList = trangThaiList;
        this.tempTrangThaiList = tempTrangThaiList;
        //initDaGiaoHangList();
    }

//    private void initDaGiaoHangList() {
//        daGiaoHangList.clear();
//        for (LuuHoaDon hoaDon : listHoaDon) {
//            boolean isDaGiaoHang = hoaDon.getTrangThai().equals("Đã giao hàng") || hoaDon.getTrangThai().equals("Đã thanh toán");
//            daGiaoHangList.add(isDaGiaoHang);
//        }
//    }

    @NonNull
    @Override
    public HoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang, parent, false);
        return new HoaDonViewHolder(view);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull HoaDonViewHolder holder, int position) {
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
        AdapterHoaDon adapterHoaDon = new AdapterHoaDon(holder.itemView.getContext(), luuHoaDon.getListGioHang());
        holder.recycle_donhang.setAdapter(adapterHoaDon);
//        boolean isDaGiaoHang = daGiaoHangList.get(position);
//        if (isDaGiaoHang) {
//            holder.txtTrangThai.setTextColor(Color.RED);
//        } else {
//            holder.txtTrangThai.setTextColor(Color.GREEN);
//        }
        // Xử lý sự kiện khi nút "Xác nhận" được bấm
        if (trangThai.equals("Đang chờ xử lý") || trangThai.equals("Đang giao hàng")) {
            holder.btnXacNhan.setVisibility(View.VISIBLE);
            holder.btnXacNhan.setEnabled(true);
            holder.btnXacNhan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (trangThai.equals("Đang chờ xử lý")) {
                        listHoaDon.get(position).setTrangThai("Đang giao hàng");
                        holder.txtTrangThai.setText("Đang giao hàng");
                        //holder.txtTrangThai.setTextColor(Color.RED);
                    } else if (trangThai.equals("Đang giao hàng")) {
                        listHoaDon.get(position).setTrangThai("Đã thanh toán");
                        holder.txtTrangThai.setText("Đã thanh toán");
                        //holder.txtTrangThai.setTextColor(Color.GREEN);
                        holder.btnXacNhan.setVisibility(View.GONE); // Ẩn nút "Xác nhận" khi đã thanh toán
                    }
                    notifyItemChanged(position);
                }
            });
        } else {
            holder.btnXacNhan.setVisibility(View.GONE); // Ẩn nút "Xác nhận" nếu trạng thái là "Đã thanh toán"
        }

    }

    @Override
    public int getItemCount() {
        return listHoaDon.size();
    }

    public static class HoaDonViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenKhachHang, txtNgayBan, txtSDT, txtDiaChi, txtTongTien, txtTrangThai;
        Button btnXacNhan;
        RecyclerView recycle_donhang;

        public HoaDonViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenKhachHang = itemView.findViewById(R.id.txtHDTenKH1);
            txtNgayBan = itemView.findViewById(R.id.txtHDNgayBan1);
            txtSDT = itemView.findViewById(R.id.txtHDSDT1);
            txtDiaChi = itemView.findViewById(R.id.txtHDDiaChi1);
            txtTongTien = itemView.findViewById(R.id.txtHDTongTien1);
            txtTrangThai = itemView.findViewById(R.id.txtHDTrangThai1);
            btnXacNhan = itemView.findViewById(R.id.btnHoaDonXN1);
            recycle_donhang = itemView.findViewById(R.id.recycle_donHang);
            // Không cần sử dụng LinearLayoutManager vì nó đã được sử dụng trong XML của item_lichsu
        }
    }
    public void setTrangThaiList(List<String> trangThaiList) {
        this.trangThaiList = trangThaiList;
    }
    public List<String> getTempTrangThaiList() {
        return tempTrangThaiList;
    }
    // Setter cho danh sách trạng thái tạm thời
    public void setTempTrangThaiList(List<String> tempTrangThaiList) {
        this.tempTrangThaiList = tempTrangThaiList;
    }
}
