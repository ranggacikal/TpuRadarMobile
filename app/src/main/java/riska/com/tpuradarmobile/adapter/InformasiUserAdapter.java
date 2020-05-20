package riska.com.tpuradarmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import riska.com.tpuradarmobile.R;
import riska.com.tpuradarmobile.model.DataInformasiItem;

public class InformasiUserAdapter extends RecyclerView.Adapter<InformasiUserAdapter.InformasiUserViewHolder> {

    Context mContext;
    List<DataInformasiItem> informasiUserItems;

    public InformasiUserAdapter(Context mContext, List<DataInformasiItem> informasiUserItems) {
        this.mContext = mContext;
        this.informasiUserItems = informasiUserItems;
    }

    @NonNull
    @Override
    public InformasiUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_informasi, parent, false);
        return new InformasiUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InformasiUserViewHolder holder, int position) {


        holder.txtTanggalUser.setText(informasiUserItems.get(position).getTanggalInformasi());
        holder.txtIsiUser.setText(informasiUserItems.get(position).getIsiInformasi());
    }

    @Override
    public int getItemCount() {
        return informasiUserItems.size();
    }

    public class InformasiUserViewHolder extends RecyclerView.ViewHolder {

        TextView txtTanggalUser, txtIsiUser;
        public InformasiUserViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTanggalUser = itemView.findViewById(R.id.text_tanggal_informasi);
            txtIsiUser = itemView.findViewById(R.id.text_isi_informasi);
        }
    }
}
