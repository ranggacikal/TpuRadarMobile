package riska.com.tpuradarmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import riska.com.tpuradarmobile.BeritaAdminActivity;
import riska.com.tpuradarmobile.R;
import riska.com.tpuradarmobile.api.EditHapusActivity;
import riska.com.tpuradarmobile.model.DataBeritaItem;

public class BeritaAdminAdapter extends RecyclerView.Adapter<BeritaAdminAdapter.BeritaAdminViewHolder> {

    Context mContext;
    List<DataBeritaItem> beritaItems;

    public BeritaAdminAdapter(Context mContext, List<DataBeritaItem> beritaItems) {
        this.mContext = mContext;
        this.beritaItems = beritaItems;
    }

    @NonNull
    @Override
    public BeritaAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_berita, parent, false);
        return new BeritaAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaAdminViewHolder holder, int position) {

        final String imgLink = beritaItems.get(position).getImageBerita();

        Glide.with(mContext)
                .load(imgLink)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.imgBerita);

        holder.txtJudul.setText(beritaItems.get(position).getJudulBerita());
        holder.txtTanggal.setText(beritaItems.get(position).getTanggalBerita());
        holder.txtIsi.setText(beritaItems.get(position).getIsiBerita());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditHapusActivity.class);
                intent.putExtra(EditHapusActivity.EXTRA_ID_BERITA, beritaItems.get(position).getIdBerita());
                intent.putExtra(EditHapusActivity.EXTRA_JUDUL_BERITA, beritaItems.get(position).getJudulBerita());
                intent.putExtra(EditHapusActivity.EXTRA_TANGGAL_BERITA, beritaItems.get(position).getTanggalBerita());
                intent.putExtra(EditHapusActivity.EXTRA_ISI_BERITA, beritaItems.get(position).getIsiBerita());
                intent.putExtra(EditHapusActivity.EXTRA_IMAGE_BERITA, beritaItems.get(position).getImageBerita());
                mContext.startActivity(intent);
                ((BeritaAdminActivity)mContext).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return beritaItems.size();
    }

    public class BeritaAdminViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBerita;
        TextView txtJudul, txtTanggal, txtIsi;
        public BeritaAdminViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBerita = itemView.findViewById(R.id.img_berita);
            txtJudul = itemView.findViewById(R.id.text_judul_berita);
            txtTanggal = itemView.findViewById(R.id.text_tanggal_berita);
            txtIsi = itemView.findViewById(R.id.text_isi_berita);
        }
    }
}
