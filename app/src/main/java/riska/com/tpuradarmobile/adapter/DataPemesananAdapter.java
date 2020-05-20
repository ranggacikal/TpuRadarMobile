package riska.com.tpuradarmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import riska.com.tpuradarmobile.CetakBuktiActivity;
import riska.com.tpuradarmobile.DataJenazahUserActivity;
import riska.com.tpuradarmobile.DataPesananActivity;
import riska.com.tpuradarmobile.PembayaranActivity;
import riska.com.tpuradarmobile.R;
import riska.com.tpuradarmobile.StatusPesananActivity;
import riska.com.tpuradarmobile.model.DataPemesananUserItem;

public class DataPemesananAdapter extends RecyclerView.Adapter<DataPemesananAdapter.DataPemesananViewHolder> {
    private Context mContext;
    private List<DataPemesananUserItem> dataPemesananItem;

    public DataPemesananAdapter(Context mContext, List<DataPemesananUserItem> dataPemesananItem) {
        this.mContext = mContext;
        this.dataPemesananItem = dataPemesananItem;
    }

    @NonNull
    @Override
    public DataPemesananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_pesanan, parent, false);
        return new DataPemesananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataPemesananViewHolder holder, int position) {

        holder.txtIdPemesan.setText(dataPemesananItem.get(position).getIdPemesanan());
        holder.txtNamaPemesan.setText(dataPemesananItem.get(position).getNamaUser());
        holder.txtTanggalPemesan.setText(dataPemesananItem.get(position).getTanggalPemesanan());

        String bukti_pembayaran = dataPemesananItem.get(position).getImageBuktiPembayaran();
        String bukti_kematian = dataPemesananItem.get(position).getValidasiBuktiKematian();
        String validasi_bukti_kematian = dataPemesananItem.get(position).getValidasiBuktiKematian();

        holder.btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, PembayaranActivity.class);
                intent.putExtra(PembayaranActivity.EXTRA_ID_PEMESANAN, dataPemesananItem.get(position).getIdPemesanan());
                mContext.startActivity(intent);

            }
        });

        if (!validasi_bukti_kematian.equals("Valid")) {
            Toast.makeText(mContext, "Pesanan Anda Belum Di Validasi", Toast.LENGTH_LONG).show();
        }


        if (bukti_pembayaran != null ){
            holder.btnCekStatus.setVisibility(View.VISIBLE);
            holder.btnCekStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentbayar1 = new Intent(mContext, StatusPesananActivity.class);
                    intentbayar1.putExtra(StatusPesananActivity.EXTRA_VALIDASI_PEMBAYARAN, dataPemesananItem.get(position).getValidasiBuktiPembayaran());
                    intentbayar1.putExtra(StatusPesananActivity.EXTRA_ID_PESANAN, dataPemesananItem.get(position).getIdPemesanan());
                    mContext.startActivity(intentbayar1);
                    ((DataPesananActivity)mContext).finish();
                }
            });
        }

        String validasi_bukti_bayar = dataPemesananItem.get(position).getValidasiBuktiPembayaran();

        if (validasi_bukti_bayar.equals("Valid")) {
            holder.btnCekStatus.setVisibility(View.VISIBLE);
            holder.btnCetakPesanan.setVisibility(View.VISIBLE);
            holder.btnCetakPesanan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show();
                }
            });

            holder.btnCekStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentbayar1 = new Intent(mContext, StatusPesananActivity.class);
                    intentbayar1.putExtra(StatusPesananActivity.EXTRA_VALIDASI_PEMBAYARAN, dataPemesananItem.get(position).getValidasiBuktiPembayaran());
                    intentbayar1.putExtra(StatusPesananActivity.EXTRA_ID_PESANAN, dataPemesananItem.get(position).getIdPemesanan());
                    mContext.startActivity(intentbayar1);
                    ((DataPesananActivity)mContext).finish();
                }
            });

            holder.btnCetakPesanan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  intent = new Intent(mContext, CetakBuktiActivity.class);
                    intent.putExtra(CetakBuktiActivity.EXTRA_ID_PEMESANAN, dataPemesananItem.get(position).getIdPemesanan());
                    intent.putExtra(CetakBuktiActivity.EXTRA_NAMA_PEMESANAN, dataPemesananItem.get(position).getNamaUser());
                    intent.putExtra(CetakBuktiActivity.EXTRA_TANGGAL_PEMESANAN, dataPemesananItem.get(position).getTanggalPemesanan());
                    intent.putExtra(CetakBuktiActivity.EXTRA_UKURAN_PETAK_PEMESANAN, dataPemesananItem.get(position).getUkuranPetak());
                    mContext.startActivity(intent);
                    ((DataPesananActivity)mContext).finish();
                }
            });

            holder.btnBayar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Anda Sudah Melakukan Pembayaran", Toast.LENGTH_SHORT).show();
                }
            });
        }

        holder.btnPerpanjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, DataJenazahUserActivity.class));
                ((DataPesananActivity)mContext).finish();
            }
        });




    }

    @Override
    public int getItemCount() {
        return dataPemesananItem.size();
    }

    public class DataPemesananViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaPemesan, txtIdPemesan, txtTanggalPemesan;
        Button btnBayar, btnCekStatus, btnCetakPesanan, btnPerpanjang;

        public DataPemesananViewHolder(@NonNull View itemView) {
            super(itemView);

            txtIdPemesan = itemView.findViewById(R.id.text_id_item_pesanan);
            txtNamaPemesan = itemView.findViewById(R.id.text_nama_item_pesanan);
            txtTanggalPemesan = itemView.findViewById(R.id.text_tanggal_item_pesanan);
            btnBayar = itemView.findViewById(R.id.btn_bayar_item_pesanan);
            btnCekStatus = itemView.findViewById(R.id.btn_cek_status_item_pesanan);
            btnCetakPesanan = itemView.findViewById(R.id.btn_cetak_bukti_item_pesanan);
            btnPerpanjang = itemView.findViewById(R.id.btn_perpanjang_item_pesanan);
        }
    }
}
