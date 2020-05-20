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
import riska.com.tpuradarmobile.adapter.InformasiUserAdapter;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.DataInformasiItem;
import riska.com.tpuradarmobile.model.ResponseDataInformasi;

public class InformasiUserActivity extends AppCompatActivity {

    @BindView(R.id.recycler_informasi_user)
    RecyclerView recyclerInformasiUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_user);
        ButterKnife.bind(this);

        LoadDataInformasi();
    }

    private void LoadDataInformasi() {

        ConfigRetrofit.service.DataInformasi().enqueue(new Callback<ResponseDataInformasi>() {
            @Override
            public void onResponse(Call<ResponseDataInformasi> call, Response<ResponseDataInformasi> response) {
                int status = response.body().getStatus();

                if (status == 1){

                    List<DataInformasiItem> informasiUserList = response.body().getDataInformasi();
                    InformasiUserAdapter adapter = new InformasiUserAdapter(InformasiUserActivity.this, informasiUserList);
                    recyclerInformasiUser.setAdapter(adapter);
                    recyclerInformasiUser.setLayoutManager(new LinearLayoutManager(InformasiUserActivity.this));
                }else{
                    Toast.makeText(InformasiUserActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataInformasi> call, Throwable t) {
                Toast.makeText(InformasiUserActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(InformasiUserActivity.this, MainActivity.class));
        finish();
    }
}
