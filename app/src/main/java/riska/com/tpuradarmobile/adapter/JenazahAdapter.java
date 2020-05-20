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

import riska.com.tpuradarmobile.DataJenazahActivity;
import riska.com.tpuradarmobile.R;
import riska.com.tpuradarmobile.ValidasiPerpanjangMakamActivity;
import riska.com.tpuradarmobile.model.DataJenazahItem;

public class JenazahAdapter extends RecyclerView.Adapter<JenazahAdapter.JenazahViewHolder> {

    Context mContext;
    List<DataJenazahItem> jenazahItems;

    public JenazahAdapter(Context mContext, List<DataJenazahItem> jenazahItems) {
        this.mContext = mContext;
        this.jenazahItems = jenazahItems;
    }

    @NonNull
    @Override
    public JenazahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_jenazah, parent, false);
        return new JenazahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JenazahViewHolder holder, int position) {

        holder.txtNama.setText(jenazahItems.get(position).getNamaJenazah());
        holder.txtNoKtp.setText(jenazahItems.get(position).getNoKtpJenazah());
        holder.txtTanggalMeninggal.setText(jenazahItems.get(position).getTanggalMeninggal());

        holder.btnValidasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ValidasiPerpanjangMakamActivity.class);
                intent.putExtra(ValidasiPerpanjangMakamActivity.EXTRA_ID_PERPANJANG, jenazahItems.get(position).getIdJenazah());
                intent.putExtra(ValidasiPerpanjangMakamActivity.EXTRA_IMG_KTP_PERPANJANG, jenazahItems.get(position).getImageKtp());
                intent.putExtra(ValidasiPerpanjangMakamActivity.EXTRA_IMG_KK_PERPANJANG, jenazahItems.get(position).getImageKk());
                intent.putExtra(ValidasiPerpanjangMakamActivity.EXTRA_IMG_IPTM_PERPANJANG, jenazahItems.get(position).getImageIptm());
                intent.putExtra(ValidasiPerpanjangMakamActivity.EXTRA_IMG_BUKTIPEMBAYARAN_PERPANJANG, jenazahItems.get(position).getImageBuktiPembayaran());
                mContext.startActivity(intent);
                ((DataJenazahActivity)mContext).finish();
            }
        });

        String validasi = jenazahItems.get(position).getValidasiBuktiPembayaran();

        if (validasi.equals("Valid")){
            holder.btnValidasi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Anda Sudah Melakukan Validasi", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return jenazahItems.size();
    }

    public class JenazahViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtNoKtp, txtTanggalMeninggal;
        Button btnValidasi;
        public JenazahViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = itemView.findViewById(R.id.text_nama_jenazah);
            txtNoKtp = itemView.findViewById(R.id.text_noktp_jenazah);
            txtTanggalMeninggal = itemView.findViewById(R.id.text_tanggalmeninggal_jenazah);
            btnValidasi = itemView.findViewById(R.id.btn_validasi_perpanjang_makam);
        }
    }
}
