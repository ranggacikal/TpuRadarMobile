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

import riska.com.tpuradarmobile.DetailBeritaActivity;
import riska.com.tpuradarmobile.MainActivity;
import riska.com.tpuradarmobile.R;
import riska.com.tpuradarmobile.model.DataBeritaItem;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder> {

    Context mContext;
    List<DataBeritaItem> beritaItems;

    public BeritaAdapter(Context mContext, List<DataBeritaItem> beritaItems) {
        this.mContext = mContext;
        this.beritaItems = beritaItems;
    }

    @NonNull
    @Override
    public BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_berita, parent, false);
        return new BeritaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaViewHolder holder, int position) {
        final String imgLink = beritaItems.get(position).getImageBerita();

        Glide.with(mContext)
                .load(imgLink)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgBerita);

        holder.txtJudul.setText(beritaItems.get(position).getJudulBerita());
        holder.txtTanggal.setText(beritaItems.get(position).getTanggalBerita());
        holder.txtIsi.setText(beritaItems.get(position).getIsiBerita());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailBeritaActivity.class);
                intent.putExtra(DetailBeritaActivity.EXTRA_IMAGE_BERITA, beritaItems.get(position).getImageBerita());
                intent.putExtra(DetailBeritaActivity.EXTRA_JUDUL_BERITA, beritaItems.get(position).getJudulBerita());
                intent.putExtra(DetailBeritaActivity.EXTRA_TANGGAL_BERITA, beritaItems.get(position).getTanggalBerita());
                intent.putExtra(DetailBeritaActivity.EXTRA_ISI_BERITA, beritaItems.get(position).getIsiBerita());
                mContext.startActivity(intent);
                ((MainActivity)mContext).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return beritaItems.size();
    }

    public class BeritaViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBerita;
        TextView txtJudul, txtTanggal, txtIsi;
        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBerita = itemView.findViewById(R.id.img_berita);
            txtJudul = itemView.findViewById(R.id.text_judul_berita);
            txtTanggal = itemView.findViewById(R.id.text_tanggal_berita);
            txtIsi = itemView.findViewById(R.id.text_isi_berita);
        }
    }
}
