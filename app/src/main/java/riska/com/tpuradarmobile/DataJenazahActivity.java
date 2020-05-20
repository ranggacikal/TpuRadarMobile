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
import riska.com.tpuradarmobile.adapter.JenazahAdapter;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.DataJenazahItem;
import riska.com.tpuradarmobile.model.ResponseDataJenazah;

public class DataJenazahActivity extends AppCompatActivity {

    @BindView(R.id.recycler_data_jenazah)
    RecyclerView recyclerDataJenazah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_jenazah);
        ButterKnife.bind(this);

        LoadDataJenazah();
    }

    private void LoadDataJenazah() {

        ConfigRetrofit.service.GetDataJenazah().enqueue(new Callback<ResponseDataJenazah>() {
            @Override
            public void onResponse(Call<ResponseDataJenazah> call, Response<ResponseDataJenazah> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    List<DataJenazahItem> jenazahList = response.body().getDataJenazah();
                    JenazahAdapter adapter = new JenazahAdapter(DataJenazahActivity.this, jenazahList);
                    recyclerDataJenazah.setAdapter(adapter);
                    recyclerDataJenazah.setLayoutManager(new LinearLayoutManager(DataJenazahActivity.this));
                }else{
                    Toast.makeText(DataJenazahActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataJenazah> call, Throwable t) {
                Toast.makeText(DataJenazahActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataJenazahActivity.this, AdminActivity.class));
        finish();
    }
}
