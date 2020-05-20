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
import riska.com.tpuradarmobile.adapter.InformasiAdminAdapter;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.DataInformasiItem;
import riska.com.tpuradarmobile.model.ResponseDataInformasi;

public class InformasiAdminActivity extends AppCompatActivity {

    @BindView(R.id.recycler_informasi_admin)
    RecyclerView recyclerInformasiAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_admin);
        ButterKnife.bind(this);

        LoadDataInformasi();
    }

    private void LoadDataInformasi() {

        ConfigRetrofit.service.DataInformasi().enqueue(new Callback<ResponseDataInformasi>() {
            @Override
            public void onResponse(Call<ResponseDataInformasi> call, Response<ResponseDataInformasi> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    List<DataInformasiItem> informasiList = response.body().getDataInformasi();
                    InformasiAdminAdapter adapter = new InformasiAdminAdapter(InformasiAdminActivity.this, informasiList);
                    recyclerInformasiAdmin.setAdapter(adapter);
                    recyclerInformasiAdmin.setLayoutManager(new LinearLayoutManager(InformasiAdminActivity.this));
                }else{
                    Toast.makeText(InformasiAdminActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataInformasi> call, Throwable t) {
                Toast.makeText(InformasiAdminActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(InformasiAdminActivity.this, AdminActivity.class));
        finish();
    }
}
