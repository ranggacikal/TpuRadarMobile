package riska.com.tpuradarmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riska.com.tpuradarmobile.adapter.DataPemesananAdminAdapter;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.DataSemuaPemesananItem;
import riska.com.tpuradarmobile.model.ResponseDataSemuaPesanan;

public class PemesananAdminActivity extends AppCompatActivity {

    @BindView(R.id.recycler_data_pemesanan_admin)
    RecyclerView recyclerDataPemesananAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan_admin);
        ButterKnife.bind(this);

        LoadDataPemesanan();

    }

    private void LoadDataPemesanan() {

        ConfigRetrofit.service.GetSemuaPesanan().enqueue(new Callback<ResponseDataSemuaPesanan>() {
            @Override
            public void onResponse(Call<ResponseDataSemuaPesanan> call, Response<ResponseDataSemuaPesanan> response) {

                int status = response.body().getStatus();

                if (status == 1){
                    List<DataSemuaPemesananItem> semuaPemesananList = response.body().getDataSemuaPemesanan();
                    DataPemesananAdminAdapter adapter = new DataPemesananAdminAdapter(PemesananAdminActivity.this, semuaPemesananList);
                    recyclerDataPemesananAdmin.setAdapter(adapter);
                    recyclerDataPemesananAdmin.setLayoutManager(new LinearLayoutManager(PemesananAdminActivity.this));
                    recyclerDataPemesananAdmin.setHasFixedSize(true);
                }else{
                    Toast.makeText(PemesananAdminActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataSemuaPesanan> call, Throwable t) {
                Toast.makeText(PemesananAdminActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PemesananAdminActivity.this, AdminActivity.class));
        finish();
    }
}
