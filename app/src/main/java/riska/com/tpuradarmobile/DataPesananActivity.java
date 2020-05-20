package riska.com.tpuradarmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riska.com.tpuradarmobile.SharedPreference.SharedPreferencedConfig;
import riska.com.tpuradarmobile.adapter.DataPemesananAdapter;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.DataPemesananUserItem;
import riska.com.tpuradarmobile.model.ResponseDataPemesanan;

public class DataPesananActivity extends AppCompatActivity {

    @BindView(R.id.card_pesanan)
    CardView cardPesanan;
    @BindView(R.id.card_bayar)
    CardView cardBayar;
    @BindView(R.id.card_berhasil)
    CardView cardBerhasil;
    @BindView(R.id.recycler_data_pesanan)
    RecyclerView recyclerDataPesanan;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pesanan);
        ButterKnife.bind(this);

        preferencedConfig = new SharedPreferencedConfig(this);

        LoadDataPesanan();
    }

    private void LoadDataPesanan() {

        String user_id = preferencedConfig.getPreferenceIdUser();

        ConfigRetrofit.service.GetDataPemesanan(user_id).enqueue(new Callback<ResponseDataPemesanan>() {
            @Override
            public void onResponse(Call<ResponseDataPemesanan> call, Response<ResponseDataPemesanan> response) {
                int status = response.body().getStatus();

                if (status==1){
                    List<DataPemesananUserItem> dataPemesananList = response.body().getDataPemesananUser();
                    DataPemesananAdapter adapter = new DataPemesananAdapter(DataPesananActivity.this, dataPemesananList);
                    recyclerDataPesanan.setAdapter(adapter);
                    recyclerDataPesanan.setLayoutManager(new LinearLayoutManager(DataPesananActivity.this));
                }else{
                    Toast.makeText(DataPesananActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataPemesanan> call, Throwable t) {
                Toast.makeText(DataPesananActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataPesananActivity.this, MainActivity.class));
        finish();
    }
}
