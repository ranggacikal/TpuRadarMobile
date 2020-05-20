package riska.com.tpuradarmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import riska.com.tpuradarmobile.PemesananAdminActivity;
import riska.com.tpuradarmobile.R;
import riska.com.tpuradarmobile.ValidasiAdminActivity;
import riska.com.tpuradarmobile.model.DataSemuaPemesananItem;

public class DataPemesananAdminAdapter extends RecyclerView.Adapter<DataPemesananAdminAdapter.DataPemesananAdminViewHolder> {

    Context mContext;
    List<DataSemuaPemesananItem> semuaPemesananItems;

    public DataPemesananAdminAdapter(Context mContext, List<DataSemuaPemesananItem> semuaPemesananItems) {
        this.mContext = mContext;
        this.semuaPemesananItems = semuaPemesananItems;
    }

    @NonNull
    @Override
    public DataPemesananAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pemesanan_admin, parent, false);
        return new DataPemesananAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataPemesananAdminViewHolder holder, int position) {

        holder.txtIdPemesanan.setText(semuaPemesananItems.get(position).getIdPemesanan());
        holder.txtNamaPemesanan.setText(semuaPemesananItems.get(position).getNamaUser());
        holder.txtTanggalPemesanan.setText(semuaPemesananItems.get(position).getTanggalPemesanan());
        holder.txtPukulPemesanan.setText(semuaPemesananItems.get(position).getJamPemesanan());
        holder.txtUkuranPetakPemesanan.setText(semuaPemesananItems.get(position).getUkuranPetak());
        holder.txtJumlahPetak.setText(semuaPemesananItems.get(position).getJumlahPetak());

        String validasi_kematian = semuaPemesananItems.get(position).getValidasiBuktiKematian();
        String validasi_pembayaran = semuaPemesananItems.get(position).getValidasiBuktiPembayaran();

        if (!validasi_pembayaran.equals("Valid")) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ValidasiAdminActivity.class);
                    intent.putExtra(ValidasiAdminActivity.EXTRA_ID_PESMESANAN, semuaPemesananItems.get(position).getIdPemesanan());
                    intent.putExtra(ValidasiAdminActivity.EXTRA_IMAGE_PEMESANAN, semuaPemesananItems.get(position).getImageBuktiKematian());
                    intent.putExtra(ValidasiAdminActivity.EXTRA_IMAGE_BUKTI_KEMATIAN_PEMESANAN, semuaPemesananItems.get(position).getValidasiBuktiKematian());
                    intent.putExtra(ValidasiAdminActivity.EXTRA_IMAGE_BUKTI_PEMBAYARAN_PEMESANAN, semuaPemesananItems.get(position).getValidasiBuktiPembayaran());
                    intent.putExtra(ValidasiAdminActivity.EXTRA_IMAGE_PEMBAYARAN, semuaPemesananItems.get(position).getImageBuktiPembayaran());
                    mContext.startActivity(intent);
                    ((PemesananAdminActivity) mContext).finish();
                }
            });
        }else if (validasi_pembayaran.equals("Valid")){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Data Ini Sudah Di Validasi", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return semuaPemesananItems.size();
    }

    public class DataPemesananAdminViewHolder extends RecyclerView.ViewHolder {

        TextView txtIdPemesanan, txtNamaPemesanan, txtTanggalPemesanan, txtPukulPemesanan, txtUkuranPetakPemesanan, txtJumlahPetak;
        public DataPemesananAdminViewHolder(@NonNull View itemView) {
            super(itemView);

            txtIdPemesanan = itemView.findViewById(R.id.text_id_item_pesanan_admin);
            txtNamaPemesanan = itemView.findViewById(R.id.text_nama_item_pesanan_admin);
            txtTanggalPemesanan = itemView.findViewById(R.id.text_tanggal_item_pesanan_admin);
            txtPukulPemesanan = itemView.findViewById(R.id.text_pukul_item_pesanan_admin);
            txtUkuranPetakPemesanan = itemView.findViewById(R.id.text_ukuranpetak_item_pesanan_admin);
            txtJumlahPetak = itemView.findViewById(R.id.text_jumlahpetak_item_pesanan_admin);
        }
    }
}
