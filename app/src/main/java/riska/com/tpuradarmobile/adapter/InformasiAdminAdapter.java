package riska.com.tpuradarmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import riska.com.tpuradarmobile.EditInformasiActivity;
import riska.com.tpuradarmobile.InformasiAdminActivity;
import riska.com.tpuradarmobile.R;
import riska.com.tpuradarmobile.model.DataInformasiItem;

public class InformasiAdminAdapter extends RecyclerView.Adapter<InformasiAdminAdapter.InformasiAdminViewHolder> {

    Context mContext;
    List<DataInformasiItem> informasiItems;

    public InformasiAdminAdapter(Context mContext, List<DataInformasiItem> informasiItems) {
        this.mContext = mContext;
        this.informasiItems = informasiItems;
    }

    @NonNull
    @Override
    public InformasiAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_informasi, parent, false);
        return new InformasiAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InformasiAdminViewHolder holder, int position) {

        holder.txtTanggal.setText(informasiItems.get(position).getTanggalInformasi());
        holder.txtIsi.setText(informasiItems.get(position).getIsiInformasi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditInformasiActivity.class);
                intent.putExtra(EditInformasiActivity.EXTRA_ID_INFORMASI, informasiItems.get(position).getIdInformasi());
                intent.putExtra(EditInformasiActivity.EXTRA_ISI_INFORMASI, informasiItems.get(position).getIsiInformasi());
                mContext.startActivity(intent);
                ((InformasiAdminActivity)mContext).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return informasiItems.size();
    }

    public class InformasiAdminViewHolder extends RecyclerView.ViewHolder {

        TextView txtTanggal, txtIsi;
        public InformasiAdminViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTanggal = itemView.findViewById(R.id.text_tanggal_informasi);
            txtIsi = itemView.findViewById(R.id.text_isi_informasi);
        }
    }
}
