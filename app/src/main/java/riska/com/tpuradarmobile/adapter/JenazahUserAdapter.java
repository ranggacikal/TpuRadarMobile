package riska.com.tpuradarmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import riska.com.tpuradarmobile.DataJenazahUserActivity;
import riska.com.tpuradarmobile.PerpanjangIzinMakamActivity;
import riska.com.tpuradarmobile.R;
import riska.com.tpuradarmobile.model.DataJenazahByUserItem;

public class JenazahUserAdapter extends RecyclerView.Adapter<JenazahUserAdapter.JenazahUserViewHolder> {

    Context mContext;
    List<DataJenazahByUserItem> jenazahByUserItems;

    public JenazahUserAdapter(Context mContext, List<DataJenazahByUserItem> jenazahByUserItems) {
        this.mContext = mContext;
        this.jenazahByUserItems = jenazahByUserItems;
    }

    @NonNull
    @Override
    public JenazahUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jenazah_user, parent, false);
        return new JenazahUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JenazahUserViewHolder holder, int position) {
        holder.txtNama.setText(jenazahByUserItems.get(position).getNamaJenazah());
        holder.txtNoKtp.setText(jenazahByUserItems.get(position).getNoKtpJenazah());
        holder.txtTanggalMeninggal.setText(jenazahByUserItems.get(position).getTanggalMeninggal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PerpanjangIzinMakamActivity.class);
                intent.putExtra(PerpanjangIzinMakamActivity.EXTRA_ID_JENAZAH, jenazahByUserItems.get(position).getIdJenazah());
                mContext.startActivity(intent);
                ((DataJenazahUserActivity)mContext).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return jenazahByUserItems.size();
    }

    public class JenazahUserViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtNoKtp, txtTanggalMeninggal;
        public JenazahUserViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = itemView.findViewById(R.id.text_nama_jenazah_user);
            txtNoKtp = itemView.findViewById(R.id.text_noktp_jenazah_user);
            txtTanggalMeninggal = itemView.findViewById(R.id.text_tanggalmeninggal_jenazah_user);
        }
    }
}
